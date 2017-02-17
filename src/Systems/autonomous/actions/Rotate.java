package Systems.autonomous.actions;

import Systems.DriveBase;
import Systems.autonomous.gyro_control;

public class Rotate implements Action {
	
	private double delta_angle;
	
	@Override
	public void loop() {
		gyro_control.rotate();
		DriveBase.input(gyro_control.left_speed, gyro_control.right_speed);
		DriveBase.drivabase_control();
	}

	@Override
	public void start() {
		gyro_control.set_to(delta_angle);

	}

	@Override
	public void done() {
		DriveBase.input(0, 0);
		DriveBase.drivabase_control();
	}

	@Override
	public boolean isFinished() {
		return gyro_control.isTargetangle;
	}
	
	public Rotate(double rotate_angle){
		delta_angle = rotate_angle;
		DriveBase.mode_selector(1);
	}

}
