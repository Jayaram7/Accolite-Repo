package com.bt.cdo.supermarket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Basket
{
    private Long id;

    private List<BasketItem> basketItems = new ArrayList<>();

    private List<SpecialOffer> specialOffers = new ArrayList<>();

    public Basket()
    {
    }

    public Basket(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers)
    {
        this.specialOffers = specialOffers;
    }

    public void addProduct(int quantity, Product product)
    {
        BasketItem productBasketItem = null;
        for (BasketItem basketItem : basketItems)
        {
            if (basketItem.getProduct().getId() == product.getId())
            {
                productBasketItem = basketItem;
            }
        }

        if (productBasketItem == null)
        {
            productBasketItem = new BasketItem(product, quantity);
            basketItems.add(productBasketItem);
        }
        else
        {
            productBasketItem.increaseQuantity(quantity);
        }
        this.recalculateOffers();
    }

    private void recalculateOffers()
    {
        for (BasketItem basketItem : basketItems)
        {
            basketItem.resetDiscounts();
        }
        for (SpecialOffer specialOffer : specialOffers)
        {
            specialOffer.applyOffer(this);
        }
    }

    public List<BasketItem> getBasketItems()
    {
        return basketItems;
    }

    public BigDecimal getTotal()
    {
        BigDecimal total = BigDecimal.ZERO;
        for (BasketItem basketItem : basketItems)
        {
            total = total.add(basketItem.getSubTotal());
            total = total.add(basketItem.getTotalDiscounts());
        }
        return total;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                .append("id", id)
                .toString();
    }
}
