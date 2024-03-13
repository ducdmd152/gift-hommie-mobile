package com.mobilers.gift_hommie_mobile.service.auth;

import com.mobilers.gift_hommie_mobile.model.auth.Account;
import com.mobilers.gift_hommie_mobile.model.product.Product;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthAPIService {
    @POST("auth/login")
    Call<Account> login(@Body Account data);

    @POST("auth/register")
    Call<Account> register(@Body Account data);
}
