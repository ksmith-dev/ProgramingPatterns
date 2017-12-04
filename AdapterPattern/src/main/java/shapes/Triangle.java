package shapes;

import javafx.scene.paint.Color;

public class Triangle extends Rectangle
{
    //triangles are defined according to their bounding box
    public Triangle(double x, double y, double width, double length, double thickness, Color color, boolean fill)
    {
        super(x, y, width, length, thickness, color, fill);
    }
}
