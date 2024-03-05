package com.mobilers.gift_hommie_mobile.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface IAPIService<T> {
    @GET("{endpoint}")
    Call<List<T>> getAll(@Path("endpoint") String endpoint);

    @GET("{endpoint}/{id}")
    Call<T> get(@Path("endpoint") String endpoint, @Path("id") Object id);

    @POST("{endpoint}")
    Call<T> create(@Path("endpoint") String endpoint, @Body T item);

    @PUT("{endpoint}/{id}")
    Call<T> update(@Path("endpoint") String endpoint, @Path("id") Object id, @Body T item);

    @DELETE("{endpoint}/{id}")
    Call<T> delete(@Path("endpoint") String endpoint, @Path("id") Object id);
}

;
