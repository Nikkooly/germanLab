package com.bstu.fit.german.yarmolik.decrypt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
String decrypted;
String keyRead;
TextView textView;
    byte[] key;
    Decrypt td;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isStoragePermissionGranted();
        readKey();
        td= null;
        td = new Decrypt();
    }
    public void Load(View view){
        readFile();
        try {
            key=keyRead.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        textView=findViewById(R.id.textView);
        textView.setText(td.decrypt(decrypted,key));


    }
    public  boolean isStoragePermissionGranted() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
            Log.v("vdfvfvfd","Permission is granted");
            return true;
        } else {

            Log.v("vfvfdvf","Permission is revoked");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return false;
        }
    }
    private String readKey()
    {
        File file =new File(Environment.getExternalStorageDirectory(), "AutoWriter/key.txt");
        try{
            FileReader fileReader = new FileReader(file);
            FileInputStream fw=new FileInputStream(file);
            char[] nw=new char[fw.available()];
            fileReader.read(nw);
            StringBuilder builder = new StringBuilder();
            for (char c : nw) {
                builder.append(c);
            }
            keyRead=builder.toString();
            fileReader.close();
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return "";
    }
    private String readFile()
    {
        File file =new File(Environment.getExternalStorageDirectory(), "AutoWriter/text.txt");
        try{
            FileReader fileReader = new FileReader(file);
            FileInputStream fw=new FileInputStream(file);
            char[] nw=new char[fw.available()];
            fileReader.read(nw);
            StringBuilder builder = new StringBuilder();
            for (char c : nw) {
                builder.append(c);
            }
            decrypted=builder.toString();
            fileReader.close();
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}
