import graphics.math.*;

/**
 * Test Vector class.
 */
public class TestVector
{
    public static void main(String[] args)
    {
        Vector v = new Vector(2,5);
        Vector w = new Vector(3,3,7);
        Vector u = new Vector(9,0,8,3);
        
        System.out.println(v);
        System.out.println(w);
        System.out.println(u);
        
        Vector a = new Vector(2,3);
        Vector b = new Vector(4,5);
        
        // test vector operations
        Vector c = Vector.addVectors(a,b);
        System.out.println( c.equals(new Vector(6,8)) );
        
        c = Vector.subtractVectors(a,b);
        System.out.println( c.equals(new Vector(-2, -2)) );
        
        c = Vector.multiplyScalarVector( 5, a );
        System.out.println( c.equals(new Vector(10, 15)) );
        
        double d = Vector.dot(a, b);
        System.out.println( d == 23 );
        
        Vector e = new Vector(1,0);
        System.out.println( e.getLength() == 1 );
        
        e = new Vector(3,4);
        System.out.println( e.getLength() == 5 );
        
        
    }
}
