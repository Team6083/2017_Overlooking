package Systems.lib;

import edu.wpi.first.wpilibj.AnalogInput;

public class Absolute_encoder {
	private int encoder_port;
	
	private AnalogInput encoder;
	
	public static final int min_step = 0;
	public static final int max_step = 4096;//needs to check out the value
	private static int lap_count_value = 4000;
	
	private int step,prev_step;//the step of this lap
	private int accumulation_step;
	private int start_step;
	
	public Absolute_encoder(int port){
		encoder_port = port;
		encoder = new AnalogInput(encoder_port);
		prev_step = encoder.getValue()-min_step;
		accumulation_step = 0;
		step = 0;
	}	
	
	public void loop(){
		read_step();
		prev_step = step;
	}
	
	private void read_step(){
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
	
	public int get_accumulation_step(){
		return accumulation_step;
	}
	
	public int get_distence(){
		return accumulation_step-start_step;
	}
	
	public void set_start_step(){
		start_step = step;
	}
	public void set_start_step(int in_step){
		start_step = in_step;
	}
	
	public int get_enc_value(){
		return encoder.getValue();
	}
}
