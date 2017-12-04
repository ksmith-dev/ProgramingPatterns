package shapes;

import javafx.scene.paint.Color;

public class Circle extends Shape
{
    private double radius;

    public Circle(double radius, double x, double y, double thickness, Color color, boolean fill)
    {
        super(x, y, thickness, color, fill);

        this.radius = radius;
    }

    public double getRadius()
    {
        return radius;
    }

    public String toString()
    {
        return "Circle [radius=" + radius + " " + super.toString() + "]";
    }
}
