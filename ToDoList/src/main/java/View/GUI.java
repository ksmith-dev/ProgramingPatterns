package View;

import Control.Model;
import Model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application
{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private Model model = new Model();

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Task List");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        try
        {
            stage.setScene(getScene());
        }
        catch (RuntimeException e)
        {
            System.out.println("RuntimeException: " + e);
        }
    }

    private Scene getScene()
    {
        BorderPane pane = new BorderPane();

        pane.setTop(getBanner());
        pane.setRight(getAddToDo());
        pane.setCenter(getToDoList());

        return new Scene(pane);
    }

    private ListView<String> getToDoList()
    {
        Model model = new Model();

        ListView<String> listView = new ListView<>();
        listView.getStyleClass().add("to-do-list");

        ToDoObservable observable = new ToDoObservable();

        for (ToDo toDo : model.getToDoList())
        {
            listView.getItems().add(toDo.getMsg());
        }
        return listView;
    }

    private Text getBanner()
    {
        return new Text("To Do's");
    }

    private Button getAddToDo()
    {
        return new Button("+");
    }
}
