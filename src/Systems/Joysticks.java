package Systems;

import edu.wpi.first.wpilibj.Joystick;

public class Joysticks {
	private static Joystick joy1,joy2;//1 is XBox, 2 is 3D Pro
	private static final int joy1_port = 0;
	private static final int joy2_port = 1;
	
	public static double lx,ly,rx,ry,lt,rt;//for XBox
	public static double xa,ya,za,silder;//for 3D Pro
	
	public static boolean a,b,x,y,lb,rb,lab,rab,back,start;//buttons for XBox
	public static boolean[] probutton = new boolean[12];//buttons for 3D Pro
	
	
	public static void init(){
		joy1 = new Joystick(joy1_port);
		joy2 = new Joystick(joy2_port);
	}
	
	public static void update_data(){
		lx = joy1.getRawAxis(0);
		ly = joy1.getRawAxis(1);
		lt = joy1.getRawAxis(2);
		rt = joy1.getRawAxis(3);
		rx = joy1.getRawAxis(4);
		ry = joy1.getRawAxis(5);
		
		xa = joy2.getRawAxis(0);
		ya = joy2.getRawAxis(1);
		za = joy2.getRawAxis(2);
		silder = joy2.getRawAxis(3);
		
		a = joy1.getRawButton(1);
		b = joy1.getRawButton(2);
		x = joy1.getRawButton(3);
		y = joy1.getRawButton(4);
		lb = joy1.getRawButton(5);
		rb = joy1.getRawButton(6);
		back = joy1.getRawButton(7);
		start = joy1.getRawButton(8);
		lab = joy1.getRawButton(9);
		rab = joy1.getRawButton(10);
		
		for(int i=0;i<12;i++){
			probutton[i] = joy2.getRawButton(i+1);
		}	
	}
	
}