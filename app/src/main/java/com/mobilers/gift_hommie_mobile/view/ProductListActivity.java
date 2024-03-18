package com.mobilers.gift_hommie_mobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.ProductListAdapter;
import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.service.product.ProductAPIService;
import com.mobilers.gift_hommie_mobile.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView rvProductList;
    private ProductListAdapter productListAdapter;
    private EditText etSearch;
    private Spinner spinnerCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout);

        if (!GlobalService.getInstance().isAuthenticated()) {
            Intent intent = new Intent(ProductListActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        ImageView ivMenu = findViewById(R.id.ivMenu);
        ivMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, User_Page_Activity.class);
            startActivity(intent);
            return;
        });

        rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));
        etSearch = findViewById(R.id.etSearch);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        setupCategorySpinner();

        etSearch.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                String keyword = etSearch.getText().toString().trim();
                performSearch(keyword);
                return true;
            }
            return false;
        });
    }

    private void setupCategorySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    displayAllProducts();
                } else {
                    int categoryId = position;
                    getProductsByCategory(categoryId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void displayAllProducts() {
        ProductAPIService productAPIService = new ProductAPIService();
        productAPIService.getAllProducts(25, new Callback<ProductListResponseDTO>() {
            @Override
            public void onResponse(Call<ProductListResponseDTO> call, Response<ProductListResponseDTO> response) {
                if (response.isSuccessful()) {
                    productListAdapter = new ProductListAdapter(ProductListActivity.this, response.body().getContent());
                    rvProductList.setAdapter(productListAdapter);
                } else {
                    Toast.makeText(ProductListActivity.this, "Không thể tải danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductListResponseDTO> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "Đã xảy ra lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProductsByCategory(int categoryId) {
        ProductAPIService productAPIService = new ProductAPIService();
        productAPIService.getProductsByCategory(categoryId, new Callback<ProductListResponseDTO>() {
            @Override
            public void onResponse(Call<ProductListResponseDTO> call, Response<ProductListResponseDTO> response) {
                if (response.isSuccessful()) {
                    productListAdapter = new ProductListAdapter(ProductListActivity.this, response.body().getContent());
                    rvProductList.setAdapter(productListAdapter);
                } else {
                    Toast.makeText(ProductListActivity.this, "Không thể tải danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductListResponseDTO> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "Đã xảy ra lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performSearch(String keyword) {
        if (productListAdapter != null) {
            productListAdapter.filter(keyword);
        }
    }
}
