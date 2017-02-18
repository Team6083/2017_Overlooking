package Systems.autonomous.modes;

import Systems.autonomous.actions.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class test_automode {
	
	private static Rotate r1,r2;
	private static Move m1;
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
				status[index] = 2;
				index++;
			}
		}
		else if(index == 1 && status[0] == 2){
			if(status[1] == 0){
				s1.start();
				if(s1.isFinished()){
					status[index] = 2;
					index++;
				}
			}
		}/*
		else if(index == 2){
			if(status[2] == 0){
				r2.start();
				status[2] = 1;
			}
			r2.loop();
			if(r2.isFinished()){
				status[2] = 2;
				finished = true;
				index++;
			}
		}*/
		
		
		
		SmartDashboard.putInt("Auto Status", index);
	}
	
	public static void finish(){
		
	}
	
}
