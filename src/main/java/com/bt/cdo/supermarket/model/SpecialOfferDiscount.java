package com.bt.cdo.supermarket.model;

import java.math.BigDecimal;

public class SpecialOfferDiscount
{
    private String description;
    private BigDecimal value;

    public SpecialOfferDiscount(SpecialOffer specialOffer)
    {
        this.description = specialOffer.getDescription();
        this.value = specialOffer.getValue();
    }

    public String getDescription()
    {
        return description;
    }

    public BigDecimal getValue()
    {
        return value;
    }
}
