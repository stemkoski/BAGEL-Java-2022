package bagel;
import java.util.ArrayList;

import javafx.scene.canvas.*;

/**
 * Create levels from tile sets (image containing smaller rectangular images)
 *
 */
public class Tilemap extends Sprite
{
    // store image containing tiles
    public Texture tilesetTexture;
    public ArrayList<Rectangle> regionList;
    
    // store map data in grid format
    public int rows;
    public int columns;
    public String[][] mapDataGrid;
    // used in draw function
    public String[] mapSymbolArray;
    public int[] tileIndexArray;

    public Tilemap(String imageFileName, int rows, int columns)
    {
        tilesetTexture = new Texture(imageFileName);
        
        regionList = new ArrayList<Rectangle>();
        // calculate size of smaller images
        double smallWidth  = tilesetTexture.image.getWidth() / columns;
        double smallHeight = tilesetTexture.image.getHeight() / rows;
        
        for (int rowNum = 0; rowNum < rows; rowNum++)
        {
            for (int columnNum = 0; columnNum < columns; columnNum++)
            {
                // coordinates of top-left corner of small image
                double smallX = columnNum * smallWidth;
                double smallY = rowNum * smallHeight;
                Rectangle smallRect = new Rectangle(smallX, smallY, smallWidth, smallHeight);
                regionList.add( smallRect );
            }
        }
    }
    
    /**
     * Each map is represented by an array of Strings, one for each row.
     * Example: mapDataRows = { "WWWWWW",
     *                          "W....W",
     *                          "W.W..W",
     *                          "W.WC.W",
     *                          "WP...W",
     *                          "WWWWWW"  }
     *          mapSymbolArray   = { "W", "C", "P" }
     *          regionIndexArray = {  6 ,  2 ,  11 }
     * @param mapDataRows contains layout of tiles, each string is one row
     * @param mapSymbolArray characters in mapDataRows that correspond to tiles 
     * @param regionIndexArray corresponding region index for each map symbol
     * 
     * For convenience: also split String[]   mapDataRows 
     *               into 2D array String[][] mapDataGrid
     * 
     */
    public void loadMapData(String[] mapDataRows, 
                            String[] mapSymbolArray, int[] regionIndexArray)
    {
        rows = mapDataRows.length;
        columns = mapDataRows[0].length();
        System.out.println("loading map data with " + rows + " rows "
                                    + " and " + columns + " columns " );
        mapDataGrid = new String[columns][rows];
    }
    
    public void draw(GraphicsContext context)
    {
        if (visible)
        {
             for (int x = 0; x < columns; x++)
             {
                 for (int y = 0; y < rows; y++)
                 {
                     // TODO: drawImage corresponding to tile
                     String tileSymbol = mapDataGrid[x][y];
                     // find index corresponding to symbol
                     int index = -1;
                     for (int i = 0; i < mapSymbolArray.length; i++)
                     {
                         if ( mapSymbolArray[i].equals(tileSymbol) )
                             index = i;
                     }
                     // if symbol does not occur in symbol array,
                     //   nothing to draw; continue to next symbol in array
                     if (index == -1)
                         continue;
                     // use region "i" to draw region of image tilesetTexture
                     Rectangle region = regionList.get(index);
                     context.drawImage(tilesetTexture,
                        x, y, w, h,
                        x, y, w, h ); // TODO: finish next time.
                 }
             }
            
        }
    }
    
    
    
    
    
    
    
    
}
