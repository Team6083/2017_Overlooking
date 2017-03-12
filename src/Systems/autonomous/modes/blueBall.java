package Systems.autonomous.modes;

import Systems.DriveBase;
import Systems.autonomous.actions.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class blueBall {
	
	private static Move a1,a3;
	private static Rotate a2;
	private static Stop a4;
	
	private static boolean finish;
	
	private static int index = 0;
	private static Action curraction;

	public static boolean isFinish(){
		return finish;
	}
	
	public static void init(){
		a1 = new Move(64);
		a2 = new Rotate(-135);
 		a3 = new Move(97);
 		a4 = new Stop(5);
 		
		
		finish = false;
		curraction = a1;
		curraction.start();
		
	}
	
	public static void loop(){
		curraction.loop();
		
		if(curraction.isFinished()){
			curraction.done();
			System.out.println("Action "+index+" had finished.");
			index++;
			switch(index){
			case 1:
				curraction = a2;
				break;
			case 2:
				curraction = a3;
				break;
			case 3:
				curraction = a4;
				break;
			default:
				finish = true;
				break;
			}
			
			
			SmartDashboard.putInt("Auto Status", index);
			curraction.start();
		}
		
		
		
	}
	
	public static void finish(){
		DriveBase.input(0, 0);
		DriveBase.drivabase_control();
	}
}
