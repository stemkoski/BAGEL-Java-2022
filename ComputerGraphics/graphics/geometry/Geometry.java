package graphics.geometry;
import java.util.HashMap;
import graphics.core.Attribute;

/**
 * Define the "structure" of a 3D object; store Attributes
 *   contain vertex-related data (position, color, etc.)
 */
public class Geometry
{
    // store a collection of attribute objects,
    //  indexed by name (String), not by number, so we can't use Array/List.
    public HashMap<String, Attribute> attributes;

    // store number of vertices, used by glDrawArrays function.
    public int vertexCount;

    public Geometry()
    {
        attributes = new HashMap<String, Attribute>();
        vertexCount = 0;
    }

    public void addAttribute(
       String dataType, String variableName, float[] dataArray)
    {
        Attribute attr = new Attribute(dataType, dataArray);
        // add to collection
        attributes.put( variableName, attr );
    }

}
