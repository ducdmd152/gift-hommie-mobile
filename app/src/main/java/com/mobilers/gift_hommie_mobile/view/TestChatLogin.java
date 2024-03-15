package com.mobilers.gift_hommie_mobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.firebase.UserDTO;
import com.mobilers.gift_hommie_mobile.util.FirebaseUtil;


public class TestChatLogin extends AppCompatActivity {
    private UserDTO userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chat_login);

        setUsername();
    }

    private void setUsername() {
        String myId = getIntent().getExtras().getString("mId");

        userModel = new UserDTO(myId, myId);

        FirebaseUtil.currentUserDetails(userModel.getUId()).set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), Test.class);
                    intent.putExtra("uId", myId);
                    startActivity(intent);
                }
            }
        });
    }

}