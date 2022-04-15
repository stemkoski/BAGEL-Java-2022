import bagel.*;

public class BrickBreaker extends Game
{
    public Sprite paddle;
    public Sprite ball;
    public String[] itemNames;
    
    public void initialize()
    {
        createGroup("main");
        createGroup("wall");
        createGroup("brick");

        // create a group for each kind of item; use file names!!
        createGroup("paddle-expand");
        createGroup("paddle-shrink");
        itemNames = new String[] {"paddle-expand", "paddle-shrink"};
        
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

        if (ball.overlap(paddle))
        {
            ball.bounceAgainst(paddle);
            double currentAngle = ball.physics.getMotionAngle();
            
            // adjust angle depending on part of paddle that is hit.
            double ballCenter = ball.position.x + ball.size.width/2;
            double paddleCenter = paddle.position.x + paddle.size.width/2;
            
            // adjust angle of ball depending on side of paddle hit.
            if (ballCenter < paddleCenter - 20)
                currentAngle -= 15;
            if (ballCenter > paddleCenter + 20)
                currentAngle += 15;
            
            // convert angle to positive value / no change in direction
            if (currentAngle < 0)
                currentAngle += 360;
                
            // if ball moving down & left, change angle to up and left:
            if (currentAngle > 90 && currentAngle < 200)
                currentAngle = 200;
                
            // if ball moving down & right, change angle to up and right:
            if (currentAngle > 0 && currentAngle <= 90)
                currentAngle = 340;
            if (currentAngle > 340 && currentAngle <= 360)
                currentAngle = 340;
       
            double randomAngle = ( Math.random() * 10 ) - 5;
 
            ball.physics.setMotionAngle( currentAngle + randomAngle ); 
        }
        
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
                
                double itemFrequency = 0.50;
                // when powerups spawn
                if (Math.random() < itemFrequency)
                {
                    // get a random item name
                    int index = (int)Math.floor(Math.random() * itemNames.length);
                    String itemName = itemNames[index];
                    // create item Sprite
                    Sprite item = new Sprite();
                    item.setTexture( new Texture("images/" + itemName + ".png") );
                    item.setSize(48,48);
                    item.alignToSprite(brick);
                    addSpriteToGroup(item, itemName);
                    // make item fall down
                    item.setPhysics( new Physics(0,100,0) );
                    item.physics.setSpeed(100);
                    item.physics.setMotionAngle(90);
                }
            }
        }

        // item collect by paddle
        for (Sprite item : getGroupSpriteList("paddle-expand"))
        {
            if (paddle.overlap(item))
            {
                // destroy because it is collected
                item.destroy();
                // effect of item
                paddle.size.width *= 1.20; // 20% bigger
            }
        }

        for (Sprite item : getGroupSpriteList("paddle-shrink"))
        {
            if (paddle.overlap(item))
            {
                // destroy because it is collected
                item.destroy();
                // effect of item
                paddle.size.width *= 0.80; // 20% smaller
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
