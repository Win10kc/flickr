package com.example.flickr;

import com.example.flickr.model.User;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login = findViewById(R.id.login);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Xử lý đăng nhập
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("John", "Doe", "01/01/2003", "johndoe@gmail.com", "password", "default_avatar.png");

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("user", user);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Welcome to Flickr", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý khi nhấn nút Sign Up
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);  // Chuyển sang SignUpActivity
            }
        });
    }
}
