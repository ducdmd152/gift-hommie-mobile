package com.mobilers.gift_hommie_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.order.OrderItemDTO;
import com.mobilers.gift_hommie_mobile.model.order.OrderPackageDTO;

import java.util.List;

public class OrderPakageAdapter extends RecyclerView.Adapter<OrderPakageAdapter.OrderAdapterViewHolder>{

    private Context context;
    private List<OrderPackageDTO> mlisDtos;

    public OrderPakageAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<OrderPackageDTO> list){
        this.mlisDtos= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_item_package, parent, false);
        return new OrderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapterViewHolder holder, int position) {
       OrderPackageDTO dto = mlisDtos.get(position);
       if (dto == null){
           return;
       }
       holder.date.setText(dto.getCreateTime());
        holder.status.setText(dto.getStatus());
        int tatal = 30000;
        for (OrderItemDTO item : dto.getOrderItem())
        {
            tatal = (int) (tatal+ item.getProduct().getPrice());
        }
        holder.tatol.setText("Tổng cộng: "+String.valueOf(tatal) +" đồng");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.pakage.setLayoutManager(linearLayoutManager);

       OrderItemAdapter orderItemAdapter = new OrderItemAdapter();
       orderItemAdapter.setData(dto.getOrderItem());
       holder.pakage.setAdapter(orderItemAdapter);

    }

    @Override
    public int getItemCount() {
        if (mlisDtos != null){
            return mlisDtos.size();
        }
        return 0;
    }

    public class OrderAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView date,status,ship,tatol;
        private RecyclerView pakage;
        Button chitiet, huy, mulai, danhgia, dadanhgia;
        public OrderAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.order_date);
            status=itemView.findViewById(R.id.order_status);
            ship=itemView.findViewById(R.id.order_ship);
            tatol=itemView.findViewById(R.id.order_tatol);

            pakage = itemView.findViewById(R.id.order_recycleview_detail);

            chitiet = itemView.findViewById(R.id.oeder_detail);
            huy = itemView.findViewById(R.id.oeder_cancel);
            mulai = itemView.findViewById(R.id.oeder_repurchase);
            danhgia = itemView.findViewById(R.id.oeder_Evaluate);
            dadanhgia = itemView.findViewById(R.id.oeder_evaluated);
        }
    }

}

