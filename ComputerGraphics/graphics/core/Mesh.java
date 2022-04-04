package graphics.core;
import graphics.geometry.*;
import graphics.material.*;

import static org.lwjgl.opengl.GL40.*;

/**
 * Group together Geometry and Material objects;
 *  store a VAO that associates vertex buffers (from Geometry) 
 *     with shader variables (from Material)
 */
public class Mesh extends Object3D
{
   public Geometry geometry;
   public Material material;
   
   public int vaoRef;
   
   public Mesh(Geometry geo, Material mat)
   {
       geometry = geo;
       material = mat;
       
       // create a Vertex Array Object to store associations between buffers and shaders
       vaoRef = glGenVertexArrays();
       glBindVertexArray(vaoRef);
       
       // iterate over set of Attribute objects in the geometry,
       // associate them with variables in the shader in the material.
       
       // keySet() returns a list of objects used for indices in a HashMap 
       for (String variableName : geometry.attributes.keySet())
       {
           // retrieve from HashMap
           Attribute attr = geometry.attributes.get(variableName);
           
           // sets up association; automatically stores in VAO.
           attr.associateVariable(material.programRef, variableName);
       }
       
       for (String variableName : material.uniforms.keySet())
       {
           // retrieve from HashMap
           Uniform unif = material.uniforms.get(variableName);
           
           // set up association; automatically stores
           unif.associateVariable(material.programRef, variableName);
       }
       
       
       
       
       
   }
}
