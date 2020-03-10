package com.bstu.fit.german.yarmolik.a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrivilegedAActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privileged_atwo);
    }
    public void goToA(View view){
        Intent intent = new Intent();
        intent.setClassName("com.bstu.fit.german.yarmolik.a","com.bstu.fit.german.yarmolik.a.PrivilegedAActivity");
        startActivity(intent);
    }
}
