package Systems.autonomous.actions;

import Systems.encoder;
import Systems.DriveBase;

public class Move implements Action {
	
	private double distance;
	
	private boolean started = false;
	
	@Override
	public void loop() {
		if(!started) return;
		
		encoder.loop();
		DriveBase.input(encoder.left_speed, encoder.right_speed);
		DriveBase.drivabase_control();
	}

	@Override
	public void start() {
		started = true;
		encoder.set_to(distance);
		DriveBase.mode_selector(1);
		DriveBase.drivabase_control();
	}

	@Override
	public void done() {
		DriveBase.input(0, 0);
		DriveBase.drivabase_control();
	}

	@Override
	public boolean isFinished() {
		return encoder.isTargetdistance;
	}
	
	public Move(double temp){
		distance = temp;
	}

}
