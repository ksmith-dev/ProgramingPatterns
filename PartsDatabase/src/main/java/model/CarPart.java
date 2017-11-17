package model;

import java.io.Serializable;

public class CarPart implements Serializable
{
    private String id;
    private String manufacturer;
    private double listPrice;
    private String[] categories;

    public CarPart()
    {
        //do nothing - leave this method here...
    }

    public CarPart(String id, String manufacturer, double listPrice, String[] categories)
    {
        this.id = id;
        this.manufacturer = manufacturer;
        this.listPrice = listPrice;
        this.categories = categories;
    }

    public String getId()
    {
        return id;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public double getListPrice()
    {
        return listPrice;
    }

    public String[] getCategories()
    {
        return categories;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public void setListPrice(double listPrice)
    {
        this.listPrice = listPrice;
    }

    public void setCategories(String[] categories)
    {
        this.categories = categories;
    }


}
