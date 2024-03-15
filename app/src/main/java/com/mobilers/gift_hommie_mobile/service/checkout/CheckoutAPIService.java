package com.mobilers.gift_hommie_mobile.service.checkout;

import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.service.APIClient;
import com.mobilers.gift_hommie_mobile.service.cart.ICartAPIService;

import retrofit2.Call;
import retrofit2.Callback;

public class CheckoutAPIService {
    private ICheckoutAPIService apiService;

    public CheckoutAPIService() {
        this.apiService = APIClient.getClient().create(ICheckoutAPIService.class);
    }

    public void checkout(CheckoutDTO model, Callback<CheckoutDTO> callback) {
        Call<CheckoutDTO> call = apiService.checkout(model);
        call.enqueue(callback);
    }
}
