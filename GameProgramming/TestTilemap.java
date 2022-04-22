import bagel.*;
import java.util.ArrayList;

public class TestTilemap extends Game
{
    public Tilemap map;
    
    // make this global so we can move it in update
    public Sprite player;
    // animations for player; set up in initialize() and switch in update() depending on player direction.
    public Animation playerNorth;
    public Animation playerSouth;
    public Animation playerEast;
    public Animation playerWest;
    
    public void initialize()
    {
        createGroup("main");
        
        this.setScreenSize(655, 675);
        map = new Tilemap("images/tileset.png", 4, 4);
        
        String[] mapDataRows = { "WWWWWWWWWW",
                                 "W...W....W",
                                 "W.P.W..W.W",
                                 "W...WWWW.W",
                                 "W....C...W",
                                 "W......WWW",
                                 "W..C.....W",
                                 "WWWW..C..W",
                                 "W........W",
                                 "WWWWWWWWWW" };
                                 
        // Note: also place floor tile underneath turtle and starfish.
        String[] mapSymbolArray = { "W", ".", "P", "C" };
        int[]    regionIndexArray = { 6, 15, 15, 15 };
        
        map.loadMapData(mapDataRows, mapSymbolArray, regionIndexArray );
        map.setTileSize(64, 64);

        // adding solid data to map (so player can't pass through walls)
        String[] solidSymbolArray = { "W" };
        map.loadSolidData( solidSymbolArray );
        
        
        addSpriteToGroup(map, "main");

        
        // place player and items at positions indicated in tilemap.
        player = new Sprite();
        
        playerSouth = new Animation("images/player-south.png", 1, 4, 0.2, true);
        playerNorth = new Animation("images/player-north.png", 1, 4, 0.2, true);
        playerEast  = new Animation("images/player-east.png",  1, 4, 0.2, true);
        playerWest  = new Animation("images/player-west.png",  1, 4, 0.2, true);
        player.setAnimation(playerSouth);

        player.setSize(48,48);
        
        ArrayList<Vector> playerPositionList = map.getSymbolPositionList("P");
        Vector position = playerPositionList.get(0);
        player.setPosition( position.x, position.y );
        
        addSpriteToGroup(player, "main");
        
        createGroup( "crate" );
        // add pushable crate objects to game
        Texture crateTexture = new Texture("images/crate.png");
        // get crate positions (also number of crates)
        ArrayList<Vector> cratePositionList = map.getSymbolPositionList("C");
        for (int i = 0; i < cratePositionList.size(); i++)
        {
            Vector pos = cratePositionList.get(i);
            Sprite crate = new Sprite();
            crate.setTexture( crateTexture );
            crate.setSize( 64, 64 );
            crate.setPosition( pos.x, pos.y );
            addSpriteToGroup( crate, "crate" );
        }
    }
    
    public void update()
    {
        if ( input.isKeyPressing("W") )
        {
            player.moveBy(0, -2);
            player.setAnimation( playerNorth );
        }
        if ( input.isKeyPressing("S") )
        {
            player.moveBy(0,  2);
            player.setAnimation( playerSouth );
        }
        if ( input.isKeyPressing("A") )
        {
            player.moveBy(-2, 0);
            player.setAnimation( playerWest );
        }
        if ( input.isKeyPressing("D") )
        {
            player.moveBy( 2, 0);
            player.setAnimation( playerEast );
        }    
        // stop overlap between tiles of map and turtle
        map.preventOverlap( player );
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
