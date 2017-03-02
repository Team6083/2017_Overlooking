package Systems.lib;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Absolute_encoder {
	private int encoder_port;
	
	private AnalogInput encoder;
	
	public static final int min_step = 34;
	public static final int max_step = 3946;//needs to check out the value
	private static int lap_count_value = 2000;
	
	private int step,prev_step;//the step of this lap
	private int accumulation_step;
	private int start_step;
	private boolean reverse;
	
	public Absolute_encoder(int port){
		if(port <0){
			encoder_port = -port; 
			reverse = true;
		}
		else{
			encoder_port = port; 
			reverse = false;
		}
		
		encoder = new AnalogInput(encoder_port);
		prev_step = encoder.getValue()-min_step;
		accumulation_step = 0;
		step = 0;
	}	
	
	public void loop(){
		read_step();
		prev_step = step;
		SmartDashboard.putNumber("encoder "+encoder_port+" accumulation_step", accumulation_step);
	}
	
	private void read_step(){
		if(reverse) step = -encoder.getValue() - min_step;
		else step = encoder.getValue() - min_step;
		if(step-prev_step < -lap_count_value){
			accumulation_step = accumulation_step + step + (max_step - prev_step);
		}//forward count lap
		else if(step-prev_step > lap_count_value){
			accumulation_step = accumulation_step - (max_step - step) - prev_step;
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
		start_step = accumulation_step;
		System.out.println("Set ecnoder "+encoder_port+" start_step to "+start_step);
		System.out.println("Current distence"+get_distence());
	}
	
	public int get_enc_value(){
		return encoder.getValue();
	}
}
