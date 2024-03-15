package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.ChatRecyclerAdapter;
import com.mobilers.gift_hommie_mobile.adapter.SearchChatUserAdapter;
import com.mobilers.gift_hommie_mobile.model.firebase.ChatroomDTO;
import com.mobilers.gift_hommie_mobile.model.firebase.UserDTO;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.util.AndroidUtil;
import com.mobilers.gift_hommie_mobile.util.FirebaseUtil;

public class Test extends AppCompatActivity {

    private String uId;

    ImageButton searchButton;
    private RecyclerView recyclerView, rcvSearchUser;
    private ChatRecyclerAdapter adapter;
    private SearchChatUserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        uId = getIntent().getExtras().getString("uId");

        recyclerView = findViewById(R.id.recycler_view);
        rcvSearchUser = findViewById(R.id.search_user_recycler_view);
        searchButton = findViewById(R.id.main_search_btn);


        if (GlobalService.getInstance().getAccount().getRoleId() == 1) {
            searchButton.setVisibility(View.GONE);
            recyclerView.setVisibility((View.GONE));
            setupViewForUserRole();
        } else {
            rcvSearchUser.setVisibility((View.GONE));
            setupRecyclerView();
            searchButton.setOnClickListener(v -> {
                Intent searchIntent = new Intent(getApplicationContext(), SearchChatUserActivity.class);
                searchIntent.putExtra("uId", uId);
                startActivity(searchIntent);
            });
        }
    }

    void setupRecyclerView() {
        Query query = FirebaseUtil.allChatroomCollectionReference()
                .whereArrayContains("userIds", uId)
                .orderBy("lastMessageTimestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ChatroomDTO> options = new FirestoreRecyclerOptions.Builder<ChatroomDTO>()
                .setQuery(query, ChatroomDTO.class).build();

        adapter = new ChatRecyclerAdapter(options, getApplicationContext());
        adapter.setUId(uId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    void setupViewForUserRole() {
        Intent chatIntent = new Intent(this, ChatActivity.class);
        AndroidUtil.passUserModelAsIntent(chatIntent, new UserDTO("staff", "Shop"), uId);
        chatIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(chatIntent);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null)
            adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }
}