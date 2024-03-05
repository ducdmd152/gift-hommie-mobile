package com.mobilers.gift_hommie_mobile.service;

import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;

public class GlobalService {
    private static GlobalService instance;

    private CheckoutDTO checkoutDTO;
    private GlobalService() {}

    public static synchronized GlobalService getInstance() {
        if (instance == null) {
            instance = new GlobalService();
        }
        return instance;
    }

    public CheckoutDTO getCheckoutDTO() {
        return checkoutDTO;
    }

    public void setCheckoutDTO(CheckoutDTO checkoutDTO) {
        this.checkoutDTO = checkoutDTO;
    }
}
