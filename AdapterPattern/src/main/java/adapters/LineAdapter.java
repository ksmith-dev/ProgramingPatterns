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
    private Line line;

    /**
     * | Constructor |
     * Adapts The Line class for use in this JavaFx application
     * @param line
     */
    public LineAdapter(Line line)
    {
        this.line = line;
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
        line = new Line(
                line.getX(),
                line.getY(),
                line.getX2(),
                line.getY2(),
                thickness,
                line.getColor(),
                line.isFill());
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
        line = new Line(
                line.getX(),
                line.getY(),
                line.getX2(),
                line.getY2(),
                line.getThickness(),
                color,
                line.isFill());
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
        line = new Line(
                line.getX(),
                line.getY(),
                line.getX2(),
                line.getY2(),
                line.getThickness(),
                line.getColor(),
                isFill);
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
        return line.getX();
    }
    /**
     * | Description |
     * Returns the Y value of the grid system for the JavaFx stage
     * @return double - represnting the Y value
     */
    @Override
    public double getY()
    {
        return line.getY();
    }
    /**
     * | Description |
     * Returns a double representing the current thickness of the stroke of the object
     * @return double - representing the current thickness
     */
    @Override
    public double getThickness()
    {
        return line.getThickness();
    }
    /**
     * | Description |
     * Returns the current color of the object
     * @return JavaFX Color - representing the current color of the object
     */
    @Override
    public Color getColor()
    {
        return line.getColor();
    }
    /**
     * | Description |
     * Returns the current fill state of the object
     * @return boolean - representing the current fill state of the object true/false
     */
    @Override
    public boolean getFilled()
    {
        return line.isFill();
    }
    /**
     * | Description |
     * Draws the object on the graphic context passed into this method
     * @param graphics GraphicsContext - representing the GUI
     */
    @Override
    public void drawShape(GraphicsContext graphics)
    {
        graphics.setStroke(line.getColor());
        graphics.setLineWidth(line.getThickness());
        graphics.strokeLine(line.getX(), line.getY(), line.getX2(), line.getY2());
    }
}
