package com.mobilers.gift_hommie_mobile.model.checkout;

public class ShippingRequestDTO {
    private int payment_type_id = 2;
    private String note = "";
    private String from_name = "Hommie Store";
    private String from_phone = "0934968393";
    private String from_address = " Lô E2a-7, Đường D1, Đ.01";
    private String from_ward_name = "Phường Long Thạnh Mỹ";
    private String from_district_name = "Quận 9";
    private String from_province_name = "TP Hồ Chí Minh";
    private String required_note = "CHOXEMHANGKHONGTHU";

    private String client_order_code = "";
    private String to_name;
    private String to_phone;
    private String to_address;
    private String to_ward_code;
    private String to_district_id;
    private String to_province_id;

    private float cod_amount;
    private String content = "HommieStore | Quà tặng đến bạn.";
    private int weight = 200;
    private int length = 10;
    private int width = 20;
    private int height = 10;
    private int pick_station_id = 1444;
    private Object deliver_station_id = null;
    private float insurance_value;
    private int service_id = 0;
    private int service_type_id;
    private Object coupon = null;
    private Object pick_shift = null;
    private long pickup_time = 1665272576;

    public ShippingRequestDTO(CheckoutDTO checkoutDTO) {
        payment_type_id = checkoutDTO.getPaymentMethod() == 1 ? 2 : 1;
        this.to_name = checkoutDTO.getName() != null ? checkoutDTO.getName() : "Guest";
        this.to_phone = checkoutDTO.getPhone() != null ? checkoutDTO.getPhone() : "0909998877";
        this.to_address = checkoutDTO.getAddress() != null ? checkoutDTO.getAddress() : "Streaming house";
        this.to_ward_code = "" + checkoutDTO.getWardCode();
        this.to_district_id = "" + checkoutDTO.getDistrictID();
        this.to_province_id = "" + checkoutDTO.getProvinceID();

        int shippingMethod = 1;
        // Calculate cod_amount
        this.cod_amount = (shippingMethod == 1) ?
                checkoutDTO.getTotal() : 0;

        // Calculate insurance_value
        this.insurance_value = checkoutDTO.getProductCost();
    }


}
