package launchers;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.UserInterface;

public class LaunchAdapterPattern extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        UserInterface view = new UserInterface();

        stage.setTitle("Adapter Graphics Application");
        stage.setScene(view.getScene());
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
