package Systems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import Systems.Joysticks;

public class ballAssembly {
	 private static final int fuel_sender_port = 0;
	 private static VictorSP fuel_sender;
	 
	 private static boolean active = false;
	 
	 private static double shooter_speed;
	 
	 public static void init(){
		 fuel_sender = new VictorSP(fuel_sender_port);
		 shooter_speed = 0;
		 
	 }
	 
	 public static void teleop(){

			 if(Joysticks.a){
				 fuel_sender.set(1);
			 }
			 else if(Joysticks.y){
				 fuel_sender.set(-1);
			 }
			 else{
				 fuel_sender.set(0);
			 }

		 
		 
	 }
	 
	 public static void shooter_test(){
		 if(Joysticks.lb&&shooter_speed<0.7){
			 shooter_speed = shooter_speed + 0.1;
		 }
		 else if(!Joysticks.lb&&shooter_speed>=0){
			 shooter_speed = shooter_speed - 0.1;
		 }
		 
		 fuel_sender.set(shooter_speed);
	 }
}