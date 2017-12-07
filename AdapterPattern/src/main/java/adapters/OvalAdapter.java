package adapters;
/**
 * | Description |
 * This class adapts the Circle class shape class into a class that works with JavaFx
 */

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Circle;

/**
 * | Description |
 * This class adapts the Circle class shape class into a class that works with JavaFx
 * @author Kevin Smith
 * @version 1.0
 */
public class OvalAdapter implements IShape
{
    private double radius;
    private double x;
    private double y;
    private double thickness;
    private Color color;
    private boolean isFill;

    /**
     * | Constructor |
     * Builds a Circle class object
     * @param circle - Circle class shape
     */
    public OvalAdapter(Circle circle)
    {
        this.radius = circle.getRadius();
        this.x = circle.getX();
        this.y = circle.getY();
        this.thickness = circle.getThickness();
        this.color = circle.getColor();
        this.isFill = circle.isFill();
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
        if (isFill)
        {
            graphics.setFill(color);
            graphics.setStroke(color);
            graphics.setLineWidth(thickness);

            graphics.fillOval(x, y, radius, radius);
            graphics.strokeOval(x, y, radius, radius);
        }
        else
        {
            graphics.setStroke(color);
            graphics.setLineWidth(thickness);

            graphics.strokeOval(x, y, radius, radius);
        }
    }
}
