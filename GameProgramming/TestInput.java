import bagel.*;

public class TestInput extends Game
{
    public Sprite turtle;
    public Sprite star;
    
    public void initialize()
    {
        Sprite water = new Sprite();
        water.setTexture( new Texture("water.png") );
        water.setPosition(0,0);
        spriteList.add(water);
        
        star = new Sprite();
        star.setTexture( new Texture("starfish.png") );
        star.setPosition( 400, 300 );
        spriteList.add(star);
        
        turtle = new Sprite();
        turtle.setTexture( new Texture("turtle.png") );
        turtle.setPosition(0,0);
        spriteList.add( turtle );
    }
    
    public void update()
    {
        if ( input.isKeyPressing("W") ) // move up
            turtle.moveBy(0, -1);
        if ( input.isKeyPressing("A") ) // move left
            turtle.moveBy(-1, 0);
        if ( input.isKeyPressing("S") ) // move down
            turtle.moveBy(0, 1);
        if ( input.isKeyPressing("D") ) // move right
            turtle.moveBy(1, 0);
            
        // if turtle overlaps star, you win the game!
        if ( turtle.overlap(star) )
        {
            System.out.println("You Win!");
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
