package com.mobilers.gift_hommie_mobile.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.mobilers.gift_hommie_mobile.model.firebase.ChatroomDTO;
import com.mobilers.gift_hommie_mobile.model.firebase.UserDTO;
import com.mobilers.gift_hommie_mobile.util.AndroidUtil;
import com.mobilers.gift_hommie_mobile.util.FirebaseUtil;
import com.mobilers.gift_hommie_mobile.view.ChatActivity;

public class ChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatroomDTO, ChatRecyclerAdapter.ChatroomModelViewHolder> {

    private Context context;
    private String uId;

    public ChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatroomDTO> options, Context context) {
        super(options);
        this.context = context;
    }

    public void setUId(String inputId) {
        this.uId = inputId;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatroomModelViewHolder holder, int position, @NonNull ChatroomDTO model) {
        FirebaseUtil.getOtherUserFromChatroom(model.getUserIds(), uId)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean lastMessageSentByMe = model.getLastMessageSenderId().equals(uId);

                        UserDTO otherUserModel = task.getResult().toObject(UserDTO.class);

                        holder.usernameText.setText(otherUserModel.getUsername());
                        holder.lastMessageTime.setText(FirebaseUtil.timestampToString(model.getLastMessageTimestamp()));

                        if (lastMessageSentByMe) {
                            holder.lastMessageText.setText("You : " + model.getLastMessage());
                        } else {
                            holder.lastMessageText.setText(model.getLastMessage());
                        }

                        holder.itemView.setOnClickListener(v -> {
                            Intent intent = new Intent(context, ChatActivity.class);
                            AndroidUtil.passUserModelAsIntent(intent, otherUserModel, uId);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        });

                    }
                });
    }

    @NonNull
    @Override
    public ChatroomModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_user_item, parent, false);
        return new ChatroomModelViewHolder(view);
    }

    class ChatroomModelViewHolder extends RecyclerView.ViewHolder {
        TextView usernameText, lastMessageText, lastMessageTime;
        ImageView profilePic;

        public ChatroomModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            lastMessageText = itemView.findViewById(R.id.last_message_text);
            lastMessageTime = itemView.findViewById(R.id.last_message_time_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
