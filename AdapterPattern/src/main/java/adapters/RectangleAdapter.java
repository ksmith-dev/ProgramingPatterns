package adapters;

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Rectangle;

public class RectangleAdapter implements IShape
{
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;
    private double thickness;
    private boolean isFill;
    /**
     * | Description |
     * Creates a Rectangle class object
     * @param rectangle - Rectangle Shape Class object
     */
    public RectangleAdapter(Rectangle rectangle)
    {
        this.x = rectangle.getX();
        this.y = rectangle.getY();
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
        this.thickness = rectangle.getThickness();
        this.color = rectangle.getColor();
        this.isFill = rectangle.isFill();
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
        return this.x;
    }
    /**
     * | Description |
     * Returns the Y value of the grid system for the JavaFx stage
     * @return double - represnting the Y value
     */
    @Override
    public double getY()
    {
        return this.y;
    }
    /**
     * | Description |
     * Returns a double representing the current thickness of the stroke of the object
     * @return double - representing the current thickness
     */
    @Override
    public double getThickness()
    {
        return this.thickness;
    }
    /**
     * | Description |
     * Returns the current color of the object
     * @return JavaFX Color - representing the current color of the object
     */
    @Override
    public Color getColor()
    {
        return this.color;
    }
    /**
     * | Description |
     * Returns the current fill state of the object
     * @return boolean - representing the current fill state of the object true/false
     */
    @Override
    public boolean getFilled()
    {
        return this.isFill;
    }
    /**
     * | Description |
     * Draws the object on the graphic context passed into this method
     * @param graphics GraphicsContext - representing the GUI
     */
    @Override
    public void drawShape(GraphicsContext graphics)
    {
        if(isFill)
        {
            //set fill and stroke color and thickness
            graphics.setFill(color);
            graphics.setStroke(color);
            graphics.setLineWidth(thickness);
            //draw stroke and fill
            graphics.fillRect(x, y, width, height);
            graphics.strokeRect(x, y, width, height);
        }
        else
        {
            //set color and thickness
            graphics.setStroke(color);
            graphics.setLineWidth(thickness);
            //draw rectangle line
            graphics.strokeRect(x, y, width, height);
        }
    }
}
