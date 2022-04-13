import bagel.*;

public class BrickBreaker extends Game
{
    public Sprite paddle;
    public Sprite ball;
    
    public void initialize()
    {
        createGroup("main");
        createGroup("wall");
        createGroup("brick");
        
        Sprite background = new Sprite();
        background.setTexture( new Texture("images/background.png") );
        background.setSize(800, 600);
        background.setPosition(0, 0);
        addSpriteToGroup(background, "main");
        
        paddle = new Sprite();
        paddle.setTexture( new Texture("images/paddle.png") );
        paddle.setPosition(350, 550);
        addSpriteToGroup(paddle, "main");
        
        ball = new Sprite();
        ball.setTexture( new Texture("images/ball.png") );
        ball.setPosition(400, 525);
        
        // ball movement
        ball.setPhysics( new Physics(0, 800, 0) );
        ball.physics.setSpeed(300);
        ball.physics.setMotionAngle(-45);
        
        addSpriteToGroup(ball, "main");
        
        Texture wallTexture = new Texture("images/wall.png");

        Sprite wallLeft = new Sprite();
        wallLeft.setTexture( wallTexture );
        wallLeft.setPosition(0,0);
        wallLeft.setSize(25, 600);
        addSpriteToGroup(wallLeft, "wall");
        
        Sprite wallRight = new Sprite();
        wallRight.setTexture( wallTexture );
        wallRight.setPosition(775, 0);
        wallRight.setSize(25, 600);
        addSpriteToGroup(wallRight, "wall");
        
        Sprite wallTop = new Sprite();
        wallTop.setTexture( wallTexture );
        wallTop.setPosition(0, 0);
        wallTop.setSize(800, 25);
        addSpriteToGroup(wallTop, "wall");
        
        // add bricks
        int rows = 6;
        int columns = 9;
        Texture brickTexture = new Texture("images/brick.png");
        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                Sprite brick = new Sprite();
                brick.setTexture( brickTexture );
                double x = 100 + 68 * i;
                double y = 100 + 32 * j;
                brick.setPosition(x, y);
                addSpriteToGroup(brick, "brick");
            }
        }
        
        
    }
    
    public void update()
    {
        if ( input.isKeyPressing("A") )
        {
            paddle.moveBy(-10, 0);
        }
        
        if ( input.isKeyPressing("D") )
        {
            paddle.moveBy( 10, 0 );
        }
        
        // TODO: keep paddle on screen
        
        ball.bounceAgainst(paddle);
                
        for (Sprite wall : getGroupSpriteList("wall"))
        {
            // prevents overlap, and changes angle of motion when needed.
            ball.bounceAgainst(wall);
        }
        
        for (Sprite brick : getGroupSpriteList("brick"))
        {
            if (ball.overlap(brick))
            {
                ball.bounceAgainst(brick);
                brick.destroy();
            }
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
