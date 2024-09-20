package com.example.flickr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        // Ánh xạ nút Sign Up
        btnSignUp = findViewById(R.id.btnSignUp);

        // Xử lý sự kiện khi nhấn nút Sign Up
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thông báo đăng ký thành công
                Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                // Chuyển hướng về LoginActivity
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);

                // Kết thúc SignUpActivity để không quay lại khi nhấn back
                finish();
            }
        });
    }
}


