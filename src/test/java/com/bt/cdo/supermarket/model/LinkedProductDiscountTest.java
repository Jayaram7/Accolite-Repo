package com.bt.cdo.supermarket.model;

import java.math.BigDecimal;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LinkedProductDiscountTest
{
    @Test
    public void shouldNotCreateAnyDiscountsIfLinkedProductNotInBasket()
    {
        Product product1 = new Product(1, "Test", "Test 1", "Test 2", new BigDecimal("100"));
        Product product2 = new Product(2, "Linked", "Linked 1", "Linked 2", new BigDecimal("5"));

        Basket basket = new Basket();
        basket.addProduct(5, product1);

        LinkedProductDiscount linkedProductDiscount = new LinkedProductDiscount(product1, product2, "Test", new BigDecimal("-10"));
        linkedProductDiscount.applyOffer(basket);

        BasketItem item1 = getBasketItem(product1, basket);
        assertThat(item1.getDiscounts().size(), is(equalTo(0)));
    }

    @Test
    public void shouldAddOneDiscountIfLinkedProductIsInBasketOnce()
    {
        Product product1 = new Product(1, "Test", "Test 1", "Test 2", new BigDecimal("100"));
        Product product2 = new Product(2, "Linked", "Linked 1", "Linked 2", new BigDecimal("5"));

        Basket basket = new Basket();
        basket.addProduct(5, product1);
        basket.addProduct(1, product2);

        LinkedProductDiscount linkedProductDiscount = new LinkedProductDiscount(product1, product2, "Test", new BigDecimal("-10"));
        linkedProductDiscount.applyOffer(basket);

        BasketItem item1 = getBasketItem(product1, basket);
        assertThat(item1.getDiscounts().size(), is(equalTo(1)));
    }

    private BasketItem getBasketItem(Product product, Basket basket)
    {
        for (BasketItem item : basket.getBasketItems())
        {
            if (item.getProduct().getId() == product.getId())
            {
                return item;
            }
        }
        throw new IllegalArgumentException("Required Item not found");
    }
}
