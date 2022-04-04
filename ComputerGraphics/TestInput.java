import graphics.core.*;
import static org.lwjgl.opengl.GL40.*;

// import statement for key constants
import static org.lwjgl.glfw.GLFW.*;

public class TestInput extends Base
{
    int programRef;
    int arrayRef;
    
    // add uniform reference
    Uniform<Float[]> colorUniform;
    
    
    public void initialize()
    {
        programRef = OpenGLUtils.initializeProgram(
             "shaders/poscolor.vert", "shaders/uniformcolor.frag");
        
        // creates an array to store all vertex data AND associations
        arrayRef = glGenVertexArrays();
        
        // make this array object active (use it in future OpenGL commands)
        glBindVertexArray(arrayRef);
        
        float[] positionData = {
              0.0f,  0.8f, 0.0f,
             -0.5f, -0.5f, 0.0f,
              0.5f, -0.5f, 0.0f
        };
        
        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(programRef, "pos");
        
        // specify uniform data
        Float[] triangleColorData = { 1.0f, 1.0f, 1.0f };
        
        // create uniform object
        colorUniform = new Uniform<Float[]>("vec3", triangleColorData);
        
        // find uniform reference
        colorUniform.associateVariable(programRef, "color");
        
    }
    
    public void update()
    {
        glUseProgram( programRef );
        
        if ( input.keyPressed(GLFW_KEY_R) )
        {
            colorUniform.data[0] = 1f;
            colorUniform.data[1] = 0f;
            colorUniform.data[2] = 0f;
        }
        if ( input.keyPressed(GLFW_KEY_G) )
        {
            colorUniform.data[0] = 0f;
            colorUniform.data[1] = 1f;
            colorUniform.data[2] = 0f;
        }
        if ( input.keyPressed(GLFW_KEY_B) )
        {
            colorUniform.data[0] = 0f;
            colorUniform.data[1] = 0f;
            colorUniform.data[2] = 1f;
        }
        
        if ( input.keyPressing(GLFW_KEY_0) )
        {
            colorUniform.data[0] -= 0.01f;
            colorUniform.data[1] -= 0.01f;
            colorUniform.data[2] -= 0.01f;
        }
        
        // send data from uniform to GPU
        colorUniform.uploadData();
       
        glPointSize(20);
        glLineWidth(20);
        
        // draw triangle data

        glBindVertexArray(arrayRef);
        
        glDrawArrays( GL_TRIANGLES, 0, 3 );
        
    }
    
    public static void main(String[] args)
    {
        new TestInput().run();
    }
    
    
    
    
    
    
}