package launchers;
/**
 * | Description |
 * This class launches this adapter pattern application
 */

import javafx.application.Application;
import javafx.stage.Stage;
import ui.UserInterface;

/**
 * | Description |
 * This class launches this adapter pattern application
 * @author Kevin Smith
 * @version 1.0
 */
public class LaunchAdapterPattern extends Application
{
    /**
     * | Description |
     * Application class start method
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        UserInterface view = new UserInterface();

        stage.setTitle("Adapter Graphics Application");
        stage.setScene(view.getScene());
        stage.show();
    }

    /**
     * | Description |
     * Java static void main method
     * @param args arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
