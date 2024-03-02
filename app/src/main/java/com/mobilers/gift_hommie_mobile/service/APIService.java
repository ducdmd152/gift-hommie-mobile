package com.mobilers.gift_hommie_mobile.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class APIService<T> {
    private final IAPIService<T> apiService;
    private final String endpoint;

    public APIService(IAPIService<T> apiService, String endpoint) {
        this.apiService = apiService;
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
