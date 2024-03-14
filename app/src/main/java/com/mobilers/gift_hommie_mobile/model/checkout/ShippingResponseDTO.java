package com.mobilers.gift_hommie_mobile.model.checkout;

public class ShippingResponseDTO {
    private String order_code;
    private int total_fee;
    private String expected_delivery_time;

    // Constructors
    public ShippingResponseDTO() {
    }

    public ShippingResponseDTO(String order_code, int total_fee, String expected_delivery_time) {
        this.order_code = order_code;
        this.total_fee = total_fee;
        this.expected_delivery_time = expected_delivery_time;
    }

    // Getters and Setters
    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getExpected_delivery_time() {
        return expected_delivery_time;
    }

    public void setExpected_delivery_time(String expected_delivery_time) {
        this.expected_delivery_time = expected_delivery_time;
    }
}
