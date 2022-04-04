package graphics.math;

/**
 * Store data to generate the vertices (and related data: positions, colors, ....)
 *    of a 3D surface.
 */
public class Surface
{
    // define a class interface to store the mathematical function.
    public interface Function
    {
        // function F(u,v) = (x,y,z)
        public Vector F(double u, double v);
    }

    // constructor stores function,
    //   used when generating vertex data
    public Function function;
    public Surface(Function function)
    {
        this.function = function;
    }

    public Vector[][] getVertexPositions(double uStart, double uEnd, int uNumPoints,
                                         double vStart, double vEnd, int vNumPoints)
    {
        // store the positions
        Vector[][] positions = new Vector[uNumPoints][vNumPoints];
        for (int uIndex = 0; uIndex < uNumPoints; uIndex++)
        {
            for (int vIndex = 0; vIndex < vNumPoints; vIndex++)
            {
                double u = uStart + ((uEnd - uStart)/(uNumPoints - 1)) * uIndex;
                double v = vStart + ((vEnd - vStart)/(vNumPoints - 1)) * vIndex;
                Vector vec = function.F(u,v);
                positions[uIndex][vIndex] = vec;
            }
        }
        return positions;
    }

}
