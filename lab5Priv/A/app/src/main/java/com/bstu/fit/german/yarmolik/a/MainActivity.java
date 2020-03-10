package com.bstu.fit.german.yarmolik.a;

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
    //TASK 1

    public void goToB(View view){
        Intent intent = new Intent();
        intent.setClassName("com.bstu.fit.german.yarmolik.b","com.bstu.fit.german.yarmolik.b.MainActivity");
        startActivity(intent);
    }
    //TASK 2
    /*
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent();
        intent.setClassName("com.bstu.fit.german.yarmolik.b", "com.bstu.fit.german.yarmolik.b.PrivilegedActivityB");
        startActivity(intent);
    }
*/

    //TASK 3
  /* public void goToB(View view){
        Intent intent = new Intent();
        intent.setClassName("com.bstu.fit.german.yarmolik.a","com.bstu.fit.german.yarmolik.a.PrivilegedAActivity");
        startActivity(intent);
    }*/

    //TASK 4
    /*
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent();
        intent.setClassName("com.bstu.fit.german.yarmolik.a", "com.bstu.fit.german.yarmolik.a.PrivilegedAActivityTwo");
        startActivity(intent);
    }
    */

}
