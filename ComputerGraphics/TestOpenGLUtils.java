import graphics.core.*;

import static org.lwjgl.opengl.GL40.*;

/**
 * Write a description of class TestOpenGLUtils here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestOpenGLUtils extends Base
{
    int programRef;
    // data array
    int arrayRef;
    public void initialize()
    {
        /*
        String code = OpenGLUtils.readFile("shaders/center.vert");
        System.out.println(code);
        
        // test compiling shader
        int shaderRef = OpenGLUtils.initializeShader(code, GL_VERTEX_SHADER);
        System.out.println("Shader reference: " + shaderRef );
        
        String code2 = OpenGLUtils.readFile("shaders/yellow.frag");
        int shaderRef2 = OpenGLUtils.initializeShader(code2, GL_FRAGMENT_SHADER);
        System.out.println("Shader reference: " + shaderRef2 );
        */
        
        programRef = OpenGLUtils.initializeProgram(
             "shaders/position.vert", "shaders/yellow.frag");
        
        System.out.println("Program reference: " + programRef);
        
        // creates an array to store all vertex data
        arrayRef = glGenVertexArrays();
        
        // make this array object active (use it in future OpenGL commands)
        glBindVertexArray(arrayRef);
        
        // create an array of points
        
        float[] positionData = {
             0.8f,  0.0f,  0.0f,
             0.2f,  0.7f,  0.0f,
            -0.2f,  0.7f,  0.0f,
            -0.8f,  0.0f,  0.0f,
            -0.2f, -0.7f,  0.0f,
             0.2f, -0.7f,  0.0f  };
        
        // create a buffer to store data
        int bufferRef = glGenBuffers();
        
        // activate buffer; storing Array data
        glBindBuffer( GL_ARRAY_BUFFER, bufferRef );
        
        // transmit data into buffer
        glBufferData( GL_ARRAY_BUFFER, positionData, GL_STATIC_DRAW );
        
        // locate reference for shader variable where data should be sent
        int variableRef = glGetAttribLocation(programRef, "pos");
        
        System.out.println("Variable ref: " + variableRef );
        
        // set up buffer to variable association
        glVertexAttribPointer( variableRef, 3, GL_FLOAT, false, 0, 0 );
        
        // activate/enable data flow
        glEnableVertexAttribArray( variableRef );
        
    }
    
    public void update()
    {
        // declare GPU program to use
        glUseProgram(programRef);
        
        // increased the size of points to 20 pixels
        glPointSize(20);
        // increase the width of lines
        glLineWidth(6);
        
        // draw all the points
        // parameters: 
        //    draw style (points/lines/triangles), starting array index, # vertices
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
    }
    
    public static void main(String[] args)
    {
        new TestOpenGLUtils().run();
    }
}
