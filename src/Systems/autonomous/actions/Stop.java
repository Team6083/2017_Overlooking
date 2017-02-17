package Systems.autonomous.actions;

import edu.wpi.first.wpilibj.Timer;
import Systems.DriveBase;

public class Stop implements Action {
	
	private double stop_time;
	private boolean finished = false;
	
	
	@Override
	public void loop() {
		
	}

	@Override
	public void start() {
		Timer.delay(stop_time/1000);
		DriveBase.mode_selector(1);
		DriveBase.input(0, 0);
		DriveBase.drivabase_control();
		
		done();
	}

	@Override
	public void done() {
		finished = true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
	
	public Stop(double ms){
		stop_time = ms;
	}

}
