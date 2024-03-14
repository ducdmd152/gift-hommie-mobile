package com.mobilers.gift_hommie_mobile.model.order;

public class OrderItemDTO {
    private String name;
    private int quantity;
    private int price;
    private String avatar;



    public OrderItemDTO(String name, int quantity, int price, String img) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.avatar = img;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImg() {
        return avatar;
    }

    public void setImg(String img) {
        this.avatar = img;
    }
}
