package com.bstu.fit.german.yarmolik.b;

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
    /*
    public void GoToA(View view){
        Intent intent = new Intent();
        intent.setClassName("com.bstu.fit.german.yarmolik.a", "com.bstu.fit.german.yarmolik.a.MainActivity");
        startActivity(intent);
    }*/
    /*
    public void GoToA(View view){
        Intent intent = new Intent();
        intent.setClassName("com.bstu.fit.german.yarmolik.a", "com.bstu.fit.german.yarmolik.a.PrivilegedAActivity");
        startActivity(intent);
    }
    */

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent();
        intent.setClassName("com.bstu.fit.german.yarmolik.a", "com.bstu.fit.german.yarmolik.a.PrivilegedAActivity");
        startActivity(intent);
    }
}
