import bagel.*;

public class PlaneDodger extends Game
{
    public Sprite sky;
    public Sprite ground;
    public Sprite player;

    public Texture enemyTexture;
    // keep track of how much time has passed since last enemy created
    public double enemyTimer;
    // keep track of current enemy speed
    public double enemySpeed;
    // keep track of how quickly enemy planes appear
    public double enemySpawnRate;
    
    public void initialize()
    {
        setScreenSize(600,800);

        createGroup("main");
        createGroup("enemy");

        sky = new Sprite();
        sky.setTexture( new Texture("images/sky.png") );
        sky.setPhysics( new Physics(0,25,0) );
        sky.physics.setSpeed(25);
        sky.physics.setMotionAngle(180);
        addSpriteToGroup(sky, "main");

        ground = new Sprite();
        ground.setTexture( new Texture("images/ground.png") );
        ground.setPhysics( new Physics(0,80,0) );
        ground.physics.setSpeed(80);
        ground.physics.setMotionAngle(180);
        ground.setPosition(0, 700);
        addSpriteToGroup(ground, "main");
        
        player = new Sprite();
        player.setTexture( new Texture("images/plane-green.png") );
        player.setPosition( 50, 100 );
        player.setPhysics( new Physics(400, 200, 0) );
        addSpriteToGroup(player, "main");
        
        enemyTexture = new Texture("images/plane-red.png");
        enemyTimer = 0;
        enemySpeed = 120;
        enemySpawnRate = 1.0;
    }

    public void update()
    {
        if (sky.position.x < -600)
            sky.position.x += 600;

        if (ground.position.x < -600)
            ground.position.x += 600;
            
        // apply gravity to the plane
        player.physics.accelerateAtAngle(90);
        
        // player can boost plane in upwards direction
        if ( input.isKeyPressed("SPACE") )
        {
            player.physics.setSpeed(200);
            player.physics.setMotionAngle(270);
        }
        
        // time passes:
        enemyTimer += 1.0 / 60.0;
        
        if (enemyTimer >= enemySpawnRate)
        {
            // create a new enemy at a random position
            Sprite enemy = new Sprite();
            enemy.setTexture( enemyTexture );
            // appear at random y coordinate
            double y = Math.random() * 600;
            enemy.setPosition( 600, y );
            enemy.setPhysics( new Physics(0,800,0) );
            // increase the base speed for new enemies
            enemySpeed += 10;
            enemy.physics.setSpeed(enemySpeed);
            enemy.physics.setMotionAngle(180);
            addSpriteToGroup(enemy, "enemy");
            // reset the timer
            enemyTimer = 0;
            // make enemies spawn more quickly;
            //  reduce time until next enemy spawns
            enemySpawnRate -= 0.01;
            // do not let spawn rate get too fast
            if (enemySpawnRate < 0.25)
                enemySpawnRate = 0.25;
                
            // TODO: destroy enemies off screen to reduce lag
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
