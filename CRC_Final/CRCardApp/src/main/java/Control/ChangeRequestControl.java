package Control;

import FileInOut.exporting.ExportJson;
import FileInOut.importing.ImportJson;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.ChangeRequestCard;
import Model.RequestPriority;
import Model.RequestStates;
import View.ChangeRequest;
import View.ChangeRequestList;
import View.ViewInputID;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChangeRequestControl extends Application
{
    private static HashMap<UUID, ChangeRequestCard> changeRequestCards = new HashMap<>();
    private static final double STAGE_WIDTH = 1000;
    private static final double STAGE_HEIGHT = 850;

    private static Stage stage;

    private static String[] names = {"John Doe", "Joe Blow", "Johnathan Nolan", "Brandon Lee", "Neil Nealson", "Jack Black"};
    private static String currentUser;


    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        this.stage.setTitle("Change Request Card");
        this.stage.setWidth(STAGE_WIDTH);
        this.stage.setHeight(STAGE_HEIGHT);

        getLaunchScene();

        this.stage.show();
    }

    public void getLaunchScene()
    {
        if (importDataFromFile("ChangeRequestCards.json"))
        {
            stage.setScene(getStageToChangeRequestListView());
        }
        else
        {
            stage.setScene(getStageToChangeRequestFormView());
        }
    }

    public static ChangeRequestCard parseInputsGetChangeRequestCard(Map<ViewInputID, Node> inputNodes)
    {
        ChangeRequestCard changeRequestCard = new ChangeRequestCard();

        TextField textField;
        TextArea textArea;

        Iterator iterator = inputNodes.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry keyValuePair = (Map.Entry) iterator.next();
            ViewInputID viewInputID = (ViewInputID) keyValuePair.getKey();

            if (keyValuePair.getValue() instanceof TextField)
            {
                switch (viewInputID)
                {
                    case NUMBER:
                        textField = (TextField) keyValuePair.getValue();
                        UUID uuid = UUID.fromString(textField.getText());
                        changeRequestCard.setId(uuid);
                        break;
                    case REQUESTER:
                        changeRequestCard.setRequesterName(currentUser);
                        break;
                    case ACCEPTED:
                        textField = (TextField) keyValuePair.getValue();
                        if (!textField.getText().equals(""))
                        {
                            try
                            {
                                changeRequestCard.addDate(RequestStates.ACCEPTED, new SimpleDateFormat("MM/dd/yyyy").parse(textField.getText()));
                            }
                            catch (ParseException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case REJECTED:
                        textField = (TextField) keyValuePair.getValue();
                        if (!textField.getText().equals(""))
                        {
                            try
                            {
                                changeRequestCard.addDate(RequestStates.REJECTED, new SimpleDateFormat("MM/dd/yyyy").parse(textField.getText()));
                            }
                            catch (ParseException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case PROCESSING:
                        textField = (TextField) keyValuePair.getValue();
                        if (!textField.getText().equals(""))
                        {
                            try
                            {
                                changeRequestCard.addDate(RequestStates.PROCESSING, new SimpleDateFormat("MM/dd/yyyy").parse(textField.getText()));
                            }
                            catch (ParseException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case COMPLETION:
                        textField = (TextField) keyValuePair.getValue();
                        if (!textField.getText().equals(""))
                        {
                            try
                            {
                                changeRequestCard.addDate(RequestStates.COMPLETED, new SimpleDateFormat("MM/dd/yyyy").parse(textField.getText()));
                            }
                            catch (ParseException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case EFFORT:
                        textField = (TextField) keyValuePair.getValue();
                        if (!textField.getText().equals(""))
                        {
                            changeRequestCard.setEstimatedEffort(Integer.valueOf(textField.getText()));
                        }
                        break;
                    case INCLUSION:
                        textField = (TextField) keyValuePair.getValue();
                        changeRequestCard.setInclusionId(textField.getText());
                        break;
                }
            }
            else if (keyValuePair.getValue() instanceof TextArea)
            {
                switch (viewInputID)
                {
                    case DESC:
                        textArea = (TextArea) keyValuePair.getValue();
                        changeRequestCard.setDescription(textArea.getText());
                        break;
                    case IMPACT:
                        textArea = (TextArea) keyValuePair.getValue();
                        changeRequestCard.setImpacted(textArea.getText());
                        break;
                }
            }
            else
            {
                switch (viewInputID)
                {
                    case PRIORITY:
                        ComboBox comboBox = (ComboBox) keyValuePair.getValue();
                        RequestPriority priority = (RequestPriority) comboBox.getValue();
                        changeRequestCard.setPriority(priority);
                        break;
                }
            }
            //avoids a ConcurrentModificationException
            iterator.remove();
        }

        return changeRequestCard;
    }

    public static void addChangeRequest(ChangeRequestCard changeRequestCard)
    {
        changeRequestCards.put(changeRequestCard.getId(), changeRequestCard);
    }

    public static boolean importDataFromFile(String path)
    {
        ImportJson importModel = new ImportJson();
        if (importModel.importJson(path))
        {
            changeRequestCards = importModel.getChangeRequestCards();
            if (!changeRequestCards.isEmpty())
            {
                return true;
            }
        }
        return false;
    }

    public static boolean exportDataToFile(String path)
    {
        ExportJson model = new ExportJson(changeRequestCards);
        if (model.exportJson(path))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Scene getStageToChangeRequestListView()
    {
        ChangeRequestList changeRequestListView = new ChangeRequestList(changeRequestCards);
        Scene scene = changeRequestListView.getNewListView();
        scene.getStylesheets().add("styles/styles.css");
        return scene;
    }

    public static Scene getStageToChangeRequestFormView()
    {
        currentUser  = names[new Random().nextInt(5)];
        ChangeRequest changeRequest = new ChangeRequest();
        Scene scene = changeRequest.getChangeRequestView(currentUser);
        scene.getStylesheets().add("styles/styles.css");
        return scene;
    }

    public static void setStageToScene(Scene scene)
    {
        stage.setScene(scene);
    }

    public static HashMap<UUID, ChangeRequestCard> getChangeRequestForTests()
    {
        return changeRequestCards;
    }

    public static void emptyChangeRequestCardsForTesting()
    {
        changeRequestCards = new HashMap<>();
    }
}
