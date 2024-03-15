package com.mobilers.gift_hommie_mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.firebase.UserDTO;
import com.mobilers.gift_hommie_mobile.util.AndroidUtil;
import com.mobilers.gift_hommie_mobile.util.FirebaseUtil;
import com.mobilers.gift_hommie_mobile.view.ChatActivity;

public class SearchChatUserAdapter extends FirestoreRecyclerAdapter<UserDTO, SearchChatUserAdapter.UserViewHolder> {

    private Context mContext;
    private String uId;

    public SearchChatUserAdapter(@NonNull FirestoreRecyclerOptions<UserDTO> options, Context context) {
        super(options);
        this.mContext = context;
    }

    public void setUId(String inputId) {
        this.uId = inputId;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull UserDTO model) {
        String modName = model.getUsername();
        if(modName.equals("staff")) modName = "Shop";
        if (model.getUId().equals(uId)) {
            holder.usernameText.setText(modName + " (Me)");
        } else {
            holder.usernameText.setText(modName);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent chatIntent = new Intent(mContext, ChatActivity.class);
            AndroidUtil.passUserModelAsIntent(chatIntent, model, uId);
            chatIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(chatIntent);
        });
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_user_item, parent, false);
        return new UserViewHolder(view);
    }


    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView usernameText;
        ImageView profilePic;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
