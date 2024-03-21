package com.mobilers.gift_hommie_mobile.view;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cardinalcommerce.a.getProgressView;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.OrderItemAdapter;
import com.mobilers.gift_hommie_mobile.adapter.OrderPakageAdapter;
import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.model.order.OrderPackageDTO;
import com.mobilers.gift_hommie_mobile.model.order.OrderPakageListResponseDTO;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.service.order.OrderAPIService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderActivity extends AppCompatActivity {
    private List<OrderPackageDTO> data = new ArrayList<>();
    private OrderPakageAdapter orderPakageAdapter;
    private RecyclerView recyclerView;
    private GlobalService globalService;
    private CheckoutDTO checkoutDTO;

    private boolean apiCalled = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_manager);

        Button btnDaDatHang = findViewById(R.id.ordered);
        Button btndelivering = findViewById(R.id.delivering);
        Button btnDaHoangThanh = findViewById(R.id.accomplished);
        Button btncancelled = findViewById(R.id.cancelled);
        Button btnAll = findViewById(R.id.order_all);
        Button btnfailure = findViewById(R.id.failure);
//        Button btnhome = findViewById(R.id.order_home);
        ImageView ivMenu = findViewById(R.id.ivMenu);
        ivMenu.setOnClickListener(v -> {
            Intent intent = new Intent(OrderActivity.this, User_Page_Activity.class);
            startActivity(intent);
            return;
        });


        btnAll.setAlpha(1f);
        btnDaDatHang.setAlpha(0.5f);
        btndelivering.setAlpha(0.5f);
        btnDaHoangThanh.setAlpha(0.5f);
        btncancelled.setAlpha(0.5f);
        btnfailure.setAlpha(0.5f);


        initViews();
        initRecyclerView();
        initService();

        if (!apiCalled) {
            callOrderAPI();
            apiCalled = true;
        }


        btnDaDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAll.setAlpha(0.5f);
                btnDaDatHang.setAlpha(1f);
                btndelivering.setAlpha(0.5f);
                btnDaHoangThanh.setAlpha(0.5f);
                btncancelled.setAlpha(0.5f);
                btnfailure.setAlpha(0.5f);
                filterDataDaDatHang();
            }
        });


        btndelivering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAll.setAlpha(0.5f);
                btnDaDatHang.setAlpha(0.5f);
                btndelivering.setAlpha(1f);
                btnDaHoangThanh.setAlpha(0.5f);
                btncancelled.setAlpha(0.5f);
                btnfailure.setAlpha(0.5f);
                filterdelivering();
            }
        });


        btnDaHoangThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAll.setAlpha(0.5f);
                btnDaDatHang.setAlpha(0.5f);
                btndelivering.setAlpha(0.5f);
                btnDaHoangThanh.setAlpha(1f);
                btncancelled.setAlpha(0.5f);
                btnfailure.setAlpha(0.5f);
                filterDaHoangThanh();
            }
        });


        btncancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAll.setAlpha(0.5f);
                btnDaDatHang.setAlpha(0.5f);
                btndelivering.setAlpha(0.5f);
                btnDaHoangThanh.setAlpha(0.5f);
                btncancelled.setAlpha(1f);
                btnfailure.setAlpha(0.5f);
                filtercancelled();
            }
        });


        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAll.setAlpha(1f);
                btnDaDatHang.setAlpha(0.5f);
                btndelivering.setAlpha(0.5f);
                btnDaHoangThanh.setAlpha(0.5f);
                btncancelled.setAlpha(0.5f);
                btnfailure.setAlpha(0.5f);
                filterAll();
            }
        });

        btnfailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAll.setAlpha(0.5f);
                btnDaDatHang.setAlpha(0.5f);
                btndelivering.setAlpha(0.5f);
                btnDaHoangThanh.setAlpha(0.5f);
                btncancelled.setAlpha(0.5f);
                btnfailure.setAlpha(1f);
                filterfailure();
            }
        });

//        btnhome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.order_list);
    }

    private void initRecyclerView() {
        orderPakageAdapter = new OrderPakageAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderPakageAdapter);
    }

    private void initService() {
        globalService = GlobalService.getInstance();
        checkoutDTO = new CheckoutDTO();
        checkoutDTO.setCarts(new ArrayList<>());
        globalService.setCheckoutDTO(checkoutDTO);
    }

    private void callOrderAPI() {
        OrderAPIService orderApiService = new OrderAPIService();
        orderApiService.getAll(new Callback<OrderPakageListResponseDTO>() {
            @Override
            public void onResponse(Call<OrderPakageListResponseDTO> call, Response<OrderPakageListResponseDTO> response) {
                if (response.isSuccessful()) {
                    List<OrderPackageDTO> result = response.body().getContent();
                    if (result != null) {
                        data.addAll(result);
                        orderPakageAdapter.setData(data);
                        orderPakageAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(OrderActivity.this, "Không thể tải danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderPakageListResponseDTO> call, Throwable t) {
                Toast.makeText(OrderActivity.this, "Đã xảy ra lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, 100);

    }

    private void filterDataDaDatHang() {
        List<OrderPackageDTO> filter = new ArrayList<>();
        for (OrderPackageDTO item : data) {
            if (item.getStatus().equals("PENDING") || item.getStatus().equals("CONFIRMED")) {
                filter.add(item);
            }
        }
        orderPakageAdapter.setData(filter);
        orderPakageAdapter.notifyDataSetChanged();
    }

    private void filterdelivering() {
        List<OrderPackageDTO> filter = new ArrayList<>();
        for (OrderPackageDTO item : data) {
            if (item.getStatus().equals("DELIVERYING")) {
                filter.add(item);
            }
        }
        orderPakageAdapter.setData(filter);
        orderPakageAdapter.notifyDataSetChanged();
    }

    private void filtercancelled() {
        List<OrderPackageDTO> filter = new ArrayList<>();
        for (OrderPackageDTO item : data) {
            if (item.getStatus().equals("FAIL")) {
                filter.add(item);
            }
        }
        orderPakageAdapter.setData(filter);
        orderPakageAdapter.notifyDataSetChanged();
    }

    private void filterDaHoangThanh() {
        List<OrderPackageDTO> filter = new ArrayList<>();
        for (OrderPackageDTO item : data) {
            if (item.getStatus().equals("SUCCESSFUL")) {
                filter.add(item);
            }
        }
        orderPakageAdapter.setData(filter);
        orderPakageAdapter.notifyDataSetChanged();
    }

    private void filterAll() {
        List<OrderPackageDTO> filter = new ArrayList<>();
        for (OrderPackageDTO item : data) {

            filter.add(item);

        }
        orderPakageAdapter.setData(filter);
        orderPakageAdapter.notifyDataSetChanged();
    }

    private void filterfailure() {
        List<OrderPackageDTO> filter = new ArrayList<>();
        for (OrderPackageDTO item : data) {

            if (item.getStatus().equals("REFUSED")) {
                filter.add(item);
            }

        }
        orderPakageAdapter.setData(filter);
        orderPakageAdapter.notifyDataSetChanged();
    }
}
