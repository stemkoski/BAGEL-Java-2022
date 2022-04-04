package graphics.geometry;
import graphics.math.*;

/**
 * Write a description of class TorusGeometry here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TorusGeometry extends SurfaceGeometry
{
    public TorusGeometry()
    {
        super(
            (u, v) ->
            {
                return new Vector( (1 + 0.25 * Math.cos(v)) * Math.cos(u),
                                   (1 + 0.25 * Math.cos(v)) * Math.sin(u),
                                    0.25 * Math.sin(v) );
                                    
            },
            
            0, 2 * Math.PI, 20,
            0, 2 * Math.PI, 20 );
    }
}
