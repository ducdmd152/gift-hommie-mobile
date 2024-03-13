package com.mobilers.gift_hommie_mobile.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.ProductListAdapter;
import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;
import com.mobilers.gift_hommie_mobile.service.product.ProductAPIService;

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

        rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));
        etSearch = findViewById(R.id.etSearch);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        // Thực hiện setup Spinner với danh sách danh mục sản phẩm
        setupCategorySpinner();

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = etSearch.getText().toString().trim();
                performSearch(keyword);
            }
        });
    }

    private void setupCategorySpinner() {
        // Thiết lập adapter cho Spinner với danh sách danh mục sản phẩm
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        // Thiết lập sự kiện chọn danh mục từ Spinner
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Lấy categoryId tương ứng với danh mục được chọn
                int categoryId = position + 1; // Ví dụ: position 0 sẽ có categoryId = 1
                // Gọi phương thức để lấy danh sách sản phẩm theo categoryId
                getProductsByCategory(categoryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không làm gì khi không có danh mục nào được chọn
            }
        });
    }

    private void getProductsByCategory(int categoryId) {
        ProductAPIService productAPIService = new ProductAPIService();
        productAPIService.getProductsByCategory(categoryId, new Callback<ProductListResponseDTO>() {
            @Override
            public void onResponse(Call<ProductListResponseDTO> call, Response<ProductListResponseDTO> response) {
                if (response.isSuccessful()) {
                    // Cập nhật RecyclerView với danh sách sản phẩm mới
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
