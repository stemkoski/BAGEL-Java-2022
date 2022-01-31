package bagel;

/**
 * Store a two-dimensional vector (x, y).
 *
 * @author Stemkoski
 * @version 1.0
 */
public class Vector
{
    public double x;

    public double y;

    /**
     * Vector Constructor; initializes vector to (x, y) = (0, 0).
     *
     */
    public Vector()
    {
        x = 0;
        y = 0;
    }

    /**
     * Vector Constructor; initializes vector to (x, y) = (a, b).
     *
     * @param a initial x value
     * @param b initial y value
     */
    public Vector(double a, double b)
    {
        x = a;
        y = b;
    }

    /**
     * Convert vector data to string format "( x , y )".
     *
     * @return vector data encoded in a string
     */
    @Override
    public String toString()
    {
        return "(" + x + " , " + y + ")";
    }
    
    /**
     * Calculate the length of this vector.
     *
     * @return the length of this vector
     */
    public double getLength()
    {
        return Math.sqrt( x * x + y * y );
    }
    
    /**
     * Calculate the direction of this vector: the angle between the vector
     *   and the x-axis.
     *
     * @return the angle (direction) of this vector in degrees
     */
    public double getAngle()
    {
        double angleRadians = Math.atan2(y, x);
        double angleDegrees = Math.toDegrees(angleRadians);
        return angleDegrees;
    }
    
    /**
     * Change the values stored in the vector.
     *
     * @param a the new x value
     * @param b the new y value
     */
    public void setValues( double a, double b )
    {
        x = a;
        y = b;
    }
    
    /**
     * Change the length of this vector,
     *   while keeping the current angle.
     *
     * @param newLength new length of this vector
     */
    public void setLength(double newLength)
    {
        double angleDegrees = getAngle();
        double angleRadians = Math.toRadians(angleDegrees);
        x = newLength * Math.cos(angleRadians);
        y = newLength * Math.sin(angleRadians);
    }
    
    /**
     * Change the angle of this vector,
     *   while keeping the current length.
     *
     * @param newAngleDegrees new angle of this vector, in degrees
     */
    public void setAngle(double newAngleDegrees)
    {
        double length = getLength();
        double newAngleRadians = Math.toRadians(newAngleDegrees);
        x = length * Math.cos(newAngleRadians);
        y = length * Math.sin(newAngleRadians);
    }
    
    /**
     * Add the values (a, b) to the current position (x, y).
     *
     * @param a the value to add to x
     * @param b the value to add to y
     */
    public void addValues(double a, double b)
    {
        x += a;
        y += b;
    }
    
    
    
    
}
