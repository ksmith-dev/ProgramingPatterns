package ui;
/**
 *  This Class handles the user interface elements, such as the buttons of the calculator application as well
 *  as the display window.
 */
import calculator.Calculator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This Class generates the Calculator application user interface, using JavaFX staging elements.
 *  @author Kevin Smith
 *  @version    1.0
 */
public class CalculatorUI extends Application
{
    private static final int COLUMN_CONSTRAINT = 50;
    private static final int HGAP = 5;
    private static final int VGAP = 5;
    private static final int SCENE_WIDTH = 325;
    private static final int SCENE_HEIGHT = 250;
    private static final int GRID_TOP_PADDING = 20;
    private static final int GRID_RIGHT_PADDING = 60;
    private static final int GRID_BOTTOM_PADDING = 20;
    private static final int GRID_LEFT_PADDING = 60;
    private GridPane gridPane = new GridPane();
    private Label displayText = new Label();

    /**
     * This method starts the calculator application.
     * @param stage Stage - representing the calculator application stage
     * @throws Exception - error if error exists
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Calculator");
        stage.setScene(assemble());
        stage.show();
    }

    private Scene assemble()
    {
        gridPane.setHgap(HGAP);
        gridPane.setVgap(VGAP);
        gridPane.setPadding(new Insets(GRID_TOP_PADDING, GRID_RIGHT_PADDING, GRID_BOTTOM_PADDING, GRID_LEFT_PADDING));
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(COLUMN_CONSTRAINT), new ColumnConstraints(COLUMN_CONSTRAINT), new ColumnConstraints(COLUMN_CONSTRAINT));

        addButtonToScene("sin", 0, 0, 1, 1,50);
        addButtonToScene("cos", 1, 0, 1, 1,50);
        addButtonToScene("tan", 2, 0, 1, 1,50);
        addButtonToScene("C", 3, 0, 1, 1,50);
        addButtonToScene("7", 0, 1, 1, 1, 50);
        addButtonToScene("8", 1, 1, 1, 1, 50);
        addButtonToScene("9", 2, 1, 1, 1, 50);
        addButtonToScene("+", 3, 1, 1, 1, 50);
        addButtonToScene("4", 0, 2, 1, 1, 50);
        addButtonToScene("5", 1, 2, 1, 1, 50);
        addButtonToScene("6", 2, 2, 1, 1, 50);
        addButtonToScene("-", 3, 2, 1, 1, 50);
        addButtonToScene("1", 0, 3, 1, 1, 50);
        addButtonToScene("2", 1, 3, 1, 1, 50);
        addButtonToScene("3", 2, 3, 1, 1, 50);
        addButtonToScene("*", 3, 3, 1, 1, 50);
        addButtonToScene("0", 0, 4, 1, 1, 50);
        addButtonToScene("Enter", 1, 4, 2, 1, 110);
        addButtonToScene("/", 3, 4, 1, 1, 50);

        displayText.setText(Calculator.getDisplayLabel());
        displayText.setId("textDisplay");
        displayText.setAlignment(Pos.CENTER_RIGHT);
        displayText.setPrefWidth(Double.MAX_VALUE);
        displayText.setPadding(new Insets(5));

        //Border border = new Border(new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        //textDisplay.setBorder(border);

        gridPane.add(displayText, 0, 5, 4, 1);

        Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);

        scene.getStylesheets().add("/styles/style.css");

        return scene;
    }

    private void addButtonToScene(String label,Integer columnIndex, Integer rowIndex, Integer colSpan, Integer rowSpan, Integer preferWidth)
    {
        Button btn = new Button(label);
        btn.setId(label);
        btn.setPadding(new Insets(5, 10, 5, 10));
        btn.setPrefWidth(preferWidth);
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Calculator.addInput(btn.getId());
                displayText.setText(Calculator.getDisplayLabel());
            }
        });
        gridPane.add(btn, columnIndex, rowIndex, colSpan, rowSpan);

    }

    private void setLableText()
    {

    }
}
