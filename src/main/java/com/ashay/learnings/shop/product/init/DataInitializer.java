package com.ashay.learnings.shop.product.init;

import com.ashay.learnings.shop.product.model.Product;
import com.ashay.learnings.shop.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.saveAll(
                        List.of(
                                new Product("P0001", "High performance laptop", BigDecimal.valueOf(1500.00), 10),
                                new Product("P0002", "Wireless noise-cancelling headphones", BigDecimal.valueOf(300.00), 25),
                                new Product("P0003", "Smartphone with 5G connectivity", BigDecimal.valueOf(800.00), 15),
                                new Product("P0004", "4K Ultra HD Smart TV", BigDecimal.valueOf(1200.00), 5),
                                new Product("P0005", "Smartwatch with health tracking", BigDecimal.valueOf(250.00), 20),
                                new Product("P0006", "Bluetooth speaker with deep bass", BigDecimal.valueOf(150.00), 30),
                                new Product("P0007", "Gaming console with 4K support", BigDecimal.valueOf(500.00), 8),
                                new Product("P0008", "Tablet with stylus support", BigDecimal.valueOf(400.00), 12)
                        )
                );
            }
        };
    }
}
