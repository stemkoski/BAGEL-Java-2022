package graphics.geometry;
import graphics.math.*;

public class PlaneGeometry extends SurfaceGeometry
{
    public PlaneGeometry()
    {
        /*
        super(  new Surface.Function()
                {
                    public Vector F(double u, double v)
                    {
                        return new Vector(u,v,0);
                    }
                }, 
                
                0,1,10, 0,1,10 );
        */
       
        // lambda expression:
        // replace with:
        //  (u, v) -> return new Vector(u,v,0)
        
        // at compile time, is replaced with above version of super,
        //  classes/types are inferred from parameter type
        super( 
           (u, v) -> { return new Vector(u,v,0); },
            0, 1, 10,
            0, 1, 10 );
         
    }
}
