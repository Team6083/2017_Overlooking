package Systems.autonomous.modes;

import Systems.DriveBase;
import Systems.autonomous.actions.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class baseline {
	
	private static Move goBaseline;
	private static Stop stop;
	
	private static boolean finish;
	
	private static int index = 0;
	private static Action curraction;
	
	public static boolean isFinish(){
		return finish;
	}
	
	public static void init(){
		goBaseline = new Move(93.3);// put distance to baseline
		stop = new Stop(5000);
		
 		
		finish = false;
		curraction = goBaseline;
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
				curraction = stop;
				break;
			case 2:
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
