import bagel.Rectangle;

/**
 * Test the overlap method in the Rectangle class.
 */
public class TestRectangle
{
    public static void main(String[] args)
    {
         Rectangle R1 = new Rectangle(1,1, 2,3);
         Rectangle R2 = new Rectangle(2,2, 5,4);
         Rectangle R3 = new Rectangle(6,1, 5,3);
         
         System.out.println("Overlap tests should be: true, true, false");
         System.out.println( R1.overlap(R2) );
         System.out.println( R2.overlap(R3) );
         System.out.println( R1.overlap(R3) );
         
        
    }
   
    
}




