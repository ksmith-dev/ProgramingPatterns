package View;

import Control.ChangeRequestControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import Model.ChangeRequestCard;
import Model.RequestStates;

import java.text.SimpleDateFormat;
import java.util.*;

public class ChangeRequestList
{
    private HashMap<UUID, ChangeRequestCard> changeRequestCards;

    public ChangeRequestList(HashMap<UUID, ChangeRequestCard> changeRequestCards)
    {
        this.changeRequestCards = changeRequestCards;
    }

    public Scene getNewListView()
    {
        BorderPane borderPane = new BorderPane();

        ObservableList<HBox> items = FXCollections.observableArrayList();

        for (Map.Entry entry : changeRequestCards.entrySet())
        {
            HBox wrapper = new HBox();
            HBox hBox;
            Text text;

            ChangeRequestCard changeRequestCard = (ChangeRequestCard) entry.getValue();

            hBox = new HBox();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String date = simpleDateFormat.format(changeRequestCard.getRequestDate());
            text = new Text("Request Date: " + date);
            hBox.getChildren().add(text);
            hBox.setMinWidth(200.00);
            wrapper.getChildren().add(hBox);

            hBox = new HBox();
            text = new Text("Requester Name: " +  changeRequestCard.getRequesterName());
            hBox.getChildren().add(text);
            hBox.setMinWidth(200.00);
            wrapper.getChildren().add(hBox);

            hBox = new HBox();
            text = new Text("Priority Status: " +  changeRequestCard.getPriority());
            hBox.getChildren().add(text);
            hBox.setMinWidth(200.00);
            wrapper.getChildren().add(hBox);

            RequestStates requestState = changeRequestCard.getState();
            if (requestState != null)
            {
                Date requestStateDate = changeRequestCard.getDate(requestState);
                String stringStateDate = simpleDateFormat.format(requestStateDate);
                hBox = new HBox();
                text = new Text("CURRENT STATUS: " + changeRequestCard.getState().toString() + " DATE: " + stringStateDate);
                hBox.getChildren().add(text);
                hBox.setMinWidth(200.00);
                wrapper.getChildren().add(hBox);
            }

            items.add(wrapper);
        }

        ListView<HBox> listView = new ListView<>();

        listView.setItems(items);

        borderPane.setTop(getTopVerticalBoxElement());
        borderPane.setLeft(getDefaultNodeElement());
        borderPane.setRight(getRightNodeElement());
        borderPane.setCenter(listView);
        borderPane.setBottom(getDefaultNodeElement());

        return new Scene(borderPane);
    }

    private VBox getTopVerticalBoxElement()
    {
        VBox vBox = new VBox();

        vBox.setMinHeight(50.00);

        Text text = new Text("Change Requests");
        text.getStyleClass().add("title");

        vBox.getChildren().add(text);

        return vBox;
    }

    private VBox getRightNodeElement()
    {
        Text text = new Text("Add");

        Button button = new Button("+");

        button.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Scene scene = ChangeRequestControl.getStageToChangeRequestFormView();
                ChangeRequestControl.setStageToScene(scene);
            }
        });

        VBox vBox = new VBox();
        vBox.getStyleClass().add("add-btn-wrapper");
        vBox.setMinWidth(40.00);
        vBox.setMinHeight(40.00);
        vBox.getChildren().addAll(text, button);
        return vBox;
    }

    private VBox getDefaultNodeElement()
    {
        VBox vBox = new VBox();
        vBox.setMinWidth(40.00);
        vBox.setMinHeight(40.00);
        return vBox;
    }
}
