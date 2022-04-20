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
    public int[] regionIndexArray;

    public int tileWidth;
    public int tileHeight;
    

    public Tilemap(String imageFileName, int imageRows, int imageColumns)
    {
        tilesetTexture = new Texture(imageFileName);
        
        regionList = new ArrayList<Rectangle>();
        // calculate size of smaller images
        double smallWidth  = tilesetTexture.image.getWidth() / imageColumns;
        double smallHeight = tilesetTexture.image.getHeight() / imageRows;
        
        for (int rowNum = 0; rowNum < imageRows; rowNum++)
        {
            for (int columnNum = 0; columnNum < imageColumns; columnNum++)
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

        // storing method variables in this class variables.
        this.mapSymbolArray = mapSymbolArray;
        this.regionIndexArray = regionIndexArray;
        
        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                mapDataGrid[x][y] = mapDataRows[y].substring(x, x + 1);
            }
        }
    }
    
    /**
     * Sets how large each individual tile should be drawn on the screen.
     *
     * @param width width of each tile
     * @param height height of each tile
     */
    public void setTileSize(int width, int height)
    {
        tileWidth = width;
        tileHeight = height;
    }
    
    
    /**
     * Get a list of Vectors that correspond to positions (top-left x,y) where
     *   a certain character appears in the tilemap. Useful for placing Sprites.
     *
     * @param symbol the character in the map we are looking for
     * @return a list of positions where that character appears
     */
    public ArrayList<Vector> getSymbolPositionList(String symbol)
    {
        ArrayList<Vector> positionList = new ArrayList<Vector>();
        
        for (int x = 0; x < columns; x++)
        {
            for (int y = 0; y < rows; y++)
            {
                if ( mapDataGrid[x][y].equals(symbol) )
                {
                    Vector position = new Vector(x * tileWidth, y * tileHeight);
                    positionList.add(position);
                }
            }
        }
        
        return positionList;
    }
    
    
    
    public void draw(GraphicsContext context)
    {
        if (visible)
        {
             for (int x = 0; x < columns; x++)
             {
                 for (int y = 0; y < rows; y++)
                 {
                     // drawImage corresponding to tile
                     String tileSymbol = mapDataGrid[x][y];
                     // find index corresponding to symbol
                     int index = -1;
                     for (int i = 0; i < mapSymbolArray.length; i++)
                     {
                         if ( mapSymbolArray[i].equals(tileSymbol) )
                             index = regionIndexArray[i];
                     }

                     // if symbol does not occur in symbol array,
                     //   nothing to draw; continue to next symbol in array
                     if (index == -1)
                         continue;
                         
                         
                     // reset any transformations set by sprites
                     context.setTransform(1,0, 0,1, 0,0);
                         
                     // use region "i" to draw region of image tilesetTexture
                     Rectangle region = regionList.get(index);
                     //                    [source/image]   [destination/canvas]
                     // drawImage( image,  x, y, w, h,      x, y, w, h  )
                     context.drawImage(tilesetTexture.image,
                        region.position.x, region.position.y, region.width, region.height,
                        x * tileWidth, y * tileHeight, tileWidth, tileHeight ); 
                 }
             }
            
        }
    }
    
    
    
    
    
    
    
    
}
