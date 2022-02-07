package bagel;

import javafx.scene.image.Image;


/**
 * Stores an image and a rectangle to specify the region of the image to draw.
 */
public class Texture
{
    public Image image;
    
    public Rectangle region;
    
    /**
     * Create a texture; default region to draw is entire image.
     *
     * @param imageFileName name of the image file to load
     */
    public Texture(String imageFileName)
    {
        image = new Image(imageFileName);
        region = new Rectangle(0,0, image.getWidth(), image.getHeight());
    }
    
}
