package com.mobilers.gift_hommie_mobile.service.checkout;

import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICheckoutAPIService {
    @POST("customer/checkout")
    Call<CheckoutDTO> checkout(@Body CheckoutDTO checkoutDTO);
}
