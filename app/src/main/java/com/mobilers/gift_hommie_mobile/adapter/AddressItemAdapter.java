package com.mobilers.gift_hommie_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobilers.gift_hommie_mobile.model.checkout.AddressItem;

import java.util.List;

public class AddressItemAdapter extends ArrayAdapter<AddressItem> {

    private Context mContext;
    private List<AddressItem> mAddressItems;

    public AddressItemAdapter(Context context, List<AddressItem> addressItems) {
        super(context, 0, addressItems);
        mContext = context;
        mAddressItems = addressItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        AddressItem currentItem = mAddressItems.get(position);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(currentItem.getName());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        AddressItem currentItem = mAddressItems.get(position);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(currentItem.getName());

        return view;
    }
}

