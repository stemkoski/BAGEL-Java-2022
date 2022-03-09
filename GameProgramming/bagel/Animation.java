package bagel;
import java.util.ArrayList;

/**
 *  An Animation displays a sequence of images in succession to create the appearance of change.
 *  Uses a list of Rectangles to specify which part of the image should be drawn.
 *  Similar to a Texture whose region is changing repeatedly.
 */
public class Animation
{
    public Texture texture;
    // store the rectangles corresponding to small images within large image
    public ArrayList<Rectangle> regionList;
    
    /**
     * Load an image file into a Texture, and create a list of rectangles
     *  corresponding to the smaller image sizes, calculated from number of rows and columns.
     *
     * @param imageFileName name of the file
     * @param rows number of rows of small images
     * @param columns number of columns of small images
     */
    public Animation(String imageFileName, int rows, int columns)
    {
        texture = new Texture(imageFileName);
        
        regionList = new ArrayList<Rectangle>();
        // calculate size of smaller images
        double smallWidth  = texture.image.getWidth() / columns;
        double smallHeight = texture.image.getHeight() / rows;
        
        for (int rowNum = 0; rowNum < rows; rowNum++)
        {
            for (int columnNum = 0; columnNum < columns; columnNum++)
            {
                // coordinates of top-left corner of small image
                double smallX = columnNum * smallWidth;
                double smallY = rowNum * columnNum;
                Rectangle smallRect = new Rectangle(smallX, smallY, smallWidth, smallHeight);
                regionList.add( smallRect );
            }
        }
    }
}
