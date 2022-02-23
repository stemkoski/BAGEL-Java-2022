import bagel.*;

/**
 * Write a game that tests the new group class and methods.
 */
public class TestGroup extends Game
{
    Sprite turtle;
    
    // two groups to store sprites:
    //  "main": background image, turtle, etc.
    //  "starfish": put all starfish here
    public void initialize()
    {
        createGroup("main");
        createGroup("starfish");
        
        Sprite water = new Sprite();
        water.setTexture( new Texture("water.png") );
        water.setPosition( 0,0 );
        water.setSize( 800,600 );
        addSpriteToGroup( water, "main" );
        
        turtle = new Sprite();
        turtle.setTexture( new Texture("turtle.png") );
        turtle.setPosition( 400,300 );
        addSpriteToGroup( turtle, "main" );
        
        // more efficient to load each texture once
        Texture starfishTexture = new Texture("starfish.png");
        int starfishCount = 100;
        for (int i = 0; i < starfishCount; i++)
        {
            Sprite starfish = new Sprite();
            starfish.setTexture( starfishTexture );
            double x = Math.random() * 800;
            double y = Math.random() * 600;
            starfish.setPosition(x,y);
            addSpriteToGroup( starfish, "starfish" );
        }
    }
    
    public void update()
    {
        if (input.isKeyPressing("W"))
            turtle.moveBy(0, -1);
        if (input.isKeyPressing("A"))
            turtle.moveBy(-1, 0);
        if (input.isKeyPressing("S"))
            turtle.moveBy(0, 1);
        if (input.isKeyPressing("D"))
            turtle.moveBy(1, 0);
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







