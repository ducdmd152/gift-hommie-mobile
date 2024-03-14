package com.mobilers.gift_hommie_mobile.service.order;

import com.mobilers.gift_hommie_mobile.model.cart.CartListResponseDTO;
import com.mobilers.gift_hommie_mobile.model.order.OrderPakageListResponseDTO;
import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;
import com.mobilers.gift_hommie_mobile.service.APIClient;
import com.mobilers.gift_hommie_mobile.service.cart.ICartAPIService;

import retrofit2.Call;
import retrofit2.Callback;


public class OrderAPIService {
    private final IOrderAPIService apiService;
    private String endpoint = "customer/order";

    public OrderAPIService() {
        this.apiService = APIClient.getClient().create(IOrderAPIService.class);

    }
    public void getAll(Callback<OrderPakageListResponseDTO> callback) {
        Call<OrderPakageListResponseDTO> call = apiService.getAll(endpoint);
        call.enqueue(callback);
        }
}
