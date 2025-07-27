package com.ashay.learnings.shop.cart;

import com.ashay.learnings.shop.cart.model.Cart;
import com.ashay.learnings.shop.cart.repository.CartRepository;
import com.ashay.learnings.shop.product.Products;
import com.ashay.learnings.shop.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class Carts {
    private final CartRepository cartRepository;
    private final Products products;

    public Carts(CartRepository cartRepository, Products products) {
        this.cartRepository = cartRepository;
        this.products = products;
    }

    public Cart getById(String cartId) {
        return cartRepository.findById(cartId).orElseThrow(
                () -> new NoSuchElementException("Cart not found with id: " + cartId));
    }

    public Cart addItem(String cartId, String productId, int quantity) {
        Cart cart = getById(cartId);
        Product product = products.getProduct(productId);
        if (!product.hasStock(quantity)) {
            throw new IllegalArgumentException("Insufficient stock for product: " + productId);
        }
        return cartRepository.save(cart.addItem(product, quantity));
    }

    public Cart create() {
        return cartRepository.save(new Cart());
    }

    public void delete(String cartId) {
        cartRepository.deleteById(cartId);
    }

    public void empty(String cartId) {
        getById(cartId).empty();
    }
}
