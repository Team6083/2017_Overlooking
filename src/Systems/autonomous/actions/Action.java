package Systems.autonomous.actions;

public interface Action {
	
	public abstract void loop();
	
	public abstract void start();
	
	public abstract void done();
	
	public abstract boolean isFinished();
}
