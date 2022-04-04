package graphics.core;

import static org.lwjgl.opengl.GL40.*;

/**
 * Store vertex-related data (position, color, ...)
 */
public class Attribute
{
    // "int" | "float" | "vec2" | "vec3" | "vec4"
    public String dataType;

    // array of data to be stored in vertex buffer
    public float[] dataArray;
    
    // reference to buffer that stores data
    public int bufferRef;
    
    public Attribute(String type, float[] array)
    {
        dataType = type;
        dataArray = array;
        
        // create a buffer to store data
        bufferRef = glGenBuffers();
        
        // activate buffer; storing Array data
        glBindBuffer( GL_ARRAY_BUFFER, bufferRef );
        
        // transmit data into buffer
        glBufferData( GL_ARRAY_BUFFER, dataArray, GL_STATIC_DRAW );
    }
    
    
    /**
     * associate the data stored in the buffer
     *  to a variable in a GPU program.
     *  ("tell the program to use the data from the buffer as the variable")
     *
     * @param programRef a GPU program reference, previously compiled
     * @param variableName the name of a variable in vertex shader
     */
    public void associateVariable(int programRef, String variableName)
    {
        // locate reference for shader variable where data should be sent
        int variableRef = glGetAttribLocation(programRef, variableName);
        
        // re-activate buffer
        glBindBuffer( GL_ARRAY_BUFFER, bufferRef );
        
        // set up buffer to variable association
        if (dataType.equals("int"))
            glVertexAttribPointer( variableRef, 1, GL_INT, false, 0, 0 );
        else if (dataType.equals("float"))
            glVertexAttribPointer( variableRef, 1, GL_FLOAT, false, 0, 0 );
        else if (dataType.equals("vec2"))
            glVertexAttribPointer( variableRef, 2, GL_FLOAT, false, 0, 0 );
        else if (dataType.equals("vec3"))
            glVertexAttribPointer( variableRef, 3, GL_FLOAT, false, 0, 0 );
        else if (dataType.equals("vec4"))
            glVertexAttribPointer( variableRef, 4, GL_FLOAT, false, 0, 0 );
        else
            throw new RuntimeException("Unknown attribute type: " + dataType);
            
        // activate/enable data flow
        glEnableVertexAttribArray( variableRef );
    }
    
}
