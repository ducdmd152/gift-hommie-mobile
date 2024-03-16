package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.cart.AddToCartDTO;
import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;
import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.service.cart.CartAPIService;
import com.mobilers.gift_hommie_mobile.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView imgAvatar;
    private TextView tvProductName, tvPrice, tvQuantity, tvDescription, tvReviews;
    private TextView btnAddToCart, btnBuyNow;
    private RecyclerView rvReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_custom);

        // Initialize views
        imgAvatar = findViewById(R.id.imgAvatar);
        tvProductName = findViewById(R.id.tvProductName);
        tvPrice = findViewById(R.id.tvPrice);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvDescription = findViewById(R.id.tvDescription);
//        tvReviews = findViewById(R.id.tvReviews);
        btnAddToCart = findViewById(R.id.btnAddToCart);
//        btnBuyNow = findViewById(R.id.btnBuyNow);
//        rvReviews = findViewById(R.id.rvReviews);

        // Get the product data from the intent
        Product product = (Product) getIntent().getSerializableExtra("product");
        String imageUrl = getIntent().getStringExtra("imageUrl"); // Get image URL

        // Check if product and image URL are not null
        if (product != null && imageUrl != null) {
            // Set product details to views
            Glide.with(this).load(imageUrl).into(imgAvatar); // Load and display image from URL
            tvProductName.setText(product.getName());
            tvPrice.setText(Util.formatPriceVND(product.getPrice())); // Format price using Util class
            tvQuantity.setText("Còn lại " + product.getQuantity() + " sản phẩm");
            tvDescription.setText(product.getDescription());

            TextView btnMoveToCart = findViewById(R.id.btnMoveToCart);

            // Thiết lập sự kiện click cho TextView btnMoveToCart
            btnMoveToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo Intent để chuyển sang ActivityCart
                    Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
                    startActivity(intent); // Bắt đầu ActivityCart
                }
            });

            btnAddToCart.setOnClickListener(v -> {
                AddToCartDTO addToCartDTO = new AddToCartDTO(product);
                CartAPIService apiService = new CartAPIService();
                apiService.addToCart(addToCartDTO, new Callback<CartDTO>() {
                    @Override
                    public void onResponse(Call<CartDTO> call, Response<CartDTO> response) {
                        if (response.isSuccessful()) {
                            // Xử lý phản hồi thành công
                            Toast.makeText(ProductDetailsActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        } else {
                            // Xử lý phản hồi không thành công
                            Toast.makeText(ProductDetailsActivity.this, "Không thể thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CartDTO> call, Throwable t) {
                        // Xử lý lỗi
                        Toast.makeText(ProductDetailsActivity.this, "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            });
        }
    }
}
