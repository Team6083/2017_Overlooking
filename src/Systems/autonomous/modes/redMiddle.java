package Systems.autonomous.modes;

import Systems.autonomous.actions.*;

public class redMiddle {
	
	private static Move goToAirShip,walkbackfromAirShip;
	private static Stop stopAtAirShip;
	private static Rotate turnToBaseline1,turnToBaseline2;
	
	private static final int action_count = 3;
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
		walkbackfromAirShip = new Move(22);
		
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
				if(stopAtAirShip.isFinished()){
					status[index] = 2;
					index++;
				}
			}
		}
		else if(index == 2 && status[1] == 2){
			if(status[2] == 0){
				walkbackfromAirShip.start();
			}
			walkbackfromAirShip.loop();
		}
		
	}
	
	public static void finish(){
		
	}
}
