package graphics.core;

import graphics.math.*;

// import statement for key constants
import static org.lwjgl.glfw.GLFW.*;

/**
 * Move an object around the screen with keyboard controls
 */
public class MovementRig
{
    public double movementSpeed; // units per second
    public double turnSpeed;     // degrees per second
    public Object3D object;      // object being moved
    public Input input;          // listens for keyboard input
    
    public MovementRig(Object3D object, Input input)
    {
        this.movementSpeed = 1;
        this.turnSpeed     = 45;
        this.object        = object;
        this.input         = input;
    }
    
    
    /**
     * Move the attached object according to the keyboard input.
     *
     * @param deltaTime how much time has passed since last update (1/60 second)
     */
    public void update(double deltaTime)
    {
        // W = forward
        if ( input.keyPressing(GLFW_KEY_W) )
        {
            // multiply object transform by a matrix that translates forward.
            // multiply on the right for a local transformation.
            Matrix T = Matrix.makeTranslation( 0, 0, -movementSpeed * deltaTime );
            object.transform = Matrix.multiplyMatrices( object.transform, T );
        }
        if ( input.keyPressing(GLFW_KEY_S) ) // backward
        {
            Matrix T = Matrix.makeTranslation( 0, 0, movementSpeed * deltaTime );
            object.transform = Matrix.multiplyMatrices( object.transform, T );
        }
        if ( input.keyPressing(GLFW_KEY_A) ) // left
        {
            Matrix T = Matrix.makeTranslation( -movementSpeed * deltaTime, 0, 0 );
            object.transform = Matrix.multiplyMatrices( object.transform, T );
        }
        if ( input.keyPressing(GLFW_KEY_D) ) // right
        {
            Matrix T = Matrix.makeTranslation( movementSpeed * deltaTime, 0, 0 );
            object.transform = Matrix.multiplyMatrices( object.transform, T );
        }
        if ( input.keyPressing(GLFW_KEY_R) ) // up
        {
            Matrix T = Matrix.makeTranslation( 0, movementSpeed * deltaTime, 0 );
            object.transform = Matrix.multiplyMatrices( object.transform, T );
        }
        if ( input.keyPressing(GLFW_KEY_F) ) // down
        {
            Matrix T = Matrix.makeTranslation( 0, -movementSpeed * deltaTime, 0 );
            object.transform = Matrix.multiplyMatrices( object.transform, T );
        }

        // note: convert degrees to radians!
        
        if ( input.keyPressing(GLFW_KEY_Q) ) // turn left
        {
            Matrix T = Matrix.makeRotationY( turnSpeed * deltaTime * Math.PI / 180 );
            object.transform = Matrix.multiplyMatrices( object.transform, T );
        }
        if ( input.keyPressing(GLFW_KEY_E) ) // turn right
        {
            Matrix T = Matrix.makeRotationY( -turnSpeed * deltaTime * Math.PI / 180 );
            object.transform = Matrix.multiplyMatrices( object.transform, T );
        }

        
    }
}
