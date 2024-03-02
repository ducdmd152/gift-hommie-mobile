package com.mobilers.gift_hommie_mobile.service;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService<T> {
    private final IAPIService<T> apiService;
    private final String endpoint;

    public APIService(String endpoint, Class<T> serviceType) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.getClient().baseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiService = retrofit.create(IAPIService.class);
        this.endpoint = endpoint;
    }

    public void getAll(Callback<List<T>> callback) {
        Call<List<T>> call = apiService.getAll(endpoint);
        call.enqueue(callback);
    }

    public void get(int id, Callback<T> callback) {
        Call<T> call = apiService.get(endpoint, id);
        call.enqueue(callback);
    }

    public void create(T model, Callback<T> callback) {
        Call<T> call = apiService.create(endpoint, model);
        call.enqueue(callback);
    }

    public void update(int id, T model, Callback<T> callback) {
        Call<T> call = apiService.update(endpoint, id, model);
        call.enqueue(callback);
    }

    public void delete(int id, Callback<T> callback) {
        Call<T> call = apiService.delete(endpoint, id);
        call.enqueue(callback);
    }
}
