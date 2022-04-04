import graphics.core.*;
import graphics.math.*;
import static org.lwjgl.opengl.GL40.*;

// import statement for key constants
import static org.lwjgl.glfw.GLFW.*;

import java.util.Arrays;

/**
 * Test Matrix multiplication in shaders to move vertices.
 */
public class Asteroids extends Base
{
    public int programRef;
    public int arrayRef;
    public Uniform<Matrix> transformUniform;
    
    public Matrix moveForward;
    public Matrix turnLeft;
    public Matrix turnRight;
    
    public void initialize()
    {
        programRef = OpenGLUtils.initializeProgram(
            "shaders/transform.vert",
            "shaders/yellow.frag"
        );

        arrayRef = glGenVertexArrays();
        glBindVertexArray(arrayRef);

        float[] positionData = {
                 0.0f,  0.3f, 0.0f,
                -0.1f, -0.1f, 0.0f,
                 0.1f, -0.1f, 0.0f
            };

        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(programRef, "pos");

        Matrix M = Matrix.makeIdentity();
        
        transformUniform = new Uniform<Matrix>(
            "mat4", M );
        transformUniform.associateVariable(
            programRef, "transform");

        moveForward  = Matrix.makeTranslation(0, 0.01, 0);
        turnLeft     = Matrix.makeRotationZ(0.01);
        turnRight    = Matrix.makeRotationZ(-0.01);
            
    }

    public void update()
    {
        glUseProgram( programRef );
        
        // clear the screen to prevent smearing when drawing and moving
        glClearColor( 0.5f, 0.5f, 0.5f, 1.0f );
        glClear( GL_COLOR_BUFFER_BIT );
        
        glBindVertexArray(arrayRef);
        
        if ( this.input.keyPressing(GLFW_KEY_A) )
            transformUniform.data = Matrix.multiplyMatrices(transformUniform.data, turnLeft);
        
        if ( this.input.keyPressing(GLFW_KEY_D) )
            transformUniform.data = Matrix.multiplyMatrices(transformUniform.data, turnRight);

        if ( this.input.keyPressing(GLFW_KEY_W) )
            transformUniform.data = Matrix.multiplyMatrices(transformUniform.data, moveForward);

        transformUniform.uploadData();
        
        glDrawArrays( GL_TRIANGLES, 0, 3 );
    }
    
    public static void main(String[] args)
    {
        new Asteroids().run();
    }
    
    
    
    
    
    
}
