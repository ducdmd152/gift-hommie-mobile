package com.mobilers.gift_hommie_mobile.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilers.gift_hommie_mobile.R;

import java.net.URI;

public class MapActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_map);
        EditText editTextSource = findViewById(R.id.source);
        EditText editTextAddress = findViewById(R.id.address);
        Button button = findViewById(R.id.btnSubmit);
        String ADDRESS = "FPT University HCMC";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source = editTextSource.getText().toString();
                String adsress = editTextAddress.getText().toString();
                if(source.equals("")){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ1",
                    Toast.LENGTH_SHORT).show();
                }
                else {
                    Uri uri = Uri.parse("https://www.google.com/maps/dir/" + source + "/" + ADDRESS);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    intent.setPackage("com.google.android.apps.maps");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }

}
