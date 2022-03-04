package bagel;

/**
 *   Do calculations involving position, velocity, and acceleration
 *     for smoother Sprite movement.
 */
public class Physics
{
    public Vector position;
    public Vector velocity;
    public Vector acceleration;
    
    /**
     * Physics Constructor
     *
     */
    public Physics()
    {
        position = new Vector();
        velocity = new Vector();
        acceleration = new Vector();
    }
    
    /**
     * Change the speed of this object.
     * @param speed the new speed of this object
     */
    public void setSpeed(double speed)
    {
        velocity.setLength(speed);
    }
    
    /**
     * Get the speed of this object.
     * @return the speed
     */
    public double getSpeed()
    {
        return velocity.getLength();
    }
    
    /**
     * Set the angle that the object is moving in.
     * @param angleDegrees the new angle of motion, in degrees
     */
    public void setMotionAngle(double angleDegrees)
    {
        velocity.setAngle(angleDegrees);
    }
    
    /**
     * Get the angle of motion of this object.
     * @return the angle of motion (in degrees)
     */
    public double getMotionAngle()
    {
        return velocity.getAngle();
    }
    
    /**
     * Update the position of this object,
     *   based on the current velocity, and the time that has passed
     *   since the last update.
     * @param deltaTime change in time since last update (usually 1/60 second)
     */
    public void update(double deltaTime)
    {
        position.addValues( velocity.x * deltaTime, velocity.y * deltaTime );
    }
    
    
    
    
}
