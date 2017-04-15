package org.usfirst.frc.team6083.robot;

import Systems.Joysticks;
import Systems.ballAssembly;
<<<<<<< HEAD
=======
import Systems.encoder;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

>>>>>>> refs/heads/release/AUSC
import Systems.DriveBase;
<<<<<<< HEAD
=======
import Systems.autonomous.gyro_control;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
>>>>>>> refs/heads/release/AUSC
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
<<<<<<< HEAD
=======
		encoder.init();
		gyro_control.init();
>>>>>>> refs/heads/release/AUSC
		SmartDashboard.putString("Status","robotInit finished");
		/*
		
		visionThread = new Thread(() -> {
			// Get the Axis camera from CameraServer
			AxisCamera camera = CameraServer.getInstance().addAxisCamera("axis-camera1.local");
			// Set the resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Axis-camera1", 640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		visionThread.setDaemon(true);
		visionThread.start();
		*/
	
		
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
<<<<<<< HEAD

=======
		SmartDashboard.putNumber("x",0.0001);
>>>>>>> refs/heads/release/AUSC
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
<<<<<<< HEAD
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			
			break;
=======
				// Put default auto code here
		if(i ==0){	
			System.out.println("auto start");
			DriveBase.left1.set(0.285);
			DriveBase.left2.set(0.285);
			DriveBase.right1.set(-0.3);
			DriveBase.right2.set(-0.3);
>>>>>>> refs/heads/release/AUSC
			
<<<<<<< HEAD
		case redMiddle:
			
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			
			break;
=======
			Timer.delay(7);
			DriveBase.left1.set(0);
			DriveBase.left2.set(0);
			DriveBase.right1.set(0);
			DriveBase.right2.set(0);
			
			
>>>>>>> refs/heads/release/AUSC
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

