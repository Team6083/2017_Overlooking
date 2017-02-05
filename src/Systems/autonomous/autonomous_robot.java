package Systems.autonomous;

import Systems.autonomous.gyro_control;
import edu.wpi.first.wpilibj.Timer;
import Systems.DriveBase;

public class autonomous_robot {
	
	private static boolean first_run = true;

	private static int []toangle ={0,30,20,-10,90,-100};
	private static int error=0,i=0;
	
	//init code when robot during autonomous mode
	public static void init(){
		gyro_control.init();
		gyro_control.toangle = 30;
	}
	
	public static void loop(){
		gyro_control.rotate();
		if(gyro_control.isTargetangle){
			gyro_control.toangle = 30;
			Timer.delay(5);
		}
		DriveBase.input_control();
		DriveBase.input(gyro_control.left_speed, gyro_control.right_speed);
		if(first_run) first_run = false;
	}
	public static void autonomousPeriodic() {
			gyro_control.rotate();
			if(gyro_control.isTargetangle){
				gyro_control.toangle=toangle[i];
				Timer.delay(2);
				i++;
			}
	}
}
