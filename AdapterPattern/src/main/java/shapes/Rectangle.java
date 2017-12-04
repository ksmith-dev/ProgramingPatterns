package shapes;

import javafx.scene.paint.Color;

public class Rectangle extends Shape
{
    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height, double thickness, Color color, boolean fill)
    {
        super(x, y, thickness, color, fill);

        this.width = width;
        this.height = height;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public String toString()
    {
        return "RectangleAdapter [width=" + width + ", height=" + height + " " + super.toString() + "]";
    }
}
