package graphics.core;

import graphics.math.*;

/**
 *   Camera affects how viewer sees all objects in a 3D scene;
 *   view matrix = inverse of the camera's transform matrix
 *   
 *   also store a projection matrix that makes objects farther away appear smaller
 */
public class Camera extends Object3D
{
    public Matrix viewMatrix;
    public Matrix projectionMatrix;
    
    public Camera()
    {
        viewMatrix = transform.inverse();
        projectionMatrix = Matrix.makePerspective();
    }
    
    public void updateViewMatrix()
    {
        viewMatrix = transform.inverse();
    }
    
    
    
}
