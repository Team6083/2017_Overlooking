package Systems.autonomous.modes;

import Systems.DriveBase;
import Systems.autonomous.actions.*;

public class redMiddle {
	
	private static Move goToAirShip,walkbackfromAirShip,moveTobaseline,goOverbaseine;
	private static Stop stopAtAirShip;
	private static Rotate turnToBaseline1,turnToBaseline2;
	
	private static boolean finish;
	
	private static int index = 0;
	private static Action curraction;
	
	public static boolean ifFinished(){
		return finish;
	}
	
	public static void init(){
		goToAirShip = new Move(110);// put distance to airship
		stopAtAirShip = new Stop(5000);
		walkbackfromAirShip = new Move(-43);
		turnToBaseline1 = new Rotate(-68);
		moveTobaseline = new Move(108);
		turnToBaseline2 = new Rotate(70);
		goOverbaseine = new Move(57);
		
 		
		finish = false;
		curraction = goToAirShip;
		curraction.start();
	}
	
	public static void loop(){
		curraction.loop();
		
		if(curraction.isFinished()){
			curraction.done();
			index++;
			switch(index){
			case 1:
				curraction = stopAtAirShip;
				break;
			case 2:
				curraction = walkbackfromAirShip;
				break;
			case 3:
				curraction = turnToBaseline1;
				break;
			case 4:
				curraction = moveTobaseline;
				break;
			case 5:
				curraction = turnToBaseline2;
				break;
			case 6:
				curraction = goToAirShip;
				finish = true;
			}
		}
		
	}
	
	public static void finish(){
		DriveBase.input(0, 0);
		DriveBase.drivabase_control();
	}
}
