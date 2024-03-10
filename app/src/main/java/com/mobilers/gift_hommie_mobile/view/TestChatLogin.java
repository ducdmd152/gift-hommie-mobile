package com.mobilers.gift_hommie_mobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.firebase.UserDTO;
import com.mobilers.gift_hommie_mobile.util.FirebaseUtil;


public class TestChatLogin extends AppCompatActivity {

    private EditText edt_username;
    private Button btn_letMeIn;
    private ProgressBar pb_username;
    private UserDTO userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chat_login);

        initUi();

        btn_letMeIn.setOnClickListener(v -> {
            setUsername();
        });

    }

    private void initUi() {
        edt_username = findViewById(R.id.edt_username);
        btn_letMeIn = findViewById(R.id.btn_letMeIn);
        pb_username = findViewById(R.id.pb_username);

    }

    private void setUsername() {
        String username = edt_username.getText().toString();
        if (username.isEmpty() || username.length() < 3) {
            edt_username.setError("Username length should be at least 3 chars !!!");
            return;
        }

        setInProgress(true);

        String myId = username;
        userModel = new UserDTO(myId, username);


        FirebaseUtil.currentUserDetails(userModel.getUId()).set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setInProgress(false);
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), Test.class);
                    intent.putExtra("uId", myId);
                    startActivity(intent);
                }
            }
        });
    }

    private void setInProgress(boolean isInProgress) {
        if (isInProgress) {
            pb_username.setVisibility(View.VISIBLE);
            btn_letMeIn.setVisibility(View.GONE);
        } else {
            pb_username.setVisibility(View.GONE);
            btn_letMeIn.setVisibility(View.VISIBLE);
        }
    }
}