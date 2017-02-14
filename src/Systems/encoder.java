package Systems;

import Systems.lib.Absolute_encoder;

public class encoder {
	private static int error_step,target_step=0;
	
	private static Absolute_encoder left,right;
	
	private static final int wheel_circumference = 0;//needs to meaesure
	
	private static final int left_port = 0;
	private static final int right_port = 1;
	
	public static boolean isTargetdistence = false;
	
	public static void init(){
		left = new Absolute_encoder(left_port);
		right = new Absolute_encoder(right_port);
	}
	
	public static void loop(){
		left.loop();
		right.loop();
		
	}
	
	public static void walk_distence(){
		
	}
	
    public static void set_to(double distence){//for inches
    	double temp = distence / wheel_circumference;
    	target_step = (int) (temp * Absolute_encoder.max_step);
    }
}
