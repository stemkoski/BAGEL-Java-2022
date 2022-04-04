package graphics.core;


public class Clock
{
    // time (in seconds) that has passed since clock initialized
    private double elapsedTime;
    
    // time that has passed since last frame/last update
    private double deltaTime;
    
    // timestamp during previous update
    public double previousUpdateTime;
    
    public Clock()
    {
        elapsedTime = 0;
        deltaTime = 0;
        
        previousUpdateTime = System.currentTimeMillis();
    }
    
    // update the values of elapsedTime and deltaTime.
    //  call this once per frame.
    public void update()
    {
        double currentTime = System.currentTimeMillis();
        
        // deltaTime = (currentTime - previousUpdateTime) / 1000.0;
        deltaTime = 0.017;
        
        elapsedTime += deltaTime;
        
        previousUpdateTime = currentTime;
    }
    
    public double getDeltaTime()
    {
        return deltaTime;
    }
   
    public double getElapsedTime()
    {
        return elapsedTime;
    }
    
    
    
    
    
    
}