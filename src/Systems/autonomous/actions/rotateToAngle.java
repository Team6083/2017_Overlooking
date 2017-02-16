package Systems.autonomous.actions;

import Systems.DriveBase;
import Systems.autonomous.gyro_control;

public class rotateToAngle implements Action {
	
	private boolean isFinished;
	
	private double[] to_angle;
	private int current_index;
	
	@Override
	public boolean isFinished() {
		if(to_angle.length < current_index) return true;
		else return false;
	}

	@Override
	public void loop() {
		if(isFinished) return ;
		gyro_control.rotate();
		DriveBase.input(gyro_control.left_speed, gyro_control.right_speed);
		if(isFinished()){
			done();
		}
	}

	@Override
	public void update() {
		gyro_control.set_to(to_angle[current_index]);
		current_index++;
	}

	@Override
	public void done() {
		isFinished = true;
	}

	@Override
	public void start() {
		gyro_control.init();
		current_index = 0;
	}
	
	public rotateToAngle(double[] temp){
		to_angle = temp;
	}

}
