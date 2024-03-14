package com.mobilers.gift_hommie_mobile.service.ghn;

import android.util.Log;

import com.google.gson.Gson;
import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.ShippingRequestDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.ShippingResponseDTO;
import com.mobilers.gift_hommie_mobile.model.ghn.DistrictResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.ProvinceResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.WardResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class GHNService {
    public static void getProvinces(Callback<ProvinceResponse> callback) {
        Call<ProvinceResponse> call = GHNApiClient.getClient().getProvinces();
        call.enqueue(callback);
    }

    public static void getDistricts(int provinceId, Callback<DistrictResponse> callback) {
        Call<DistrictResponse> call = GHNApiClient.getClient().getDistricts(provinceId);
        call.enqueue(callback);
    }

    public static void getWards(int districtId, Callback<WardResponse> callback) {
        Call<WardResponse> call = GHNApiClient.getClient().getWards(districtId);
        call.enqueue(callback);
    }

    public static void previewOrder(CheckoutDTO checkoutDTO, Callback<ShippingResponseDTO> callback) {
        Call<ShippingResponseDTO> call = GHNApiClient.getClient().previewOrder(new ShippingRequestDTO(checkoutDTO));
        Gson gson = new Gson();
        Log.d("checkoutDTO: ", gson.toJson(checkoutDTO));
        Log.d("Request: ", gson.toJson(new ShippingRequestDTO(checkoutDTO)));
        call.enqueue(callback);
    }

    public static void createOrder(CheckoutDTO checkoutDTO, Callback<ShippingResponseDTO> callback) {
        Call<ShippingResponseDTO> call = GHNApiClient.getClient().createOrder(new ShippingRequestDTO(checkoutDTO));
        call.enqueue(callback);
    }
}
