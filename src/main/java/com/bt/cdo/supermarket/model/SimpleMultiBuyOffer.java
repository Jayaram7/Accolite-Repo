package com.bt.cdo.supermarket.model;

import java.math.BigDecimal;

public class SimpleMultiBuyOffer implements SpecialOffer
{
    private Product requiredProduct;
    private int requiredQuantity;
    private String description;
    private BigDecimal value;

    public SimpleMultiBuyOffer(Product requiredProduct,
            int requiredQuantity,
            String description,
            BigDecimal value)
    {
        this.requiredProduct = requiredProduct;
        this.requiredQuantity = requiredQuantity;
        this.description = description;
        this.value = value;
    }

    @Override
    public void applyOffer(Basket basket)
    {
        for (BasketItem basketItem : basket.getBasketItems())
        {
            applyOffer(basketItem);
        }
    }

    private void applyOffer(BasketItem item)
    {
        if (requiredProduct.getId() == item.getProduct().getId())
        {
            int numberOfDiscounts = item.getQuantity() / requiredQuantity;
            if (numberOfDiscounts > 0)
            {
                SpecialOfferDiscount discount = new SpecialOfferDiscount(this);
                for (int i = 1; i <= numberOfDiscounts; i++)
                {
                    item.addDiscount(discount);
                }
            }
        }
    }

    public BigDecimal getValue()
    {
        return value;
    }

    public String getDescription()
    {
        return description;
    }

    public Product getRequiredProduct()
    {
        return requiredProduct;
    }

    public int getRequiredQuantity()
    {
        return requiredQuantity;
    }
}
