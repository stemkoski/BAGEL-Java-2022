import bagel.*;
import javafx.scene.paint.Color;

public class Adventure extends Game
{
   public Tilemap map;
   public Sprite player;
   
   public Animation playerNorth;
   public Animation playerSouth;
   public Animation playerEast;
   public Animation playerWest;
   
   public int health; 
   public Label healthLabel;
   
   public void initialize()
   {
       setScreenSize(960 + 15, 960 + 25);
       
       map = new Tilemap("images/adventure-tileset.png", 5, 8);
       
       String[] mapDataRows = {"ABBBBBBBBBBBBBBBBBBC",
                               "D.:................F",
                               "D....;.............F",
                               "D..ABBBBBC......;..F",
                               "D..D.....F.........F",
                               "D.,D.....M.........F",
                               "D..D...:...........F",
                               "D..D.....J....:....F",
                               "D:.D...,.F.........F",
                               "D..GHK.LHI.........F",
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
    
       
       // create an enemy
       createGroup("enemy");
       Sprite enemy = new Sprite();
       enemy.setTexture( new Texture("images/enemy-righter.png") );
       enemy.setSize( 60, 60 );
       enemy.setPosition( 600, 400 );
       addSpriteToGroup(enemy, "enemy");
       
       // health stuff
       health = 5;
       healthLabel = new Label();
       healthLabel.fontColor = Color.WHITE;
       healthLabel.setText( "Health: " + health );
       healthLabel.setPosition( 20, 50 );
       addSpriteToGroup( healthLabel, "main" );
       
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
       
       for (Sprite enemy : getGroupSpriteList("enemy") )
       {
           // also check that player is *not* currently flashing;
           //  in other words: player should have *no* actions currently in process.
           int playerActionCount = player.actionList.size();
           
           if ( player.overlap(enemy) && playerActionCount == 0 )
           {
               health -= 1;
               healthLabel.setText("Health: " + health);
               player.addAction( Action.flashRepeat(10) );
           }
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
