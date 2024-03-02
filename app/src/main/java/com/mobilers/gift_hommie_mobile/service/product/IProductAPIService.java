package com.mobilers.gift_hommie_mobile.service.product;

import com.mobilers.gift_hommie_mobile.model.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IProductAPIService {
    @GET("{endpoint}")
    Call<List<Product>> getAll(@Path(value = "endpoint", encoded = true) String endpoint);

    @GET("{endpoint}/{id}")
    Call<Product> get(@Path(value = "endpoint", encoded = true) String endpoint, @Path("id") Object id);

    @POST("{endpoint}")
    Call<Product> create(@Path(value = "endpoint", encoded = true) String endpoint, @Body Product item);

    @PUT("{endpoint}/{id}")
    Call<Product> update(@Path(value = "endpoint", encoded = true) String endpoint, @Path("id") Object id, @Body Product item);

    @DELETE("{endpoint}/{id}")
    Call<Product> delete(@Path(value = "endpoint", encoded = true) String endpoint, @Path("id") Object id);
}
