package com.mobilers.gift_hommie_mobile.model.ghn;

public class DistrictDTO {
    private int DistrictID;
    private String DistrictName;
    private int ProvinceID;

    public int getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(int districtId) {
        this.DistrictID = districtId;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        this.DistrictName = districtName;
    }

    public int getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(int provinceID) {
        ProvinceID = provinceID;
    }
}

