package Systems.autonomous.modes;

import Systems.autonomous.actions.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class test_automode {
	
	private static Rotate r1,r2;
	private static Move m1,m2;
	private static Stop s1;
	private static Action curraction;
	
	private static final int action_count = 3;
	private static boolean finish;
	/*
	 * 0 for default
	 * 1 for started
	 * 2 for finished
	 */
	private static int index = 0;
	
	public static boolean finished = false;
	
	public static boolean isFinish(){
		return finish;
	}
	
	public static void init(){
		if(finish) return;
		r1 = new Rotate(180);
		r2 = new Rotate(-90);
		s1 = new Stop(5000);
		m1 = new Move(57);
		m2 = new Move(-38);
		
		
		curraction = m1;
		curraction.start();
		finish = false;
		SmartDashboard.putString("Control mode", "test_automode inited");
		
	}
	
	public static void loop(){
		curraction.loop();
		
		if(curraction.isFinished()){
			curraction.done();
			System.out.println("Action "+index+" had finished.");
			index++;
			switch(index){
			case 1:
				curraction = s1;
				break;
			case 2:
				curraction = m2;
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
		
	}
	
}
