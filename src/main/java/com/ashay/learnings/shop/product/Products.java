package com.ashay.learnings.shop.product;

import com.ashay.learnings.shop.product.model.Product;
import com.ashay.learnings.shop.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class Products {
    private final ProductRepository productRepository;

    public Products(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(String productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + productId));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
