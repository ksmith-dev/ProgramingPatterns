package View;

import Control.ChangeRequestControlTest;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeRequestListTest extends Application
{
    private static final double STAGE_WIDTH = 1000;
    private static final double STAGE_HEIGHT = 850;

    private static Stage stage;

    @BeforeClass
    public static void initJFX()
    {
        Thread t = new Thread("JavaFX Init Thread")
        {
            @Override
            public void run()
            {
                Application.launch(ChangeRequestControlTest.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        this.stage.setTitle("Change Request Card");
        this.stage.setWidth(STAGE_WIDTH);
        this.stage.setHeight(STAGE_HEIGHT);

        this.stage.show();
    }

    @Test
    public void getNewListView()
    {
        ChangeRequest changeRequest = new ChangeRequest();

        Scene scene = changeRequest.getChangeRequestView("John Doe");

        assertTrue(scene instanceof Scene);
    }
}