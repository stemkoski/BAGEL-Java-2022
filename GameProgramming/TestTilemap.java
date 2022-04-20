import bagel.*;
import java.util.ArrayList;

public class TestTilemap extends Game
{
    Tilemap map;
    public void initialize()
    {
        createGroup("main");
        
        this.setScreenSize(655, 675);
        map = new Tilemap("images/tileset.png", 4, 4);
        
        String[] mapDataRows = { "WWWWWWWWWW",
                                 "W...W....W",
                                 "W.T.W..W.W",
                                 "W...WWWW.W",
                                 "W....S...W",
                                 "W......WWW",
                                 "W..S.....W",
                                 "WWWW..S..W",
                                 "W........W",
                                 "WWWWWWWWWW" };
                                 
        // Note: also place floor tile underneath turtle and starfish.
        String[] mapSymbolArray = { "W", ".", "S", "T" };
        int[]    regionIndexArray = { 6, 15, 15, 15 };
        
        map.loadMapData(mapDataRows, mapSymbolArray, regionIndexArray );
        map.setTileSize(64, 64);

        addSpriteToGroup(map, "main");

        
        // place player and items at positions indicated in tilemap.
        Sprite turtle = new Sprite();
        turtle.setTexture( new Texture("images/turtle.png") );
        turtle.setSize(64,64);
        
        ArrayList<Vector> turtlePositionList = map.getSymbolPositionList("T");
        Vector position = turtlePositionList.get(0);
        turtle.setPosition( position.x, position.y );
        
        addSpriteToGroup(turtle, "main");
        
    }
    
    public void update()
    {
        
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
