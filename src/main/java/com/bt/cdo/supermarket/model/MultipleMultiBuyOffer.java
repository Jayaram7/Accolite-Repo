package com.bt.cdo.supermarket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MultipleMultiBuyOffer implements SpecialOffer
{
    private Product requiredProduct;
    private List<SimpleMultiBuyOffer> multiBuyOffers = new ArrayList<>();

    public MultipleMultiBuyOffer(List<SimpleMultiBuyOffer> specialOffers)
    {
        if(specialOffers == null || specialOffers.isEmpty())
        {
            throw new IllegalArgumentException("You must provide at least 1 special offer");
        }

        multiBuyOffers.addAll(specialOffers);
        requiredProduct = specialOffers.get(0).getRequiredProduct();
    }

    @Override
    public void applyOffer(Basket basket)
    {
        for (BasketItem item : basket.getBasketItems())
        {
            applyOffer(item);
        }
    }

    private void applyOffer(BasketItem item)
    {
        if (requiredProduct.getId() == item.getProduct().getId())
        {
            int highestNumberOfDiscounts = 0;
            SimpleMultiBuyOffer selectedOffer = null;

            for (SimpleMultiBuyOffer simpleMultiBuyOffer : multiBuyOffers)
            {
                int numberOfDiscounts = item.getQuantity() / simpleMultiBuyOffer.getRequiredQuantity();
                if (numberOfDiscounts >= highestNumberOfDiscounts)
                {
                    highestNumberOfDiscounts = numberOfDiscounts;
                    selectedOffer = simpleMultiBuyOffer;
                }
            }

            if (selectedOffer != null)
            {
                SpecialOfferDiscount discount = new SpecialOfferDiscount(selectedOffer);
                for (int i = 1; i <= highestNumberOfDiscounts; i++)
                {
                    item.addDiscount(discount);
                }
            }
        }
    }

    @Override
    public String getDescription()
    {
        throw new UnsupportedOperationException("Description not supported");
    }

    @Override
    public BigDecimal getValue()
    {
        throw new UnsupportedOperationException("Value not supported");
    }
}
