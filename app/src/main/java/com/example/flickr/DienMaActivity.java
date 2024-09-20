package com.example.flickr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DienMaActivity extends AppCompatActivity {

    private EditText etCode;
    private Button btnVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dien_ma_activity);

        etCode = findViewById(R.id.etCode);
        btnVerifyCode = findViewById(R.id.btnVerifyCode);

        btnVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etCode.getText().toString().trim();

                // Nếu không cần kiểm tra mã xác nhận, chuyển thẳng đến màn hình Điền Lại Mật Khẩu
                Intent intent = new Intent(DienMaActivity.this, DienLaiPassActivity.class);
                startActivity(intent);
            }
        });
    }
}

