package com.mobilers.gift_hommie_mobile.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.mobilers.gift_hommie_mobile.firebase.UserDTO;

public class AndroidUtil {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void passUserModelAsIntent(Intent mIntent, UserDTO uModel) {
        mIntent.putExtra("uId", uModel.getUId());
        mIntent.putExtra("username", uModel.getUsername());
    }

    public static UserDTO getUserModelFromIntent(Intent mIntent) {
        String uId = mIntent.getStringExtra("uId").toString();
        String username = mIntent.getStringExtra("username").toString();

        UserDTO userModel = new UserDTO(uId, username);
        return userModel;
    }
}
