package graphics.core;

import java.util.ArrayList;
import static org.lwjgl.glfw.GLFW.*;


// process keyboard input
public class Input
{
    // computer processes input events (key press, key release)
    //   independently from main program loop;
    // save lists of names of keys (key codes) that have been pressed
    //   until we are ready to process them in main program.
    
    // keys to process
    public ArrayList<Integer> keyPressQueue;
    public ArrayList<Integer> keyReleaseQueue;
    
    // lists are checked by input methods
    public ArrayList<Integer> keyPressedList;
    public ArrayList<Integer> keyPressingList;
    public ArrayList<Integer> keyReleasedList;
    
    // 
    public Input(long windowReference)
    {
        keyPressQueue = new ArrayList<Integer>();
        keyReleaseQueue = new ArrayList<Integer>();
        
        keyPressedList = new ArrayList<Integer>();
        keyPressingList = new ArrayList<Integer>();
        keyReleasedList = new ArrayList<Integer>();
        
        // specify code to run when a key is pressed/released; "callback"
        //   code will store key data in lists for later processing
        
        // specify code using "lambda expression" - notation for a class containing
        //   a single function. 
        //  ( inputs ) -> { code statements; }
        
        glfwSetKeyCallback( windowReference, 
          (window_, key, scancode, action, mods) ->
          {
              if ( action == GLFW_PRESS )
                  keyPressQueue.add( key );
            
              if ( action == GLFW_RELEASE )
                  keyReleaseQueue.add( key );
          }
        );
    }
    
    // process key queues, add keys to appropriate lists.
    // this method should be called once per frame.
    public void update()
    {
        // every time key states are updated, 
        //  pressed/released events are completed and should be cleared.
        keyPressedList.clear();
        keyReleasedList.clear();
        
        for (Integer key : keyPressQueue)
        {
            keyPressedList.add(key);
            keyPressingList.add(key);
        }
        
        // clear queue when finished
        keyPressQueue.clear();
        
        for (Integer key : keyReleaseQueue)
        {
            keyPressingList.remove(key);
            keyReleasedList.add(key);
        }
        
        // clear queue when finished
        keyReleaseQueue.clear();
        
    }
    
    
    // check for keyboard input
    
    /**
     * True for a single update frame after key was pressed.
     */
    public boolean keyPressed(Integer key)
    {
        return keyPressedList.contains(key);
    }
    
    /**
     * True for the duration between keyPressed and keyReleased;
     *   used for continuous events that happen while a key is being held.
     */
    public boolean keyPressing(Integer key)
    {
        return keyPressingList.contains(key);
    }
    
    /**
     * True for a single update frame after key was released.
     */
    public boolean keyReleased(Integer key)
    {
        return keyReleasedList.contains(key);    
    }
    
}