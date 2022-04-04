package graphics.core;

import static org.lwjgl.opengl.GL40.*;
import java.util.ArrayList;

/**
 * Main purpose is to draw all objects specified by meshes.
 * 
 */
public class Renderer
{
    public Renderer()
    {
        // specify color used to clear screen
        glClearColor(0, 0, 0, 1);
        
        // enable depth testing for 3D objects;
        //  otherwise, objects drawn in order added to scene
        glEnable( GL_DEPTH_TEST );
    }
    
    // where all the drawing happens
    // requires both group of objects, and the camera which gives the viewpoint.
    public void render(Object3D sceneRoot, Camera camera)
    {
        // clear the graphics window
        glClear( GL_COLOR_BUFFER_BIT );
        // refresh depth information 
        glClear( GL_DEPTH_BUFFER_BIT );
        
        
        ArrayList<Object3D> descendentList = sceneRoot.getDescendentList();
        
        // calculates the inverse once per render, for efficiency.
        camera.updateViewMatrix();
        
        // draw all Mesh objects 
        for (Object3D obj : descendentList)
        {
            // if object is not a Mesh, continue to next object in list
            //   (skips the rest of this iteration of the loop)
            if ( !(obj instanceof Mesh) ) 
            {
                continue;
            }
            // cast Object3d into a Mesh to access class variables
            Mesh mesh = (Mesh)obj;
            // use the correct GPU program, stored in material
            glUseProgram( mesh.material.programRef );
            // use correct buffer -> variable associations (stored in VAO)
            glBindVertexArray( mesh.vaoRef );
            
            // upload Matrix uniforms, which are not stored in material
            mesh.material.uniforms.get("modelMatrix").data      = mesh.getWorldMatrix();
            mesh.material.uniforms.get("viewMatrix").data       = camera.viewMatrix;
            mesh.material.uniforms.get("projectionMatrix").data = camera.projectionMatrix;
            
            // upload all data stored in all related Uniforms
            for (String variableName : mesh.material.uniforms.keySet())
            {
                Uniform unif = mesh.material.uniforms.get(variableName);
                unif.uploadData();
            }
            
            // actually draw the vertex data using shader program
            glDrawArrays(mesh.material.drawStyle, 0, mesh.geometry.vertexCount);
        }
    }
}
