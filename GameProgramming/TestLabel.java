import bagel.*;

/**
 * Write a game that tests the new Label class.
 */
public class TestLabel extends Game
{
    Sprite turtle;
    Sprite win;

    Label scoreLabel;
    int score;
    
    Label timeLabel;
    double time; // time is seconds
    
    Label gameOverLabel;
    
    Label winLabel;
    
    // two groups to store sprites:
    //  "main": background image, turtle, etc.
    //  "starfish": put all starfish here
    public void initialize()
    {
        createGroup("main");
        createGroup("starfish");
        // add labels to this group so they are drawn last and appear on everything else
        createGroup("labels");

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
        int starfishCount = 1000;
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


        score = 0;
        
        scoreLabel = new Label();
        scoreLabel.setText("Score: " + score);
        scoreLabel.setPosition( 20, 50 );
        scoreLabel.setFont("Impact", 48 );
        // draw text in yellow
        scoreLabel.setColor(1.00, 1.00, 0.00);
        addSpriteToGroup( scoreLabel, "labels" );
        
        time = 30;
        
        timeLabel = new Label();
        timeLabel.setText("Time left: " + time);
        timeLabel.setPosition( 20, 580 );
        timeLabel.setFont("Impact", 48);
        timeLabel.setColor(0.00, 1.00, 0.00);
        addSpriteToGroup( timeLabel, "labels" );
        
        gameOverLabel = new Label();
        gameOverLabel.setText("Game Over");
        gameOverLabel.setPosition( 250, 300 );
        gameOverLabel.setFont("Impact", 80);
        gameOverLabel.setColor(0.80, 0.80, 0.80);
        gameOverLabel.setVisible(false);
        addSpriteToGroup( gameOverLabel, "labels" );
        
        winLabel = new Label();
        winLabel.setText("You Win!!!");
        winLabel.setPosition( 250, 300);
        winLabel.setFont("Impact", 80);
        winLabel.setColor(0.80, 0.80, 0.80);
        winLabel.setVisible(false);
        addSpriteToGroup(winLabel, "labels");
    }

    public void update()
    {
        // if the win message or the game over message is visible,
        //  then we need to stop everything from moving; stop user input
        
        if ( winLabel.visible == true || gameOverLabel.visible == true )
        {
            // exit this method immediately
            return;
        }
        
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
                // earn points for collecting starfish
                score += 100;
                scoreLabel.setText( "Score: " + score );
            }
        }

        // we win the game when there are no starfish left
        if ( getGroupSpriteCount("starfish") == 0 )
        {
            winLabel.setVisible( true );
        }
        
        time -= 1.0 / 60.0; 
        timeLabel.setText("Time left: " + Math.round(time) );
        
        // lose the game if time reaches 0.0
        if (time <= 0.0) 
        {
            gameOverLabel.setVisible(true);
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




