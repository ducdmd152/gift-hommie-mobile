package com.mobilers.gift_hommie_mobile.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;

import java.util.List;

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.CheckoutListHolder> {
    private List<CartDTO> cartList;
    private OnItemClickListener listener;
    public CheckoutListAdapter(List<CartDTO> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CheckoutListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item, parent, false);
        return new CheckoutListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutListHolder holder, int position) {
        CartDTO cartItem = cartList.get(position);
        holder.bind(cartItem, listener);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class CheckoutListHolder extends RecyclerView.ViewHolder {

        private CartDTO cart;
        private TextView tvName, tvPrice, tvQuantity;
//        private ImageView
        public CheckoutListHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }

        public void bind(CartDTO cartItem, final OnItemClickListener listener) {
            cart = cartItem;
            tvName.setText(cart.getProduct().getName());
            tvQuantity.setText("" + cart.getQuantity());
            tvPrice.setText("" + cart.getTotal());
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
    }
}

