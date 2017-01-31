package Systems.autonomous;

import Systems.autonomous.gyro_control;
import Systems.DriveBase;

public class autonomous_robot {
	
	
	
	//init code when robot during autonomous mode
	public static void init(){
		gyro_control.init();
	}
}
