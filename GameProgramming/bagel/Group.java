package bagel;
import java.util.ArrayList;


/**
 * Manage a collection of Sprites.
 */
public class Group
{
    // only allow interaction with list via group methods
    private ArrayList<Sprite> spriteList;
    
    /**
     * Constructor; initialize underlying list.
     */
    public Group()
    {
        spriteList = new ArrayList<Sprite>();
    }
    
    /**
     * Add a sprite to this collection.
     *
     * @param s sprite to be added
     */
    public void addSprite(Sprite s)
    {
        spriteList.add( s );
    }
    
    /**
     * Remove this sprite from the collection.
     *
     * @param s sprite to be removed
     */
    public void removeSprite(Sprite s)
    {
        spriteList.remove( s );
    }
    
    /**
     * Return the number of sprites in this collection.
     *
     * @return the number of sprites
     */
    public int getSpriteCount()
    {
        return spriteList.size();
    }
    
    /**
     * Return the underlying list, useful when iterating (for loop)
     *   over a collection of sprites.
     *
     * @return the list of sprites
     */
    public ArrayList<Sprite> getList()
    {
        return spriteList;
    }
    
}
