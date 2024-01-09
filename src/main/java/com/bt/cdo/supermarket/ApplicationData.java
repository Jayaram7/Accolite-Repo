package com.bt.cdo.supermarket;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import com.bt.cdo.supermarket.model.Product;
import com.bt.cdo.supermarket.model.SpecialOffer;
import com.bt.cdo.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

import com.bt.cdo.supermarket.model.LinkedProductDiscount;
import com.bt.cdo.supermarket.model.MultipleMultiBuyOffer;
import com.bt.cdo.supermarket.model.SimpleMultiBuyOffer;
import com.bt.cdo.supermarket.repository.SpecialOfferRepository;

@Component
public class ApplicationData
{
    private ProductRepository productRepository;

    private SpecialOfferRepository specialOfferRepository;

    @Autowired
    public ApplicationData(ProductRepository productRepository,
            SpecialOfferRepository specialOfferRepository)
    {
        this.productRepository = productRepository;
        this.specialOfferRepository = specialOfferRepository;
    }

    @PostConstruct
    public void loadTestData()
    {
        Product productA = new Product(1, "A", "A", "A", new BigDecimal("50"));
        productRepository.addProduct(productA);
        SpecialOffer specialOffer1 = new SimpleMultiBuyOffer(productA, 3, "3 for 130", new BigDecimal("-20"));
        specialOfferRepository.addSpecialOffer(specialOffer1);

        Product productB = new Product(2, "B", "B", "B", new BigDecimal("30"));
        productRepository.addProduct(productB);
        SpecialOffer specialOffer2 = new SimpleMultiBuyOffer(productB, 2, "2 for 45", new BigDecimal("-15"));
        specialOfferRepository.addSpecialOffer(specialOffer2);

        Product productC = new Product(3, "C", "C", "C", new BigDecimal("20"));
        productRepository.addProduct(productC);
        SpecialOffer specialOffer3 = new MultipleMultiBuyOffer(
                asList(
                        new SimpleMultiBuyOffer(productC, 2, "2 for 38", new BigDecimal("-2")),
                        new SimpleMultiBuyOffer(productC, 3, "3 for 50", new BigDecimal("-10"))
                )
        );
        specialOfferRepository.addSpecialOffer(specialOffer3);

        Product productD = new Product(4, "D", "D", "D", new BigDecimal("15"));
        productRepository.addProduct(productD);
        SpecialOffer specialOffer4 = new LinkedProductDiscount(productD, productA, "5 if purchased with an A", new BigDecimal("-10"));
        specialOfferRepository.addSpecialOffer(specialOffer4);

        Product productE = new Product(5, "E", "E", "E", new BigDecimal("5"));
        productRepository.addProduct(productE);
    }
}
