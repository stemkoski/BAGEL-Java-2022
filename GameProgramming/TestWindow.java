import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.canvas.*;
import javafx.scene.image.Image;

/**
 * Create a window on the screen.
 */
public class TestWindow extends Application
{
    
    /**
     * Method automatically called when application is launched.
     *
     * @param mainStage the window
     */
    public void start(Stage mainStage)
    {
        // set text that appears in title bar along top of window
        mainStage.setTitle("Panther");
        
        // a Pane enables us to organize the scene contents
        Pane root = new Pane();
        
        // a Scene contains window content
        Scene mainScene = new Scene( root, 800, 600 );
        
        // attach scene to stage
        mainStage.setScene( mainScene );
        
        Label message = new Label("Hello, world!");
        message.setLayoutX(400);
        message.setLayoutY(300);
        
        // to add an object to the pane, 
        //   need to get list of child objects,
        //   and add new object to that list.
        root.getChildren().add( message );
        
        // canvas is where images can be drawn
        Canvas canvas = new Canvas(800, 600);
        // add canvas to pane so it shows up
        root.getChildren().add( canvas );
        
        // context object has methods for drawing images on canvas
        GraphicsContext context = canvas.getGraphicsContext2D();
        
        // load an image file into a JavaFX image object
        Image panther = new Image("panther.jpg");
        
        // draw image object with top-left corner at (x,y) on canvas with size (width,height)
        // context.drawImage( imageObj, x,y, width,height );
        context.drawImage(panther, 0,0, 200,200);
        
        // make stage visible
        mainStage.show();
    }
    
    public static void main(String[] args)
    {
        // launch the application, creates stage and calls start() method.
        launch(args);
        
        // stop background processes when done, so we can relaunch later.
        System.exit(0);
    }
}
