package com.bt.cdo.supermarket.formbeans;

import java.util.List;

import com.bt.cdo.supermarket.model.Basket;

public class BasketFormBean
{
    private Long id;
    private Basket basket;
    private List<ProductFormBean> productFormBeans;

    public BasketFormBean()
    {
    }

    public BasketFormBean(Long id, Basket basket, List<ProductFormBean> productFormBeans)
    {
        this.id = id;
        this.basket = basket;
        this.productFormBeans = productFormBeans;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Basket getBasket()
    {
        return basket;
    }

    public void setBasket(Basket basket)
    {
        this.basket = basket;
    }

    public List<ProductFormBean> getProductFormBeans()
    {
        return productFormBeans;
    }

    public void setProductFormBeans(List<ProductFormBean> productFormBeans)
    {
        this.productFormBeans = productFormBeans;
    }
}
