package Systems.autonomous.modes;

import Systems.autonomous.actions.*;

public class redMiddle {
	
	private static Move goToAirShip;
	
	private static final int action_count = 3;
	private static boolean[] finished = new boolean[action_count];
	private static int index = 0;
	
	public static void init(){
		goToAirShip = new Move(110);// put distance to airship
		
		for(int i=0;i<action_count;i++){
			finished[i] = false;
		}
		
		goToAirShip.start();
	}
	
	public static void loop(){
		if(index == 0){
			goToAirShip.loop();
			if(goToAirShip.isFinished()) index++;
		}
		else if(index == 1){
			
		}
		
	}
	
	public static void finish(){
		
	}
}
