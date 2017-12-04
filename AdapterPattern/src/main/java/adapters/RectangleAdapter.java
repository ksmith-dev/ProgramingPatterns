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
        return this.x;
    }

    @Override
    public double getY()
    {
        return this.y;
    }

    @Override
    public double getThickness()
    {
        return this.thickness;
    }

    @Override
    public Color getColor()
    {
        return this.color;
    }

    @Override
    public boolean getFilled()
    {
        return this.isFill;
    }

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
