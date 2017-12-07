package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import model.ChangeRequestCard;
import model.ChangeRequestPriority;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Stage extends Application
{
    private static final double STAGE_WIDTH = 1000;
    private static final double STAGE_HEIGHT = 900;

    GridPane gridPane = new GridPane();

    HashMap<String, Node> nodes = new HashMap<>();

    @Override
    public void start(javafx.stage.Stage stage) throws Exception
    {
        stage.setTitle("Change Request Card");
        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);

        final int numCols = 40;

        for (int i=0; i<numCols; i++)
        {
            ColumnConstraints columnConstraints = new ColumnConstraints();

            columnConstraints.setPrefWidth(100.00 / numCols);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        //show grid lines
        gridPane.setGridLinesVisible(false);

        gridPane.setVgap(15);
        gridPane.setHgap(20);
        gridPane.setPadding(new Insets(20));

        buildScene();

        Scene scene = new Scene(gridPane);

        scene.getStylesheets().add("styles/styles.css");

        stage.setScene(scene);

        stage.show();
    }

    private void buildScene()
    {

        addTitle("Change Request Form:", 0, 0, 40);
        addRequestNumber(UUID.randomUUID(),1);
        addRequestDate(InputIdList.REQUEST, new Date(), 1);
        addRequesterFullName(InputIdList.FULL_NAME, "John Doe", 2);
        addChangeRequestPriority(InputIdList.PRIORITY, ChangeRequestPriority.LOW, 3);
        addRequestStateDate(InputIdList.ACCEPTED, "Accepted Date:", new Date(), 2);
        addRequestStateDate(InputIdList.REJECTED, "Rejected Date:", 3);
        addRequestStateDate(InputIdList.PROCESSING, "Processing Date:", 4);
        addRequestStateDate(InputIdList.COMPLETION, "Completion Date:", 5);
        addDescription(InputIdList.DESC, 6);
        addImpactedAreas(InputIdList.IMPACT, 7);
        addEstimatedEffort(InputIdList.EFFORT, 8);
        addInclusionMaintenanceNumber(InputIdList.INCLUSION, 10, 8);
        addSubmitButton(9);
    }

    private void addTitle(String title, int columnIndex, int rowIndex, int columnSpan)
    {
        Text text = new Text(title);
        GridPane.setConstraints(text, columnIndex, rowIndex);
        GridPane.setColumnSpan(text, columnSpan);
        GridPane.setValignment(text, VPos.TOP);
        text.getStyleClass().add("title");

        gridPane.getChildren().add(text);
    }

    private void addRequestNumber(UUID id, int rowIndex)
    {
        setLabel("ID:", 0, rowIndex, 3);
        setTextField(InputIdList.UUID, id.toString(), 3, rowIndex, 15);
    }

    private void addRequestDate(InputIdList id, Date date, int rowIndex)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = simpleDateFormat.format(date);

        setLabel("Request Date:", 30, rowIndex, 3);
        setTextField(id, stringDate, 35, rowIndex, 5);
    }

    private void addRequestStateDate(InputIdList id, String label, int rowIndex)
    {
        setLabel(label, 30, rowIndex, 3);
        setTextFieldPrompt(id,"Enter Date", 35, rowIndex, 5);
    }

    private void addRequesterFullName(InputIdList id, String fullName, int rowIndex)
    {
        setLabel("Requester:", 0, rowIndex,3);
        setTextField(id, fullName, 3, rowIndex, 15);
    }

    private void addChangeRequestPriority(InputIdList id, ChangeRequestPriority priority, int rowIndex)
    {
        setLabel("Priority:", 0, rowIndex, 3);
        setTextField(id, priority.toString(), 3, rowIndex, 15);
    }

    private void addRequestStateDate(InputIdList id, String label, Date date, int rowIndex)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = simpleDateFormat.format(date);

        setLabel(label, 30, rowIndex, 3);
        setTextField(id ,stringDate, 35, rowIndex, 5);
    }

    private void addDescription(InputIdList id, String description, int rowIndex)
    {
        setLabel("Description:", 0, rowIndex, 5);
        setTextArea(id, description, 4, rowIndex, 36);
    }

    private void addDescription(InputIdList id, int rowIndex)
    {
        setLabel("Description:", 0, rowIndex, 5);
        setTextAreaPrompt(id,"Enter Description", 4, rowIndex, 36);
    }

    private void addImpactedAreas(String impactedDescription, int rowIndex)
    {
        setLabel("Impacted:", 0, rowIndex, 4);
        setTextArea(InputIdList.IMPACT, impactedDescription, 4, rowIndex, 36);
    }

    private void addImpactedAreas(InputIdList id, int rowIndex)
    {
        setLabel("Impacted:", 0, rowIndex,4);
        setTextAreaPrompt(id, "Enter Description of Areas of Impact", 4, rowIndex, 36);
    }

    private void addEstimatedEffort(int estimatedEffortNumber, int rowIndex)
    {
        setLabel("Estimated Effort:", 0, rowIndex, 2);
        setTextField(InputIdList.EFFORT ,Integer.toString(estimatedEffortNumber), 1, rowIndex, 2);
    }

    private void addEstimatedEffort(InputIdList id, int rowIndex)
    {
        setLabel("Estimated Effort:", 0, rowIndex, 5);
        setTextFieldPrompt(id,"Enter Estimated Effort Number", 5, rowIndex, 10);
    }

    private void addInclusionMaintenanceNumber(InputIdList id, int number, int rowIndex)
    {
        setLabel("Inclusion Maintenance No.:", 28, rowIndex, 3);
        setTextField(id ,Integer.toString(number), 35, rowIndex, 5);
    }

    private void addSubmitButton(int rowIndex)
    {
        Button button = new Button("SUBMIT");

        GridPane.setConstraints(button, 0, rowIndex);
        GridPane.setColumnSpan(button, 5);

        button.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                ChangeRequestCard changeRequestCard = new ChangeRequestCard("John Doe", ChangeRequestPriority.LOW, "Description");

                changeRequestCard.setDescription(nodes.get(InputIdList.DESC.toString()).getAccessibleText());
            }
        });

        gridPane.getChildren().add(button);
    }

    private void setTextField(InputIdList id, String fieldText, int columnIndex, int rowIndex, int columnSpan)
    {
        TextField textField = new TextField();
        if(id.equals(InputIdList.UUID))
        {
            textField.setEditable(false);
        }
        else if (id.equals(InputIdList.REQUEST))
        {
            textField.setEditable(false);
        }
        else if (id.equals(InputIdList.FULL_NAME))
        {
            textField.setEditable(false);
        }
        textField.setText(fieldText);
        textField.setId(id.toString());
        nodes.put(id.toString(), textField);
        GridPane.setConstraints(textField, columnIndex, rowIndex);
        GridPane.setColumnSpan(textField, columnSpan);

        gridPane.getChildren().add(textField);
    }

    private void setTextFieldPrompt(InputIdList id, String prompt, int columnIndex, int rowIndex, int columnSpan)
    {
        TextField textField = new TextField();
        textField.setId(id.toString());
        nodes.put(id.toString(), textField);
        textField.setPromptText(prompt);
        GridPane.setConstraints(textField, columnIndex, rowIndex);
        GridPane.setColumnSpan(textField, columnSpan);

        gridPane.getChildren().add(textField);
    }

    private void setTextArea(InputIdList id, String areaText, int columnIndex, int rowIndex, int columnSpan)
    {
        TextArea textArea = new TextArea();
        textArea.setText(areaText);
        textArea.setId(id.toString());
        nodes.put(id.toString(), textArea);
        GridPane.setConstraints(textArea, columnIndex, rowIndex);
        GridPane.setColumnSpan(textArea, columnSpan);

        gridPane.getChildren().add(textArea);
    }

    private void setTextAreaPrompt(InputIdList id, String prompt, int columnIndex, int rowIndex, int columnSpan)
    {
        TextArea textArea = new TextArea();
        textArea.setId(id.toString());
        nodes.put(id.toString(), textArea);
        textArea.setPromptText(prompt);
        GridPane.setConstraints(textArea, columnIndex, rowIndex);
        GridPane.setColumnSpan(textArea, columnSpan);

        gridPane.getChildren().add(textArea);
    }

    private void setLabel(String label, int columnIndex, int rowIndex, int columnSpan)
    {
        Text text = new Text(label + " ");
        text.getStyleClass().add("label");
        GridPane.setConstraints(text, columnIndex, rowIndex);
        GridPane.setValignment(text, VPos.TOP);

        gridPane.getChildren().add(text);
    }
}