import bagel.*;

public class TestTilemap extends Game
{
    Tilemap map;
    public void initialize()
    {
        this.setScreenSize(640, 640);
        map = new Tilemap("images/tileset.png", 4, 4);
        
        String[] mapDataRows = { "WWWWW",
                                 "W...W",
                                 "WWWWW" };
                                 
        String[] mapSymbolArray = { "W" };
        
        int[]    regionIndexArray = { 6 };
        
        map.loadMapData(mapDataRows, mapSymbolArray, regionIndexArray );
        
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
