package Systems.lib;

import edu.wpi.first.wpilibj.AnalogInput;

public class Absolute_encoder {
	private int encoder_port;
	
	private AnalogInput encoder;
	
	private static final int min_step = 0;
	private static final int max_step = 4096;//needs to check out the value
	private static int lap_count_value = 4000;
	
	private int step,prev_step;//the step of this lap
	private int accumulation_step;
	
	Absolute_encoder(int port){
		encoder_port = port;
		encoder = new AnalogInput(encoder_port);
		prev_step = encoder.getValue()-min_step;
		accumulation_step = 0;
		step = 0;
	}	
	
	public void read_step(){
		step = encoder.getValue() - min_step;
		if(step-prev_step > -lap_count_value){
			accumulation_step = accumulation_step + step;
		}//forward count lap
		else if(step-prev_step > lap_count_value){
			accumulation_step = accumulation_step - (max_step - step);
		}//backward count lap
		else{
			accumulation_step = accumulation_step + step - prev_step;
		}//in same lap
	}
}
