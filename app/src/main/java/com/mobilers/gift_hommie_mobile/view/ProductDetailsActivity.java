package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.product.Product;

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
            tvPrice.setText(product.getPrice()+"đ");
            tvQuantity.setText("Số lượng còn lại: " + product.getQuantity());
            tvDescription.setText(product.getDescription());

            // Handle Add to Cart button click
            btnAddToCart.setOnClickListener(v -> {
                // Add product to cart logic here
                Toast.makeText(ProductDetailsActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
            });

            // Handle Buy Now button click
//            btnBuyNow.setOnClickListener(v -> {
//                // Buy now logic here
//                Toast.makeText(ProductDetailsActivity.this, "Buying now", Toast.LENGTH_SHORT).show();
//            });

            // Initialize and set up RecyclerView for reviews
            // Example:
            // ReviewAdapter reviewAdapter = new ReviewAdapter(product.getReviews());
            // rvReviews.setAdapter(reviewAdapter);
            // rvReviews.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
