package Systems;

import Systems.lib.Absolute_encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class encoder {
	private static int error_step_left,error_step_right,target_step=0;
	private static double x = 0.00005;
	
	private static Absolute_encoder left,right;
	
	private static final int wheel_circumference = 19;//needs to measure
	
	private static final int left_port = -1;
	private static final int right_port = 0;
	
	private static int error_range = 1500;
	private static double max_speed = 0.3;
	
	private static boolean isTargetdistancel = false,isTargetdistancer = false;
	public static boolean isTargetdistance = false;
	public static double left_speed=0,right_speed=0;
	
	public static void init(){
		left = new Absolute_encoder(left_port);
		right = new Absolute_encoder(right_port);
	}
	
	public static void loop(){
		left.loop();
		right.loop();
		walk_distence();
		dashboard();
	}
	
	private static void walk_distence(){
		error_step_left = -left.get_distence() - target_step;
		error_step_right = right.get_distence() - target_step;//get the error step
		
		
		if((int) Math.abs(error_step_left) > error_range){
			left_speed = (error_step_left * x);
		}
		else left_speed = 0;
		
		if((int) Math.abs(error_step_right) > error_range){
			right_speed = error_step_right * x;
		}
		else right_speed = 0;
		
		
		//make the speed won't expend the max speed
		if(Math.abs(left_speed) > max_speed){
			if(left_speed > 0) left_speed = max_speed;
			else left_speed = -max_speed;
			isTargetdistancel = false;
		}
		else if(left_speed == 0) isTargetdistancel = true;
		
		if(Math.abs(right_speed) > max_speed){
			if(right_speed > 0) right_speed = max_speed;
			else right_speed = -max_speed;
			isTargetdistancer = false;
		}
		else if(right_speed == 0) isTargetdistancer = true;
		
		
		if(isTargetdistancer&&isTargetdistancel) isTargetdistance = true;
		else isTargetdistance = false;
		
		left_speed = -left_speed;//reverse the left drive
	}
	
    public static void set_to(double distance){//for inches
    	double temp = distance / wheel_circumference;
    	System.out.println("temp = "+temp);
    	target_step = (int) (temp * (Absolute_encoder.max_step-Absolute_encoder.min_step) );
    	left.set_start_step();
    	right.set_start_step();
    }
    
    private static void dashboard(){
		SmartDashboard.putNumber("target_step",target_step);
		SmartDashboard.putNumber("error_step_left", error_step_left);
		SmartDashboard.putNumber("error_step_right", error_step_right);
		SmartDashboard.putNumber("left_distence", left.get_distence());
		SmartDashboard.putNumber("right_distence", right.get_distence());
    }
}
