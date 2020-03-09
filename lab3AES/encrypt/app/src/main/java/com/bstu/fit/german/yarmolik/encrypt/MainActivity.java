package com.bstu.fit.german.yarmolik.encrypt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
EditText editText;
    Encrypt td;
    String text="ThisIsSpartaThisIsSparta";
    byte[] key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isStoragePermissionGranted();
        init();
        writeKey();
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
    private void init(){
        editText= findViewById(R.id.editText);
    }
    public void writeKey()  {
        FileOutputStream fKey = null;
        File directory = new File(Environment.getExternalStorageDirectory(), "AutoWriter");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File keys = new File(directory,"key.txt");
        if(!keys.exists()){
            try {
                if(!keys.createNewFile()){
                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.ic_launcher_background)
                            .setTitle("[" + keys.getName() + "] key doesn't exists!")
                            .setPositiveButton("OK", null).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fKey = new FileOutputStream(keys);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter k = new OutputStreamWriter(fKey);
        try {
            k.write(text);
            k.flush();
            k.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Load(View view) throws UnsupportedEncodingException {
        key=text.getBytes("UTF-8");
        td= null;
        td = new Encrypt();

        isStoragePermissionGranted();
        FileOutputStream fOut = null;

        File directory = new File(Environment.getExternalStorageDirectory(), "AutoWriter");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "text.txt");
        if(!file.exists()){
            try {
                if(!file.createNewFile()){
                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.ic_launcher_background)
                            .setTitle("[" + file.getName() + "] file doesn't exists!")
                            .setPositiveButton("OK", null).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fOut = new FileOutputStream(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String nochartOutput = td.encrypt(editText.getText().toString(),key);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);

        try {
            osw.write(nochartOutput);
            osw.flush();
            osw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
