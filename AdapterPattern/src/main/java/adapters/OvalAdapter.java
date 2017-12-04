package adapters;

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Circle;

public class OvalAdapter implements IShape
{
    private double radius;
    private double x;
    private double y;
    private double thickness;
    private Color color;
    private boolean isFill;

    public OvalAdapter(Circle circle)
    {
        this.radius = circle.getRadius();
        this.x = circle.getX();
        this.y = circle.getY();
        this.thickness = circle.getThickness();
        this.color = circle.getColor();
        this.isFill = circle.isFill();
    }

    @Override
    public IShape setThickness(double thickness)
    {
        this.thickness = thickness;
        return this;
    }

    @Override
    public IShape setColor(Color color)
    {
        this.color = color;
        return this;
    }

    @Override
    public IShape setFilled(boolean isFilled)
    {
        this.isFill = isFilled;
        return this;
    }

    @Override
    public double getX()
    {
        return x;
    }

    @Override
    public double getY()
    {
        return y;
    }

    @Override
    public double getThickness()
    {
        return thickness;
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    @Override
    public boolean getFilled()
    {
        return isFill;
    }

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
