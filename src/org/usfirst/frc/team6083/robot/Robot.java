package org.usfirst.frc.team6083.robot;

import Systems.Joysticks;
import Systems.ballAssembly;
import Systems.encoder;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import Systems.DriveBase;
import Systems.autonomous.gyro_control;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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
	Thread visionThread;
	
	int i=0; 
	
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
		encoder.init();
		gyro_control.init();
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
		SmartDashboard.putNumber("x",0.0001);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
				// Put default auto code here
		if(i ==0){	
			System.out.println("auto start");
			DriveBase.left1.set(0.285);
			DriveBase.left2.set(0.285);
			DriveBase.right1.set(-0.3);
			DriveBase.right2.set(-0.3);
			
			Timer.delay(7);
			DriveBase.left1.set(0);
			DriveBase.left2.set(0);
			DriveBase.right1.set(0);
			DriveBase.right2.set(0);
			
			
		}
		i++;
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		DriveBase.mode_selector(0);
		Joysticks.update_data();
		DriveBase.drivabase_control();
		ballAssembly.shooter_test();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

