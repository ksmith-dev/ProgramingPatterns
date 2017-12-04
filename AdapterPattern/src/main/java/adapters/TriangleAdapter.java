package adapters;

import drawing.IShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes.Triangle;

public class TriangleAdapter implements IShape
{
    private double x;
    private double y;
    private double width;
    private double height;
    private double thickness;
    private Color color;
    private boolean isFill;

    public TriangleAdapter(Triangle triangle)
    {
        x = triangle.getX();
        y = triangle.getY();
        width = triangle.getWidth();
        height = triangle.getHeight();
        thickness = triangle.getThickness();
        color = triangle.getColor();
        isFill = triangle.isFill();
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
        if (isFill)
        {
            graphics.setStroke(color);
            graphics.setFill(color);
            graphics.setLineWidth(thickness);
            graphics.fillPolygon(new double[]{x, x + (width / 2), x + width }, new double[]{y, y + height, y}, 3);

        }
        else
        {
            graphics.setStroke(color);
            graphics.setLineWidth(thickness);
            graphics.strokePolygon(new double[]{x, x + (width / 2), x + width }, new double[]{y, y + height, y}, 3);
        }
    }
}
