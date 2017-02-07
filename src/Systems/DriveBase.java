package Systems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import Systems.Joysticks;

public class DriveBase {
    private static VictorSP left1,left2,right1,right2;
    private static double left_speed,right_speed;
    
    private static final int left1_port = 1;
    private static final int left2_port = 2;
    private static final int right1_port = 3;
    private static final int right2_port = 4;
    
    private static double speed_dawn = 4;
    
    public static void init(){
    	left1 = new VictorSP(left1_port);
    	left2 = new VictorSP(left2_port);
    	right1 = new VictorSP(right1_port);
    	right2 = new VictorSP(right2_port);
        SmartDashboard.putNumber("Drive speed dawn vlaue", speed_dawn);
    }
    
    public static void tankDrive(){
    	left_speed = -Joysticks.ly/speed_dawn;
    	right_speed = Joysticks.ry/speed_dawn;
    	
    	if(Joysticks.lab) left_speed = left_speed*2;
    	if(Joysticks.rab) right_speed = right_speed*2;
    	
    	left1.set(left_speed);
    	left2.set(left_speed);
    	right1.set(right_speed);
    	right2.set(right_speed);
    	
    	dashboard();
    }
    
    private static void dashboard(){
    	SmartDashboard.putNumber("left_drive", left1.get());
    	SmartDashboard.putNumber("right_drive", right1.get());
    }
}
