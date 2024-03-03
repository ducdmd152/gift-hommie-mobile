package com.mobilers.gift_hommie_mobile.service.ghn;

import com.mobilers.gift_hommie_mobile.model.ghn.DistrictResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.ProvinceResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.WardResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGHNService {
    @GET("province")
    Call<ProvinceResponse> getProvinces();

    @GET("district")
    Call<DistrictResponse> getDistricts(@Query("province_id") int provinceId);

    @GET("ward")
    Call<WardResponse> getWards(@Query("district_id") int districtId);
}

