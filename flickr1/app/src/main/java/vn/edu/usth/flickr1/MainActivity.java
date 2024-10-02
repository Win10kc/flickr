package vn.edu.usth.flickr1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import vn.edu.usth.flickr1.Fragment.HomeFragment;
import vn.edu.usth.flickr1.Fragment.NotificationFragment;
import vn.edu.usth.flickr1.Fragment.ProfileFragment;
import vn.edu.usth.flickr1.Fragment.SearchFragment;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedLintener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }
//    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedLintener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                    switch (menuItem.getItemId()){
//                        case R.id.nav_home:
//                            selectedFragment = new HomeFragment();
//                            break;
//                        case R.id.nav_search:
//                            selectedFragment = new SearchFragment();
//                            break;
//                        case R.id.nav_add:
//                            selectedFragment = null;
//                            startActivity(new Intent(MainActivity.this,PostActivity.class));
//                            break;
//                        case R.id.nav_heart:
//                            selectedFragment = new NotificationFragment();
//                            break;
//                        case R.id.nav_profile:
//                            SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
//                            editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            editor.apply();
//                            selectedFragment = new ProfileFragment();
//                            break;
//                    }
//                    if (selectedFragment != null){
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
//                    }
//                    return true;
//                }
//            };
private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedLintener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (id == R.id.nav_search) {
                    selectedFragment = new SearchFragment();
                } else if (id == R.id.nav_add) {
                    selectedFragment = null;
                    startActivity(new Intent(MainActivity.this, PostActivity.class));
                } else if (id == R.id.nav_heart) {
                    selectedFragment = new NotificationFragment();
                } else if (id == R.id.nav_profile) {
                    SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
                    editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    editor.apply();
                    selectedFragment = new ProfileFragment();
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }

                return true;
            }
        };

}