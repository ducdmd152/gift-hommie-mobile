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

    public int getPayment_type_id() {
        return payment_type_id;
    }

    public void setPayment_type_id(int payment_type_id) {
        this.payment_type_id = payment_type_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getFrom_phone() {
        return from_phone;
    }

    public void setFrom_phone(String from_phone) {
        this.from_phone = from_phone;
    }

    public String getFrom_address() {
        return from_address;
    }

    public void setFrom_address(String from_address) {
        this.from_address = from_address;
    }

    public String getFrom_ward_name() {
        return from_ward_name;
    }

    public void setFrom_ward_name(String from_ward_name) {
        this.from_ward_name = from_ward_name;
    }

    public String getFrom_district_name() {
        return from_district_name;
    }

    public void setFrom_district_name(String from_district_name) {
        this.from_district_name = from_district_name;
    }

    public String getFrom_province_name() {
        return from_province_name;
    }

    public void setFrom_province_name(String from_province_name) {
        this.from_province_name = from_province_name;
    }

    public String getRequired_note() {
        return required_note;
    }

    public void setRequired_note(String required_note) {
        this.required_note = required_note;
    }

    public String getClient_order_code() {
        return client_order_code;
    }

    public void setClient_order_code(String client_order_code) {
        this.client_order_code = client_order_code;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getTo_phone() {
        return to_phone;
    }

    public void setTo_phone(String to_phone) {
        this.to_phone = to_phone;
    }

    public String getTo_address() {
        return to_address;
    }

    public void setTo_address(String to_address) {
        this.to_address = to_address;
    }

    public String getTo_ward_code() {
        return to_ward_code;
    }

    public void setTo_ward_code(String to_ward_code) {
        this.to_ward_code = to_ward_code;
    }

    public String getTo_district_id() {
        return to_district_id;
    }

    public void setTo_district_id(String to_district_id) {
        this.to_district_id = to_district_id;
    }

    public String getTo_province_id() {
        return to_province_id;
    }

    public void setTo_province_id(String to_province_id) {
        this.to_province_id = to_province_id;
    }

    public float getCod_amount() {
        return cod_amount;
    }

    public void setCod_amount(float cod_amount) {
        this.cod_amount = cod_amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPick_station_id() {
        return pick_station_id;
    }

    public void setPick_station_id(int pick_station_id) {
        this.pick_station_id = pick_station_id;
    }

    public Object getDeliver_station_id() {
        return deliver_station_id;
    }

    public void setDeliver_station_id(Object deliver_station_id) {
        this.deliver_station_id = deliver_station_id;
    }

    public float getInsurance_value() {
        return insurance_value;
    }

    public void setInsurance_value(float insurance_value) {
        this.insurance_value = insurance_value;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(int service_type_id) {
        this.service_type_id = service_type_id;
    }

    public Object getCoupon() {
        return coupon;
    }

    public void setCoupon(Object coupon) {
        this.coupon = coupon;
    }

    public Object getPick_shift() {
        return pick_shift;
    }

    public void setPick_shift(Object pick_shift) {
        this.pick_shift = pick_shift;
    }

    public long getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(long pickup_time) {
        this.pickup_time = pickup_time;
    }
}
