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
    
    private static int control_mode = 0;
    /** Prevent robot interrupted by data from different control mode
     * 0 for tankDrive mode
     * 1 for input_control mode
     * 2 for arcade mode
     */
    
    private static double speed_down = 4;
    
    public static void init(){
    	left1 = new VictorSP(left1_port);
    	left2 = new VictorSP(left2_port);
    	right1 = new VictorSP(right1_port);
    	right2 = new VictorSP(right2_port);
        SmartDashboard.putNumber("Drive speed dawn vlaue", speed_down);
    }
    
    public static void drivabase_control(){
    	
    	switch (control_mode) {
    	case 1:
    		
    		break;
    	case 2:
    		arcade();
    		break;
    	case 0:
    	default:
    		tankDrive();
    	}
    }
    
    public static void tankDrive(){
    	left_speed = -Joysticks.ly/speed_down;
    	right_speed = Joysticks.ry/speed_down;
    	
    	if(Joysticks.lb) left_speed = left_speed*2;
    	if(Joysticks.rb) right_speed = right_speed*2;
    	
    	left1.set(left_speed);
    	left2.set(left_speed);
    	right1.set(right_speed);
    	right2.set(right_speed);
    	
    	dashboard();
    }
    
    public static void arcade(){
    	
    	right_speed=(-Joysticks.ly+Joysticks.lx)/speed_down;
    	left_speed=(Joysticks.ly+Joysticks.ly)/speed_down;
    	
    	if(Joysticks.lab){
    		left_speed=left_speed*2;
    		right_speed=right_speed*2;
    	}
    	left1.set(left_speed);
    	left2.set(left_speed);
    	right1.set(right_speed);
    	right2.set(right_speed);
    	SmartDashboard.putNumber("leftmotor", left1.get());
    	SmartDashboard.putNumber("rightmotor", right1.get());
    }
    
    
    
    private static void dashboard(){
    	SmartDashboard.putNumber("left_drive", left1.get());
    	SmartDashboard.putNumber("right_drive", right1.get());
    }
    
    private static void mode_selector(){
    	
    }
    
}
