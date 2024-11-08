//package vn.edu.usth.flickr1;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.content.Intent;
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.fragment.app.Fragment;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.firebase.auth.FirebaseAuth;
//
//import vn.edu.usth.flickr1.Fragment.HomeFragment;
//import vn.edu.usth.flickr1.Fragment.NotificationFragment;
//import vn.edu.usth.flickr1.Fragment.ProfileFragment;
//import vn.edu.usth.flickr1.Fragment.SearchFragment;
//
//
//public class MainActivity extends AppCompatActivity {
//    BottomNavigationView bottomNavigationView;
//    Fragment selectedFragment = null;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedLintener);
//        Bundle intent = getIntent().getExtras();
//        if (intent != null){
//            String publisher = intent.getString("publisherid");
//
//            SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
//            editor.putString("profileid",publisher);
//            editor.apply();
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new ProfileFragment()).commit();
//        } else {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new HomeFragment()).commit();
//        }
//    }
////    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedLintener =
////            new BottomNavigationView.OnNavigationItemSelectedListener() {
////                @Override
////                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
////                    switch (menuItem.getItemId()){
////                        case R.id.nav_home:
////                            selectedFragment = new HomeFragment();
////                            break;
////                        case R.id.nav_search:
////                            selectedFragment = new SearchFragment();
////                            break;
////                        case R.id.nav_add:
////                            selectedFragment = null;
////                            startActivity(new Intent(MainActivity.this,PostActivity.class));
////                            break;
////                        case R.id.nav_heart:
////                            selectedFragment = new NotificationFragment();
////                            break;
////                        case R.id.nav_profile:
////                            SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
////                            editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
////                            editor.apply();
////                            selectedFragment = new ProfileFragment();
////                            break;
////                    }
////                    if (selectedFragment != null){
////                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
////                    }
////                    return true;
////                }
////            };
//private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedLintener =
//        new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                int id = menuItem.getItemId();
//
//                if (id == R.id.nav_home) {
//                    selectedFragment = new HomeFragment();
//                } else if (id == R.id.nav_search) {
//                    selectedFragment = new SearchFragment();
//                } else if (id == R.id.nav_add) {
//                    selectedFragment = null;
//                    startActivity(new Intent(MainActivity.this, PostActivity.class));
//                } else if (id == R.id.nav_heart) {
//                    selectedFragment = new NotificationFragment();
//                } else if (id == R.id.nav_profile) {
//                    SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
//                    editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
//                    editor.apply();
//                    selectedFragment = new ProfileFragment();
//                }
//                if (selectedFragment != null) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
//                }
//
//                return true;
//            }
//        };
//
//}
package vn.edu.usth.flickr1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import vn.edu.usth.flickr1.Fragment.HomeFragment;
import vn.edu.usth.flickr1.Fragment.NotificationFragment;
import vn.edu.usth.flickr1.Fragment.ProfileFragment;
import vn.edu.usth.flickr1.Fragment.SearchFragment;
import vn.edu.usth.flickr1.R;


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
    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment = null;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this); // Khởi tạo DatabaseHelper
        fetchDataAndSave(); // Chuyển dữ liệu từ Firebase sang SQLite

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        // Kiểm tra xem có dữ liệu từ Intent không
        Bundle intent = getIntent().getExtras();
        if (intent != null) {
            String publisher = intent.getString("publisherid");

            // Lưu profileid vào SharedPreferences
            SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
            editor.putString("profileid", publisher);
            editor.apply();

            // Mở ProfileFragment khi có publisherid
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragment()).commit();
        } else {
            // Mặc định mở HomeFragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    // Phương thức lấy dữ liệu từ Firebase và lưu vào SQLite
    private void fetchDataAndSave() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // Lấy dữ liệu từ "Follow"
        databaseReference.child("Follow").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String id = snapshot.getKey();
                    String data = snapshot.getValue().toString();
                    dbHelper.insertData(DatabaseHelper.TABLE_FOLLOW, id, data);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });

        // Lấy dữ liệu từ "Notifications"
        databaseReference.child("Notifications").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String id = snapshot.getKey();
                    String data = snapshot.getValue().toString();
                    dbHelper.insertData(DatabaseHelper.TABLE_NOTIFICATIONS, id, data);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });

        // Lấy dữ liệu từ "Users"
        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String id = snapshot.getKey();
                    String data = snapshot.getValue().toString();
                    dbHelper.insertData(DatabaseHelper.TABLE_USERS, id, data);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });
    }

    // Listener cho BottomNavigationView
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_add:
                            // Khi nhấn vào nút Add, mở PostActivity
                            startActivity(new Intent(MainActivity.this, PostActivity.class));
                            return true; // Không cần đổi fragment
                        case R.id.nav_heart:
                            selectedFragment = new NotificationFragment();
                            break;
                        case R.id.nav_profile:
                            // Lưu UID của người dùng hiện tại vào SharedPreferences
                            SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
                            editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                            editor.apply();
                            selectedFragment = new ProfileFragment();
                            break;
                        default:
                            selectedFragment = null;
                            break;
                    }

                    // Đổi fragment nếu có fragment được chọn
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    }

                    return true;
                }
            };
}
