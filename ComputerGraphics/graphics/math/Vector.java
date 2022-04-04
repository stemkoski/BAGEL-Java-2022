package graphics.math;


/**
 * Store Vector-related data: a list of numbers, and a set of methods
 *    corresponding to vector operations.
 */
public class Vector
{
    public double[] values;
    
    /**
     * Use this constructor when you don't know the initial values,
     *   but you do know the size (number of values) that will be stored.
     */
    public Vector(int size)
    {
        values = new double[size];
    }
    
    public Vector(double x, double y)
    {
        values = new double[2];
        values[0] = x;
        values[1] = y;
    }
    
    public Vector(double x, double y, double z)
    {
        values = new double[3];
        values[0] = x;
        values[1] = y;
        values[2] = z;
    }
    
    public Vector(double x, double y, double z, double w)
    {
        values = new double[4];
        values[0] = x;
        values[1] = y;
        values[2] = z;
        values[3] = w;
    }
    
    // convert data to reading String format
    @Override
    public String toString()
    {
        String str = "[  ";
        for (int i = 0; i < values.length; i++)
            str += values[i] + "  ";
        str += "]";
        
        return str;
    }
    
    // check if two vectors are equal (values stored in vectors are equal)
    // useful for checking test cases
    
    public boolean equals(Vector other)
    { 
        boolean same = true;
        for (int i = 0; i < this.values.length; i++)
        {
            //System.out.println("checking: " + i);
            //System.out.println( this.values[i] == other.values[i] );
            same = same && (this.values[i] == other.values[i]);
            //System.out.println("still same? " + same);
        }
        return same;
        // return (this.values[0] == other.values[0] && this.values[1] == other.values[1] && ....
    }
    
    // vector operations:
    
    
    /**
     * Add values in other vector to this instance.
     * Note: this vector and other vector *must* have same number of components
     * 
     * @param other another vector
     */
    public void addVector(Vector other)
    {
        // if (this.values.length != other.values.length)
        //    throw new RuntimeException("What Are You Doing?!?!?");
            
        for (int i = 0; i < this.values.length; i++)
            this.values[i] += other.values[i];
    }
    
    /**
     * Create a new vector by adding two other vectors together.
     *
     * @param v the first vector
     * @param w the second vector
     * @return the sum of the vectors
     */
    public static Vector addVectors(Vector v, Vector w)
    {
        Vector sum = new Vector(v.values.length);
        
        for (int i = 0; i < sum.values.length; i++)
            sum.values[i] = v.values[i] + w.values[i];
            
        return sum;
    }
    
    public void subtractVector(Vector other)
    {
        for (int i = 0; i < this.values.length; i++)
            this.values[i] -= other.values[i];
    }
    
    public static Vector subtractVectors(Vector v, Vector w)
    {
        Vector diff = new Vector(v.values.length);
        
        for (int i = 0; i < diff.values.length; i++)
            diff.values[i] = v.values[i] - w.values[i];
            
        return diff;
    }
    
    /**
     * Multiply all the values in this vector by a constant.
     *  Corresponds to stretching or shrinking this vector.
     *  
     * @param c constant to multiply values by
     */
    public void multiplyScalar(double c)
    {
        for (int i = 0; i < this.values.length; i++)
        {
            this.values[i] *= c;
        }
    }
    
    /**
     * Multiply all the values in a vector by a constant.
     *  Corresponds to stretching or shrinking a vector.
     *  
     * @param c constant to multiply values by
     * @param v Vector being multiplied by a constant
     */
    public static Vector multiplyScalarVector(double c, Vector v)
    {
        Vector w = new Vector(v.values.length);
        for (int i = 0; i < w.values.length; i++)
        {
            w.values[i] = c * v.values[i];
        }
        return w;
    }
    
    /**
     * Calculate the dot product of two vectors:
     * if V = (v1, v2) and W = (w1, w2), then V o W = v1 * w1 + v2 * w2;
     * Used in length calculations and matrix multiplcations.
     */
    public static double dot(Vector v, Vector w)
    {
        double sum = 0;
        for (int i = 0; i < v.values.length; i++)
        {
            sum += v.values[i] * w.values[i];
        }
        return sum;
    }
    
    
    /**
     * Calculate the length of a vector.
     * example: if V = (v1, v2), then length(V) = sqrt( v1*v1 + v2*v2 ) = sqrt( dot( V, V ) )
     * 
     * @return the length of this vector
     */
    public double getLength()
    {
        return Math.sqrt( Vector.dot( this, this ) );
    }
    
    /**
     * Take any number of vector parameters, and place all components into a single float array.
     * example: flattenArray( new Vector(2,3,5), new Vector(5,3,7) )
     *          returns [2, 3, 5,  5, 3, 7]
     * This is the format required by OpenGL vertex buffers.
     */
    public static float[] flattenArray(Vector ...vectorArray)
    {
        int vectorSize = vectorArray[0].values.length;
        float[] array = new float[ vectorArray.length * vectorSize ];
        int arrayIndex = 0;
        for (int vectorNum = 0; vectorNum < vectorArray.length; vectorNum++)
        {
            Vector vec = vectorArray[vectorNum];
            for (int indexNum = 0; indexNum < vectorSize; indexNum++)
            {
                // arrayIndex = vectorNum * vectorSize + indexNum;
                array[ arrayIndex ] = (float)vec.values[indexNum];
                arrayIndex++;
            }
        }
        return array;
    }
    
    
    
    
    
    
    
}
