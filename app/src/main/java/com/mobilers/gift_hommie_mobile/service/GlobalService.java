package com.mobilers.gift_hommie_mobile.service;

import com.mobilers.gift_hommie_mobile.model.auth.Account;
import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.model.product.Product;

public class GlobalService {
    private static GlobalService instance;

    private Product product;
    private Account account;
    private CheckoutDTO checkoutDTO;
    private boolean isAuthenticated;

    private GlobalService() {}

    public static synchronized GlobalService getInstance() {
        if (instance == null) {
            instance = new GlobalService();
        }
        return instance;
    }

    public void clear() {
        account = null;
        isAuthenticated = false;
    }

    public CheckoutDTO getCheckoutDTO() {
        return checkoutDTO;
    }

    public void setCheckoutDTO(CheckoutDTO checkoutDTO) {
        this.checkoutDTO = checkoutDTO;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Account getAccount() {
        if (account == null)
            return new Account("customer@gmail.com", "customer", "123456");
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
