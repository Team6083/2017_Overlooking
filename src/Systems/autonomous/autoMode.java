package Systems.autonomous;

import Systems.DriveBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class autoMode {
	
	static int status = 0;
	
	public static void init(){
		gyro_control.init();
	}
	
	public static void loop(){
		if(status == 0){
			gyro_control.set_deltato(90);
			status++;
		}
		else if(status == 1){
			gyro_control.loop();
			DriveBase.input(gyro_control.left_speed, gyro_control.right_speed);
			SmartDashboard.putBoolean("isTargetangle", gyro_control.isTargetangle);
			if(gyro_control.isTargetangle) status++;
		}
		else if(status == 2){
			DriveBase.input(0.3, -0.3);
			Timer.delay(5);
			status = 0;
		}
	}
}
