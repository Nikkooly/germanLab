package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    GestureLibrary mLibrary;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!mLibrary.load()) {
            finish();
        }

        GestureOverlayView gestures = (GestureOverlayView)
                findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);

        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
            String result = predictions.get(0).name;

            if ("open".equalsIgnoreCase(result)) {
                Toast.makeText(this, "Open", Toast.LENGTH_LONG).show();
            } else if ("save".equalsIgnoreCase(result)) {
                Toast.makeText(this, "Save", Toast.LENGTH_LONG).show();
            }
            else if ("olesha".equalsIgnoreCase(result)) {
                Toast.makeText(this, "Olesha", Toast.LENGTH_LONG).show();
            }
            else if ("oh".equalsIgnoreCase(result)) {
                Toast.makeText(this, "oh bl", Toast.LENGTH_LONG).show();
            }
        }
    }
}
