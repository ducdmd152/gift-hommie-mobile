package com.mobilers.gift_hommie_mobile.service.auth;
import com.mobilers.gift_hommie_mobile.model.auth.Account;
import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.service.APIClient;
import com.mobilers.gift_hommie_mobile.service.GlobalService;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthAPIService {
    private final IAuthAPIService authApiService;

    private String endpoint = "public/product";

    public AuthAPIService() {
        this.authApiService = APIClient.getClient().create(IAuthAPIService.class);
    }

    public void login(Callback<Account> callback) {
        Call<Account> call = authApiService.login(GlobalService.getInstance().getAccount());
        call.enqueue(callback);
    }
    public void register(Account model, Callback<Account> callback) {
        Call<Account> call = authApiService.register(model);
        call.enqueue(callback);
    }
}
