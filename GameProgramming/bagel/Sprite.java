package bagel;


/**
 * Represents the game entities displayed on the screen;
 *  can move and interact with each other.
 */
public class Sprite
{
    public Vector position;
    
    public Rectangle size;
    
    public Texture texture;
    
    
    /**
     * Sprite Constructor; initializes to position (0,0).
     *
     */
    public Sprite()
    {
        position = new Vector(0,0);
        size = new Rectangle();
        // make rectangle share same position data as this sprite
        size.position = position;
    }
    
    /**
     * Sprite Constructor; initializes position to (x,y).
     *
     * @param x x coordinate of top-left corner
     * @param y y coordinate of top-left corner
     */
    public Sprite(double x, double y)
    {
        position = new Vector(x,y);
        size = new Rectangle();
        // make rectangle share same position data as this sprite
        size.position = position;
    }
    
    /**
     * Update the position of this sprite. 
     * Automatically updates position of rectangle bounds.
     *
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void setPosition(double x, double y)
    {
        position.setValues(x,y);
    }
    
    /**
     * Update the size of this sprite. Used for collision detection.
     * By default, size of sprite is size of texture image region.
     *
     * @param width size along x direction
     * @param height size along y direction
     */
    public void setSize(double width, double height)
    {
        size.setSize(width, height);
    }
    
    /**
     * Set the texture data used for drawing this sprite in the game
     *
     * @param tex previously created texture object, containing an image
     */
    public void setTexture(Texture tex)
    {
        texture = tex;
        // by default, set rectangle size to image size
        size.setSize( tex.region.width, tex.region.height );
    }
}
