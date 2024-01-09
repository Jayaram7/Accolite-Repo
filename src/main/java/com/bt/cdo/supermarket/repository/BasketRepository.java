package com.bt.cdo.supermarket.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bt.cdo.supermarket.model.Basket;

@Repository
public class BasketRepository
{
    private Map<Long, Basket> basketMap;
    private static Long id = 0L;

    public BasketRepository()
    {
        this.basketMap = new HashMap<>();
    }

    public Basket createBasket()
    {
        Basket basket = new Basket(++id);
        basketMap.put(basket.getId(), basket);
        return basket;
    }

    public Basket findById(Long id)
    {
        return basketMap.get(id);
    }
}
