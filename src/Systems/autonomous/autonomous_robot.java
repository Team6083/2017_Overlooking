package Systems.autonomous;

import Systems.autonomous.gyro_control;
import edu.wpi.first.wpilibj.Timer;
import Systems.DriveBase;

public class autonomous_robot {
	
	
	
	//init code when robot during autonomous mode
	public static void init(){
		gyro_control.init();
	}
	
	public static void loop(){
		gyro_control.rotate();
		if(gyro_control.isTargetangle){
			gyro_control.toangle = 30;
			Timer.delay(5);
		}
		DriveBase.input_control();
		DriveBase.input(gyro_control.left_speed, gyro_control.right_speed);
	}
}
