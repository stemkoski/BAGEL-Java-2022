import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.util.ArrayList;

import javafx.scene.canvas.*;
import javafx.scene.image.Image;

import javafx.animation.*;

import bagel.*;

/**
 * Create a window on the screen.
 */
public class TestAnimation extends Application
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

        // create a panther sprite.
        Sprite turtle = new Sprite();
        // set top left corner coordinates
        turtle.setPosition(50, 50);
        // load texture data into sprite
        Texture turtleTexture = new Texture("turtle.png");
        turtle.setTexture( turtleTexture );
        // set size of sprite *after* image is loaded
        turtle.setSize(300, 300);

        // create a sword sprite
        Sprite sword = new Sprite();
        sword.setPosition(0,0);
        sword.setTexture( new Texture("sword.png") );
        sword.setSize(200,200);
        
        ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
        spriteList.add(turtle);
        spriteList.add(sword);
        
        // create an instance of AnimationTimer class,
        // need to define a method called "handle";
        // it automatically runs 60 times per second
        AnimationTimer gameloop = new AnimationTimer()
            {
                // this method contains code that will run 60 times per second
                public void handle(long nanoseconds)
                {
                    // refresh canvas by changing all pixels to a single color
                    // fillRect( x,y, width,height );
                    context.fillRect(0,0, 800,600);
                    
                    // move panther to new position
                    turtle.moveBy(2, 1);
                    
                    // ask all sprites in sprite list to draw themselves
                    for (Sprite s : spriteList)
                    {
                        s.draw(context);
                    }
                    
                    /*
                    // the above for loop does the same task as this for loop
                    for (int i = 0; i < spriteList.size(); i++)
                    {
                        Sprite s = spriteList.get(i);
                        s.draw(context);
                    }
                    */
                }
            };
            
        // start the gameloop running
        gameloop.start();

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
