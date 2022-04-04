import graphics.core.*;

// GLFW = Graphics Library FrameWork

public class TestBase extends Base
{
    
    public void initialize()
    {
        System.out.println("Initializing program...");
    }

    public void update()
    {

    }

    // driver method
    public static void main(String[] args)
    {
        new TestBase().run();
    }
}
