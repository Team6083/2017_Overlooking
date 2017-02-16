package Systems.autonomous.modes;

import Systems.autonomous.actions.*;

public class redMiddle {
	
	private static ControlDrivebase drive;
	private static double[] walk_distences = {};
	
	public static void init(){
		drive = new ControlDrivebase(walk_distences);
	}
	
	public static void loop(){
		
	}
	
	public static void finish(){
		
	}
}
