package adapters;

import drawing.IShape;
import shapes.Line;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineAdapter implements IShape
{
    private double x;
    private double y;
    private double x2;
    private double y2;
    private double thickness;
    private Color color;
    private boolean isFill;

    public LineAdapter(Line line)
    {
        x = line.getX();
        y = line.getY();
        x2 = line.getX2();
        y2 = line.getY2();
        thickness = line.getThickness();
        color = line.getColor();
        isFill = line.isFill();
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
    public IShape setFilled(boolean isFill)
    {
        this.isFill = isFill;
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
        graphics.setStroke(color);
        graphics.setLineWidth(thickness);
        graphics.strokeLine(x, y, x2, y2);
    }
}
