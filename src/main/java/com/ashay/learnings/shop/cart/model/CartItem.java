package com.ashay.learnings.shop.cart.model;

import com.ashay.learnings.shop.product.model.Product;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String itemId;
    @ManyToOne
    private Product product;

    private int quantity;

    public CartItem(
            Product product,
            int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem() {

    }

    public String getItemId() {
        return itemId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CartItem) obj;
        return Objects.equals(this.itemId, that.itemId) &&
                Objects.equals(this.product, that.product) &&
                this.quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, product, quantity);
    }

    @Override
    public String toString() {
        return "Item[" +
                "itemId=" + itemId + ", " +
                "product=" + product + ", " +
                "quantity=" + quantity + ']';
    }
}
