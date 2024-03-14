package com.mobilers.gift_hommie_mobile.service.cart;
import com.mobilers.gift_hommie_mobile.model.cart.AddToCartDTO;
import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;
import com.mobilers.gift_hommie_mobile.model.cart.CartListResponseDTO;
import com.mobilers.gift_hommie_mobile.service.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CartAPIService {
    private final ICartAPIService apiService;
    private String endpoint = "customer/cart";

    public CartAPIService() {
        this.apiService = APIClient.getClient().create(ICartAPIService.class);
    }

    public CartAPIService(String endpoint) {
        this.apiService = APIClient.getClient().create(ICartAPIService.class);
        this.endpoint = endpoint;
    }

    public void getAll(Callback<CartListResponseDTO> callback) {
        Call<CartListResponseDTO> call = apiService.getAll(endpoint);
        call.enqueue(callback);
    }

    public void get(int id, Callback<CartDTO> callback) {
        Call<CartDTO> call = apiService.get(endpoint, id);
        call.enqueue(callback);
    }

    public void addToCart(AddToCartDTO model, Callback<CartDTO> callback) {
        Call<CartDTO> call = apiService.create(endpoint, model);
        call.enqueue(callback);
    }

    public void delete(int id, Callback<CartDTO> callback) {
        Call<CartDTO> call = apiService.delete(endpoint, id);
        call.enqueue(callback);
    }

    public void update(int id, CartDTO model, Callback<CartDTO> callback) {
        Call<CartDTO> call = apiService.update(endpoint, id, model);
        call.enqueue(callback);
    }
}


