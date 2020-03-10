package com.bstu.fit.german.yarmolik.c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToB(View view){
        Intent intent = new Intent();
        intent.setClassName("com.bstu.fit.german.yarmolik.b","com.bstu.fit.german.yarmolik.b.MainActivity");
        startActivity(intent);
    }
}
