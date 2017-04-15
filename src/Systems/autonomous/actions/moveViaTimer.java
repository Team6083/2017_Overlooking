package Systems.autonomous.actions;

import edu.wpi.first.wpilibj.Timer;
import Systems.DriveBase;
import Systems.autonomous.gyro_control;

public class moveViaTimer implements Action {
	
	private Timer timer = new Timer();
	private double time,angle;
	private boolean finished;
	
	private static final double configured_speed = 0.35;
	
	@Override
	public void loop() {
		if(finished) return;
		if(timer.get()>=time&&gyro_control.isTargetangle){
			finished = true;
		}
		gyro_control.rotate();
		double l_speed=0,r_speed=0;
		
		l_speed = gyro_control.left_speed + configured_speed;
		r_speed = gyro_control.right_speed - configured_speed;
		
		DriveBase.input(l_speed, r_speed);
		DriveBase.drivabase_control();
	}

	@Override
	public void start() {
		timer.start();
		gyro_control.set_to(angle);
		finished = false;
	}

	@Override
	public void done() {
		
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	public moveViaTimer(double temp_sec, double temp_angle){
		time = temp_sec;
		angle = temp_angle;
	}
	
}
