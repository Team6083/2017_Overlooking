package Systems.autonomous.actions;

import Systems.encoder;
import Systems.DriveBase;

public class ControlDrivebase implements Action {
	
	private double[] distence;
	private int current_index;
	
	public boolean isInTarget = false;
	
	@Override
	public boolean isFinished() {
		
		return false;
	}

	@Override
	public void loop() {
		encoder.loop();
		DriveBase.input(encoder.left_speed, encoder.left_speed);
		isInTarget = encoder.isTargetdistence;
	}
	
	@Override
	public void update() {
		encoder.set_to(distence[current_index]);
		current_index++;
	}


	@Override
	public void done() {
		
	}

	@Override
	public void start() {
		encoder.init();
		DriveBase.init();
		current_index = 0;
	}
	
	public ControlDrivebase(double[] temp){
		distence = temp;
	}

}
