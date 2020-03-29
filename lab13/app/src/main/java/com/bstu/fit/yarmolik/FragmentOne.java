package com.bstu.fit.yarmolik;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


public class FragmentOne extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ImageView imageView = view.findViewById(R.id.icon);
        imageView.setImageResource(R.drawable.lukash);
        ImageView imageViews = view.findViewById(R.id.snoop);
        imageViews.setImageResource(R.drawable.snop);
        return view;
    }
}
