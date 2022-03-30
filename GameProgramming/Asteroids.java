import bagel.*;

public class Asteroids extends Game
{
    // need to use Spaceship in all methods
    public Sprite spaceship;
    
    // use a shared image for lasers and also asteroids
    public Texture laserTexture;
    public Texture asteroidTexture;
    
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
        
        int asteroidCount = 6;
        for (int i = 0; i < asteroidCount; i++)
        {
            Sprite asteroid = new Sprite();
            asteroid.setTexture( asteroidTexture );
            asteroid.setPosition(600, 400);
            asteroid.setSize(64,64);
            asteroid.setPhysics( new Physics(0, 80, 0) );
            asteroid.physics.setSpeed( 80 );
            // want them all moving in different directions
            asteroid.physics.setMotionAngle( Math.random() * 360 );
            asteroid.addAction( Action.wrap(800,600) );
            
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
            
        // accelerate spaceship forwards while pressing W key
        if (input.isKeyPressing("W"))
            spaceship.physics.accelerateAtAngle( spaceship.angle );
            
        if (input.isKeyPressed("SPACE"))
        {
            Sprite laser = new Sprite();
            laser.setTexture( laserTexture );
            laser.setSize(16, 16);
            laser.setPosition( spaceship.position.x + spaceship.size.width/2  - laser.size.width/2, 
                               spaceship.position.y + spaceship.size.height/2 - laser.size.height/2);
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
                }
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
