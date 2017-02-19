package Systems.autonomous.modes;

import Systems.autonomous.actions.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class test_automode {
	
	private static Rotate r1,r2;
	private static Move m1,m2;
	private static Stop s1;
	
	private static final int action_count = 3;
	private static int[] status = new int[action_count];
	/*
	 * 0 for default
	 * 1 for started
	 * 2 for finished
	 */
	private static int index = 0;
	
	public static boolean finished = false;
	
	public static void init(){
		r1 = new Rotate(180);
		r2 = new Rotate(-90);
		s1 = new Stop(3000);
		m1 = new Move(20);
		m2 = new Move(-30);
		
		for(int i=0;i<action_count;i++){
			status[i] = 0;
		}
		
		
		m1.start();
		status[0] = 1;
		SmartDashboard.putString("Control mode", "auto inited");
		
	}
	
	public static void loop(){
		if(finished) return;
		if(index == 0 && status[0] == 1){
			m1.loop();
			if(m1.isFinished()){
				m1.done();
				System.out.println("Task m1 had finished");
				status[index] = 2;
				index++;
				
			}
		}
		else if(index == 1 && status[0] == 2){
			if(status[1] == 0){
				s1.start();
				if(s1.isFinished()){
					System.out.println("Task s1 had finished");
					status[index] = 2;
					index++;
				}
			}
		}
		else if(index == 2){
			if(status[2] == 0){
				m2.start();
				status[2] = 1;
			}
			m2.loop();
			if(m2.isFinished()){
				System.out.println("Task m2 had finished");
				status[2] = 2;
				m2.done();
				finished = true;
				index++;
			}
		}
		
		
		
		SmartDashboard.putInt("Auto Status", index);
	}
	
	public static void finish(){
		
	}
	
}
