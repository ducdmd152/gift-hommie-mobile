package com.mobilers.gift_hommie_mobile.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilers.gift_hommie_mobile.CartActivity;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;
import com.mobilers.gift_hommie_mobile.model.cart.CartListResponseDTO;
import com.mobilers.gift_hommie_mobile.service.cart.CartAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartListHolder> {
    private Context context;
    private List<CartDTO> list;
    private OnItemClickListener listener;

    public CartListAdapter(Context context, List<CartDTO> cartList) {
        this.context = context;
        this.list = cartList;
    }

    @NonNull
    @Override
    public CartListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartListHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListHolder holder, int position) {
        CartDTO cartItem = list.get(position);
        holder.bind(cartItem, listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class CartListHolder extends RecyclerView.ViewHolder {
        private CartListAdapter adapter;
        private CartDTO item;
        private TextView tvName, tvPrice, tvQuantity;
        private ImageView ivMinus, ivPlus, ivDelete, ivProduct;
        CartAPIService cartAPIService = new CartAPIService();

        //        private ImageView
        public CartListHolder(CartListAdapter adapter, @NonNull View itemView) {
            super(itemView);
            this.adapter = adapter;
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            ivMinus = itemView.findViewById(R.id.ivMinus);
            ivPlus = itemView.findViewById(R.id.ivPlus);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivProduct = itemView.findViewById(R.id.ivProduct);
        }

        public void bind(CartDTO item, final OnItemClickListener listener) {
            this.item = item;
            tvName.setText(item.getProduct().getName());
            tvQuantity.setText("" + item.getQuantity());
            tvPrice.setText("" + item.getTotal());

            ivMinus.setOnClickListener(v -> {
                if (item.getQuantity() == 1) {
                    confirmToDelete();
                } else {
                    item.setQuantity(item.getQuantity() - 1);
                    adapter.notifyDataSetChanged();
                }
            });
            ivPlus.setOnClickListener(v -> {
                if (item.getQuantity() == item.getProduct().getAvailable()) {
                    Toast.makeText(adapter.context, "Số lượng sản phẩm " + item.getProduct().getName() + " đã được thêm hết vào danh sách!", Toast.LENGTH_SHORT).show();
                } else {
                    item.setQuantity(item.getQuantity() + 1);
                    adapter.notifyDataSetChanged();
                }
            });
            ivDelete.setOnClickListener(v -> {
                confirmToDelete();
            });
//            try {
//                Picasso.get().load(data.getImgSrc()).into(holder.ivAvatar);
//            }
//            catch (Exception ex) {
//                holder.ivAvatar.setImageResource(R.drawable.no_img);
//            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }

        private void confirmToDelete() {
            if (adapter.list == null || adapter.list.size() <= 1) {
                Toast.makeText(adapter.context, "Bạn không thể xóa, để checkout cần ít nhất 1 sản phẩm!", Toast.LENGTH_SHORT).show();
                return;
            }
            AlertDialog.Builder dialog = new androidx.appcompat.app.AlertDialog.Builder(adapter.context);
            dialog.setMessage("Bạn muốn xóa " + item.getProduct().getName() + " khỏi danh sách!");
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    cartAPIService.delete(item.getId(), new Callback<CartDTO>() {
                        @Override
                        public void onResponse(Call<CartDTO> call, Response<CartDTO> response) {
                            if (response.isSuccessful()) {
                            } else {
                                Toast.makeText(adapter.context, "Không thể tải danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<CartDTO> call, Throwable t) {
//                            Toast.makeText(adapter.context, "Đã xảy ra lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    adapter.list.remove(item);
                    Toast.makeText(adapter.context, "Đã xóa " + item.getProduct().getName() + " khỏi danh sách!", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            dialog.show();
        }
    }
}