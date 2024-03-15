package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.adapter.SearchChatUserAdapter;
import com.mobilers.gift_hommie_mobile.model.firebase.UserDTO;
import com.mobilers.gift_hommie_mobile.util.FirebaseUtil;

public class SearchChatUserActivity extends AppCompatActivity {
    private String uId;

    private ImageButton backButton;
    private RecyclerView recyclerView;
    private SearchChatUserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_chat_user);

        uId = getIntent().getExtras().getString("uId");

        backButton = findViewById(R.id.back_btn);
        recyclerView = findViewById(R.id.search_user_recycler_view);

        setupSearchRecyclerView();

        backButton.setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void setupSearchRecyclerView() {
        Query query = FirebaseUtil.allUserCollectionReference();

        FirestoreRecyclerOptions<UserDTO> options = new FirestoreRecyclerOptions.Builder<UserDTO>()
                .setQuery(query, UserDTO.class).build();

        userAdapter = new SearchChatUserAdapter(options, getApplicationContext());
        userAdapter.setUId(uId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
        userAdapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (userAdapter != null)
            userAdapter.notifyDataSetChanged();
    }
}