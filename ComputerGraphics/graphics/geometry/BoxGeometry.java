package graphics.geometry;

import graphics.math.*;

/**
 * It's a BOX
 */
public class BoxGeometry extends Geometry
{
    // set up attributes (position and vertex colors) and vertex count
    public BoxGeometry(double width, double height, double depth)
    {
        Vector P0 = new Vector(-width/2,  height/2, -depth/2);
        Vector P1 = new Vector( width/2,  height/2, -depth/2);
        Vector P2 = new Vector(-width/2,  height/2,  depth/2);
        Vector P3 = new Vector( width/2,  height/2,  depth/2);
        Vector P4 = new Vector(-width/2, -height/2, -depth/2);
        Vector P5 = new Vector( width/2, -height/2, -depth/2);
        Vector P6 = new Vector(-width/2, -height/2,  depth/2);
        Vector P7 = new Vector( width/2, -height/2,  depth/2);
        
        float[] positionData = Vector.flattenArray( 
                                    P0,P1,P2, P1,P3,P2, // TOP SIDE
                                    P2,P3,P6, P3,P7,P6, // FRONT
                                    P6,P7,P4, P7,P5,P4, // BOTTOM
                                    P0,P2,P4, P2,P6,P4, // LEFT
                                    P3,P1,P7, P1,P5,P7, // RIGHT
                                    P1,P0,P5, P0,P4,P5  // BACK
                                    );
        
        addAttribute("vec3", "position", positionData);
        
        Vector C0 = new Vector(1, 0, 0);
        Vector C1 = new Vector(1, 0.5, 0);
        Vector C2 = new Vector(0.5, 1, 0.5);
        Vector C3 = new Vector(0.75, 0.75, 1);
        Vector C4 = new Vector(1, 0.75, 0.75);
        Vector C5 = new Vector(0.8, 0.6, 1);
        
        float[] colorData = Vector.flattenArray( C0,C0,C0,C0,C0,C0,
                                                 C1,C1,C1,C1,C1,C1,
                                                 C2,C2,C2,C2,C2,C2,
                                                 C3,C3,C3,C3,C3,C3,
                                                 C4,C4,C4,C4,C4,C4,
                                                 C5,C5,C5,C5,C5,C5 );
                                    
        addAttribute("vec3", "vertexColor", colorData);
        
        vertexCount = 36;
    }
}
