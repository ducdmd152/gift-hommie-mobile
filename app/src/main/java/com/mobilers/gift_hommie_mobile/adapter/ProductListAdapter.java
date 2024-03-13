package com.mobilers.gift_hommie_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private List<Product> filteredProductList; // Declare filteredProductList

    public ProductListAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.filteredProductList = new ArrayList<>(productList); // Initialize filteredProductList
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductPrice.setText("Giá: " + product.getPrice() + " đ");

        // Sử dụng Glide để tải ảnh từ URL và hiển thị trong ImageView
        Glide.with(context)
                .load(product.getAvatar()) // Load URL của hình ảnh
                .placeholder(R.drawable.placeholder_image) // Hình ảnh placeholder khi đang tải
                .error(R.drawable.error_image) // Hình ảnh hiển thị khi có lỗi
                .into(holder.ivProductImage); // Hiển thị trong ImageView
    }

    @Override
    public int getItemCount() {
        return filteredProductList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView tvProductName;
        TextView tvProductPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }

    public void filter(String keyword) {
        filteredProductList.clear();
        if (keyword.isEmpty()) {
            filteredProductList.addAll(productList);
        } else {
            keyword = keyword.toLowerCase();
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(keyword)) {
                    filteredProductList.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }


}
