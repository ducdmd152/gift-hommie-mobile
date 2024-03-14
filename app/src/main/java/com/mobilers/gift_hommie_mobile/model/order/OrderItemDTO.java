package com.mobilers.gift_hommie_mobile.model.order;

import com.mobilers.gift_hommie_mobile.model.product.Product;

public class OrderItemDTO {
    private int quantity;
    private int price;
    Product product;

    public OrderItemDTO(int quantity, int price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
