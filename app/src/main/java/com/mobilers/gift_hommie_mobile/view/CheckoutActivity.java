package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.AddressItemAdapter;
import com.mobilers.gift_hommie_mobile.adapter.CheckoutListAdapter;
import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.AddressItem;
import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.service.GlobalService;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    private GlobalService globalService;
    private CheckoutDTO checkoutDTO;
    private EditText etName, etPhone, etAddress, etMessage;
    private Spinner spProvince, spDistrict, spWard;
    private RecyclerView rvCheckoutList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        initService();
        binding();
        initData();
    }

    private void initService() {
        globalService = GlobalService.getInstance();
        checkoutDTO = new CheckoutDTO();
        globalService.setCheckoutDTO(checkoutDTO);
    }
    private void binding() {
        etPhone = findViewById(R.id.etPhone);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etMessage = findViewById(R.id.etMessage);

        spProvince = findViewById(R.id.spProvince);
        spDistrict = findViewById(R.id.spDistrict);
        spWard = findViewById(R.id.spWard);

        rvCheckoutList = findViewById(R.id.rvCheckoutList);
    }

    private void initData() {
        // load data
        List<AddressItem> provinces = new ArrayList<>();
        List<AddressItem> districts = new ArrayList<>();
        List<AddressItem> wards = new ArrayList<>();
        List<CartDTO> carts = new ArrayList<>();
        carts.add(new CartDTO(127, 62, 1, new Product(62, "Cốc Sứ Họa Tiết", "", 333, 150000,"https://anh.quatructuyen.com/media/catalog/product/cache/1/image/480x480/9df78eab33525d08d6e5fb8d27136e95/c/_/c_c_s_h_a_ti_t_beautiful_girl_7.jpg", 1), 150000));
        carts.add(new CartDTO(127, 62, 1, new Product(62, "Cốc Sứ Họa Tiết", "", 333, 150000,"https://anh.quatructuyen.com/media/catalog/product/cache/1/image/480x480/9df78eab33525d08d6e5fb8d27136e95/c/_/c_c_s_h_a_ti_t_beautiful_girl_7.jpg", 1), 150000));
        checkoutDTO.setCarts(carts);

        // mapping data to ui
        provinces.add(new AddressItem(0, "Tỉnh/thành phố"));
        ArrayAdapter<AddressItem> spProvinceAdapter = new AddressItemAdapter(this, provinces);
        spProvince.setAdapter(spProvinceAdapter);
        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                AddressItem selectedAddress = (AddressItem) adapterView.getItemAtPosition(position);
                checkoutDTO.setProvinceID(selectedAddress.getCode());
                checkoutDTO.setProvinceName(selectedAddress.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                checkoutDTO.setProvinceID(0);
                checkoutDTO.setProvinceName("");
            }
        });


        districts.add(new AddressItem(0, "Quận/huyện"));
        ArrayAdapter<AddressItem> spDistrictAdapter = new AddressItemAdapter(this, districts);
        spDistrict.setAdapter(spDistrictAdapter);
        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                AddressItem selectedAddress = (AddressItem) adapterView.getItemAtPosition(position);
                checkoutDTO.setDistrictID(selectedAddress.getCode());
                checkoutDTO.setDistrictName(selectedAddress.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                checkoutDTO.setProvinceID(0);
                checkoutDTO.setDistrictName("");
            }
        });


        wards.add(new AddressItem(0, "Phường/xã"));
        ArrayAdapter<AddressItem> spWardAdapter = new AddressItemAdapter(this, wards);
        spWard.setAdapter(spWardAdapter);
        spWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                AddressItem selectedAddress = (AddressItem) adapterView.getItemAtPosition(position);
                checkoutDTO.setWardCode(selectedAddress.getCode());
                checkoutDTO.setWardName(selectedAddress.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                checkoutDTO.setProvinceID(0);
                checkoutDTO.setWardName("");
            }
        });

        CheckoutListAdapter checkoutListAdapter = new CheckoutListAdapter(checkoutDTO.getCarts());
        rvCheckoutList.setAdapter(checkoutListAdapter);
        rvCheckoutList.setLayoutManager (new LinearLayoutManager(this));
    }
}