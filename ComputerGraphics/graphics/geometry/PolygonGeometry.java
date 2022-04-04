package graphics.geometry;

import graphics.math.*;

public class PolygonGeometry extends Geometry
{
    public PolygonGeometry(int numberOfSides)
    {
        double baseAngle = 2 * Math.PI / numberOfSides;
        
        // store position of each vertex around triangle
        Vector[] P = new Vector[numberOfSides];
        
        for (int i = 0; i < numberOfSides; i++)
        {
            double x = Math.cos( i * baseAngle );
            double y = Math.sin( i * baseAngle );
            double z = 0;
            P[i] = new Vector( x, y, z );
        }
        
        Vector[] trianglePoints = new Vector[numberOfSides * 3];
        Vector origin = new Vector(0,0,0);
        int triangleArrayIndex = 0;
        
        for (int pointArrayIndex = 0; pointArrayIndex < numberOfSides; pointArrayIndex++)
        {
            trianglePoints[triangleArrayIndex] = P[pointArrayIndex];
            triangleArrayIndex++;
            
            // when i is equal to last value, set it back to 0 with mod (%)
            trianglePoints[triangleArrayIndex] = P[(pointArrayIndex+1) % numberOfSides];
            triangleArrayIndex++;
            
            trianglePoints[triangleArrayIndex] = origin;
            triangleArrayIndex++;
        }
        
        float[] positionData = Vector.flattenArray(trianglePoints);
        
        addAttribute("vec3", "position", positionData);
        
        Vector[] triangleColors = new Vector[numberOfSides * 3];
        Vector C0 = new Vector(0.5, 0, 1);
        Vector C1 = new Vector(0, 1, 1);
        Vector C2 = new Vector(1, 0, 0);
        
        triangleArrayIndex = 0;
        for (int i = 0; i < numberOfSides; i++)
        {
            triangleColors[triangleArrayIndex] = C0;
            triangleArrayIndex++;
            
            triangleColors[triangleArrayIndex] = C1;
            triangleArrayIndex++;
            
            triangleColors[triangleArrayIndex] = C2;
            triangleArrayIndex++;
        }
        
        float[] vertexColorData = Vector.flattenArray( triangleColors );
        addAttribute("vec3", "vertexColor", vertexColorData);
        
        vertexCount = numberOfSides * 3;
    }
}
