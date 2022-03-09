package bagel;

import javafx.scene.canvas.*;

/**
 * Represents the game entities displayed on the screen;
 *  can move and interact with each other.
 */
public class Sprite
{
    public Vector position;

    public Rectangle size;

    public Texture texture;

    public boolean visible;
    
    public Physics physics;

    // angle of rotation of the sprite
    public double angle;
    
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
        // by default, all sprites are visible
        visible = true;
        
        angle = 0;
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
        // by default, all sprites are visible
        visible = true;
        
        angle = 0;
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

    /**
     * Set visibility of this sprite, which determines whether it appears on the screen.
     *
     * @param vis should this sprite be visible?
     */
    public void setVisible(boolean vis)
    {
        visible = vis;
    }

    /**
     * Assign a Physics object to this Sprite.
     * When physics object updates position, Sprite position will be updated too.
     * @param p Physics object
     */
    public void setPhysics(Physics p)
    {
        physics = p;
        
        // link physics object to sprite position vector
        physics.position = this.position;
    }
    
    
    /**
     * Set the angle of rotation of the image of this sprite.
     * @param angleDegrees the angle of rotation
     */
    public void setAngle(double angleDegrees)
    {
        angle = angleDegrees;    
    }

    
    
    /**
     * Draw the image contained in this sprite, 
     *   at the position stored in this sprite,
     *   with the size stored in the rectangle in this sprite.
     *
     * @param context The context object attached to the canvas in the window where the game will appear.
     */
    public void draw(GraphicsContext context)
    {
        if (visible)
        {
            // we store angle in degrees; Math class functions require radians.
            double A = Math.toRadians(angle);
            
            // rotates objects around the center point of the sprite;
            //  also renders objects at center of sprite.
            context.setTransform(  Math.cos(A), Math.sin(A),
                                  -Math.sin(A), Math.cos(A),
                                    position.x + size.width/2, position.y + size.height/2 );
                                            
            context.drawImage( texture.image, -size.width/2, -size.height/2, size.width, size.height ); 
        }
    }

    /**
     * Move the sprite's position (x, y) by adding the given amounts.
     *
     * @param xAmount amount to add to x-coordinate of position
     * @param yAmount amount to add to y-coordinate of position
     */
    public void moveBy(double xAmount, double yAmount)
    {
        position.addValues(xAmount, yAmount);
    }
    
    /**
     * Rotate the angle of the sprite image by the given amount (in degrees).
     *
     * @param angleAmount amount to add to current angle of rotation
     */
    public void rotateBy(double angleAmount)
    {
        angle += angleAmount;
    }

    /**
     * Check if this sprite overlaps another sprite
     *   by checking if this sprite's rectangle overlaps other sprite's rectangle.
     *
     * @param other the other sprite to check for overlap with
     * @return true, if sprites overlap
     */
    public boolean overlap(Sprite other)
    {
        return this.size.overlap( other.size );
    }

    /**
     * If the sprite moves past the edge of the screen,
     *   adjust its position to the opposite side of the screen.
     *
     * @param screenWidth width of the game window (800 by default)
     * @param screenHeight height of the game window (600 by default)
     */
    public void wrap(double screenWidth, double screenHeight)
    {
        // check if sprite has moved completely past left screen edge
        if (position.x + size.width < 0)
            position.x = screenWidth;
        // check if sprite has moved completely past right screen edge
        if (position.x > screenWidth)
            position.x = -size.width;
        // check if sprite has moved completely past top screen edge
        if (position.y + size.height < 0)
            position.y = screenHeight;
        // check if sprite has moved completely past bottom screen edge
        if (position.y > screenHeight)
            position.y = -size.height;
    }
    
    
    /**
     * Automatically update any special objects 
     *  that have been added to this sprite, such as physics, ...
     *
     * @param deltaTime the time that has passed since last update (1/60 second)
     */
    public void update(double deltaTime)
    {
        // update physics, if present
        //  if physics is null, physics variable has not been assigned.
        if ( physics != null )
        {
            physics.update(deltaTime);
        }
    }

}
