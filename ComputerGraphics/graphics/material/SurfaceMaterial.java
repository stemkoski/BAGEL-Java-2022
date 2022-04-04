package graphics.material;

import static org.lwjgl.opengl.GL40.*;

public class SurfaceMaterial extends Material
{
    public SurfaceMaterial()
    {
        // call constructor of the Material class;
        //  specify the shader programs to use.
        super("shaders/SurfaceMaterial.vert", "shaders/SurfaceMaterial.frag");
        
        float[] defaultColor = {1, 1, 1};
        addUniform("vec3", "color", defaultColor);
        
        // boolean variables in OpenGL are ints: 0 = false, 1 = true.
        addUniform("bool", "useVertexColor", 1);
        
        // ALWAYS STATE WHAT DRAW MODE TO USE
        drawStyle = GL_TRIANGLES;
    }
}