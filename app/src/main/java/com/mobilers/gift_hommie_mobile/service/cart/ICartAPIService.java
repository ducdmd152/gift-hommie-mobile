package com.mobilers.gift_hommie_mobile.service.cart;

import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;
import com.mobilers.gift_hommie_mobile.model.cart.CartListResponseDTO;
import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.DELETE;

public interface ICartAPIService {
    @GET("{endpoint}")
    Call<CartListResponseDTO> getAll(@Path(value = "endpoint", encoded = true) String endpoint);

    @GET("{endpoint}/{id}")
    Call<CartDTO> get(@Path(value = "endpoint", encoded = true) String endpoint, @Path("id") Object id);

    @PUT("{endpoint}/{id}")
    Call<CartDTO> update(@Path(value = "endpoint", encoded = true) String endpoint, @Path("id") Object id, @Body CartDTO item);

    @DELETE("{endpoint}/{id}")
    Call<CartDTO> delete(@Path(value = "endpoint", encoded = true) String endpoint, @Path("id") Object id);
}