package com.bt.cdo.supermarket.repository;

import java.util.ArrayList;
import java.util.List;

import com.bt.cdo.supermarket.model.SpecialOffer;
import org.springframework.stereotype.Repository;

@Repository
public class SpecialOfferRepository
{
    private List<SpecialOffer> specialOffers;

    public SpecialOfferRepository()
    {
        this.specialOffers = new ArrayList<>();
    }

    public List<SpecialOffer> getSpecialOffers()
    {
        return specialOffers;
    }

    public void addSpecialOffer(SpecialOffer specialOffer)
    {
        this.specialOffers.add(specialOffer);
    }
}
