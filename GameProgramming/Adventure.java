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
    public Sprite winMessage;
    public Sprite loseMessage;
    
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
                "Dg:..f.........g...F",
                "D....;.............F",
                "D...R.......f...;..F",
                "D......:...........F",
                "D.,.......,,.....f.F",
                "D.f....:......R....F",
                "D....;........:....F",
                "D:.....,...........F",
                "D.........P........F",
                "D.....R:....f..R;..F",
                "D.,........,.....,.F",
                "D..f....R........f.F",
                "D..............,...F",
                "D.R...,.....f.....gF",
                "D..........,R......F",
                "D..:...............F",
                "D........f.....;...F",
                "Dg..f............g.F",
                "GHHHHHHHHHHHHHHHHHHI" };

        String[] mapSymbolArray = { ".", ",", ":", ";", 
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "P", 
                "J", "K", "L", "M", "f", "g", "R" };
        int[]    regionIndexArray = { 19, 25, 26, 32, 
                5, 6, 7, 13, 14, 15, 21, 22, 23, 19, 
                36, 37, 38, 39, 19, 19, 19 };

        map.loadMapData( mapDataRows, mapSymbolArray, regionIndexArray );
        map.setTileSize( 48, 48 );

        String[] solidSymbolArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", 
                                      "J", "K", "L", "M" };
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

        createGroup("rock");
        for (Vector position : map.getSymbolPositionList("R"))
        {
            Sprite rock = new Sprite();
            rock.setTexture( new Texture("images/rock.png") );
            rock.setSize(48, 48);
            rock.setPosition(position.x, position.y);
            addSpriteToGroup(rock, "rock");
        }

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
            enemy.setPhysics( new Physics(0, 200, 0) );
            enemy.physics.setSpeed(100);
            addSpriteToGroup( enemy, "enemy-flying" );
        }
        
        for (Vector position : map.getSymbolPositionList("g"))
        {
            Sprite enemy = new Sprite();
            Animation enemyAnim = new Animation("images/enemy-seeker.png", 1, 4, 0.1, true);
            enemy.setAnimation( enemyAnim );
            enemy.setSize(40, 40);
            enemy.setPosition(position.x, position.y);
            enemy.setPhysics( new Physics(0, 110, 0) );
            enemy.physics.setSpeed(110);
            addSpriteToGroup( enemy, "enemy-ground" );
        }
        

        

        createGroup("arrow");
    
        // used for item (gain 1 health)
        createGroup("heart");

        
        // used for messages
        createGroup("label");
        
        // health stuff
        health = 5;
        healthLabel = new Label();
        healthLabel.fontColor = Color.WHITE;
        healthLabel.setText( "Health: " + health );
        healthLabel.setPosition( 20, 50 );
        addSpriteToGroup( healthLabel, "label" );
        
        winMessage = new Sprite();
        winMessage.setTexture( new Texture("images/message-win.png") );
        winMessage.setPosition(200, 400);
        winMessage.visible = false;
        addSpriteToGroup( winMessage, "label");
        
        loseMessage = new Sprite();
        loseMessage.setTexture( new Texture("images/message-lose.png") );
        loseMessage.setPosition(260, 400);
        loseMessage.visible = false;
        addSpriteToGroup( loseMessage, "label");
        
    }

    public void update()
    {
        if (winMessage.visible == true || loseMessage.visible == true)
            return;
        
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

        // prevent player from overlapping rocks
        for (Sprite rock : getGroupSpriteList("rock"))
        {
            if (player.overlap(rock))
            {
                player.preventOverlap(rock);
            }
        }

        for (Sprite enemy : getGroupSpriteList("enemy-ground"))
        {
            Vector direction = new Vector();
            direction.x = player.position.x - enemy.position.x;
            direction.y = player.position.y - enemy.position.y;
            double angle = direction.getAngle();
            // enemy faces player
            enemy.angle = angle;
            // enemy moves towards player
            enemy.physics.setMotionAngle(angle);
            
            // block movement through solids
            map.preventOverlap(enemy);
            // also check rocks
            for (Sprite rock : getGroupSpriteList("rock"))
            {
                enemy.preventOverlap(rock);
            }
            // prevent enemies from overlapping with each other
            for (Sprite enemy2 : getGroupSpriteList("enemy-ground"))
            {
                // check that enemies are different!
                if ( !enemy.equals(enemy2) )
                {
                    enemy.preventOverlap(enemy2);
                }
            }
            
            // check for player damage
            int playerActionCount = player.actionList.size();
            if (player.overlap(enemy) && playerActionCount == 0)
            {
                health -= 1;
                healthLabel.setText("Health: " + health);
                player.addAction( Action.flashRepeat(10) );
            }
        }
        
        for (Sprite enemy : getGroupSpriteList("enemy-flying"))
        {
            if (enemy.actionList.size() == 0)
            {
                double delayTime = Math.random() + 0.5;
                double angle = 360 * Math.random() - 180;
                Action[] actionArray = { Action.delay(delayTime), Action.rotateBy(angle, 0.5) };
                enemy.addAction( Action.sequence( actionArray ) );

                // also change enemy speed
                double speed = 100 + (100 * Math.random());
                enemy.physics.setSpeed(speed);
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

        // limit arrows to N at a time.
        int arrowCount = getGroupSpriteCount("arrow");
        if (input.isKeyPressed("SPACE") && arrowCount < 1)
        {
            Sprite arrow = new Sprite();
            arrow.setTexture( new Texture("images/arrow.png") );
            arrow.setSize( 40, 20 );
            arrow.alignToSprite(player);
            arrow.setPhysics( new Physics(0, 500, 0) );
            arrow.physics.setSpeed(500);
            arrow.physics.setMotionAngle(playerDirection);
            arrow.angle = playerDirection;
            // arrows disappear after 2 seconds
            Action[] actionArray = { Action.delay(2), Action.destroy() };
            arrow.addAction( Action.sequence(actionArray) );
            addSpriteToGroup(arrow, "arrow");
        }

        // destroy enemies:
        for ( Sprite arrow : getGroupSpriteList("arrow") )
        {
            // check for overlap with each type of enemy
            for ( Sprite enemy : getGroupSpriteList("enemy-flying") )
            {
                if (arrow.overlap(enemy))
                {
                    enemy.destroy();
                    // do not destroy arrow, so it flies through and can hit multiple enemies

                    // explosion
                    Sprite explosion = new Sprite();
                    Animation explosionAnim = 
                        new Animation("images/explosion.png", 6, 6, 0.01, false);
                    explosion.setAnimation(explosionAnim);
                    explosion.setSize(100,100);
                    explosion.alignToSprite(enemy);
                    addSpriteToGroup(explosion, "main");

                    // random item (health) drop
                    if (Math.random() < 0.25)
                    {
                        Sprite heart = new Sprite();
                        heart.setTexture( new Texture("images/heart.png") );
                        heart.setSize(50,50);
                        heart.alignToSprite(enemy);
                        heart.angle = 0;
                        addSpriteToGroup(heart, "heart");
                    }
                }
            }
            
            for ( Sprite enemy : getGroupSpriteList("enemy-ground") )
            {
                if (arrow.overlap(enemy))
                {
                    enemy.destroy();
                    // do not destroy arrow, so it flies through and can hit multiple enemies

                    // explosion
                    Sprite explosion = new Sprite();
                    Animation explosionAnim = 
                        new Animation("images/explosion.png", 6, 6, 0.01, false);
                    explosion.setAnimation(explosionAnim);
                    explosion.setSize(100,100);
                    explosion.alignToSprite(enemy);
                    addSpriteToGroup(explosion, "main");

                    // random item (health) drop
                    if (Math.random() < 0.25)
                    {
                        Sprite heart = new Sprite();
                        heart.setTexture( new Texture("images/heart.png") );
                        heart.setSize(50,50);
                        heart.alignToSprite(enemy);
                        heart.angle = 0;
                        addSpriteToGroup(heart, "heart");
                    }
                }
            }
        }

        for (Sprite heart : getGroupSpriteList("heart"))
        {
            if (player.overlap(heart))
            {
                heart.destroy();
                health += 1;
                healthLabel.setText("Health: " + health);
            }
        }

        // win condition: no more enemies of either type left.
        int flyingEnemyCount = getGroupSpriteCount("enemy-flying");
        int groundEnemyCount = getGroupSpriteCount("enemy-ground");
        if (flyingEnemyCount == 0 && groundEnemyCount == 0)
            winMessage.visible = true;
       
        // lose condition: health equals 0
        if (health == 0)
        {
            player.destroy();
            loseMessage.visible = true;
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
