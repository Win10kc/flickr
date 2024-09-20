package com.example.flickr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pass_activity);

        etEmail = findViewById(R.id.etEmail);
        btnSendCode = findViewById(R.id.btnSendCode);

        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();

                // Nếu không cần kiểm tra email, thì chuyển thẳng qua màn hình Điền Mã
                Intent intent = new Intent(ForgetPassActivity.this, DienMaActivity.class);
                startActivity(intent);
            }
        });
    }
}

