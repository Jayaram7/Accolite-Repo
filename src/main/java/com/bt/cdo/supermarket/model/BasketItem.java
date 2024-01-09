package com.bt.cdo.supermarket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketItem
{
    private Product product;
    private int quantity;
    private List<SpecialOfferDiscount> discounts = new ArrayList<>();

    public BasketItem()
    {
    }

    public BasketItem(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct()
    {
        return product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public List<SpecialOfferDiscount> getDiscounts()
    {
        return discounts;
    }

    public void increaseQuantity(int quantity)
    {
        this.quantity += quantity;
    }

    public BigDecimal getSubTotal()
    {
        return product.getValue().multiply(new BigDecimal(quantity));
    }

    public void addDiscount(SpecialOfferDiscount discount)
    {
        this.discounts.add(discount);
    }

    public void resetDiscounts()
    {
        this.discounts.clear();
    }

    public BigDecimal getTotalDiscounts()
    {
        BigDecimal total = BigDecimal.ZERO;
        for (SpecialOfferDiscount discount : discounts)
        {
            total = total.add(discount.getValue());
        }
        return total;
    }
}
