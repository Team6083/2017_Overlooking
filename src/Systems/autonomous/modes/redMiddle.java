package Systems.autonomous.modes;

import Systems.DriveBase;
import Systems.autonomous.actions.*;

public class redMiddle {
	
	private static Move goToAirShip,walkbackfromAirShip,moveTobaseline,goOverbaseine;
	private static Stop stopAtAirShip;
	private static Rotate turnToBaseline1,turnToBaseline2;
	
	private static boolean finish;
	
	private static final int action_count = 7;
	private static int[] status = new int[action_count];
	/*
	 * 0 for default
	 * 1 for started
	 * 2 for finished
	 */
	private static int index = 0;
	
	public static void init(){
		goToAirShip = new Move(110);// put distance to airship
		stopAtAirShip = new Stop(5000);
		walkbackfromAirShip = new Move(-43);
		turnToBaseline1 = new Rotate(-68);
		moveTobaseline = new Move(108);
		turnToBaseline2 = new Rotate(70);
		goOverbaseine = new Move(57);
		
 		
		finish = false;
		
		for(int i=0;i<action_count;i++){
			status[i] = 0;
		}
		
		goToAirShip.start();
		status[0] = 0;
	}
	
	public static void loop(){
		
		if(index == 0 && status[0] == 1){
			goToAirShip.loop();
			if(goToAirShip.isFinished()){
				status[index] = 2;
				index++;
			}
		}
		else if(index == 1 && status[0] == 2){
			if(status[1] == 0){
				stopAtAirShip.start();
				status[index] = 1;
			}
			
			if(stopAtAirShip.isFinished()){
				status[index] = 2;
				index++;
			}
		}
		else if(index == 2 && status[1] == 2){
			if(status[2] == 0){
				walkbackfromAirShip.start();
				status[index] = 1;
			}
			walkbackfromAirShip.loop();
			if(walkbackfromAirShip.isFinished()){
				walkbackfromAirShip.done();
				
				status[index] = 2;
				index++;
				
			}
		}
		else if(index == 3 && status[2] == 2){
			if(status[3] == 0){
				turnToBaseline1.start();
				status[index] = 1;
			}
			turnToBaseline1.loop();
			if(turnToBaseline1.isFinished()){
				turnToBaseline1.done();
				
				status[index] = 2;
				index++;
			}
		}
		else if(index == 4 && status[3] == 2){
			if(status[4] == 0){
				moveTobaseline.start();
				status[index] = 1;
			}
			moveTobaseline.loop();
			if(moveTobaseline.isFinished()){
				moveTobaseline.done();
				
				status[index] = 2;
				index++;
			}
		}
		else if(index == 5 && status[4] == 2){
			if(status[5] == 0){
				turnToBaseline2.start();
				status[index] = 1;
			}
			turnToBaseline2.loop();
			if(turnToBaseline2.isFinished()){
				turnToBaseline2.done();
				
				status[index] = 2;
				index++;
			}
		}
		else if(index == 6 && status[5] == 2){
			if(status[6] == 0){
				goOverbaseine.start();
			}
			goOverbaseine.loop();
			if(goOverbaseine.isFinished()){
				goOverbaseine.done();
				
				status[index] = 2;
				index++;
			}
		}
		
		if(status[action_count-1] == 2 && !finish){
			finish = true;
			return;
		}
		else if(status[action_count-1] == 2 && finish){
			return;
		}
		
	}
	
	public static void finish(){
		DriveBase.input(0, 0);
		DriveBase.drivabase_control();
	}
}
