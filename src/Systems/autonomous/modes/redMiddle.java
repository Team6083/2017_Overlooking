package Systems.autonomous.modes;

import Systems.autonomous.actions.*;

public class redMiddle {
	
	private static int index = 0;
	
	public static void init(){
		
	}
	
	public static void liner(){
		
	}
	
	public static void excuter(int action,int value){
		
		boolean finish = false;
		
		switch(action){
		case 1:
			ControlDrivebase walk = new ControlDrivebase(value);
			while(!walk.isFinished()){
				walk.loop();
			}
			walk.done();
			break;
		case 2:
			rotateToAngle turn = new rotateToAngle(value);
			while(!turn.isFinished()){
				turn.loop();
			}
			turn.done();
			break;
		default:
			
			break;
		}
		
	}
	
	public static void finish(){
		
	}
}
