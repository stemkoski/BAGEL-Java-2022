import javafx.application.Application;
import javafx.stage.Stage;

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
