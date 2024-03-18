package com.mobilers.gift_hommie_mobile.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.CartListAdapter;
import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;
import com.mobilers.gift_hommie_mobile.model.cart.CartListResponseDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.service.cart.CartAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private Context context;
    private GlobalService globalService;
    private CheckoutDTO checkoutDTO;
    private CartListAdapter cartListAdapter;
    private RecyclerView rvItemCart;
    private List<CartDTO> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = CartActivity.this;

        initService();
        binding();
        initData();
        TextView btnCheckout = findViewById(R.id.btnCheckout);
        TextView tvTB = findViewById(R.id.tvTB);
        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            if (!checkoutDTO.getCarts().isEmpty()) startActivity(intent);
            else
                Toast.makeText(context, "Vui lòng chọn ít nhất 1 sản phẩm!", Toast.LENGTH_SHORT).show();
            return;
        });
        ImageView ivMenu = findViewById(R.id.ivMenu);
        ivMenu.setOnClickListener(v -> {
            Intent intent = new Intent(context, User_Page_Activity.class);
            startActivity(intent);
            return;
        });

        list = new ArrayList<>();
        cartListAdapter = new CartListAdapter(context, list);
        cartListAdapter.setOnItemClickListener(v -> {

        });

        rvItemCart.setAdapter(cartListAdapter);
        rvItemCart.setLayoutManager(new LinearLayoutManager(this));

        CartAPIService cartAPIService = new CartAPIService();
        cartAPIService.getAll(new Callback<CartListResponseDTO>() {
            @Override
            public void onResponse(Call<CartListResponseDTO> call, Response<CartListResponseDTO> response) {
                if (response.isSuccessful()) {
                    List<CartDTO> result = response.body().getContent();
                    list.clear();
                    if (result != null) list.addAll(result);
                    if (list.isEmpty()) tvTB.setText("Không có sản phẩm nào trong giỏ hàng!");
                    cartListAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Không thể tải danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartListResponseDTO> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Đã xảy ra lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        cartListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                //updateCheckoutSummary();
            }
        });
        cartListAdapter.setOnItemClickListener(new CartListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CartDTO item = list.get(position);
                Log.d("Index", "" + position);
//                View currentView = rvItemCart.getChildAt(position);
                if (item == null) return;
                if (globalService.getCheckoutDTO().getCarts().contains(item)) {
                    globalService.getCheckoutDTO().getCarts().remove(item);
//                    currentView.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    globalService.getCheckoutDTO().getCarts().add(item);
//                    currentView.setBackgroundColor(Color.CYAN);
                }
                cartListAdapter.notifyItemChanged(position);
            }
        });
        //events();
    }

    private void initService() {
        globalService = GlobalService.getInstance();
        checkoutDTO = new CheckoutDTO();
        checkoutDTO.setCarts(new ArrayList<>());
        globalService.setCheckoutDTO(checkoutDTO);
    }

    private void initData() {
    }

    private void binding() {
        rvItemCart = findViewById(R.id.rvItemCart);
    }
}