package shapes;

import javafx.scene.paint.Color;

public class Shape
{
    //location
    private double x;
    private double y;

    //shape drawing settings
    private double thickness;
    private Color color;
    private boolean fill;

    public Shape(double x, double y, double thickness, Color color, boolean fill)
    {
        this.x = x;
        this.y = y;

        this.thickness = thickness;
        this.color = color;
        this.fill = fill;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getThickness()
    {
        return thickness;
    }

    public Color getColor()
    {
        return color;
    }

    public boolean isFill()
    {
        return fill;
    }

    public String toString()
    {
        return "Shape [x=" + x + ", y=" + y + ", thickness=" + thickness + ", color=" + color + ", fill=" + fill + "]";
    }
}
