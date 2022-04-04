import graphics.core.*;
import static org.lwjgl.opengl.GL40.*;

public class TestAttribute extends Base
{
    int programRef;
    int arrayRef, arrayRef2;
    
    public void initialize()
    {
        programRef = OpenGLUtils.initializeProgram(
             "shaders/position.vert", "shaders/yellow.frag");
        
        // creates an array to store all vertex data AND associations
        arrayRef = glGenVertexArrays();
        
        // make this array object active (use it in future OpenGL commands)
        glBindVertexArray(arrayRef);
        
        float[] positionData = {
             0.5f, 0.9f, 0.0f,
             0.2f, 0.2f, 0.0f,
             0.8f, 0.2f, 0.0f
        };
        
        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(programRef, "pos");
        
        // create a second vertex array object to store
        //    different settings for the vertices in the shader.
        //    (get data from different buffers)
        arrayRef2 = glGenVertexArrays();
        glBindVertexArray(arrayRef2);
        
        float[] positionData2 = {
             -0.1f, 0.9f, 0.0f,
             -0.9f, 0.9f, 0.0f,
             -0.9f, 0.2f, 0.0f,
             -0.1f, 0.2f, 0.0f
        };
        
        Attribute positionAttribute2 = new Attribute("vec3", positionData2);
        positionAttribute2.associateVariable(programRef, "pos");
    }
    
    public void update()
    {
        glUseProgram( programRef );
        
        
        glPointSize(10);
        
        // draw triangle data
        
        // activate first buffer association
        glBindVertexArray(arrayRef);
        
        glDrawArrays( GL_TRIANGLES, 0, 3 );
        
        // active second buffer association
        glBindVertexArray(arrayRef2);
        
        glDrawArrays( GL_TRIANGLE_FAN, 0, 4 );
    }
    
    public static void main(String[] args)
    {
        new TestAttribute().run();
    }
    
    
    
    
    
    
}