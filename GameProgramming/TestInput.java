import bagel.*;
import java.util.ArrayList;

public class TestInput extends Game
{
    public Sprite turtle;
    public Sprite star;
    public Sprite youwin;
    
    public ArrayList<Sprite> starfishList;
    
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
        
        starfishList = new ArrayList<Sprite>();
        int starCount = 100;
        for (int i = 0; i < starCount; i++)
        {
            Sprite starfish = new Sprite();
            starfish.setTexture( new Texture("starfish.png") );
            double x = Math.random() * 800;
            double y = Math.random() * 600;
            starfish.setPosition(x, y);
            starfishList.add(starfish);
            spriteList.add(starfish);
        }
        
        turtle = new Sprite();
        turtle.setTexture( new Texture("turtle.png") );
        turtle.setPosition(0,0);
        spriteList.add( turtle );
        
        youwin = new Sprite();
        youwin.setTexture( new Texture("youwin.png") );
        youwin.setPosition(200, 250);
        // this message should not be visible when the game starts
        youwin.setVisible( false );
        spriteList.add( youwin );
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
            star.setVisible( false );
            youwin.setVisible( true );
        }
        
        System.out.println( "Starfish left: " + starfishList.size() );
        
        for (Sprite s : starfishList)
        {
            if (turtle.overlap(s))
                s.setVisible(false);
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
