package org.usfirst.frc.team6083.robot;

import Systems.Joysticks;
import Systems.ballAssembly;
import Systems.encoder;
import Systems.DriveBase;
import Systems.autonomous.autonomous_robot;
import Systems.autonomous.gyro_control;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Baseline";
	final String customAuto = "My Auto";
	final String redMiddle = "Middle";
	String autoSelected;
	SendableChooser chooser = new SendableChooser();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		chooser.addObject("Red Middle", redMiddle);
		SmartDashboard.putData("Auto choices", chooser);
		Joysticks.init();
		DriveBase.init();
		ballAssembly.init();
		autonomous_robot.init_robotinit();
		encoder.init();
		gyro_control.init();
		SmartDashboard.putString("Status","robotInit finished");
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		autonomous_robot.init();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			
			break;
			
		case redMiddle:
			autonomous_robot.Middle();
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			autonomous_robot.Baseline();
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		DriveBase.mode_selector(0);
		Joysticks.update_data();
		DriveBase.drivabase_control();
		ballAssembly.teleop();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

