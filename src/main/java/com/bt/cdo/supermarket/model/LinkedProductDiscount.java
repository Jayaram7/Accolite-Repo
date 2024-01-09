package com.bt.cdo.supermarket.model;

import java.math.BigDecimal;
import java.util.List;

public class LinkedProductDiscount implements SpecialOffer
{
    private Product discountedProduct;
    private Product requiredProduct;
    private String description;
    private BigDecimal value;

    public LinkedProductDiscount(Product discountedProduct,
            Product requiredProduct,
            String description,
            BigDecimal value)
    {
        this.discountedProduct = discountedProduct;
        this.requiredProduct = requiredProduct;
        this.description = description;
        this.value = value;
    }

    @Override
    public void applyOffer(Basket basket)
    {
        BasketItem discountedProductBasketItem = getDiscountedProduct(basket.getBasketItems());
        if (discountedProductBasketItem != null)
        {
            int quantityOfDiscountedProduct = discountedProductBasketItem.getQuantity();
            int quantityOfRequiredProduct = countProducts(requiredProduct, basket.getBasketItems());
            createDiscount(discountedProductBasketItem,
                           Math.min(quantityOfDiscountedProduct, quantityOfRequiredProduct));
        }
    }

    private void createDiscount(BasketItem discountedProductBasketItem, int numberOfDiscounts)
    {
        for (int i = 0; i < numberOfDiscounts; i++)
        {
            discountedProductBasketItem.addDiscount(new SpecialOfferDiscount(this));
        }
    }

    private int countProducts(Product discountedProduct, List<BasketItem> basketItems)
    {
        int count = 0;
        for (BasketItem basketItem : basketItems)
        {
            if (basketItem.getProduct().getId() == discountedProduct.getId())
            {
                count += basketItem.getQuantity();
            }
        }
        return count;
    }

    private BasketItem getDiscountedProduct(List<BasketItem> basketItems)
    {
        for (BasketItem basketItem : basketItems)
        {
            if (basketItem.getProduct().getId() == discountedProduct.getId())
            {
                return basketItem;
            }
        }
        return null;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public BigDecimal getValue()
    {
        return value;
    }
}
