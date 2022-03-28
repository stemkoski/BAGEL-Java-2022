import bagel.*;

public class Asteroids extends Game
{
    // need to use Spaceship in all methods
    public Sprite spaceship;
    
    // use a shared image for lasers
    public Texture laserTexture;
    
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
        spaceship.setPosition(400, 300);
        // low deceleration - spaceship will "drift" when not accelerating
        spaceship.setPhysics( new Physics(100, 200, 5) );
        addSpriteToGroup(spaceship, "main");
        
        laserTexture = new Texture("images/laser.png");
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
            
        // wrap around screen
        spaceship.wrap(800, 600);
        
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
            addSpriteToGroup(laser, "laser");
        }
        
        // make sure lasers wrap
        for (Sprite laser : getGroupSpriteList("laser"))
        {
            laser.wrap(800, 600);
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
