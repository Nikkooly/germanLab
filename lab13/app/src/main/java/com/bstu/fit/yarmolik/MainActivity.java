package com.bstu.fit.yarmolik;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    Fragment currentFragment = null;
    FragmentTransaction ft;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ft = getSupportFragmentManager().beginTransaction();
        currentFragment = new FragmentOne();
        ft.replace(R.id.container, currentFragment);
        ft.commit();
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.bottombaritem_popular:
                                currentFragment = new FragmentOne();
                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container, currentFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                                return true;
                            case R.id.bottombaritem_films:
                                currentFragment = new FragmentTwo();
                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container, currentFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                                return true;
                            case R.id.bottombaritem_ticket:
                                currentFragment = new ThirdFragment();
                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container, currentFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                                return true;
                        }
                        return false;
                    }
                });
    }
}