package com.mobilers.gift_hommie_mobile.model.cart;

import com.mobilers.gift_hommie_mobile.model.product.Product;

public class CartDTO {
    private int id;
    private int productId;
    private int quantity;
    private Product product;

    public CartDTO() {
    }

    public CartDTO(int id, int productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartDTO(int id, int productId, int quantity, Product product) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotal() {
        return quantity* product.getPrice();
    }
}
