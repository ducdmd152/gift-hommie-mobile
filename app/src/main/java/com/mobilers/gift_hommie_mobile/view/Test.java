package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.firebase.UserDTO;
import com.mobilers.gift_hommie_mobile.util.AndroidUtil;

public class Test extends AppCompatActivity {

    private String uId;
    private UserDTO userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        uId = getIntent().getExtras().getString("uId");
        AndroidUtil.showToast(getApplicationContext(), uId);
    }
}