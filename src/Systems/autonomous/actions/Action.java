package Systems.autonomous.actions;

public interface Action {

    /**
     * Returns whether or not the code has finished execution.
     * 
     * @return boolean
     */
    public abstract boolean isFinished();

    /**
     * Called periodically after the action had been started
     */
    public abstract void loop();
    
    /**
     * Call this when the previous step had finished
     */
    public abstract void update();

    /**
     * Run code once when the action finishes, usually for clean up
     */
    public abstract void done();

    /**
     * Run code once when the action is started, for set up
     */
    public abstract void start();
}
