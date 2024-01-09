package com.bt.cdo.supermarket.service;

import com.bt.cdo.supermarket.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bt.cdo.supermarket.model.Basket;
import com.bt.cdo.supermarket.repository.SpecialOfferRepository;

@Component
public class BasketService
{
    private BasketRepository basketRepository;

    private SpecialOfferRepository specialOfferRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository,
            SpecialOfferRepository specialOfferRepository)
    {
        this.basketRepository = basketRepository;
        this.specialOfferRepository = specialOfferRepository;
    }

    public Basket createBasket()
    {
        Basket basket = basketRepository.createBasket();
        basket.setSpecialOffers(specialOfferRepository.getSpecialOffers());
        return basket;
    }

    public Basket findById(Long id)
    {
        Basket basket = basketRepository.findById(id);
        basket.setSpecialOffers(specialOfferRepository.getSpecialOffers());
        return basket;
    }
}
