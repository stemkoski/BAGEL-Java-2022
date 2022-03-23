import bagel.*;

/**
 * Test the Action class.
 */
public class TestActions extends Game
{
    public void initialize()
    {
        createGroup("main");
        
        Sprite water = new Sprite();
        water.setTexture( new Texture("water.png") );
        water.setSize(800,600);
        water.setPosition(0,0);
        addSpriteToGroup(water, "main");
        
        Sprite starfish = new Sprite();
        starfish.setTexture( new Texture("starfish.png") );
        starfish.setSize(48, 48);
        starfish.setPosition(400, 300);
        
        starfish.addAction( Action.rotateBy(360, 4) );
        
        addSpriteToGroup(starfish, "main");
        
    }
    
    public void update()
    {
        
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
