package com.mobilers.gift_hommie_mobile.model.checkout;

import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDTO {
    private String name;
    private String phone;
    private String address;
    private String wardName;
    private String districtName;
    private String provinceName;
    private int wardCode;
    private int districtID;
    private int provinceID;
    private String message;
    private List<CartDTO> carts;
    private int paymentMethod;
    private float shippingFee;
    private int shippingMethod;
    private String expectedDeliveryTime;

    public CheckoutDTO() {
        shippingMethod = 1;
        paymentMethod = 1;
        carts = new ArrayList<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            expectedDeliveryTime = LocalDateTime.now().plusDays(1).toString();
        }
    }

    public CheckoutDTO(String name, String phone, String address, String wardName, String districtName, String provinceName, int wardCode, int districtID, int provinceID, String message, List<CartDTO> carts, int paymentMethod, float shippingFee, int shippingMethod) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.wardName = wardName;
        this.districtName = districtName;
        this.provinceName = provinceName;
        this.wardCode = wardCode;
        this.districtID = districtID;
        this.provinceID = provinceID;
        this.message = message;
        this.carts = carts;
        this.paymentMethod = paymentMethod;
        this.shippingFee = shippingFee;
        this.shippingMethod = shippingMethod;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            expectedDeliveryTime = LocalDateTime.now().plusDays(1).toString();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getWardCode() {
        return wardCode;
    }

    public void setWardCode(int wardCode) {
        this.wardCode = wardCode;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CartDTO> getCarts() {
        return carts;
    }

    public void setCarts(List<CartDTO> carts) {
        this.carts = carts;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(float shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(int shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public void setExpectedDeliveryTime(String expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public float getProductCost() {
        float total = 0;
        for(CartDTO cart : carts)
            total += cart.getTotal();
        return total;
    }
    public float getTotal() {
        float total = shippingFee;
        for(CartDTO cart : carts)
            total += cart.getTotal();
        return total;
    }
}
