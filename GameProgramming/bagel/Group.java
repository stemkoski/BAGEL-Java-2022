package bagel;
import java.util.ArrayList;


/**
 * Manage a collection of Sprites.
 */
public class Group
{
    // only allow interaction with list via group methods
    private ArrayList<Sprite> spriteList;
    
    // identify this group by a name
    public String name;
    
    /**
     * Constructor; initialize underlying list.
     * @param groupName the name of this group; used to identify group in the game class.
     */
    public Group(String groupName)
    {
        spriteList = new ArrayList<Sprite>();
        name = groupName;
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
     * Return a copy of the underlying list, useful when iterating (for loop)
     *   over a collection of sprites.
     * Necessary to avoid "Concurrent Modification Exception" - can't modify (add/remove)
     *   objects to a list while iterating over the elements in that list.
     *
     * @return the list of sprites
     */
    public ArrayList<Sprite> getSpriteList()
    {
        return new ArrayList<Sprite>(spriteList);
    }
    
}
