package com.mobilers.gift_hommie_mobile.service.ghn;

import com.mobilers.gift_hommie_mobile.model.checkout.ShippingRequestDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.ShippingResponseDTO;
import com.mobilers.gift_hommie_mobile.model.ghn.DistrictResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.ProvinceResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.WardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGHNService {
    @GET("master-data/province")
    Call<ProvinceResponse> getProvinces();

    @GET("master-data/district")
    Call<DistrictResponse> getDistricts(@Query("province_id") int provinceId);

    @GET("master-data/ward")
    Call<WardResponse> getWards(@Query("district_id") int districtId);

    @POST("v2/shipping-order/preview")
    Call<ShippingResponseDTO> previewOrder(@Body ShippingRequestDTO dto);

    @POST("v2/shipping-order/create")
    Call<ShippingResponseDTO> createOrder(@Body ShippingRequestDTO orderDTO);
}

