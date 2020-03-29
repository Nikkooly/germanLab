package com.bstu.fit.lab14;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class MyService extends Service {
    MediaPlayer myPlayer;
    private final static String NOTES="notes.txt";
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service", "onBind");
        return null;
    }
    @Override
    public void onCreate() {
        writeFile("Service Created");
        Log.d("Service","Service Created");
        myPlayer = MediaPlayer.create(this, R.raw.krug);
        myPlayer.setLooping(false);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        writeFile("Service Start");
        Log.d("Service","Service Started");
        myPlayer.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        writeFile("Service Stopped");
        Log.d("Service","Service Stopped");
        myPlayer.stop();
        readFile();
    }
    private void writeFile(String string)
    {
        File file =new File(super.getFilesDir()+NOTES);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(string+"\n");
            fileWriter.close();
        } catch (IOException ex) {
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private String readFile()
    {
        File file =new File(super.getFilesDir()+NOTES);
        try{
            FileReader fileReader = new FileReader(file);
            FileInputStream fw=new FileInputStream(file);
            char[] nw=new char[fw.available()];
            fileReader.read(nw);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < nw.length; ++i) {
                builder.append(nw[i]);
            }
            Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
            fileReader.close();
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        file.delete();
        return "";
    }
}

