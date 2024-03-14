package com.mobilers.gift_hommie_mobile.service.order;

import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;


public class OrderAPIService {
    private final IOrderAPIService iOrderAPIService;
    private String endpoint = "customer/order";

    public OrderAPIService(IOrderAPIService iOrderAPIService) {
        this.iOrderAPIService = iOrderAPIService;
    }

    public void getAll(Callback<ProductListResponseDTO> callback) {
        Call<ProductListResponseDTO> call = iOrderAPIService.getAll(endpoint);
        call.enqueue(callback);
    }


}
