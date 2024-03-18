package com.mobilers.gift_hommie_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.order.OrderItemDTO;
import com.mobilers.gift_hommie_mobile.util.Util;
import com.squareup.picasso.Picasso;


import java.util.List;

    public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderDetailViewHolder>{

        private List<OrderItemDTO> orderDetailDTOS;
        public void setData(List<OrderItemDTO> list){
            this.orderDetailDTOS = list;
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
            return new OrderDetailViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
            OrderItemDTO item = orderDetailDTOS.get(position);
            if (item == null){
                return;
            }

            Picasso.get().load(item.getProduct().getAvatar()).into(holder.avatar);
            holder.name.setText(item.getProduct().getName());
            holder.price.setText("Giá: "+ Util.formatPriceVND((int)item.getPrice()));
            holder.quanlity.setText("Số lượng: "+item.getQuantity());
        }

        @Override
        public int getItemCount() {
            if (orderDetailDTOS != null){
                return orderDetailDTOS.size();
            }
            return 0;
        }

        public class OrderDetailViewHolder extends RecyclerView.ViewHolder{
            private ImageView avatar;
            private TextView name, price, quanlity;
            public OrderDetailViewHolder(@NonNull View itemView) {
                super(itemView);

                avatar = itemView.findViewById(R.id.order_imgage);
                name = itemView.findViewById(R.id.order_name);
                price = itemView.findViewById(R.id.order_price);
                quanlity = itemView.findViewById(R.id.order_quality);

            }
        }
    }


