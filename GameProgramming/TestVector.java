import bagel.Vector;

/**
 * Write a description of class TestVector here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestVector
{
    public static void main(String[] args)
    {
        Vector v = new Vector(3, 4);
        System.out.println("The vector is:");
        System.out.println( v );
        
        System.out.println("The length is:");
        System.out.println( v.getLength() );
        
        Vector w = new Vector(0, 5);
        System.out.println( "The vector" );
        System.out.println( w );
        System.out.println( "has angle" );
        System.out.println( w.getAngle() );
        
        w.setValues(0, 1);
        System.out.println("The vector has changed to: " + w);
        
        System.out.println("Changing length to 2");
        w.setLength(2);
        System.out.println("The vector has changed to: " + w); // expect (0,2)

        w.setValues(0, 1);
        System.out.println("The vector has changed to: " + w);
        System.out.println("Changing angle to 180 degrees");
        w.setAngle(180);
        System.out.println("The vector has changed to: " + w); // expect (-1, 0)

        w.setValues(1, 2);
        System.out.println("The vector has changed to: " + w);
        System.out.println("adding values (5, 6)");
        w.addValues(5, 6);
        System.out.println("The vector has changed to: " + w); // expect (6, 8)

    
    }
}
