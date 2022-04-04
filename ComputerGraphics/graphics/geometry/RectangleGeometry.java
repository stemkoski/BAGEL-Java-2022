package graphics.geometry;
import graphics.math.*;

public class RectangleGeometry extends Geometry
{
    // purpose: define all data arrays and create attributes.
    
    public RectangleGeometry(double width, double height)
    {
        Vector P0 = new Vector( width/2, height/2, 0);
        Vector P1 = new Vector(-width/2, height/2, 0);
        Vector P2 = new Vector(-width/2, -height/2, 0);
        Vector P3 = new Vector( width/2, -height/2, 0);
         
        float[] positionData = Vector.flattenArray( P0,P1,P2, P0,P2,P3 );

        addAttribute("vec3", "position", positionData);
    
        Vector C0 = new Vector(1, 0, 0);
        Vector C1 = new Vector(0, 1, 1); 
        Vector C2 = new Vector(1, 0.5, 0);
        Vector C3 = new Vector(0.75, 0.75, 1);
        
        float[] colorData = Vector.flattenArray( C0,C1,C2, C0,C2,C3 );
        
        addAttribute("vec3", "vertexColor", colorData);
        
        // ALWAYS STATE HOW MANY VERTICES NEED TO BE DRAWN
        vertexCount = 6;
    }
}