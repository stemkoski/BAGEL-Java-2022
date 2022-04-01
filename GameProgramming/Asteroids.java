import bagel.*;

public class Asteroids extends Game
{
    // need to use Spaceship in all methods
    public Sprite spaceship;
    public Sprite rocketfire;
    
    // use a shared image for lasers and also asteroids
    public Texture laserTexture;
    public Texture asteroidTexture;
    
    // a useful function for producing random numbers between min and max
    public double random(double min, double max)
    {
        double range = max - min;
        return min + Math.random() * range;
    }
    
    public void initialize()
    {
        createGroup("main");
        createGroup("laser");
        createGroup("asteroid");

        Sprite background = new Sprite();
        background.setTexture( new Texture("images/space.png") );
        background.setSize(800, 600);
        background.setPosition(0, 0);
        addSpriteToGroup(background, "main");

        // create rocketfire effect,
        //  must add to same group as spaceship, but before spaceship, to appear underneath it.
        rocketfire = new Sprite();
        rocketfire.setTexture( new Texture( "images/fire.png" ) );
        rocketfire.setSize(128, 16);
        addSpriteToGroup(rocketfire, "main");
        
        spaceship = new Sprite();
        spaceship.setTexture( new Texture("images/spaceship.png") );
        spaceship.setSize(50, 50);
        spaceship.setPosition(200, 300);
        // low deceleration - spaceship will "drift" when not accelerating
        spaceship.setPhysics( new Physics(100, 200, 5) );
        // set up automatic wrap
        spaceship.addAction( Action.wrap(800,600) );
        addSpriteToGroup(spaceship, "main");
        
        
        
        laserTexture = new Texture("images/laser.png");
        
        asteroidTexture = new Texture("images/asteroid.png");
        
        int asteroidCount = 8;
        for (int i = 0; i < asteroidCount; i++)
        {
            Sprite asteroid = new Sprite();
            asteroid.setTexture( asteroidTexture );
            asteroid.setPosition(600, 400);
            asteroid.setSize( random(50,70), random(50,70) );
            asteroid.setPhysics( new Physics(0, 150, 0) );
            asteroid.physics.setSpeed( random(80,150) );
            // want them all moving in different directions
            asteroid.physics.setMotionAngle( random(0,360) );
            asteroid.addAction( Action.wrap(800,600) );
            asteroid.addAction( 
                Action.repeat( Action.rotateBy(random(50,100), 1), 10000 ) 
            );
            
            addSpriteToGroup(asteroid, "asteroid");
        }
    }

    public void update()
    {
        // turn spaceship left with A key
        if (input.isKeyPressing("A"))
            spaceship.rotateBy(-2);

        // turn spaceship right with D key
        if (input.isKeyPressing("D"))
            spaceship.rotateBy(2);
            
        rocketfire.alignToSprite(spaceship);
            
        // accelerate spaceship forwards while pressing W key
        if (input.isKeyPressing("W"))
        {
            spaceship.physics.accelerateAtAngle( spaceship.angle );
            rocketfire.setVisible(true);
        }
        else
        {
            rocketfire.setVisible(false);
        }
        
        
        if (input.isKeyPressed("SPACE"))
        {
            Sprite laser = new Sprite();
            laser.setTexture( laserTexture );
            laser.setSize(16, 16);
            
            // aligns center point and angle with spaceship
            laser.alignToSprite(spaceship);
            
            laser.setPhysics( new Physics(0,400,0) );
            laser.physics.setSpeed(400);
            laser.physics.setMotionAngle( spaceship.angle );
            // automatically call wrap function
            laser.addAction( Action.wrap(800,600) );
            
            // lasers will destroy themselves after 2 seconds have passed
            Action[] actionArray = { Action.delay(2), Action.destroy() };
            laser.addAction( Action.sequence(actionArray) );
            
            addSpriteToGroup(laser, "laser");
        }
        
        // handle laser-rock overlap
        for (Sprite laser : getGroupSpriteList("laser"))
        {
            for (Sprite asteroid : getGroupSpriteList("asteroid"))
            {
                if (laser.overlap(asteroid))
                {
                    laser.destroy();
                    asteroid.destroy();
                    // TODO: add explosion animation
                    // TODO: add to score and update Label to display score.
                }
            }
        }
       
        // TODO: check if any rock overlaps spaceship:
        //   if so, destroy spaceship, create explosion, game over message visible
        
        // TODO: if 0 asteroids left, win message visible
        
        
        
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
