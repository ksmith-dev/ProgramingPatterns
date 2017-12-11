package View;

import Control.ChangeRequestControl;
import Model.ChangeRequestCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import Model.RequestPriority;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class ChangeRequest
{
    private GridPane gridPane = new GridPane();
    private HashMap<ViewInputID, Node> nodes = new HashMap<>();

    public Scene getChangeRequestView(String fullName)
    {
        gridPane = new GridPane();

        //show grid lines
        gridPane.setGridLinesVisible(false);

        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.getChildren().add(addTitle("Change Request Form:", 0, 0, 10));
        addRequestNumber(UUID.randomUUID(),1);
        addRequestDate(ViewInputID.DATE, new Date(), 1);
        addRequesterFullName(ViewInputID.REQUESTER, fullName, 2);
        addChangeRequestPriority(ViewInputID.PRIORITY, 3);
        addRequestStateDate(ViewInputID.ACCEPTED, "Accepted Date:", 2);
        addRequestStateDate(ViewInputID.REJECTED, "Rejected Date:", 3);
        addRequestStateDate(ViewInputID.PROCESSING, "Processing Date:", 4);
        addRequestStateDate(ViewInputID.COMPLETION, "Completion Date:", 5);
        addDescription(ViewInputID.DESC, 6);
        addImpactedAreas(ViewInputID.IMPACT, 7);
        addEstimatedEffort(ViewInputID.EFFORT, 8);
        addInclusionMaintenanceNumber(ViewInputID.INCLUSION, 10, 8);
        gridPane.getChildren().add(addSubmitButton(9));

        return new Scene(gridPane);
    }

    private Text addTitle(String title, int columnIndex, int rowIndex, int columnSpan)
    {
        Text text = new Text(title);
        GridPane.setConstraints(text, columnIndex, rowIndex);
        GridPane.setColumnSpan(text, columnSpan);
        GridPane.setValignment(text, VPos.TOP);
        text.getStyleClass().add("title");

        return text;
    }

    private void addRequestNumber(UUID uuid, int rowIndex)
    {
        gridPane.getChildren().add(setLabel("ID:", 0, rowIndex, 2));
        gridPane.getChildren().add(setTextField(ViewInputID.UUID, uuid.toString(), 3, rowIndex, 20));
    }

    private void addRequestDate(ViewInputID viewInputID, Date date, int rowIndex)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = simpleDateFormat.format(date);

        gridPane.getChildren().add(setLabel("Request Date:", 30, rowIndex, 3));
        gridPane.getChildren().add(setTextField(viewInputID, stringDate, 35, rowIndex, 15));
    }

    private void addRequestStateDate(ViewInputID viewInputID, String label, int rowIndex)
    {
        gridPane.getChildren().add(setLabel(label, 30, rowIndex, 3));
        gridPane.getChildren().add(setTextFieldPrompt(viewInputID,"Enter Date", 35, rowIndex, 15));
    }

    private void addRequesterFullName(ViewInputID viewInputID, String fullName, int rowIndex)
    {
        gridPane.getChildren().add(setLabel("Requester:", 0, rowIndex,3));
        gridPane.getChildren().add(setTextField(viewInputID, fullName, 3, rowIndex, 15));
    }

    private void addChangeRequestPriority(ViewInputID viewInputID, int rowIndex)
    {
        setLabel("Priority:", 0, rowIndex, 3);

        ObservableList<RequestPriority> priorities = FXCollections.observableArrayList( RequestPriority.HIGH, RequestPriority.MEDIUM, RequestPriority.LOW);
        ComboBox comboBox = new ComboBox(priorities);
        comboBox.setId(viewInputID.toString());
        comboBox.getSelectionModel().selectFirst();
        nodes.put(viewInputID, comboBox);

        GridPane.setConstraints(comboBox, 3, rowIndex);
        GridPane.setColumnSpan(comboBox, 50);

        gridPane.getChildren().add(comboBox);
    }

    private void addRequestStateDate(ViewInputID viewInputID, String label, Date date, int rowIndex)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = simpleDateFormat.format(date);

        gridPane.getChildren().add(setLabel(label, 30, rowIndex, 3));
        gridPane.getChildren().add(setTextField(viewInputID,stringDate, 35, rowIndex, 5));
    }

    private void addDescription(ViewInputID viewInputID, String description, int rowIndex)
    {
        gridPane.getChildren().add(setLabel("Description:", 0, rowIndex, 5));
        gridPane.getChildren().add(setTextArea(viewInputID, description, 4, rowIndex, 36));
    }

    private void addDescription(ViewInputID viewInputID, int rowIndex)
    {
        gridPane.getChildren().add(setLabel("Description:", 0, rowIndex, 5));
        gridPane.getChildren().add(setTextAreaPrompt(viewInputID,"Enter Description", 3, rowIndex, 47));
    }

    private void addImpactedAreas(String impactedDescription, int rowIndex)
    {
        gridPane.getChildren().add(setLabel("Impacted:", 0, rowIndex, 4));
        gridPane.getChildren().add(setTextArea(ViewInputID.IMPACT, impactedDescription, 4, rowIndex, 36));
    }

    private void addImpactedAreas(ViewInputID viewInputID, int rowIndex)
    {
        gridPane.getChildren().add(setLabel("Impacted:", 0, rowIndex,4));
        gridPane.getChildren().add(setTextAreaPrompt(viewInputID, "Enter Description of Areas of Impact", 3, rowIndex, 47));
    }

    private void addEstimatedEffort(int estimatedEffortNumber, int rowIndex)
    {
        gridPane.getChildren().add(setLabel("Estimated Effort:", 0, rowIndex, 2));
        gridPane.getChildren().add(setTextField(ViewInputID.EFFORT ,Integer.toString(estimatedEffortNumber), 1, rowIndex, 2));
    }

    private void addEstimatedEffort(ViewInputID viewInputID, int rowIndex)
    {
        gridPane.getChildren().add(setLabel("Estimated Effort:", 0, rowIndex, 5));
        gridPane.getChildren().add(setTextFieldPrompt(viewInputID,"Enter Estimated Effort #", 5, rowIndex, 10));
    }

    private void addInclusionMaintenanceNumber(ViewInputID viewInputID, int number, int rowIndex)
    {
        gridPane.getChildren().add(setLabel("Inclusion Maintenance Number:", 32, rowIndex, 8));
        gridPane.getChildren().add(setTextFieldPrompt(viewInputID,"Enter Inclusion #", 34, rowIndex, 15));
    }

    private Button addSubmitButton(int rowIndex)
    {
        Button button = new Button("SUBMIT");

        GridPane.setConstraints(button, 0, rowIndex);
        GridPane.setColumnSpan(button, 5);

        button.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                ChangeRequestCard changeRequestCard = ChangeRequestControl.parseInputsGetChangeRequestCard(nodes);
                ChangeRequestControl.addChangeRequest(changeRequestCard);
                ChangeRequestControl.exportDataToFile("ChangeRequestCards.json");
                Scene scene = ChangeRequestControl.getStageToChangeRequestListView();
                ChangeRequestControl.setStageToScene(scene);
            }
        });
        return button;
    }

    private TextField setTextField(ViewInputID viewInputID, String fieldText, int columnIndex, int rowIndex, int columnSpan)
    {
        TextField textField = new TextField();
        if(viewInputID.equals(ViewInputID.UUID))
        {
            textField.setEditable(false);
        }
        else if (viewInputID.equals(ViewInputID.DATE))
        {
            textField.setEditable(false);
        }
        else if (viewInputID.equals(ViewInputID.REQUESTER))
        {
            textField.setEditable(false);
        }
        textField.setText(fieldText);
        textField.setId(viewInputID.toString());
        nodes.put(viewInputID, textField);
        GridPane.setConstraints(textField, columnIndex, rowIndex);
        GridPane.setColumnSpan(textField, columnSpan);

        return textField;
    }

    private TextField setTextFieldPrompt(ViewInputID viewInputID, String prompt, int columnIndex, int rowIndex, int columnSpan)
    {
        TextField textField = new TextField();
        textField.setId(viewInputID.toString());
        nodes.put(viewInputID, textField);
        textField.setPromptText(prompt);
        GridPane.setConstraints(textField, columnIndex, rowIndex);
        GridPane.setColumnSpan(textField, columnSpan);

        return textField;
    }

    private TextArea setTextArea(ViewInputID viewInputID, String areaText, int columnIndex, int rowIndex, int columnSpan)
    {
        TextArea textArea = new TextArea();
        textArea.setText(areaText);
        textArea.setId(viewInputID.toString());
        nodes.put(viewInputID, textArea);
        GridPane.setConstraints(textArea, columnIndex, rowIndex);
        GridPane.setColumnSpan(textArea, columnSpan);

        return textArea;
    }

    private TextArea setTextAreaPrompt(ViewInputID viewInputID, String prompt, int columnIndex, int rowIndex, int columnSpan)
    {
        TextArea textArea = new TextArea();
        textArea.setId(viewInputID.toString());
        nodes.put(viewInputID, textArea);
        textArea.setPromptText(prompt);
        GridPane.setConstraints(textArea, columnIndex, rowIndex);
        GridPane.setColumnSpan(textArea, columnSpan);

        return textArea;
    }

    private Text setLabel(String label, int columnIndex, int rowIndex, int columnSpan)
    {
        Text text = new Text(label + " ");
        text.getStyleClass().add("label");
        GridPane.setConstraints(text, columnIndex, rowIndex);
        GridPane.setValignment(text, VPos.TOP);

        return text;
    }
}
