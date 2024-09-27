package com.example.flickr;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.flickr.fragment.MenuFragment;
import com.example.flickr.fragment.NewsFragment;
import com.example.flickr.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set default fragment to NewsFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new NewsFragment())
                    .commit();
        }

        // Use setOnItemSelectedListener instead of the deprecated setOnNavigationItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.navigation_news) {
                selectedFragment = new NewsFragment();
            } else if (item.getItemId() == R.id.navigation_search) {
                selectedFragment = new SearchFragment();
            } else if (item.getItemId() == R.id.navigation_menu) {
                selectedFragment = new MenuFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, selectedFragment)
                        .commit();
            }
            return true;
        });
    }
}