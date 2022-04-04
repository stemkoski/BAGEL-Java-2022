package graphics.geometry;

import graphics.math.*;

public class SurfaceGeometry extends Geometry
{
    public SurfaceGeometry( Surface.Function F, 
        double uStart, double uEnd, int uNumPoints,
        double vStart, double vEnd, int vNumPoints  )
    {
        Surface surf = new Surface(F);
        
        Vector[][] positions = surf.getVertexPositions(uStart, uEnd, uNumPoints,
                                                       vStart, vEnd, vNumPoints  );
                                             
        vertexCount = 3 * 2 * (uNumPoints - 1) * (vNumPoints - 1);
        
        Vector[] trianglePoints = new Vector[vertexCount];
        Vector[] triangleColors = new Vector[vertexCount];
        Vector C0 = new Vector(0.5, 0.75, 1); 
        Vector C1 = new Vector(0.5, 1, 0.5);
        Vector C2 = new Vector(0.75, 0.5, 1);
        Vector C3 = new Vector(0.85, 0.10, 0.25); // crimson!
        Vector C4 = new Vector(1, 0.5, 0);
        Vector C5 = new Vector(1, 1, 0);
        int triangleIndex = 0;
        
        for (int uIndex = 0; uIndex < uNumPoints - 1; uIndex++)
        {
            for (int vIndex = 0; vIndex < vNumPoints - 1; vIndex++)
            {
                Vector P0 = positions[uIndex][vIndex];
                Vector P1 = positions[uIndex+1][vIndex];
                Vector P2 = positions[uIndex+1][vIndex+1];
                Vector P3 = positions[uIndex][vIndex+1];
                
                trianglePoints[triangleIndex] = P0;
                triangleColors[triangleIndex] = C0;
                triangleIndex++;
                trianglePoints[triangleIndex] = P1;
                triangleColors[triangleIndex] = C1;
                triangleIndex++;
                trianglePoints[triangleIndex] = P2;
                triangleColors[triangleIndex] = C2;
                triangleIndex++;
                trianglePoints[triangleIndex] = P0;
                triangleColors[triangleIndex] = C3;
                triangleIndex++;
                trianglePoints[triangleIndex] = P2;
                triangleColors[triangleIndex] = C4;
                triangleIndex++;
                trianglePoints[triangleIndex] = P3;
                triangleColors[triangleIndex] = C5;
                triangleIndex++;
            }
        }
        
        float[] vertexPositionData = Vector.flattenArray(trianglePoints);
        addAttribute("vec3", "position", vertexPositionData);
        
        float[] vertexColorData = Vector.flattenArray(triangleColors);
        addAttribute("vec3", "vertexColor", vertexColorData);
        
    }
}
