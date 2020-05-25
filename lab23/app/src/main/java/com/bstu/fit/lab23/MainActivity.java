package com.bstu.fit.lab23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button b1,b2;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        b1=(Button)findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n  = ed1.getText().toString();
                String ph  = ed2.getText().toString();
                String e  = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.commit();
                Toast.makeText(MainActivity.this,"Thanks",Toast.LENGTH_LONG).show();
                ed2.setText(null);
                ed3.setText(null);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ed2.setText(null);
                ed3.setText(null);
                if(!ed1.getText().toString().equals("")){
                    if(sharedpreferences.contains(Name)) {
                        boolean result=sharedpreferences.getString(Name, "").contains(ed1.getText().toString());
                        if(result) {
                            ed2.setText(sharedpreferences.getString(Phone, ""));
                            ed3.setText(sharedpreferences.getString(Email, ""));
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Нету значений",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Не пустое",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}

