package bstu.fit.germanlabs.myapplication.lab1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;


public class MainActivity extends Activity {

    EditText username;
    EditText password;
    String user = "admin";
    char[] pass = "admin".toCharArray();
    int count = 0;
    int maxCountAttempts = 5;
    int countAttempts = 3;
    String l, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        Button login = findViewById(R.id.button1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLoginScreen();
            }
        });
    }

    public void checkLoginScreen() {
        s = password.getText().toString().trim();
        l = username.getText().toString().trim();
        if (l.length() == 0  || s.length() == 0) {
            Toast.makeText(getApplicationContext(), "Empty field. Please check!",
                    Toast.LENGTH_SHORT).show();
        }
        else if(!l.equals(user)) {
            Toast.makeText(getApplicationContext(), "Empty user blya. Please check!",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            if(s.length() == pass.length)
                checkSymbols(s.toCharArray());
            else{
                if(Math.abs(pass.length - s.length()) == 1)
                    countAttempts = maxCountAttempts;
                count++;
                errorCheck();
            }
        }
    }

    public void checkSymbols(char[] password) {
        int countLocalError = 0;
        for (int i = 0; i < pass.length; i++) {
            if(password[i] != pass[i]){
                countLocalError++;
            }
        }
        if(countLocalError == 0){
            Toast.makeText(getApplicationContext(), "You are welcome!",
                    Toast.LENGTH_SHORT).show();
            count = 0;
        }
        else if(countLocalError == 1){
            countAttempts = maxCountAttempts;
            count++;
        }
        else{
            count++;
        }
        errorCheck();
    }

    public void errorCheck() {
        if (countAttempts == count)
            this.finish();
        if(count != 0){
            Toast.makeText(getApplicationContext(), "Rong Credentials",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
