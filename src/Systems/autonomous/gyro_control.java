package Systems.autonomous;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class gyro_control {
    private static ADXRS450_Gyro Gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    private static double error_angle=0,current_angle,delta_angle=0,x=0.01,to=0;
    private static double error_range=8,max_speed=0.2;
	
    public static double left_speed=0,right_speed=0;
    
    private static final String reset_msg = "Rotation System Reset Complete!!";
    private static final String startup_msg = "Rotation System Enabled!!";
    
    public static boolean isTargetangle = false;
	
    public static void reset(){
    	gyro_reset();
        System.out.println(reset_msg);
        SmartDashboard.putString("Status", reset_msg);
    }
    
    public static void gyro_reset(){
    	Gyro.reset();
    	SmartDashboard.putString("Status", "Rotation System reset!!");
    }
    
    public static void init(){
    	SmartDashboard.putNumber("delta_angle", delta_angle);
        SmartDashboard.putNumber("error_angle", error_angle);
        SmartDashboard.putNumber("x", x);
        SmartDashboard.putNumber("error_range", error_range);
        SmartDashboard.putNumber("max_speed",max_speed);
        System.out.println(startup_msg);
        SmartDashboard.putString("Status", startup_msg);
    }
    
    public static void rotate(){
    	delta_angle=SmartDashboard.getNumber("delta_angle");
    	to += delta_angle;
    	if(delta_angle!=0){
    		SmartDashboard.putNumber("delta_angle", 0);
    		delta_angle = 0;
    	}
    	SmartDashboard.putNumber("to", to);
    	loop();
    }
    
    public static void loop(){
    	to = to%360;//make the to angle not exceed 360
    	
    	//left is - right is +
        current_angle = Gyro.getAngle();
        error_range = SmartDashboard.getNumber("error_range");
        max_speed = SmartDashboard.getNumber("max_speed");
        x = SmartDashboard.getNumber("x");
        
        current_angle = current_angle%360;//make the current angle not exceed 360
        SmartDashboard.putNumber("current angle", current_angle);
        error_angle = current_angle-to;
        
        if(error_angle > 180){
        	error_angle = error_angle-360;
        }
        else if(error_angle < -180){
        	error_angle = error_angle+360;
        }
        
        if(error_angle <= -error_range && error_angle >= -(360-error_range)){
        	if(error_angle*x <= -max_speed){
        		right_speed = max_speed;//+  turn right
        		left_speed = max_speed;
        	}//limit the speed
        	else{
        		right_speed = -error_angle*x;
        		left_speed = -error_angle*x;
        	}
        	isTargetangle = false;
        }
        else if(error_angle >= error_range && error_angle <= (360-error_range)){
        	if(error_angle*x >= max_speed){
        		left_speed = -max_speed;
        		right_speed = -max_speed;
        	}//limit the speed
        	else{
        		right_speed = -error_angle*x;
        		left_speed = -error_angle*x;
        	}
        	isTargetangle = false;
        }
        else{
        	left_speed = 0;
        	right_speed = 0;
        	isTargetangle = true;
        }
        SmartDashboard.putNumber("error_angle", error_angle);
        SmartDashboard.putNumber("left_speed", left_speed);
        SmartDashboard.putNumber("right_speed", right_speed);
    }
    
    public static void set_to(double temp_to){
    	to = temp_to;
    }
    
}
