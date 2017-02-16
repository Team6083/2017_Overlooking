package Systems.autonomous.actions;

import Systems.DriveBase;
import Systems.autonomous.gyro_control;

public class rotateToAngle implements Action {
	
	private double to_angle;
	
	@Override
	public boolean isFinished() {
		return gyro_control.isTargetangle;
	}

	@Override
	public void loop() {
		gyro_control.rotate();
		DriveBase.input(gyro_control.left_speed, gyro_control.right_speed);
		
	}

	@Override
	public void update() {
		gyro_control.set_to(to_angle);
	}

	@Override
	public void done() {
		
	}

	@Override
	public void start() {
		gyro_control.init();
	}
	
	public rotateToAngle(double temp){
		to_angle = temp;
		update();
	}

}
