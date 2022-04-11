import bagel.*;

/**
 * Test solid functions
 */
public class TestSolid extends Game
{
    Sprite turtle;
    Sprite starfish;
    
    public void initialize()
    {
        createGroup("main");
        
        Sprite background = new Sprite();
        background.setTexture( new Texture("images/water.png") );
        addSpriteToGroup(background, "main");
        
        turtle = new Sprite();
        turtle.setTexture( new Texture("images/turtle.png") );
        turtle.setPosition( 100, 100 );
        addSpriteToGroup(turtle, "main");
        
        starfish = new Sprite();
        starfish.setTexture( new Texture("images/starfish.png") );
        starfish.setPosition( 400, 300 );
        addSpriteToGroup( starfish, "main");
        
    }
    
    public void update()
    {
        if ( input.isKeyPressing("A") )
            turtle.moveBy(-1,0);
        if ( input.isKeyPressing("D") )
            turtle.moveBy(1,0);
        if ( input.isKeyPressing("W") )
            turtle.moveBy(0, -1);
        if ( input.isKeyPressing("S") )
            turtle.moveBy(0, 1);
            
        // make the starfish "solid"
        // turtle.preventOverlap(starfish);
        
        // make the turtle "solid" -- so the starfish moves!
        starfish.preventOverlap(turtle);
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
