package graphics.core;

import java.nio.file.*;
// use method names without referring to class
import static org.lwjgl.opengl.GL40.*;
  
/**
 * methods to load, send, compile, link, check shader programs
 *
 * @author Stemkoski
 * @version 1
 */
public class OpenGLUtils
{
   /**
    * Load text file with shader code, and return a String with that code.
    *
    * @param fileName the shader file name
    * @return a string containing file contents
    */
   public static String readFile(String fileName)
   {      
       String text = "";
       try
       {
           text = new String(
                    Files.readAllBytes(
                      Paths.get(fileName)
                    )
                  );
       }
       catch (Exception ex)
       {
           ex.printStackTrace();
       }
       
       return text;
   }

   /**
    * Create a shader object on the GPU, send shader code to object, and compile.
    *
    * @param shaderCode contains all shader code as a string
    * @param shaderType integer specifies vertex or fragment shader
    * @return index representing shader program location on GPU
    */
   public static int initializeShader(String shaderCode, int shaderType)
   {
       // create a shader object on GPU
       int shaderRef = glCreateShader(shaderType);
       
       // force particular version of OpenGL to be used when compiling
       shaderCode = "#version 330 \n " + shaderCode;
       
       // send source code to shader
       glShaderSource(shaderRef, shaderCode);
       
       // compile shader code
       glCompileShader(shaderRef);
       
       // store error codes
       int[] status = new int[1];
       
       // check for errors; status is used as pass-by-reference
       glGetShaderiv(shaderRef, GL_COMPILE_STATUS, status);
       
       if (status[0] == GL_FALSE)
       {
           // retrieve error message
           String errorMessage = glGetShaderInfoLog(shaderRef);
           
           // quit java program, display error message
           throw new RuntimeException(errorMessage);
       }
       
       return shaderRef;
   }
   
   public static int initializeProgram(
      String vertexShaderFileName,
      String fragmentShaderFileName )
   {
       // load shader code
       String vertexShaderCode   = readFile(vertexShaderFileName);
       String fragmentShaderCode = readFile(fragmentShaderFileName);
       
       // load and compile shaders
       int vertexShaderRef = 
               initializeShader(vertexShaderCode, GL_VERTEX_SHADER);
       int fragmentShaderRef = 
               initializeShader(fragmentShaderCode, GL_FRAGMENT_SHADER);
               
       // create GPU program object to store shaders
       int programRef = glCreateProgram();
       
       // put shaders into program object
       glAttachShader(programRef, vertexShaderRef);
       glAttachShader(programRef, fragmentShaderRef);
       
       // link shader programs, so that data from vertex shader
       //   is sent to the fragment shader
       glLinkProgram(programRef);
       
       // TODO: error checking
       
       // success~
       return programRef;
   }
   
   
   
   
   
   
   
}
