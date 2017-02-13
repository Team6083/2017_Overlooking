package Systems;

import edu.wpi.first.wpilibj.AnalogInput;

public class encoder {
	
	private final static int encoder_left_port = 0;
	private final static int encoder_right_port = 1;
	
	private static AnalogInput encoder_left,encoder_right;
	
	
	public static void init(){
		encoder_left = new AnalogInput(encoder_left_port);
		encoder_right = new AnalogInput(encoder_right_port);
		
	}
	
	
	
}
