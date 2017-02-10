package Systems.autonomous;

import Systems.autonomous.gyro_control;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import Systems.DriveBase;

public class autonomous_robot {
	
	private static boolean first_run = true;

	private static int []toangle ={0,30,20,-10,90,-100};
	private static int error=0,i=0;
	
	
	public static void init_robotinit(){
		gyro_control.gyro_reset();
	}
	
	//init code when robot during autonomous mode
	public static void init(){
		gyro_control.init();
		gyro_control.toangle = 30;
		
		SmartDashboard.putBoolean("Auto Code Inited", true);
		SmartDashboard.putNumber("to", 0);
	}
	
	public static void set_gyro_to(){
		gyro_control.loop(SmartDashboard.getNumber("to"));
		DriveBase.input(gyro_control.left_speed, gyro_control.right_speed);
		SmartDashboard.putBoolean("isTargetangle", gyro_control.isTargetangle);
	}
	
	
	public static void autonomousPeriodic() {
			gyro_control.rotate();
			/*if(gyro_control.isTargetangle){
				gyro_control.toangle=toangle[i];
				DriveBase.input(0, 0);
				i++;
				Timer.delay(2);
			}*/
			SmartDashboard.putBoolean("isTargetangle", gyro_control.isTargetangle);
			
	}
}