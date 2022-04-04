import graphics.core.*;
import static org.lwjgl.opengl.GL40.*;

public class TestMove extends Base
{
    int programRef;
    int arrayRef;
    
    // add uniform reference
    Uniform<Float[]> offsetUniform;
    Uniform<Float[]> colorUniform;
    
    
    public void initialize()
    {
        programRef = OpenGLUtils.initializeProgram(
             "shaders/translate.vert", "shaders/uniformcolor.frag");
        
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
        colorUniform = new Uniform<Float[]>("vec3", triangleColorData);
        colorUniform.associateVariable(programRef, "color");
        
        Float[] translateOffsetData = { 0.0f, 0.5f, 0.0f };
        offsetUniform = new Uniform<Float[]>("vec3", translateOffsetData);
        offsetUniform.associateVariable(programRef, "offset");
    }
    
    public void update()
    {
        glUseProgram( programRef );
        
        /*
        // specify uniform data
        float[] triangleColor = { 0.0f, 0.0f, 0.5f };
        
        // find reference to variable where data will be sent
        int uniformRef = glGetUniformLocation( programRef, "color" );
        
        // send data (3 floats) to uniform variable
        glUniform3f( uniformRef, triangleColor[0], triangleColor[1], triangleColor[2] );
        */
        
        /*
        // change uniform data
        colorUniform.data[2] += 0.01f;     
        // make sure color values never go above 1.0 OR ELSE
        if ( colorUniform.data[2] > 1.0f )
          colorUniform.data[2] = 0.0f;
        */
       
        // change uniform data in a cyclic pattern
        double t = clock.getElapsedTime();
        colorUniform.data[0] = (float)Math.abs( Math.sin( t ) );
        colorUniform.data[1] = (float)Math.abs( Math.sin( t + 2 ) );
        colorUniform.data[2] = (float)Math.abs( Math.sin( t + 4 ) );
        colorUniform.uploadData();
       
        offsetUniform.data[1] += 0.01f;
        if (offsetUniform.data[1] > 1.5f)
            offsetUniform.data[1] = -1.5f;
        offsetUniform.uploadData();
        
        glPointSize(20);
        glLineWidth(20);
        
        // draw triangle data

        glBindVertexArray(arrayRef);
        
        glDrawArrays( GL_TRIANGLES, 0, 3 );
        
        // draw a border around triangle?
        colorUniform.data[0] = 1f;
        colorUniform.data[1] = 1f;
        colorUniform.data[2] = 1f;
        colorUniform.uploadData();
        
        //glDrawArrays( GL_LINE_LOOP, 0, 3 );
    }
    
    public static void main(String[] args)
    {
        new TestMove().run();
    }
    
    
    
    
    
    
}