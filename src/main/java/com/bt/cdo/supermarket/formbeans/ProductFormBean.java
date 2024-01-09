package com.bt.cdo.supermarket.formbeans;

public class ProductFormBean
{
    private int id;
    private String description;
    private int quantity;

    public ProductFormBean()
    {
    }

    public ProductFormBean(int id, String description, int quantity)
    {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
