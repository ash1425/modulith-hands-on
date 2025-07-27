package com.ashay.learnings.shop.cart.dto;

import com.ashay.learnings.shop.cart.model.Cart;
import com.ashay.learnings.shop.cart.model.CartItem;
import com.ashay.learnings.shop.product.model.Product;

import java.math.BigDecimal;
import java.util.List;

record ItemDto(String id, Product product, int quantity) {

    public static ItemDto from(CartItem item) {
        return new ItemDto(
                item.getItemId(),
                item.getProduct(),
                item.getQuantity()
        );
    }
}

public record CartDto(String id, List<ItemDto> items, BigDecimal total) {
    public static CartDto from(Cart cart) {
        List<ItemDto> itemDtos = cart.getItems().stream()
                .map(ItemDto::from)
                .toList();
        return new CartDto(
                cart.getCartId(),
                itemDtos,
                cart.getTotal()
        );
    }
}
