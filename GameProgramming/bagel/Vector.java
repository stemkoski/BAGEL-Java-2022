package bagel;

/**
 * Store a two-dimensional vector <x, y>.
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
    
    
}
