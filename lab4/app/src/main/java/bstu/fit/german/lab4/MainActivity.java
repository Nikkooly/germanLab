package bstu.fit.german.lab4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends ListActivity {

    private List<String> item = null;
    private List<String> path = null;
    private String root;
    private TextView myPath;
    EncryptDecrypt td;
    private String text="Hello i am laba!";
    byte[] key;
    String encrypted,decrypted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         td= null;
        try {
            td = new EncryptDecrypt();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        isStoragePermissionGranted();
        FileOutputStream fOut = null;
        File directory = new File(Environment.getExternalStorageDirectory(), "AutoWriter");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "samplefile.txt");
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
        String nochartOutput = td.encrypt(text);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);
        try {
            osw.write(nochartOutput);
            osw.flush();
            osw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        myPath = findViewById(R.id.path);
        root = Environment.getExternalStorageDirectory().getPath();
        getDir(root);
    }

    private void getDir(String dirPath)
    {
        myPath.setText("Location: " + dirPath);
        item = new ArrayList<>();
        path = new ArrayList<>();
        File f = new File(dirPath);
        File[] files = f.listFiles();

        if(!dirPath.equals(root))
        {
            item.add(root);
            path.add(root);
            item.add("../");
            path.add(f.getParent());
        }

        for (File file : files) {
            if (!file.isHidden() && file.canRead()) {
                path.add(file.getPath());
                if (file.isDirectory()) {
                    item.add(file.getName() + "/");
                } else {
                    item.add(file.getName());
                }
            }
        }

        ArrayAdapter<String> fileList =
                new ArrayAdapter<>(this, R.layout.row, item);
        setListAdapter(fileList);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        File file = new File(path.get(position));

        if (file.isDirectory())
        {
            if(file.canRead()){
                getDir(path.get(position));
            }else{
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_launcher_background)
                        .setTitle("[" + file.getName() + "] folder can't be read!")
                        .setPositiveButton("OK", null).show();
            }
        }else {
            readFile();
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_launcher_background)
                    .setTitle("[" + file.getName() + "]"+"\n"+
                            td.decrypt(decrypted))
                    .setPositiveButton("OK", null).show();
        }
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
    private String readFile()
    {
        File file =new File(Environment.getExternalStorageDirectory(), "AutoWriter/samplefile.txt");
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
