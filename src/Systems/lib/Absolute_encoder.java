package Systems.lib;

import edu.wpi.first.wpilibj.AnalogInput;

public class Absolute_encoder {
	private int encoder_port;
	
	private AnalogInput encoder;
	
	private int encoder_step;//the step of this lap
	
	Absolute_encoder(int port){
		encoder_port = port;
		encoder = new AnalogInput(encoder_port);
	}	
	
	public void read_distence(){
		encoder_step = encoder.getValue();
	}
}
