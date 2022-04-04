import graphics.core.*;
import static org.lwjgl.opengl.GL40.*;

public class TestColor extends Base
{
    int programRef;
    int arrayRef;
    
    public void initialize()
    {
        programRef = OpenGLUtils.initializeProgram(
             "shaders/poscolor.vert", "shaders/color.frag");
        
        // creates an array to store all vertex data AND associations
        arrayRef = glGenVertexArrays();
        
        // make this array object active (use it in future OpenGL commands)
        glBindVertexArray(arrayRef);
        
        float[] positionData = {
             0.0f, 0.8f, 0.0f,
             -0.5f, -0.5f, 0.0f,
             0.5f, -0.5f, 0.0f
        };
        
        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(programRef, "pos");
        
        float[] colorData = {
             1.0f, 0.0f, 0.0f,
             0.0f, 1.0f, 0.0f,
             0.4f, 0.4f, 1.0f
        };
        
        Attribute colorAttribute = new Attribute("vec3", colorData);
        colorAttribute.associateVariable(programRef, "vertexColor");
    }
    
    public void update()
    {
        glUseProgram( programRef );
        
        glPointSize(20);
        glLineWidth(20);
        
        // draw triangle data

        glBindVertexArray(arrayRef);
        
        glDrawArrays( GL_TRIANGLES, 0, 3 );
        
    }
    
    public static void main(String[] args)
    {
        new TestColor().run();
    }
    
    
    
    
    
    
}