package Control;

import Model.RequestStates;
import View.ChangeRequest;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.ChangeRequestCard;
import org.junit.BeforeClass;
import org.junit.Test;
import View.ViewInputID;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class ChangeRequestControlTest extends Application
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
    public void testInputDataMapToCorrectValues() throws Exception
    {
        HashMap<ViewInputID, Node> inputNodes = new HashMap<>();

        UUID uuidFirst = UUID.randomUUID();

        inputNodes.put(ViewInputID.NUMBER, new TextField(uuidFirst.toString()));
        inputNodes.put(ViewInputID.EFFORT, new TextField("10"));
        inputNodes.put(ViewInputID.IMPACT, new TextField("impacted"));
        inputNodes.put(ViewInputID.DATE, new TextField("12/04/2017"));
        inputNodes.put(ViewInputID.INCLUSION, new TextField("23348456"));
        inputNodes.put(ViewInputID.ACCEPTED, new TextField("12/01/2017"));
        inputNodes.put(ViewInputID.DESC, new TextArea("Test"));
        inputNodes.put(ViewInputID.REQUESTER, new TextField("John Doe"));

        ChangeRequestCard changeRequestCard = ChangeRequestControl.parseInputsGetChangeRequestCard(inputNodes);

        ChangeRequestControl.addChangeRequest(changeRequestCard);

        inputNodes = new HashMap<>();

        UUID uuidSecond = UUID.randomUUID();

        inputNodes.put(ViewInputID.NUMBER, new TextField(uuidSecond.toString()));
        inputNodes.put(ViewInputID.EFFORT, new TextField("10"));
        inputNodes.put(ViewInputID.IMPACT, new TextField("impacted"));
        inputNodes.put(ViewInputID.DATE, new TextField("12/04/2017"));
        inputNodes.put(ViewInputID.INCLUSION, new TextField("23348456"));
        inputNodes.put(ViewInputID.ACCEPTED, new TextField("12/01/2017"));
        inputNodes.put(ViewInputID.DESC, new TextArea("description"));
        inputNodes.put(ViewInputID.REQUESTER, new TextField("Jow Blow"));

        changeRequestCard = ChangeRequestControl.parseInputsGetChangeRequestCard(inputNodes);

        ChangeRequestControl.addChangeRequest(changeRequestCard);

        HashMap<UUID, ChangeRequestCard> changeRequestCards = ChangeRequestControl.getChangeRequestForTests();

        ChangeRequestControl.exportDataToFile("ChangeRequestCardsTest.json");

        assertTrue(changeRequestCards.containsKey(uuidFirst));
        assertTrue(changeRequestCards.get(uuidFirst).getId().equals(uuidFirst));
        assertTrue(changeRequestCards.get(uuidFirst).getDescription().equals("Test"));

        assertTrue(changeRequestCards.containsKey(uuidSecond));
        assertTrue(changeRequestCards.get(uuidSecond).getId().equals(uuidSecond));
        assertTrue(changeRequestCards.get(uuidSecond).getDescription().equals("description"));
    }

    @Test
    public void testUUIDEqualsChangeRequestUUID()
    {

        ChangeRequestControl.importDataFromFile("ChangeRequestCardsTest.json");

        HashMap<UUID, ChangeRequestCard> changeRequestCards = ChangeRequestControl.getChangeRequestForTests();

        for (Map.Entry<UUID, ChangeRequestCard> entry : changeRequestCards.entrySet())
        {
            UUID uuid = (UUID) entry.getKey();
            ChangeRequestCard changeRequestCard = (ChangeRequestCard) entry.getValue();

            assertTrue(changeRequestCard != null);
            assertTrue(entry.getKey().equals(changeRequestCard.getId()));
        }
    }

    @Test
    public void testExportJson()
    {
        File file = new File("ChangeRequestCardsTest.json");

        if (file.delete())
        {
            System.out.println("file was deleted");
        }
        else
        {
            System.out.println("file was not deleted");
        }

        ChangeRequestCard changeRequestCard = new ChangeRequestCard();

        changeRequestCard.setId(UUID.randomUUID());
        changeRequestCard.setRequesterName("John Smith");
        changeRequestCard.setDescription("Test Request");
        changeRequestCard.setRequestDate(new Date());
        changeRequestCard.setImpacted("Test Impacted");

        ChangeRequestControl.addChangeRequest(changeRequestCard);
        assertTrue(ChangeRequestControl.exportDataToFile("ChangeRequestCardsTest.json"));
    }

    @Test
    public void testImportJson()
    {
        assertTrue(ChangeRequestControl.importDataFromFile("ChangeRequestCardsTest.json"));
    }

    @Test
    public void testImportDataIsCorrect()
    {
        UUID uuid = UUID.randomUUID();

        File file = new File("ChangeRequestCardsTest.json");
        if (file.delete())
        {
            ChangeRequestControl.emptyChangeRequestCardsForTesting();
            ChangeRequestCard changeRequestCard = new ChangeRequestCard();

            changeRequestCard.setId(uuid);
            changeRequestCard.setRequestDate(new Date());
            changeRequestCard.setRequesterName("John Doe");

            ChangeRequestControl.addChangeRequest(changeRequestCard);

            ChangeRequestControl.exportDataToFile("ChangeRequestCardsTest.json");
        }

        ChangeRequestControl.importDataFromFile("ChangeRequestCardsTest.json");
        HashMap<UUID, ChangeRequestCard> changeRequestCards = ChangeRequestControl.getChangeRequestForTests();

        assertTrue(changeRequestCards.size() == 1);
        assertTrue(changeRequestCards.get(uuid).getId().equals(uuid));
        assertTrue(changeRequestCards.get(uuid).getRequesterName().equals("John Doe"));
    }

    @Test
    public void testRequestDateState()
    {
        ChangeRequestCard changeRequestCard = new ChangeRequestCard();
        DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        Date acceptedDate = null;
        Date rejectedDate = null;
        Date completedDate = null;

        try
        {
            acceptedDate = date.parse("12/01/2017");
            rejectedDate = date.parse("12/04/2017");
            completedDate = date.parse("12/08/2017");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        changeRequestCard.addDate(RequestStates.ACCEPTED, acceptedDate);
        changeRequestCard.addDate(RequestStates.REJECTED, rejectedDate);
        changeRequestCard.addDate(RequestStates.COMPLETED, completedDate);

        assertTrue(RequestStates.COMPLETED.equals(changeRequestCard.getState()));
    }
}