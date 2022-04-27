import bagel.*;

public class Adventure extends Game
{
   public Tilemap map;
   public Sprite player;
   
   public Animation playerNorth;
   public Animation playerSouth;
   public Animation playerEast;
   public Animation playerWest;
   
   public void initialize()
   {
       setScreenSize(960 + 15, 960 + 25);
       
       map = new Tilemap("images/adventure-tileset.png", 5, 8);
       
       String[] mapDataRows = {"ABBBBBBBBBBBBBBBBBBC",
                               "D.:................F",
                               "D....;.............F",
                               "D...............;..F",
                               "D........,.........F",
                               "D.,................F",
                               "D......:...........F",
                               "D.............:....F",
                               "D:.....,...........F",
                               "D..................F",
                               "D......:........;..F",
                               "D.,........,.....,.F",
                               "D..................F",
                               "D..............,...F",
                               "D.....,............F",
                               "D..........,.......F",
                               "D..:......P........F",
                               "D..............;...F",
                               "D..................F",
                               "GHHHHHHHHHHHHHHHHHHI" };
       
       String[] mapSymbolArray = { ".", ",", ":", ";", "A", "B", "C", "D", "E", "F",
                                   "G", "H", "I", "P", "J", "K", "L", "M" };
       int[]    regionIndexArray = { 19, 25, 26, 32, 5, 6, 7, 13, 14, 15, 21, 22, 23, 19, 36, 37, 38, 39 };
       
       map.loadMapData( mapDataRows, mapSymbolArray, regionIndexArray );
       map.setTileSize( 48, 48 );
       
       String[] solidSymbolArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M" };
       map.loadSolidData( solidSymbolArray );
       
       createGroup( "main" );
       addSpriteToGroup( map, "main" );
       
       player = new Sprite();
       playerNorth = new Animation("images/player-north.png", 1, 4, 0.2, true);
       playerSouth = new Animation("images/player-south.png", 1, 4, 0.2, true);
       playerEast  = new Animation("images/player-east.png", 1, 4, 0.2, true);
       playerWest  = new Animation("images/player-west.png", 1, 4, 0.2, true);
       player.setAnimation(playerSouth);
       player.setSize(40,40);
       // position
       Vector playerPosition = map.getSymbolPositionList("P").get(0);
       player.setPosition( playerPosition.x, playerPosition.y );
       addSpriteToGroup( player, "main" );
   }
   
   public void update()
   {
       if ( input.isKeyPressing("W") )
       {
           player.setAnimation( playerNorth );
           player.moveBy( 0, -2 );
       }
       if ( input.isKeyPressing("S") )
       {
           player.setAnimation( playerSouth );
           player.moveBy( 0,  2 );
       }
       if ( input.isKeyPressing("A") )
       {
           player.setAnimation( playerWest );
           player.moveBy( -2, 0 );
       }
       if ( input.isKeyPressing("D") )
       {
           player.setAnimation( playerEast );
           player.moveBy( 2, 0 );
       }
       
       player.setSize(40, 40);
       
       // prevent overlap with solid map tiles and player
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
