package bagel;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import java.util.ArrayList;

import javafx.scene.canvas.*;
import javafx.animation.*;

// abstract class: contains abstract method(s)
// can not create an instance of this class;
//  must write extending class that defines abstract methods.
public abstract class Game extends Application
{
    public Game()
    {
        
    }
    
    // list of sprites
    public ArrayList<Sprite> spriteList;
    
    // Input object to store keyboard inputs
    public Input input;
    
    // game-specific code to initialize sprite objects
    //  abstract method: code MUST be added by an extending class
    public abstract void initialize();
    public abstract void update();
    
    public void start(Stage mainStage)
    {
        // create a Scene, using a Pane to organize
        Pane root = new Pane();
        Scene mainScene = new Scene(root, 800, 600); 
        // attach Scene to Stage
        mainStage.setScene( mainScene );

        // canvas displays images
        Canvas canvas = new Canvas(800, 600);
        // attach canvas to scene
        root.getChildren().add( canvas );
        // context has methods for drawing images
        GraphicsContext context = 
            canvas.getGraphicsContext2D();

        // create a list to store sprites
        spriteList = new ArrayList<Sprite>();

        // initialize the Input object; 
        //  window containing mainScene must have focus
        //  to register input
        input = new Input( mainScene );
        
        // create the loop that runs 60 times per second
        AnimationTimer gameloop = new AnimationTimer()
            {
                // code to run
                public void handle(long nanoseconds)
                {
                    // refresh canvas by making all pixels
                    //   a solid color (black)
                    context.fillRect(0,0, 800,600);
                    
                    // update input key lists
                    input.update();
                    
                    update();
                    
                    // draw all Sprites within list
                    for (Sprite s : spriteList)
                    {
                        s.draw(context);
                    }
                }
            };
        
        // initialize game sprites
        initialize();
        
        // start the game loop
        gameloop.start();
        
        
        // make stage visible
        mainStage.show();
    }

   
    
    
}