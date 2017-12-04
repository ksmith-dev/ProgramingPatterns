package shapes;

import javafx.scene.paint.Color;

public class Line extends Shape
{
    private double x2;
    private double y2;

    public Line(double x, double y, double x2, double y2, double thickness, Color color, boolean fill)
    {
        super(x, y, thickness, color, fill);

        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX2()
    {
        return x2;
    }

    public double getY2()
    {
        return y2;
    }

    public String toString()
    {
        return "LineAdapter [x2=" + x2 + ", y2=" + y2 + " " + super.toString() + "]";
    }
}
