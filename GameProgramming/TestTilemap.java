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
    
    public Sprite blueLock;
    public Sprite blueKey;
    
    public void initialize()
    {
        createGroup("main");
        
        this.setScreenSize(960 + 15, 960 + 25);
        map = new Tilemap("images/tileset.png", 4, 4);
        
        
        // 1 = Blue Key, A = Blue Lock
        
        String[] mapDataRows = { "WWWWWWWWWWWWWWWWWWWW",
                                 "W...W..A...........W",
                                 "W.P.W..W...........W",
                                 "W...WWWW...........W",
                                 "W..................W",
                                 "W....1...........WWW",
                                 "W..................W",
                                 "WWWW...............W",
                                 "W..................W",
                                 "W..................W",
                                 "W..................W",
                                 "W..................W",
                                 "W..................W",
                                 "W..................W",
                                 "W..................W",
                                 "W..................W",
                                 "W..................W",
                                 "W..................W",
                                 "W..................W",
                                 "WWWWWWWWWWWWWWWWWWWW" };
                                 
        // Note: also place floor tile underneath turtle and starfish.
        String[] mapSymbolArray = { "W", ".", "P", "1", "A"};
        int[]    regionIndexArray = { 6, 15, 15, 15, 15 };
        
        map.loadMapData(mapDataRows, mapSymbolArray, regionIndexArray );
        map.setTileSize(48, 48);

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

        player.setSize(40, 40);
        
        ArrayList<Vector> playerPositionList = map.getSymbolPositionList("P");
        Vector position = playerPositionList.get(0);
        player.setPosition( position.x, position.y );
        
        addSpriteToGroup(player, "main");
        
        blueKey = new Sprite();
        blueKey.setTexture( new Texture("images/key-blue.png") );
        blueKey.setSize( 48, 48 );
        position = map.getSymbolPositionList("1").get(0);
        blueKey.setPosition( position.x, position.y );
        addSpriteToGroup(blueKey, "main");
        
        
        
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
        
        // sto pplayer from using size stored in other animations
        player.setSize(40, 40);
        
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
