package com.ashay.learnings.shop.cart.model;

import com.ashay.learnings.shop.product.model.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cartId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public Cart() {

    }

    public Cart addItem(Product product, int quantity) {
        CartItem itemToAdd = new CartItem(product, quantity);

        items.stream().filter(item -> item.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(
                        existingItem -> existingItem.setQuantity(quantity),
                        () -> items.add(itemToAdd)
                );

        return this;
    }

    public void empty() {
        this.items.clear();
    }

    public BigDecimal getTotal() {
        return items == null ? BigDecimal.ZERO : items.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getCartId() {
        return cartId;
    }

    public List<CartItem> getItems() {
        return Objects.requireNonNullElseGet(items, List::of);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Cart) obj;
        return Objects.equals(this.cartId, that.cartId) &&
                Objects.equals(this.items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, items);
    }

    @Override
    public String toString() {
        return "Cart[" +
                "cartId=" + cartId + ", " +
                "items=" + items + ']';
    }

    public boolean canCheckout() {
        return !items.isEmpty();
    }
}
