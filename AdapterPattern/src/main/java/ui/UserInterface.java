package ui;

import adapters.LineAdapter;
import adapters.OvalAdapter;
import adapters.RectangleAdapter;
import adapters.TriangleAdapter;
import drawing.IShape;
import drawing.SavedShapes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import shapes.Circle;
import shapes.Line;
import shapes.Rectangle;
import shapes.Triangle;

public class UserInterface
{
    private static final double SLIDER_MIN = 100.00;
    private static final double SLIDER_MAX = 200.00;
    private static final double SLIDER_DEFAULT_VALUE = 150;
    private static final int SPINNER_H_GAP = 10;
    private static final int SPINNER_V_GAP = 10;
    private static final double DEFAULT_SPACING = 20.00;
    private static final int MAX_WIDTH = 750;
    private static final int MAX_HEIGHT = 700;
    private static final int SCENE_WIDTH = 900;
    private static final int SCENE_HEIGHT = 800;
    private static final Color DEFAULT_COLOR = Color.CORAL;
    private static final int DEFAULT_THICKNESS = 1;
    private static final int SPINNER_MIN_VALUE = 1;
    private static final int SPINNER_MAX_VALUE = 5;

    private SavedShapes savedShapes = new SavedShapes();
    private IShape currentIShape;
    private GraphicsContext graphicsContext;
    private ShapeClass[] imgPaths =  { ShapeClass.CIRCLE, ShapeClass.RECTANGLE, ShapeClass.TRIANGLE, ShapeClass.LINE };

    private ShapeClass currentShapeClass = ShapeClass.CIRCLE;
    private Color color = DEFAULT_COLOR;
    private double thickness = DEFAULT_THICKNESS;
    private boolean isFill = false;
    private double size = SLIDER_DEFAULT_VALUE;

    public Scene getScene()
    {
        BorderPane pane = new BorderPane();
        //set default left pane
        HBox defaultBox = new HBox();
        defaultBox.setMinWidth(DEFAULT_SPACING);
        pane.setLeft(defaultBox);
        //set default bottom pane
        defaultBox = new HBox();
        defaultBox.setMinHeight(DEFAULT_SPACING);
        pane.setBottom(defaultBox);
        //set default right pane
        defaultBox = new HBox();
        defaultBox.setMinWidth(DEFAULT_SPACING);
        pane.setRight(defaultBox);
        //center pane content
        HBox hBox = new HBox();
        hBox.setSpacing(DEFAULT_SPACING);
        hBox.setAlignment(Pos.CENTER);

        //generate toggle buttons for each image path
        for (ShapeClass imgPath : imgPaths)
        {
            hBox.getChildren().add(getToggleButton(imgPath));
        }

        addColorPickerToHBox(hBox);
        addCheckBoxToHBox(hBox);
        addSpinnerToPane(hBox);
        addSliderToHBox(hBox);
        pane.setTop(hBox);
        addCanvasToPane(pane);

        return new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private ToggleButton getToggleButton(ShapeClass shapeClass)
    {
        Image img = new Image("img/" + shapeClass.toString().toLowerCase() + ".png");
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(DEFAULT_SPACING);
        imgView.setFitHeight(DEFAULT_SPACING);
        ToggleButton toggleButton = new ToggleButton("", imgView);
        toggleButton.setId(shapeClass.toString().toLowerCase());

        if (shapeClass.equals(ShapeClass.CIRCLE))
        {
            toggleButton.setSelected(true);
        }

        Tooltip tooltip = new Tooltip();
        tooltip.setText(shapeClass.toString().toLowerCase());
        toggleButton.setTooltip(tooltip);

        toggleButton.setOnMouseClicked(event ->
        {
            switch (toggleButton.getId())
            {
                case "circle" :
                    currentShapeClass = ShapeClass.CIRCLE;
                    break;
                case "rectangle" :
                    currentShapeClass = ShapeClass.RECTANGLE;
                    break;
                case "triangle" :
                    currentShapeClass = ShapeClass.TRIANGLE;
                    break;
                default :
                    currentShapeClass = ShapeClass.LINE;
                    break;
            }
        });
        return toggleButton;
    }

    private void updateCurrentIShape()
    {
        if (graphicsContext != null && currentIShape != null)
        {
            savedShapes.update(currentIShape, thickness, color, isFill);
            savedShapes.drawShapes(graphicsContext);
        }
    }

    private void addColorPickerToHBox(HBox hBox)
    {
        //add color picker
        ColorPicker colorPicker = new ColorPicker(color);
        colorPicker.setStyle("-fx-color-label-visible: false;");

        colorPicker.setOnAction(event ->
        {
            color = colorPicker.getValue();
            savedShapes.update(currentIShape, thickness, color, isFill);
            savedShapes.drawShapes(graphicsContext);
        });

        hBox.getChildren().add(colorPicker);
    }

    private void addSpinnerToPane(HBox hBox)
    {
        //add filled toggle
        Label label = new Label("Thickness:");
        final Spinner<Integer> spinner = new Spinner<>();

        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(SPINNER_MIN_VALUE, SPINNER_MAX_VALUE, DEFAULT_THICKNESS);

        spinner.setValueFactory(valueFactory);

        spinner.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            thickness = newValue.doubleValue();
            updateCurrentIShape();
        });


        FlowPane root = new FlowPane();
        root.setHgap(SPINNER_H_GAP);
        root.setVgap(SPINNER_V_GAP);
        root.setPadding(new Insets(10));

        hBox.getChildren().addAll(label, spinner);
    }

    private void addCheckBoxToHBox(HBox hBox)
    {
        CheckBox checkBox = new CheckBox("Fill");

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            isFill = newValue;
            updateCurrentIShape();
        });

        hBox.getChildren().add(checkBox);
    }

    private void addSliderToHBox(HBox hBox)
    {
        //add slider to hBox
        Slider slider = new Slider();
        slider.setMin(SLIDER_MIN);
        slider.setMax(SLIDER_MAX);
        slider.setValue(SLIDER_DEFAULT_VALUE);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(5);
        slider.setMinorTickCount(1);
        slider.setBlockIncrement(1);
        slider.setPadding(new Insets(10, 0, 0, 0));

        slider.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            size = newValue.doubleValue();
        });

        hBox.getChildren().add(slider);
    }

    private void addCanvasToPane(BorderPane pane)
    {
        StackPane stackPane = new StackPane();
        stackPane.setMaxWidth(MAX_WIDTH);
        stackPane.setMaxHeight(MAX_HEIGHT);
        Canvas canvas = new Canvas(MAX_WIDTH, MAX_HEIGHT);
        stackPane.getChildren().add(canvas);
        stackPane.setStyle("-fx-background-color: lightgray;");

        canvas.setOnMouseClicked(event ->
        {
            graphicsContext = canvas.getGraphicsContext2D();

            double x = event.getX();
            double y = event.getY();

            switch (currentShapeClass)
            {
                case CIRCLE:
                    OvalAdapter ovalAdapter = new OvalAdapter(new Circle(size, x, y, thickness, color, isFill));
                    currentIShape = ovalAdapter;
                    savedShapes.add(ovalAdapter);
                    break;
                case RECTANGLE:
                    RectangleAdapter rectangleAdapter = new RectangleAdapter(new Rectangle(x, y, size, size / 2, thickness, color, isFill));
                    currentIShape = rectangleAdapter;
                    savedShapes.add(rectangleAdapter);
                    break;
                case TRIANGLE:
                    TriangleAdapter triangleAdapter = new TriangleAdapter(new Triangle(x, y, size, size, thickness, color, isFill));
                    currentIShape = triangleAdapter;
                    savedShapes.add(triangleAdapter);
                    break;
                case LINE:
                    LineAdapter lineAdapter = new LineAdapter(new Line(x, y, x*2, y*2, thickness, color, isFill));
                    currentIShape = lineAdapter;
                    savedShapes.add(lineAdapter);
                    break;
                default:
                    System.out.println("Something Went Wrong, the currentShapeClass switch statement reached default status");
                    break;
            }
            savedShapes.drawShapes(graphicsContext);
        });

        pane.setCenter(stackPane);
    }
}
