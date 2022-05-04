import bagel.*;
import javafx.scene.paint.Color;

public class Adventure extends Game
{
    public Tilemap map;
    public Sprite player;

    public Animation playerNorth;
    public Animation playerSouth;
    public Animation playerEast;
    public Animation playerWest;

    public int health; 
    public Label healthLabel;

    // enemies

    // not affected by solids; follow player
    public Sprite flyingTracker;
    // affected by solids; follow player
    public Sprite groundTracker;
    // not affected by solids; move randomly
    public Sprite flyingRandom;

    // store current player direction angle
    //  for use in shooting arrows.
    
    public int playerDirection;

    public void initialize()
    {
        setScreenSize(960 + 15, 960 + 25);

        map = new Tilemap("images/adventure-tileset.png", 5, 8);

        // f = flyingRandom enemy location
        // g = groundTracker enemy location
        // R = rocks (solid; provide protection from tracking enemies)
        
        String[] mapDataRows = {"ABBBBBBBBBBBBBBBBBBC",
                "D.:............g...F",
                "D....;.............F",
                "Dg.ABBBBBC..f...;..F",
                "D..D.....F.........F",
                "D.,D.....M.........F",
                "D..D..P:......R....F",
                "D..D.....J....:....F",
                "D:.D...,.F.........F",
                "D..GHK.LHI.........F",
                "D......:....f..R;..F",
                "D.,........,.....,.F",
                "D..f....R........f.F",
                "D..............,...F",
                "D.R...,.....f......F",
                "D..........,R......F",
                "D..:g...........g..F",
                "D..............;...F",
                "D..................F",
                "GHHHHHHHHHHHHHHHHHHI" };

        String[] mapSymbolArray = { ".", ",", ":", ";", 
                                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "P", 
                                      "J", "K", "L", "M", "f", "g", "R" };
        int[]    regionIndexArray = { 19, 25, 26, 32, 
                                      5, 6, 7, 13, 14, 15, 21, 22, 23, 19, 
                                      36, 37, 38, 39, 19, 19, 19 };

        map.loadMapData( mapDataRows, mapSymbolArray, regionIndexArray );
        map.setTileSize( 48, 48 );

        String[] solidSymbolArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M" };
        map.loadSolidData( solidSymbolArray );

        createGroup( "main" );
        addSpriteToGroup( map, "main" );

        player = new Sprite();
        playerNorth = new Animation("images/player-north.png", 1, 4, 0.2, true);
        playerSouth = new Animation("images/player-south.png", 1, 4, 0.2, true);
        playerEast  = new Animation("images/player-east.png", 1, 4, 0.2, true);
        playerWest  = new Animation("images/player-west.png", 1, 4, 0.2, true);
        player.setAnimation(playerSouth);
        player.setSize(40,40);
        // position
        Vector playerPosition = map.getSymbolPositionList("P").get(0);
        player.setPosition( playerPosition.x, playerPosition.y );
        addSpriteToGroup( player, "main" );

        playerDirection = 90;
        
        // create enemy groups; different groups for different behavior
        createGroup("enemy-ground");
        createGroup("enemy-flying");

        for (Vector position : map.getSymbolPositionList("f"))
        {
            Sprite enemy = new Sprite();
            Animation enemyAnim = new Animation("images/enemy-flyer.png", 1, 4, 0.1, true);
            enemy.setAnimation( enemyAnim );
            enemy.setSize(50, 40);
            enemy.setPosition( position.x, position.y );
            enemy.setPhysics( new Physics(0, 100, 0) );
            enemy.physics.setSpeed(100);
            addSpriteToGroup( enemy, "enemy-flying" );
        }
        
        // health stuff
        health = 5;
        healthLabel = new Label();
        healthLabel.fontColor = Color.WHITE;
        healthLabel.setText( "Health: " + health );
        healthLabel.setPosition( 20, 50 );
        addSpriteToGroup( healthLabel, "main" );
        
        createGroup("arrow");
        /*
        // enemies
        flyingTracker = new Sprite();
        flyingTracker.setTexture( new Texture("images/plane-red.png") );
        flyingTracker.setSize(60, 60);
        flyingTracker.setPosition(800, 100);
        flyingTracker.setPhysics( new Physics(0, 100, 0) );
        flyingTracker.physics.setSpeed(75);
        addSpriteToGroup(flyingTracker, "main");

        groundTracker = new Sprite();
        groundTracker.setTexture( new Texture("images/enemy.png") );
        groundTracker.setSize(48, 48);
        groundTracker.setPosition(100, 100);
        groundTracker.setPhysics( new Physics(0, 50, 0) );
        groundTracker.physics.setSpeed(50);
        addSpriteToGroup(groundTracker, "main");

        flyingRandom = new Sprite();
        flyingRandom.setTexture( new Texture("images/spaceship.png") );
        flyingRandom.setSize(60, 60);
        flyingRandom.setPosition(400, 400);
        flyingRandom.setPhysics( new Physics(0, 100, 0) );
        flyingRandom.physics.setSpeed(100);
        addSpriteToGroup(flyingRandom, "main");

        Action[] actionArray = { Action.delay(1), Action.rotateBy(45, 1) };
        flyingRandom.addAction( Action.sequence( actionArray ) );
        */

    }
    public void update()
    {
        if ( input.isKeyPressing("W") )
        {
            player.setAnimation( playerNorth );
            player.moveBy( 0, -2 );
            playerDirection = 270;
        }
        if ( input.isKeyPressing("S") )
        {
            player.setAnimation( playerSouth );
            player.moveBy( 0,  2 );
            playerDirection = 90;
        }
        if ( input.isKeyPressing("A") )
        {
            player.setAnimation( playerWest );
            player.moveBy( -2, 0 );
            playerDirection = 180;
        }
        if ( input.isKeyPressing("D") )
        {
            player.setAnimation( playerEast );
            player.moveBy( 2, 0 );
            playerDirection = 0;
        }

        player.setSize(40, 40);

        // prevent overlap with solid map tiles and player
        map.preventOverlap( player );
        
        /*
        for (Sprite enemy : getGroupSpriteList("enemy") )
        {
            // also check that player is *not* currently flashing;
            //  in other words: player should have *no* actions currently in process.
            int playerActionCount = player.actionList.size();

            if ( player.overlap(enemy) && playerActionCount == 0 )
            {
                health -= 1;
                healthLabel.setText("Health: " + health);
                player.addAction( Action.flashRepeat(10) );
            }
        }
        */

        /*
        // enemy movement code
        Vector vectorToPlayer = new Vector();
        vectorToPlayer.x = player.position.x - flyingTracker.position.x;
        vectorToPlayer.y = player.position.y - flyingTracker.position.y;
        double angleToPlayer = vectorToPlayer.getAngle();
        flyingTracker.physics.setMotionAngle(angleToPlayer);

        // move ground enemy
        vectorToPlayer.x = player.position.x - groundTracker.position.x;
        vectorToPlayer.y = player.position.y - groundTracker.position.y;
        angleToPlayer = vectorToPlayer.getAngle();
        groundTracker.physics.setMotionAngle(angleToPlayer);
        // make turtle face player
        groundTracker.angle = angleToPlayer;
        // ground enemy can not overlap solid map tiles
        map.preventOverlap(groundTracker);

        // move random flying enemy
        
        flyingRandom.physics.setMotionAngle( flyingRandom.angle );
        if (flyingRandom.actionList.size() == 0)   
        {
            double angle = 360 * Math.random() - 180;
            Action[] actionArray = { Action.delay(1), Action.rotateBy(angle, 0.5) };
            flyingRandom.addAction( Action.sequence( actionArray ) );
        }
        flyingRandom.wrap(975, 985);
        */
       
        for (Sprite enemy : getGroupSpriteList("enemy-flying"))
        {
            if (enemy.actionList.size() == 0)
            {
                double delayTime = Math.random() + 0.5;
                double angle = 360 * Math.random() - 180;
                Action[] actionArray = { Action.delay(delayTime), Action.rotateBy(angle, 0.5) };
                enemy.addAction( Action.sequence( actionArray ) );
            }
            
            // things that need to happen during every frame
            enemy.physics.setMotionAngle(enemy.angle);
            enemy.wrap( 975, 985 );
            
            // check for player damage
            int playerActionCount = player.actionList.size();
            if (player.overlap(enemy) && playerActionCount == 0)
            {
                health -= 1;
                healthLabel.setText("Health: " + health);
                player.addAction( Action.flashRepeat(10) );
            }
        }
       
        if (input.isKeyPressed("SPACE"))
        {
            Sprite arrow = new Sprite();
            arrow.setTexture( new Texture("images/arrow.png") );
            arrow.setSize( 30, 15 );
            arrow.alignToSprite(player);
            arrow.setPhysics( new Physics(0, 300, 0) );
            arrow.physics.setSpeed(300);
            arrow.physics.setMotionAngle(playerDirection);
            arrow.angle = playerDirection;
            // arrows disappear after 2 seconds
            Action[] actionArray = { Action.delay(2), Action.destroy() };
            arrow.addAction( Action.sequence(actionArray) );
            addSpriteToGroup(arrow, "arrow");
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
