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
    private Circle circle;

    /**
     * | Constructor |
     * Builds a Circle class object
     * @param circle - Circle class shape
     */
    public OvalAdapter(Circle circle)
    {
        this.circle = circle;
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
        circle = new Circle(
                circle.getRadius(),
                circle.getX(),
                circle.getY(),
                thickness,
                circle.getColor(),
                circle.isFill());
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
        circle = new Circle(
                circle.getRadius(),
                circle.getX(),
                circle.getY(),
                circle.getThickness(),
                color,
                circle.isFill());
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
        circle = new Circle(
                circle.getRadius(),
                circle.getX(),
                circle.getY(),
                circle.getThickness(),
                circle.getColor(),
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
        return circle.getX();
    }
    /**
     * | Description |
     * Returns the Y value of the grid system for the JavaFx stage
     * @return double - represnting the Y value
     */
    @Override
    public double getY()
    {
        return circle.getY();
    }

    /**
     * | Description |
     * Returns a double representing the current thickness of the stroke of the object
     * @return double - representing the current thickness
     */
    @Override
    public double getThickness()
    {
        return circle.getThickness();
    }

    /**
     * | Description |
     * Returns the current color of the object
     * @return JavaFX Color - representing the current color of the object
     */
    @Override
    public Color getColor()
    {
        return circle.getColor();
    }

    /**
     * | Description |
     * Returns the current fill state of the object
     * @return boolean - representing the current fill state of the object true/false
     */
    @Override
    public boolean getFilled()
    {
        return circle.isFill();
    }

    /**
     * | Description |
     * Draws the object on the graphic context passed into this method
     * @param graphics GraphicsContext - representing the GUI
     */
    @Override
    public void drawShape(GraphicsContext graphics)
    {
        if (circle.isFill())
        {
            graphics.setFill(circle.getColor());
            graphics.setStroke(circle.getColor());
            graphics.setLineWidth(circle.getThickness());

            graphics.fillOval(circle.getX(), circle.getY(), circle.getRadius(), circle.getRadius());
            graphics.strokeOval(circle.getX(), circle.getY(), circle.getRadius(), circle.getRadius());
        }
        else
        {
            graphics.setStroke(circle.getColor());
            graphics.setLineWidth(circle.getThickness());

            graphics.strokeOval(circle.getX(), circle.getY(), circle.getRadius(), circle.getRadius());
        }
    }
}
