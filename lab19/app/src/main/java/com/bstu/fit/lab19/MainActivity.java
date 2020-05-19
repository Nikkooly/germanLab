package com.bstu.fit.lab19;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Bitmap bitmap = null;
    private ImageView img;
    private final String url="https://wallpapers.moviemania.io/phone/movie/508439/740ae9/onward-phone-wallpaper.jpg?w=1080&h=1920";
    private String localUrl="http://10.0.2.2/file:///android_asset/artemis_fowl.jpg";
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
         img= findViewById(R.id.imageView);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get().load(url).into(img);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checkInternetConenction();         downloadImage("https://image.prntscr.com/image/LoCdXyd1TpS_iazcTwaHVQ.png");
                Intent intent =new Intent(MainActivity.this,Firebase.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //загрузка рисунка
                checkInternetConenction();         downloadImage("https://wallpapers.moviemania.io/phone/movie/506528/08198d/harriet-phone-wallpaper.jpg?w=1080&h=1920");
            }
        });
    }

    private void downloadImage(String urlStr) {
        progressDialog = ProgressDialog.show(this, "", "Downloading Image from " + urlStr);
        final String url = urlStr;
        new Thread() {
            public void run() {
                InputStream in = null;

                Message msg = Message.obtain();
                msg.what = 1;

                try {
                    in = openHttpConnection(url);
                    bitmap = BitmapFactory.decodeStream(in);
                    Bundle b = new Bundle();
                    b.putParcelable("bitmap", bitmap);
                    msg.setData(b);
                    in.close();
                }catch (IOException e1) {
                    e1.printStackTrace();
                }
                messageHandler.sendMessage(msg);
            }
        }.start();
    }

    private InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode = -1;

        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            img.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap")));
            progressDialog.dismiss();
        }
    };

    private boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

}
