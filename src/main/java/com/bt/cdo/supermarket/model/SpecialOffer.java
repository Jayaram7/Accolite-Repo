package com.bt.cdo.supermarket.model;

import java.math.BigDecimal;

public interface SpecialOffer
{
    void applyOffer(Basket basket);

    String getDescription();

    BigDecimal getValue();
}
