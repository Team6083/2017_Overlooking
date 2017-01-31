package Systems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import Systems.Joysticks;

public class DriveBase {
    private static Spark left,right;
    private static double left_speed,right_speed;
    
    private static final int left_port = 0;
    private static final int right_port = 1;
    
    private static double speed_dawn = 4;
    
    private static int control_mode;
    /** Prevent robot interrupted by data from different control mode
     * 0 for tankDrive mode
     * 1 for input_control mode
     */
    
    public static void init(){
        left = new Spark(left_port);
        right = new Spark(right_port);
        SmartDashboard.putNumber("Drive speed dawn vlaue", speed_dawn);
    }
    
    public static void tankDrive(){
    	control_mode = 0;
    	left_speed = -Joysticks.ly/speed_dawn;
    	right_speed = Joysticks.ry/speed_dawn;
    	
    	if(Joysticks.lab) left_speed = left_speed*2;
    	if(Joysticks.rab) right_speed = right_speed*2;
    	
    	left.set(left_speed);
    	right.set(right_speed);
    	
    	dashboard();
    }
    
    public static void input_control(){
    	control_mode = 1;
    }
    
    public static void input(double temp_left,double temp_right){
    	if(control_mode == 1){
        	left_speed = temp_left;
        	right_speed = temp_right;
    	}
    }
    
    private static void dashboard(){
    	SmartDashboard.putNumber("left_drive", left.get());
    	SmartDashboard.putNumber("right_drive", right.get());
    }
}
