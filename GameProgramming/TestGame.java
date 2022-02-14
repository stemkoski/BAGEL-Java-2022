import bagel.*;

// extend: this class contains all fields and methods of the Game class
//  (and we can define more)
public class TestGame extends Game
{ 
    public TestGame()
    {
        
    }
    // note: constructor of this class
    //   is *never* called!
    
    Sprite turtle;
    
    // implementing method from Game class.
    public void initialize()
    {        
        Sprite water = new Sprite();
        water.setTexture( new Texture("water.png") );
        water.setPosition( 0, 0 );
        water.setSize( 800, 600 );        
        spriteList.add( water );
        
        turtle = new Sprite();
        turtle.setTexture( new Texture("turtle.png") );
        turtle.setPosition( 400, 300 );
        turtle.setSize( 64, 64 );        
        spriteList.add( turtle );

    }
    
    public void update()
    {
        turtle.moveBy(2,1);
    }
    
    public static void main(String[] args)
    {
        try
        {
            launch(args);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();        
        }
        finally
        {
            System.exit(0);
        }
    }
    
    
    
    
}