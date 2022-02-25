import bagel.*;

/**
 * Write a game that tests the new group class and methods.
 */
public class TestGroup extends Game
{
    Sprite turtle;
    Sprite win;

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
        turtle.setSize(64, 64);
        addSpriteToGroup( turtle, "main" );

        // more efficient to load each texture once
        Texture starfishTexture = new Texture("starfish.png");
        int starfishCount = 100;
        for (int i = 0; i < starfishCount; i++)
        {
            Sprite starfish = new Sprite();
            starfish.setTexture( starfishTexture );
            starfish.setSize(32, 32);
            double x = (Math.random() * 700) + 50;
            double y = (Math.random() * 500) + 50;
            starfish.setPosition(x,y);
            addSpriteToGroup( starfish, "starfish" );
        }

        win = new Sprite();
        win.setTexture( new Texture("youwin.png") );
        win.setPosition( 200, 200 );
        win.setVisible( false );
        addSpriteToGroup( win, "main" );
    }

    public void update()
    {
        double speed = 5;
        if (input.isKeyPressing("W"))
            turtle.moveBy(0, -speed);
        if (input.isKeyPressing("A"))
            turtle.moveBy(-speed, 0);
        if (input.isKeyPressing("S"))
            turtle.moveBy(0, speed);
        if (input.isKeyPressing("D"))
            turtle.moveBy(speed, 0);

            
        for (Sprite starfish : getGroupSpriteList("starfish"))
        {
            if ( turtle.overlap(starfish) )
            {
                removeSpriteFromGroup(starfish, "starfish");
            }
        }

        // we win the game when there are no starfish left
        if ( getGroupSpriteCount("starfish") == 0 )
        {
            win.setVisible( true );
        }
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




