import bagel.Vector;

/**
 * Testing the methods in the Vector class.
 *
 */
public class TestVector
{
    public static void main(String[] args)
    {
        Vector v = new Vector(3, 4);
        System.out.println("The vector is: " + v);
        System.out.println("The length is: " + v.getLength() );
        System.out.println("The angle is:  " + v.getAngle() );
        
        Vector w = new Vector(0, 5);
        System.out.println( "The vector " + w + " has angle " + w.getAngle() );
        
        w.setValues(0, 1);
        System.out.println("The vector has changed to: " + w);
        
        System.out.println("Changing length to 2");
        w.setLength(2);
        System.out.println( w.equals( new Vector(0,2) ) );

        w.setValues(0, 1);
        System.out.println("The vector has changed to: " + w);
        System.out.println("Changing angle to 180 degrees");
        w.setAngle(180);
        System.out.println( w.equals( new Vector(-1,0) ) );

        w.setValues(1, 2);
        System.out.println("The vector has changed to: " + w);
        System.out.println("adding values (5, 6)");
        w.addValues(5, 6);
        System.out.println( w.equals( new Vector(6,8) ) );

        Vector u = new Vector(0,1);
        u.setAngle(180);
        System.out.println(u);
        
    }
}
