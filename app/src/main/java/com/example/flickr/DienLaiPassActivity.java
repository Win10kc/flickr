package com.example.flickr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DienLaiPassActivity extends AppCompatActivity {

    private EditText etNewPassword, etConfirmPassword;
    private Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dien_lai_pass_activity);

        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = etNewPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                // Nếu không kiểm tra mật khẩu, chuyển thẳng về màn hình đăng nhập
                Intent intent = new Intent(DienLaiPassActivity.this, LoginActivity.class);
                startActivity(intent);

                // Hiển thị thông báo thành công
                Toast.makeText(DienLaiPassActivity.this, "Password reset successfully", Toast.LENGTH_SHORT).show();

                // Kết thúc activity để ngăn việc quay lại
                finish();
            }
        });
    }
}

