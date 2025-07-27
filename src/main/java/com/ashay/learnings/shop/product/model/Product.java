package com.ashay.learnings.shop.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product {
    @Id
    private String productId;
    private String productName;
    private BigDecimal price;
    private int stock;

    public Product(String productId, String productName, BigDecimal price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public Product() {

    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean hasStock(int requestedQuantity) {
        return stock >= requestedQuantity;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.productId, that.productId) &&
                Objects.equals(this.productName, that.productName) &&
                Objects.equals(this.price, that.price) &&
                this.stock == that.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, price, stock);
    }

    @Override
    public String toString() {
        return "Product[" +
                "productId=" + productId + ", " +
                "productName=" + productName + ", " +
                "price=" + price + ", " +
                "stock=" + stock + ']';
    }
}
