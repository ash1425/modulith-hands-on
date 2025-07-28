package com.ashay.learnings.shop.product.api;

import com.ashay.learnings.shop.product.Products;
import com.ashay.learnings.shop.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private final Products products;

    public ProductsController(Products products) {
        this.products = products;
    }

    @GetMapping
    public List<Product> listAll() {
        logger.info("listAll");
        return products.getAll();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable String productId) {
        logger.info("getProduct with ID: {}", productId);
        return products.getProduct(productId);
    }
}
