package bagel;


/**
 * Rectangles store position and size; used to check overlap between sprites.
 */
public class Rectangle
{
    public double width;
    public double height;
    public Vector position;
    
    /**
     * Rectangle Constructor: sets default values to a 1x1 rectangle,
     *   top-left corner at (0,0).
     *
     */
    public Rectangle()
    {
        width = 1;
        height = 1;
        position = new Vector(0,0);
    }
    
    /**
     * Rectangle Constructor; sets position of top-left corner to (x,y)
     *   and the size to w by h.
     *
     * @param x coordinate for left side
     * @param y coordinate for top side
     * @param w width
     * @param h height
     */
    public Rectangle(double x, double y, double w, double h)
    {
        position = new Vector(x,y);
        width = w;
        height = h;
    }
    
    /**
     * Update the position of this rectangle
     *
     * @param x coordinate of left side
     * @param y coordinate of top side
     */
    public void setPosition(double x, double y)
    {
        position.setValues(x, y);
    }
    
    // TODO: setSize? (might be useful for complicated games)
    
    
    /**
     * Check if this rectangle overlaps another rectangle.
     *
     * @param other other rectangle to check for overlap
     * @return true if rectangles overlap
     */
    public boolean overlap(Rectangle other)
    {
        boolean notOverlap = 
          (position.x + width < other.position.x) || 
          (position.y + height < other.position.y) ||
          (other.position.x + other.width < position.x) || 
          (other.position.y + other.height < position.y);
          
        return !(notOverlap);
    }
    
    
    
    
    
    
}
