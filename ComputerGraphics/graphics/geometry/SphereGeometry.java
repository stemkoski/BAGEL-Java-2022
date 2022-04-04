package graphics.geometry;

import graphics.math.*;

/**
 * 
 */
public class SphereGeometry extends SurfaceGeometry
{
    public SphereGeometry()
    {
        super(
            (u, v) -> 
            {
                return new Vector(Math.cos(u) * Math.sin(v),
                                  Math.cos(v),
                                  Math.sin(u) * Math.sin(v));
            },
            0, 2 * Math.PI, 64,
            0, Math.PI, 32  );
    }
}
