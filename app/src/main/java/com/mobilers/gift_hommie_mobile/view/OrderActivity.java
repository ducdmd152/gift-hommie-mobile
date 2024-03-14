package com.mobilers.gift_hommie_mobile.view;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.OrderItemAdapter;
import com.mobilers.gift_hommie_mobile.adapter.OrderPakageAdapter;
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

    private boolean apiCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_manager);

        initViews();
        initRecyclerView();
        initService();

        if (!apiCalled) {
            callOrderAPI();
            apiCalled = true;
        }

        Button btnDaDatHang = findViewById(R.id.ordered);
        btnDaDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDataDaDatHang();
            }
        });

        Button btndelivering = findViewById(R.id.delivering);
        btndelivering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterdelivering();
            }
        });

        Button btnDaHoangThanh = findViewById(R.id.accomplished);
        btnDaHoangThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDaHoangThanh();
            }
        });

        Button btncancelled = findViewById(R.id.cancelled);
        btncancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtercancelled();
            }
        });

        Button btnAll = findViewById(R.id.order_all);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterAll();
            }
        });
        Button btnfailure = findViewById(R.id.failure);
        btnfailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterfailure();
            }
        });
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
        // Khởi tạo dịch vụ
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
        });
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
