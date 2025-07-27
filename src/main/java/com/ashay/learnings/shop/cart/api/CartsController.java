package com.ashay.learnings.shop.cart.api;

import com.ashay.learnings.shop.cart.Carts;
import com.ashay.learnings.shop.cart.dto.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartsController {

    private final Carts carts;

    public CartsController(Carts carts) {
        this.carts = carts;
    }

    @PostMapping
    public CartDto createCart() {
        return CartDto.from(carts.create());
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable String cartId) {
        return CartDto.from(carts.getById(cartId));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable String cartId) {
        carts.delete(cartId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<String> emptyCart(@PathVariable String cartId) {
        carts.empty(cartId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/items/{productId}")
    public CartDto addItemToCart(@PathVariable String cartId, @PathVariable String productId, @RequestParam int quantity) {
        return CartDto.from(carts.addItem(cartId, productId, quantity));
    }
}
