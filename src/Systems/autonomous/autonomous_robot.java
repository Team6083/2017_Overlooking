package Systems.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import Systems.DriveBase;
import Systems.ballAssembly;
import Systems.encoder;

import Systems.autonomous.gyro_control;

import Systems.autonomous.modes.*;

public class autonomous_robot {
	
	private static boolean first_run = true;
	
	public static void init_robotinit(){
		gyro_control.gyro_reset();
	}
	
	//init code when robot during autonomous mode
	public static void init(){
		
		test_automode.init();
		middle.init();
		baseline.init();
		
		encoder.set_to(80);
		
		System.out.println("autonomous_java:Auto Code Inited");
	}
	
	public static void test_mode(){
		if(!test_automode.isFinish()) test_automode.loop();
	}
	
	public static void Middle(){
		if(!middle.isFinish()) middle.loop();
	}
	
	public static void Baseline(){
		if(!baseline.isFinish())  baseline.loop();
	}
	
	
	
	/*
	 * Below is the test zone, don't use the code that after this for matches
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
