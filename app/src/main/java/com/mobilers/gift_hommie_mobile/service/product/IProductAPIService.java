package com.mobilers.gift_hommie_mobile.service.product;

import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IProductAPIService {
    @GET("{endpoint}")
    Call<ProductListResponseDTO> getAll(@Path(value = "endpoint", encoded = true) String endpoint);

    @GET("{endpoint}/{id}")
    Call<Product> get(@Path(value = "endpoint", encoded = true) String endpoint, @Path("id") Object id);


    //new import

    Call<ProductListResponseDTO> searchProducts(@Query("search") String searchText);

    Call<ProductListResponseDTO> getByCategory(@Query("category") int categoryId);
    @GET("{endpoint}")
    Call<ProductListResponseDTO> getProductsByCategory(@Path(value = "endpoint", encoded = true) String endpoint, @Query("category") int categoryId);
    @GET("{endpoint}")
    Call<ProductListResponseDTO> getAllProducts(@Path(value = "endpoint", encoded = true) String endpoint, @Query("size") int size);
//    @POST("{endpoint}")
//    Call<Product> create(@Path(value = "endpoint", encoded = true) String endpoint, @Body Product item);
//
//    @PUT("{endpoint}/{id}")
//    Call<Product> update(@Path(value = "endpoint", encoded = true) String endpoint, @Path("id") Object id, @Body Product item);
//
//    @DELETE("{endpoint}/{id}")
//    Call<Product> delete(@Path(value = "endpoint", encoded = true) String endpoint, @Path("id") Object id);
}
