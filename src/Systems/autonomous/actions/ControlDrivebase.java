package Systems.autonomous.actions;

import Systems.encoder;
import Systems.DriveBase;

public class ControlDrivebase implements Action {
	
	private double distence;
	
	@Override
	public boolean isFinished() {
		return encoder.isTargetdistence;
	}

	@Override
	public void loop() {
		encoder.loop();
		DriveBase.input(encoder.left_speed, encoder.left_speed);
	}
	
	@Override
	public void update() {
		encoder.set_to(distence);
	}


	@Override
	public void done() {
		
	}

	@Override
	public void start() {
		encoder.init();
		DriveBase.init();
	}
	
	public ControlDrivebase(double dis){
		distence = dis;
		update();
	}

}
