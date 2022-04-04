package graphics.material;
import java.util.HashMap;
import graphics.core.*;
import graphics.math.*;

/**
 * Store appearance-related data:
 *  - shader program reference
 *  - collection of uniform objects needed by shader program
 */
public class Material
{
    public int programRef;
    
    // GL_TRIANGLES, GL_POINTS, GL_LINES, ...
    public int drawStyle;
    
    public HashMap<String, Uniform> uniforms;
    
    public Material(String vertexShaderFileName, String fragmentShaderFileName)
    {
        programRef = OpenGLUtils.initializeProgram(
                        vertexShaderFileName, fragmentShaderFileName );
                        
        uniforms = new HashMap<String, Uniform>();
        
        // add the three matrices every 3D object needs:
        //  model, view, and projection matrices
        addUniform( "mat4", "modelMatrix",      Matrix.makeIdentity() );
        addUniform( "mat4", "viewMatrix",       Matrix.makeIdentity() );
        addUniform( "mat4", "projectionMatrix", Matrix.makeIdentity() );
        
        
    }
    
    public void addUniform(String dataType, String variableName, Object data)
    {
        Uniform unif = new Uniform(dataType, data);

        uniforms.put(variableName, unif);
    }








}