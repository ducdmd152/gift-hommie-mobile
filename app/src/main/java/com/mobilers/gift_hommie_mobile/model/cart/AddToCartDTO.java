package com.mobilers.gift_hommie_mobile.model.cart;

import com.mobilers.gift_hommie_mobile.model.product.Product;

public class AddToCartDTO {
    private int productId;
    private int id;
    private int quantity;

    public AddToCartDTO() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public AddToCartDTO(Product product) {
        id = product.getId();
        productId = product.getId();
        quantity = 1;
    }

}
