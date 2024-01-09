package com.bt.cdo.supermarket.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Product
{
    private int id;
    private String name;
    private String productDescription;
    private String basketDescription;
    private BigDecimal value;

    public Product()
    {
    }

    public Product(int id, String name, String productDescription, String basketDescription, BigDecimal value)
    {
        this.id = id;
        this.name = name;
        this.productDescription = productDescription;
        this.basketDescription = basketDescription;
        this.value = value;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getProductDescription()
    {
        return productDescription;
    }

    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }

    public String getBasketDescription()
    {
        return basketDescription;
    }

    public void setBasketDescription(String basketDescription)
    {
        this.basketDescription = basketDescription;
    }

    public BigDecimal getValue()
    {
        return value;
    }

    public void setValue(BigDecimal value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("productDescription", productDescription)
                .append("basketDescription", basketDescription)
                .append("value", value)
                .toString();
    }
}
