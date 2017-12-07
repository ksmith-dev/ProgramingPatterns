package adapters;
/**
 * | Description |
 * This class adapts the Line shape class for this JavaFx project
 */

import drawing.IShape;
import shapes.Line;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * | Description |
 * This class adapts the Line shape class for this JavaFx project
 * @author Kevin Smith
 * @version 1.0
 */
public class LineAdapter implements IShape
{
    private double x;
    private double y;
    private double x2;
    private double y2;
    private double thickness;
    private Color color;
    private boolean isFill;

    /**
     * | Constructor |
     * Adapts The Line class for use in this JavaFx application
     * @param line
     */
    public LineAdapter(Line line)
    {
        x = line.getX();
        y = line.getY();
        x2 = line.getX2();
        y2 = line.getY2();
        thickness = line.getThickness();
        color = line.getColor();
        isFill = line.isFill();
    }
    /**
     * | Description |
     * Sets thickens for graphic stroke
     * @param thickness double - representing the thickness of the stroke
     * @return IShape - Shape Interface object
     */
    @Override
    public IShape setThickness(double thickness)
    {
        this.thickness = thickness;
        return this;
    }
    /**
     * | Description |
     * Sets Class level color for the object to be displayed with
     * @param color JavaFx Color - representing the color of the object
     * @return IShape - Shape Interface Object
     */
    @Override
    public IShape setColor(Color color)
    {
        this.color = color;
        return this;
    }
    /**
     * | Description |
     * Identifies the state of an objects fill true/false
     * @param isFill boolean - representing the fill state of the object
     * @return IShape - Shape Interface Object
     */
    @Override
    public IShape setFilled(boolean isFill)
    {
        this.isFill = isFill;
        return this;
    }
    /**
     * | Description |
     * Returns the X value of the grid system for the JavaFx stage
     * @return double - represnting the X value
     */
    @Override
    public double getX()
    {
        return x;
    }
    /**
     * | Description |
     * Returns the Y value of the grid system for the JavaFx stage
     * @return double - represnting the Y value
     */
    @Override
    public double getY()
    {
        return y;
    }
    /**
     * | Description |
     * Returns a double representing the current thickness of the stroke of the object
     * @return double - representing the current thickness
     */
    @Override
    public double getThickness()
    {
        return thickness;
    }
    /**
     * | Description |
     * Returns the current color of the object
     * @return JavaFX Color - representing the current color of the object
     */
    @Override
    public Color getColor()
    {
        return color;
    }
    /**
     * | Description |
     * Returns the current fill state of the object
     * @return boolean - representing the current fill state of the object true/false
     */
    @Override
    public boolean getFilled()
    {
        return isFill;
    }
    /**
     * | Description |
     * Draws the object on the graphic context passed into this method
     * @param graphics GraphicsContext - representing the GUI
     */
    @Override
    public void drawShape(GraphicsContext graphics)
    {
        graphics.setStroke(color);
        graphics.setLineWidth(thickness);
        graphics.strokeLine(x, y, x2, y2);
    }
}
