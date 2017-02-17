package Systems.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import Systems.DriveBase;
import Systems.encoder;

import Systems.autonomous.gyro_control;

import Systems.autonomous.modes.*;

public class autonomous_robot {
	
	private static boolean first_run = true;

	private static int []rotate_angle ={0,30,20,-10,90,-100};
	private static int error=0,i=0;
	
	
	public static void init_robotinit(){
		gyro_control.gyro_reset();
	}
	
	//init code when robot during autonomous mode
	public static void init(){
		
		SmartDashboard.putBoolean("Auto Code Inited", true);
		SmartDashboard.putNumber("to", 0);
		
		encoder.init();
		gyro_control.init();
		
		test_automode.init();
	}
	
	public static void test_mode(){
		test_automode.loop();
	}
	
	
	
	/*
	 * Below is the test zone, don't use the code that after this for matchs
	 */
	
	public static void test_enc(){
		encoder.loop();
	}
	
	public static void set_angle_to(){
		gyro_control.set_to(SmartDashboard.getNumber("to"));
		gyro_control.loop();
		DriveBase.input(gyro_control.left_speed, gyro_control.right_speed);
		SmartDashboard.putBoolean("isTargetangle", gyro_control.isTargetangle);
	}
	
	
	public static void set_angle_delta_angle() {
		gyro_control.rotate();
		SmartDashboard.putBoolean("isTargetangle", gyro_control.isTargetangle);
		DriveBase.input(gyro_control.left_speed, gyro_control.right_speed);
	}
	
}
