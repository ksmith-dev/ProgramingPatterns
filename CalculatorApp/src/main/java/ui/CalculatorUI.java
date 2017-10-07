package ui;
/**
 *  This Class handles the user interface elements, such as the buttons of the calculator application as well
 *  as the display window.
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This Class generates the Calculator application user interface, using JavaFX staging elements.
 *  @author Kevin Smith
 *  @version    1.0
 */
public class CalculatorUI extends Application
{
    private static final int COLUMN_CONSTRAINT = 30;
    private static final int HGAP = 5;
    private static final int VGAP = 5;
    private static final int SCENE_WIDTH = 250;
    private static final int SCENE_HEIGHT = 220;
    private static final int GRID_TOP_PADDING = 20;
    private static final int GRID_RIGHT_PADDING = 60;
    private static final int GRID_BOTTOM_PADDING = 20;
    private static final int GRID_LEFT_PADDING = 60;
    private GridPane gridPane = new GridPane();

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

        addButtonToScene("7", 0, 0, 1, 1, 30);
        addButtonToScene("8", 1, 0, 1, 1, 30);
        addButtonToScene("9", 2, 0, 1, 1, 30);
        addButtonToScene("+", 3, 0, 1, 1, 50);
        addButtonToScene("4", 0, 1, 1, 1, 30);
        addButtonToScene("5", 1, 1, 1, 1, 30);
        addButtonToScene("6", 2, 1, 1, 1, 30);
        addButtonToScene("-", 3, 1, 1, 1, 30);
        addButtonToScene("1", 0, 2, 1, 1, 30);
        addButtonToScene("2", 1, 2, 1, 1, 30);
        addButtonToScene("3", 2, 2, 1, 1, 30);
        addButtonToScene("*", 3, 2, 1, 1, 30);
        addButtonToScene("0", 0, 3, 1, 1, 30);
        addButtonToScene("Enter", 1, 3, 3, 1, 65);
        addButtonToScene("/", 3, 3, 1, 1, 30);

        Label textDisplay = new Label();
        textDisplay.setAlignment(Pos.CENTER_RIGHT);
        textDisplay.setPrefWidth(Double.MAX_VALUE);
        textDisplay.setPadding(new Insets(5));

        //Border border = new Border(new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        //textDisplay.setBorder(border);

        gridPane.add(textDisplay, 0, 4, 4, 1);

        Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);

        scene.getStylesheets().add("/styles/style.css");

        return scene;
    }

    private void addButtonToScene(String label,Integer columnIndex, Integer rowIndex, Integer colSpan, Integer rowSpan, Integer preferWidth)
    {
        Button btn = new Button(label);
        btn.setPadding(new Insets(5, 10, 5, 10));
        btn.setPrefWidth(preferWidth);
        gridPane.add(btn, columnIndex, rowIndex, colSpan, rowSpan);
    }
}
