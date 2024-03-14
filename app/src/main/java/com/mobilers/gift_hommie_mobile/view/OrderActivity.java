package com.mobilers.gift_hommie_mobile.view;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.OrderPakageAdapter;
import com.mobilers.gift_hommie_mobile.model.order.OrderItemDTO;
import com.mobilers.gift_hommie_mobile.model.order.OrderPakageDTO;


import java.util.ArrayList;
import java.util.List;


public class OrderActivity extends AppCompatActivity {
    List<OrderPakageDTO> data;

    private RecyclerView recyclerView;
    private OrderPakageAdapter orderPakageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_manager);

        data=getListOrder();
        recyclerView= findViewById(R.id.order_list);
        orderPakageAdapter = new OrderPakageAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        orderPakageAdapter.setData(data);
        recyclerView.setAdapter(orderPakageAdapter);

    }
    private List<OrderPakageDTO> getListOrder(){
        List<OrderPakageDTO> listpakage = new ArrayList<>();

        List<OrderItemDTO> listitem  = new ArrayList<>();
        listitem.add(new OrderItemDTO("anh 1", 2 , 3000 ,"https://images4.alphacoders.com/130/1304509.jpeg"));
        listitem.add(new OrderItemDTO("anh 1", 2 , 3000 ,"https://images4.alphacoders.com/130/1304509.jpeg"));
        listitem.add(new OrderItemDTO("anh 1", 2 , 3000 ,"https://images4.alphacoders.com/130/1304509.jpeg"));
        listitem.add(new OrderItemDTO("anh 1", 2 , 3000 ,"https://images4.alphacoders.com/130/1304509.jpeg"));

        listpakage.add(new OrderPakageDTO(listitem, "1/1/2022", "đang cận chuyển"));
        listpakage.add(new OrderPakageDTO(listitem, "1/1/2022", "đang cận chuyển"));
        listpakage.add(new OrderPakageDTO(listitem, "1/1/2022", "đang cận chuyển"));
        return listpakage;
    }



    }


