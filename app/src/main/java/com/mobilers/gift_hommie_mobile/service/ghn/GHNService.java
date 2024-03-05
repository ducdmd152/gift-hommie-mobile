package com.mobilers.gift_hommie_mobile.service.ghn;

import com.mobilers.gift_hommie_mobile.model.ghn.DistrictResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.ProvinceResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.WardResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

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
}
