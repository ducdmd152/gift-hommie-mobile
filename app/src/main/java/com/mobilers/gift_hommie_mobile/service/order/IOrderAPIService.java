package com.mobilers.gift_hommie_mobile.service.order;

import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IOrderAPIService {
    @GET("{endpoint}")
    Call<ProductListResponseDTO> getAll(@Path(value = "endpoint", encoded = true) String endpoint);
}
