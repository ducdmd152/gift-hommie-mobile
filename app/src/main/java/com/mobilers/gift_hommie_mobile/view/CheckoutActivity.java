package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.AddressItemAdapter;
import com.mobilers.gift_hommie_mobile.adapter.CheckoutListAdapter;
import com.mobilers.gift_hommie_mobile.model.auth.Account;
import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.AddressItem;
import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.ShippingInfoDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.ShippingResponseDTO;
import com.mobilers.gift_hommie_mobile.model.ghn.DistrictDTO;
import com.mobilers.gift_hommie_mobile.model.ghn.DistrictResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.ProvinceDTO;
import com.mobilers.gift_hommie_mobile.model.ghn.ProvinceResponse;
import com.mobilers.gift_hommie_mobile.model.ghn.WardDTO;
import com.mobilers.gift_hommie_mobile.model.ghn.WardResponse;
import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.service.checkout.CheckoutAPIService;
import com.mobilers.gift_hommie_mobile.service.ghn.GHNApiClient;
import com.mobilers.gift_hommie_mobile.service.ghn.GHNService;
import com.mobilers.gift_hommie_mobile.util.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {
    private Context context;
    private GlobalService globalService;
    private CheckoutDTO checkoutDTO;
    private EditText etName, etPhone, etAddress, etMessage, etVoucherCode;
    private TextView tvBtnApplyVoucher, btnCheckout, tvProductFee, tvShipFee, tvTotal;
    private Spinner spProvince, spDistrict, spWard;
    private RecyclerView rvCheckoutList;
    private CheckoutAPIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        context = CheckoutActivity.this;

        initService();
        binding();
        initData();
        events();
    }

    private void initService() {
        globalService = GlobalService.getInstance();
        checkoutDTO = globalService.getCheckoutDTO();
        apiService = new CheckoutAPIService();
    }
    private void binding() {
        etPhone = findViewById(R.id.etPhone);
        etName = findViewById(R.id.etName);
        etName.setText(globalService.getAccount().getFirstName() + " " + globalService.getAccount().getLastName());
        etPhone.setText(globalService.getAccount().getPhone());
        etAddress = findViewById(R.id.etAddress);
        etMessage = findViewById(R.id.etMessage);
        etVoucherCode = findViewById(R.id.etVoucherCode);

        spProvince = findViewById(R.id.spProvince);
        spDistrict = findViewById(R.id.spDistrict);
        spWard = findViewById(R.id.spWard);

        rvCheckoutList = findViewById(R.id.rvCheckoutList);

        tvBtnApplyVoucher = findViewById(R.id.tvBtnApplyVoucher);
        tvProductFee = findViewById(R.id.tvProductFee);
        tvShipFee = findViewById(R.id.tvShipFee);
        tvTotal = findViewById(R.id.tvTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        ImageView ivMenu = findViewById(R.id.ivMenu);
        ivMenu.setOnClickListener(v -> {
            Intent intent = new Intent(CheckoutActivity.this, User_Page_Activity.class);
            startActivity(intent);
            return;
        });
    }

    private void initData() {

        // load data
        List<AddressItem> provinces = new ArrayList<>();
        provinces.add(new AddressItem(0, "Tỉnh/thành phố"));
        ArrayAdapter<AddressItem> spProvinceAdapter = new AddressItemAdapter(this, provinces);
        spProvince.setAdapter(spProvinceAdapter);

        List<AddressItem> districts = new ArrayList<>();
        districts.add(new AddressItem(0, "Quận/huyện"));
        ArrayAdapter<AddressItem> spDistrictAdapter = new AddressItemAdapter(this, districts);
        spDistrict.setAdapter(spDistrictAdapter);

        List<AddressItem> wards = new ArrayList<>();
        wards.add(new AddressItem(0, "Phường/xã"));
        ArrayAdapter<AddressItem> spWardAdapter = new AddressItemAdapter(this, wards);
        spWard.setAdapter(spWardAdapter);

        GHNService.getProvinces(new Callback<ProvinceResponse>() {
            @Override
            public void onResponse(Call<ProvinceResponse> call, Response<ProvinceResponse> response) {
                if (response.isSuccessful()) {
                    ProvinceResponse provinceResponse = response.body();
                    if (provinceResponse != null && provinceResponse.getData() != null) {
                        provinces.clear();
                        for(ProvinceDTO item : provinceResponse.getData()) {
                            provinces.add(new AddressItem(item.getProvinceID(), item.getProvinceName()));
                        }
                        provinces.sort(Comparator.comparing(AddressItem::getName));
                        provinces.add(0, new AddressItem(0, "Tỉnh/thành phố"));
                        spProvinceAdapter.notifyDataSetChanged();
                    }
//                    Toast.makeText(context, "Loaded items successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Something wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProvinceResponse> call, Throwable t) {
                Toast.makeText(context, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
            }
        });

        // mapping data to ui
        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                AddressItem selectedAddress = (AddressItem) adapterView.getItemAtPosition(position);
                checkoutDTO.setProvinceID(selectedAddress.getCode());
                checkoutDTO.setProvinceName(selectedAddress.getName());

                districts.clear();
                districts.add(new AddressItem(0, "Quận/huyện"));

                if (checkoutDTO.getProvinceID() == 0) {
                    spDistrictAdapter.notifyDataSetChanged();
                    return;
                }

//                Toast.makeText(context, "Load quan huyen", Toast.LENGTH_SHORT).show();
                GHNService.getDistricts(checkoutDTO.getProvinceID(), new Callback<DistrictResponse>() {
                    @Override
                    public void onResponse(Call<DistrictResponse> call, Response<DistrictResponse> response) {
                        if (response.isSuccessful()) {
                            DistrictResponse districtResponse = response.body();
                            if (districtResponse != null && districtResponse.getData() != null) {
                                for(DistrictDTO item : districtResponse.getData()) {
                                    if (item.getProvinceID() == checkoutDTO.getProvinceID())
                                        districts.add(new AddressItem(item.getDistrictID(), item.getDistrictName()));
                                }
                                spDistrictAdapter.notifyDataSetChanged();
                            }
//                    Toast.makeText(context, "Loaded items successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Something wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DistrictResponse> call, Throwable t) {
                        Toast.makeText(context, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                checkoutDTO.setProvinceID(0);
                checkoutDTO.setProvinceName("");
                districts.clear();
                districts.add(new AddressItem(0, "Quận/huyện"));
                spDistrictAdapter.notifyDataSetChanged();
            }
        });

        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                AddressItem selectedAddress = (AddressItem) adapterView.getItemAtPosition(position);
                checkoutDTO.setDistrictID(selectedAddress.getCode());
                checkoutDTO.setDistrictName(selectedAddress.getName());
                wards.clear();
                wards.add(new AddressItem(0, "Phường/xã"));

                if (checkoutDTO.getDistrictID() == 0) {
                    spWardAdapter.notifyDataSetChanged();
                    return;
                }
                GHNService.getWards(checkoutDTO.getDistrictID(), new Callback<WardResponse>() {
                    @Override
                    public void onResponse(Call<WardResponse> call, Response<WardResponse> response) {
                        if (response.isSuccessful()) {
                            WardResponse wardResponse = response.body();
                            if (wardResponse != null && wardResponse.getData() != null) {
                                for(WardDTO item : wardResponse.getData()) {
                                    if (item.getDistrictID() == checkoutDTO.getDistrictID())
                                        wards.add(new AddressItem(item.getWardCode(), item.getWardName()));
                                }
                                spWardAdapter.notifyDataSetChanged();
                            }
//                    Toast.makeText(context, "Loaded items successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Something wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WardResponse> call, Throwable t) {
                        Toast.makeText(context, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                checkoutDTO.setProvinceID(0);
                checkoutDTO.setDistrictName("");
                wards.clear();
                wards.add(new AddressItem(0, "Phường/xã"));
                spWardAdapter.notifyDataSetChanged();
            }
        });

        spWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                AddressItem selectedAddress = (AddressItem) adapterView.getItemAtPosition(position);
                Log.d("Ward " + position, selectedAddress.getName() + " " + selectedAddress.getCode());
                checkoutDTO.setWardCode(selectedAddress.getCode());
                checkoutDTO.setWardName(selectedAddress.getName());
                Log.d("After Ward " + position, selectedAddress.getName() + " " + checkoutDTO.getWardCode());

                GHNService.previewOrder(checkoutDTO, new Callback<ShippingResponseDTO>() {
                    @Override
                    public void onResponse(Call<ShippingResponseDTO> call, Response<ShippingResponseDTO> response) {
                        if (response.isSuccessful()) {
                            ShippingInfoDTO res = response.body().getData();
                            checkoutDTO.setShippingFee(res.getTotal_fee());
                            updateCheckoutSummary();
                        }
                        else {
//                            checkoutDTO.setShippingFee(20000);
//                            updateCheckoutSummary();
                        }
                    }

                    @Override
                    public void onFailure(Call<ShippingResponseDTO> call, Throwable t) {
//                        checkoutDTO.setShippingFee(20000);
//                        updateCheckoutSummary();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                checkoutDTO.setProvinceID(0);
                checkoutDTO.setWardName("");
            }
        });

        CheckoutListAdapter checkoutListAdapter = new CheckoutListAdapter(context, checkoutDTO.getCarts());
        rvCheckoutList.setAdapter(checkoutListAdapter);
        rvCheckoutList.setLayoutManager (new LinearLayoutManager(this));
        updateCheckoutSummary();
        checkoutListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                updateCheckoutSummary();
            }
        });
    }
    private void events() {
        tvBtnApplyVoucher.setOnClickListener(v -> {
            if (etVoucherCode.getText().toString().trim().isEmpty()) {
                Toast.makeText(context, "Vui lòng nhập mã voucher!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Mã voucher không hợp lệ hoặc đã hết hạn!", Toast.LENGTH_SHORT).show();
            }
        });

        btnCheckout.setOnClickListener(v -> {
            if(!checkoutFormValidation()) return;

            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();
            String address = etAddress.getText().toString();
            String message = etMessage.getText().toString();


            checkoutDTO.setName(name);
            checkoutDTO.setPhone(phone);
            checkoutDTO.setAddress(address);
            checkoutDTO.setMessage(message);

            Intent intent = new Intent(CheckoutActivity.this, PaymentActivity.class);
            startActivity(intent);

        });
    }

    private boolean checkoutFormValidation() {
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String address = etAddress.getText().toString();
        String message = etMessage.getText().toString();
        if (name.trim().length() < 4) {
            Toast.makeText(context, "Vui lòng nhập tên người nhận hợp lệ, ít nhất 4 kí tự!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.isEmpty()) {
            Toast.makeText(context, "Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!phone.matches("^[+]?[0-9]{10,13}$")) {
            Toast.makeText(context, "Vui lòng nhập số điện thoại hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (checkoutDTO.getProvinceID() == 0 || checkoutDTO.getDistrictID() == 0 || checkoutDTO.getWardCode() == 0) {
            Toast.makeText(context, "Vui lòng chọn đầy đủ thông tin địa chỉ!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (address.isEmpty()) {
            Toast.makeText(context, "Vui lòng nhập địa chỉ cụ thể!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateCheckoutSummary() {
        tvProductFee.setText(Util.formatPriceVND(checkoutDTO.getProductCost()));
        tvShipFee.setText(Util.formatPriceVND(checkoutDTO.getShippingFee()));
        tvTotal.setText(Util.formatPriceVND(checkoutDTO.getTotal()));
    }
}