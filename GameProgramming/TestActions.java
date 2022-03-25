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
        
        /*
         
        // by default: all Actions added to a Sprite are applied at the same time
        starfish.addAction( Action.rotateBy(3600, 10) );
        starfish.addAction( Action.moveBy(200, 100, 5) );

        */
       
        /*
        // test sequence of actions:
        Action[] actionArray = { Action.moveBy(100,0, 2),  Action.moveBy(0,100, 2),
                                 Action.moveBy(-100,0, 2), Action.moveBy(0,-100, 2) };
                                 
        starfish.addAction( Action.sequence( actionArray ) );
        */
       
        // test repeat action
        // starfish.addAction( Action.repeat( Action.rotateBy(360,1), 10000 ) );
       
        
        // test repeat a sequence of actions
        Action[] actionArray = { Action.moveBy(100,0, 1),  Action.rotateBy(360, 1),
                                 Action.moveBy(-100,0, 1), Action.rotateBy(360, 1) };
                                 
        starfish.addAction( Action.repeat( Action.sequence(actionArray), 10000 ) );
       
        addSpriteToGroup(starfish, "main");
        
        
        // add the turtle
        Sprite turtle = new Sprite();
        turtle.setTexture( new Texture("turtle.png") );
        turtle.setSize(64,64);
        turtle.setPosition(400, 100);
        addSpriteToGroup( turtle, "main" );
        
        Action[] turtleActions = { Action.moveBy(200,0, 3),
                                   Action.rotateBy(180, 1),
                                   Action.moveBy(-200,0, 3) };

        turtle.addAction( Action.sequence(turtleActions) );
        
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
