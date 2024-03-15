package com.mobilers.gift_hommie_mobile.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.ChatAdapter;
import com.mobilers.gift_hommie_mobile.model.firebase.ChatMessageDTO;
import com.mobilers.gift_hommie_mobile.model.firebase.ChatroomDTO;
import com.mobilers.gift_hommie_mobile.model.firebase.UserDTO;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.util.AndroidUtil;
import com.mobilers.gift_hommie_mobile.util.FirebaseUtil;

import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {

    private String uId;
    private UserDTO otherUser;
    private ChatroomDTO chatroomModel;
    private String chatroomId;
    private ChatAdapter chatAdapter;

    private EditText messageInput;
    private ImageButton sendMessageBtn, backBtn;
    private TextView otherUsername;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        uId = getIntent().getExtras().getString("mId");
        otherUser = AndroidUtil.getUserModelFromIntent(getIntent());
        chatroomId = FirebaseUtil.getChatroomId(uId, otherUser.getUId());

        messageInput = findViewById(R.id.chat_message_input);
        sendMessageBtn = findViewById(R.id.message_send_btn);
        backBtn = findViewById(R.id.back_btn_chat);
        otherUsername = findViewById(R.id.other_username);
        recyclerView = findViewById(R.id.chat_recycler_view);

        backBtn.setOnClickListener((v) -> {
            onBackPressed();
        });

        otherUsername.setText(otherUser.getUsername());

        sendMessageBtn.setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();
            if (message.isEmpty()) return;
            handleSendMessage(message);
        });

        initChatroom();
        setupChatRecyclerView();
    }

    private void setupChatRecyclerView() {
        Query query = FirebaseUtil.getChatroomMessageReference(chatroomId)
                .orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ChatMessageDTO> options = new FirestoreRecyclerOptions.Builder<ChatMessageDTO>()
                .setQuery(query, ChatMessageDTO.class).build();

        chatAdapter = new ChatAdapter(options, getApplicationContext());
        chatAdapter.setUId(uId);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(chatAdapter);
        chatAdapter.startListening();

        chatAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }

    private void handleSendMessage(String message) {
        chatroomModel.setLastMessageTimestamp(Timestamp.now());
        chatroomModel.setLastMessageSenderId(uId);
        chatroomModel.setLastMessage(message);
        FirebaseUtil.getChatRoomReference(chatroomId).set(chatroomModel);

        ChatMessageDTO chatMessageModel = new ChatMessageDTO(message, uId, Timestamp.now());
        FirebaseUtil.getChatroomMessageReference(chatroomId).add(chatMessageModel)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            messageInput.setText("");
                        }
                    }
                });
    }

    private void initChatroom() {
        FirebaseUtil.getChatRoomReference(chatroomId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                chatroomModel = task.getResult().toObject(ChatroomDTO.class);
                if (chatroomModel == null) {
                    chatroomModel = new ChatroomDTO(
                            chatroomId,
                            Arrays.asList(uId, otherUser.getUId()),
                            Timestamp.now(),
                            ""
                    );
                    FirebaseUtil.getChatRoomReference(chatroomId).set(chatroomModel);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (GlobalService.getInstance().getAccount().getRoleId() == 2) {
            super.onBackPressed();
        } else {
            Intent mIntent = new Intent(this, ProductListActivity.class);
            startActivity(mIntent);
        }
    }
}