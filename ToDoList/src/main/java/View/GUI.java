package View;

import Control.ToDoControl;
import Object.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class GUI extends Application implements ToDoObserver
{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private ToDoControl control = new ToDoControl(this);
    private ArrayList<ToDo> toToList;
    private HashMap<UUID, Boolean> toDoStates;

    private BorderPane pane = new BorderPane();

    private TextArea inputText;

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Task List");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        System.out.println("checking if to do list is empty");
        if(control.getToDoListFromModel().isEmpty())
        {
            loadView(ViewStates.WELCOME);
        }
        else
        {
            loadView(ViewStates.LIST);
        }

        try
        {
            Scene scene = new Scene(pane);
            scene.getStylesheets().add("styles/styles.css");
            stage.setScene(scene);
            stage.show();
        }
        catch (RuntimeException e)
        {
            System.out.println("RuntimeException: " + e);
        }
    }

    private void loadView(ViewStates viewState)
    {
        setTopPaneContent(viewState);
        setLeftPaneContent(viewState);
        setRightPaneContent(viewState);
        setCenterPaneContent(viewState);
        setBottomPaneContent(viewState);
    }

    private void setTopPaneContent(ViewStates viewState)
    {
        Text text;
        switch (viewState)
        {
            case WELCOME :
                pane.setTop(null);
                break;
            case TEXT_INPUT:
                text = new Text("Add New Task");
                text.setFont(Font.font ("Verdana", 20));
                pane.setTop(text);
                break;
            case LIST:
                text = new Text("Tasks");
                text.setFont(Font.font ("Verdana", 20));
                pane.setTop(text);
                break;
            default:
                text = new Text("Welcome");
                text.setFont(Font.font ("Verdana", 20));
                pane.setTop(text);
                break;
        }
    }

    private void setCenterPaneContent(ViewStates viewState)
    {
        switch (viewState)
        {
            case WELCOME:
                VBox vBox = new VBox();
                Text welcome = new Text("Welcome");
                welcome.setFont(Font.font ("Verdana", 20));
                Text message = new Text("You have 0 unfinished tasks");
                message.setFont(Font.font ("Verdana", 10));
                Button viewButton = new Button("View");
                viewButton.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        loadView(ViewStates.LIST);
                    }
                });
                Image image = new Image("img/tasks.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                vBox.getChildren().addAll(welcome, message, viewButton, imageView);
                vBox.setAlignment(Pos.CENTER);
                vBox.setSpacing(10.00);
                pane.setCenter(vBox);
                break;
            case LIST:
                ListView<HBox> listView = new ListView<>();
                listView.getStyleClass().add("to-do-list");

                this.toToList = control.getToDoListFromModel();

                if (toToList.isEmpty())
                {
                    pane.setCenter(new Text("There are no tasks currently. Add a task by clicking the button + above."));
                }
                else
                {
                    for (ToDo toDo : toToList)
                    {
                        HBox hBox = new HBox();
                        hBox.setId(toDo.getId().toString());

                        CheckBox checkBox = new CheckBox();
                        if (toDoStates.containsKey(toDo.getId()))
                        {
                            checkBox.setSelected(toDoStates.get(toDo.getId()));
                        }
                        else
                        {
                            control.updateCheckedStatesOfModel(toDo.getId(), false);
                        }

                        Text text = new Text(toDo.getMsg());

                        checkBox.setOnAction(new EventHandler<ActionEvent>()
                        {
                            @Override
                            public void handle(ActionEvent event)
                            {
                                control.updateCheckedStatesOfModel(toDo.getId(), checkBox.isSelected());
                            }
                        });
                        hBox.getChildren().addAll(checkBox, text);
                        listView.getItems().add(hBox);
                    }
                    pane.setCenter(listView);
                }
                break;
            case TEXT_INPUT:
                inputText = new TextArea();
                pane.setCenter(inputText);
                break;
            default:
                pane.setCenter(null);
                break;
        }

    }

    private void setLeftPaneContent(ViewStates viewState)
    {
        switch (viewState)
        {
            case WELCOME :
                pane.setLeft(null);
                break;
            case LIST :
                pane.setLeft(null);
                break;
            case TEXT_INPUT :
                pane.setLeft(null);
                break;
            default :
                pane.setLeft(null);
                break;
        }
    }

    private void setRightPaneContent(ViewStates viewState)
    {
        switch (viewState)
        {
            case WELCOME:
                pane.setRight(null);
                break;
            case LIST:
                Button button = new Button("+");
                button.setAlignment(Pos.CENTER_LEFT);
                button.getStyleClass().add("add-button");
                button.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        loadView(ViewStates.TEXT_INPUT);
                    }
                });
                pane.setRight(button);
                break;
            case TEXT_INPUT:
                pane.setRight(null);
                break;
            default:
                pane.setRight(null);
                break;
        }
    }

    private void setBottomPaneContent(ViewStates viewState)
    {
        switch (viewState)
        {
            case TEXT_INPUT:
                Button button = new Button("Submit");
                button.getStyleClass().add("btn-submit");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        control.addNewToDoToModel(inputText.getText());
                    }
                });
                pane.setBottom(button);
                break;
            default:
                pane.setBottom(null);
                break;
        }
    }

    @Override
    public void updateObservable(ToDoObservable observable, Object... args)
    {
        this.toToList = control.getToDoListFromModel();
        this.toDoStates = control.getToDoStatesFromModel();
        ViewStates viewState = (ViewStates) args[0];
        loadView(viewState);
    }
}
