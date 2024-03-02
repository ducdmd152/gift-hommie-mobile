package com.mobilers.gift_hommie_mobile.service.product;
import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.service.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductAPIService {
    private final IProductAPIService apiService;
    private String endpoint = "public/product";

    public ProductAPIService() {
        this.apiService = APIClient.getClient().create(IProductAPIService.class);
    }
    public ProductAPIService(String endpoint) {
        this.apiService = APIClient.getClient().create(IProductAPIService.class);
        this.endpoint = endpoint;
    }

    public void getAll(Callback<List<Product>> callback) {
        Call<List<Product>> call = apiService.getAll(endpoint);
        call.enqueue(callback);
    }

    public void get(int id, Callback<Product> callback) {
        Call<Product> call = apiService.get(endpoint, id);
        call.enqueue(callback);
    }

    public void create(Product model, Callback<Product> callback) {
        Call<Product> call = apiService.create(endpoint, model);
        call.enqueue(callback);
    }

    public void update(int id, Product model, Callback<Product> callback) {
        Call<Product> call = apiService.update(endpoint, id, model);
        call.enqueue(callback);
    }

    public void delete(int id, Callback<Product> callback) {
        Call<Product> call = apiService.delete(endpoint, id);
        call.enqueue(callback);
    }
}
