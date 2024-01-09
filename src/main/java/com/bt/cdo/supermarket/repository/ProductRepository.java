package com.bt.cdo.supermarket.repository;

import java.util.ArrayList;
import java.util.List;

import com.bt.cdo.supermarket.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository
{
    private List<Product> productList;

    public ProductRepository()
    {
        productList = new ArrayList<>();
    }

    public List<Product> getProductList()
    {
        return productList;
    }

    public void addProducts(List<Product> productList)
    {
        this.productList.addAll(productList);
    }

    public void addProduct(Product product)
    {
        this.productList.add(product);
    }

    public Product findById(Long productId)
    {
        Product result = null;
        for (Product product : productList)
        {
            if (product.getId() == productId)
            {
                result = product;
                break;
            }
        }
        return result;
    }
}
