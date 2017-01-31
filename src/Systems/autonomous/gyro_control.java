package Systems.autonomous;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import Systems.DriveBase;

public class gyro_control {
    static ADXRS450_Gyro Gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    static double angle=0,x=0.01,curr=0;
    static double error_range=3,max_speed=0.25;
    
    public static double left_speed=0,right_speed=0;
    
    private static final String reset_msg = "Rotation System Reset Complete!!";
    private static final String startup_msg = "Rotation System Enabled!!";
	
    public static void reset(){
    	curr = 0;
        Gyro.reset();
        System.out.println(reset_msg);
        SmartDashboard.putString("Status", reset_msg);
    }
    
    public static void init(){
        Gyro.reset();
        Gyro.calibrate();
        SmartDashboard.putNumber("angle", angle);
        SmartDashboard.putNumber("x", x);
        SmartDashboard.putNumber("error_range", error_range);
        SmartDashboard.putNumber("max_speed",max_speed);
        System.out.println(startup_msg);
        SmartDashboard.putString("Status", startup_msg);
    }
    
    public static void rotate(double to){
    	error_range = SmartDashboard.getNumber("error_range");
    	max_speed = SmartDashboard.getNumber("max_speed");
    	to = curr+to;
    	do{
    	}while(loop(to));
    	curr = to;
    }
    
    private static boolean loop(double to){
    	//left is - right is +
    	boolean isTargetangle = true;
        angle = Gyro.getAngle()-to;
        x = SmartDashboard.getNumber("x");
        if(angle >=360){
        	do{
        		angle=angle-360;
        	}while(angle>0);
        }
        else if(angle <=-360){
        	do{
        		angle=angle+360;
        	}while(angle<0);
        }//make the error angle not exceed 360
        
        if(angle<=-error_range && angle >=-(360-error_range)){
        	if(angle*x <=-max_speed){
        		right_speed = -max_speed;//-  turn right
        		left_speed = -max_speed;
        	}//limit the speed
        	else{
        		right_speed = angle*x;
        		left_speed = angle*x;
        	}
        }
        else if(angle >=error_range && angle <=(360-error_range)){
        	if(angle*x >= max_speed){
        		left_speed = max_speed;
        		right_speed = max_speed;
        	}//limit the speed
        	else{
        		right_speed = angle*x;
        		left_speed = angle*x;
        	}
        }
        else{
        	left_speed= 0;
        	right_speed= 0;
        	isTargetangle = false;
        }
        
        SmartDashboard.putNumber("angle", angle);
        return isTargetangle;
    }
    
    
}