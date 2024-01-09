package com.bt.cdo.supermarket.controller;

import java.util.ArrayList;
import java.util.List;

import com.bt.cdo.supermarket.formbeans.BasketFormBean;
import com.bt.cdo.supermarket.formbeans.ProductFormBean;
import com.bt.cdo.supermarket.model.Product;
import com.bt.cdo.supermarket.repository.ProductRepository;
import com.bt.cdo.supermarket.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.bt.cdo.supermarket.model.Basket;

@Controller
public class BasketController
{
    public static final String BASKET_FORM_BEAN = "basketFormBean";

    private ProductRepository productRepository;

    private BasketService basketService;

    @Autowired
    public BasketController(
            ProductRepository productRepository,
            BasketService basketService)
    {
        this.productRepository = productRepository;
        this.basketService = basketService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model)
    {
        Basket basket = basketService.createBasket();
        BasketFormBean basketFormBean = new BasketFormBean(basket.getId(), basket, getProductFormBeans());
        model.addAttribute(BASKET_FORM_BEAN, basketFormBean);
        return "basket";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getMyBasket(@PathVariable
            Long id, Model model)
    {
        Basket basket = basketService.findById(id);
        BasketFormBean basketFormBean = new BasketFormBean(basket.getId(), basket, getProductFormBeans());
        model.addAttribute(BASKET_FORM_BEAN, basketFormBean);
        return "basket";
    }

    @RequestMapping(value = "{basketId}", params = {"addProduct"}, method = RequestMethod.POST)
    public String addProduct(@PathVariable Long basketId, @ModelAttribute(BASKET_FORM_BEAN) BasketFormBean basketFormBean, Model model, WebRequest webRequest)
    {
        String productIdString = webRequest.getParameter("addProduct");
        Long productId = Long.valueOf(productIdString);
        Product product = productRepository.findById(productId);

        Basket basket = basketService.findById(basketId);
        basket.addProduct(getQuantity(productId, basketFormBean), product);
        basketFormBean.setBasket(basket);

        model.addAttribute(BASKET_FORM_BEAN, basketFormBean);
        return "basket";
    }

    private List<ProductFormBean> getProductFormBeans()
    {
        List<ProductFormBean> productFormBeans = new ArrayList<>();
        for (Product product : productRepository.getProductList())
        {
            productFormBeans.add(new ProductFormBean(product.getId(), product.getProductDescription(), 1));
        }
        return productFormBeans;
    }

    private int getQuantity(Long productId, BasketFormBean basketFormBean)
    {
        int quantity = 1;
        for (ProductFormBean productFormBean : basketFormBean.getProductFormBeans())
        {
            if (productFormBean.getId() == productId)
            {
                quantity = productFormBean.getQuantity();
                break;
            }
        }
        return quantity;
    }
}
