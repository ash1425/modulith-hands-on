package com.ashay.learnings.shop.cart.api;

import com.ashay.learnings.shop.cart.Carts;
import com.ashay.learnings.shop.cart.dto.CartDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartsController {

    private Logger logger = LoggerFactory.getLogger(CartsController.class);

    private final Carts carts;

    public CartsController(Carts carts) {
        this.carts = carts;
    }

    @PostMapping
    public CartDto createCart() {
        CartDto cartDto = CartDto.from(carts.create());
        logger.info("Creating new cart with ID: {}", cartDto.id());
        return cartDto;
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable String cartId) {
        logger.info("Retrieving cart with ID: {}", cartId);
        return CartDto.from(carts.getById(cartId));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable String cartId) {
        logger.info("Deleting cart with ID: {}", cartId);
        carts.delete(cartId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<String> emptyCart(@PathVariable String cartId) {
        logger.info("Emptying cart with ID: {}", cartId);
        carts.empty(cartId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/items/{productId}")
    public CartDto addItemToCart(@PathVariable String cartId, @PathVariable String productId, @RequestParam int quantity) {
        logger.info("Adding item with ID: {} to cart with ID: {} with quantity: {}", productId, cartId, quantity);
        return CartDto.from(carts.addItem(cartId, productId, quantity));
    }
}
