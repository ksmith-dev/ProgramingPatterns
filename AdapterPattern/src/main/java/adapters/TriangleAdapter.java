package adapters;
/**
 * | Description |
 * This class adapts Triangle shape class for this JavaFx application
 */

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Triangle;

/**
 * | Description |
 * This class adapts Triangle shape class for this JavaFx application
 * @author Kevin Smith
 * @version 1.0
 */
public class TriangleAdapter implements IShape
{
    private Triangle triangle;

    /**
     * | Description |
     * Creates a Triangle class object
     * @param triangle - Triangle shape class
     */
    public TriangleAdapter(Triangle triangle)
    {
        this.triangle = triangle;
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
        triangle = new Triangle(
                triangle.getX(),
                triangle.getY(),
                triangle.getWidth(),
                triangle.getHeight(),
                thickness,
                triangle.getColor(),
                triangle.isFill());
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
        triangle = new Triangle(
                triangle.getX(),
                triangle.getY(),
                triangle.getWidth(),
                triangle.getHeight(),
                triangle.getThickness(),
                color,
                triangle.isFill());
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
        triangle = new Triangle(
                triangle.getX(),
                triangle.getY(),
                triangle.getWidth(),
                triangle.getHeight(),
                triangle.getThickness(),
                triangle.getColor(),
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
        return triangle.getX();
    }
    /**
     * | Description |
     * Returns the Y value of the grid system for the JavaFx stage
     * @return double - represnting the Y value
     */
    @Override
    public double getY()
    {
        return triangle.getY();
    }
    /**
     * | Description |
     * Returns a double representing the current thickness of the stroke of the object
     * @return double - representing the current thickness
     */
    @Override
    public double getThickness()
    {
        return getThickness();
    }
    /**
     * | Description |
     * Returns the current color of the object
     * @return JavaFX Color - representing the current color of the object
     */
    @Override
    public Color getColor()
    {
        return triangle.getColor();
    }
    /**
     * | Description |
     * Returns the current fill state of the object
     * @return boolean - representing the current fill state of the object true/false
     */
    @Override
    public boolean getFilled()
    {
        return triangle.isFill();
    }
    /**
     * | Description |
     * Draws the object on the graphic context passed into this method
     * @param graphics GraphicsContext - representing the GUI
     */
    @Override
    public void drawShape(GraphicsContext graphics)
    {
        if (triangle.isFill())
        {
            graphics.setStroke(triangle.getColor());
            graphics.setFill(triangle.getColor());
            graphics.setLineWidth(triangle.getThickness());
            graphics.fillPolygon(new double[]{
                    triangle.getX(),
                    triangle.getX() + (triangle.getWidth() / 2),
                    triangle.getX() + triangle.getWidth() },
                    new double[]{triangle.getY(), triangle.getY() + triangle.getHeight(), triangle.getY()}, 3);

        }
        else
        {
            graphics.setStroke(triangle.getColor());
            graphics.setLineWidth(triangle.getThickness());
            graphics.strokePolygon(new double[]{
                    triangle.getX(),
                    triangle.getX() + (triangle.getWidth() / 2),
                    triangle.getX() + triangle.getWidth() },
                    new double[]{triangle.getY(), triangle.getY() + triangle.getHeight(), triangle.getY()}, 3);
        }
    }
}
