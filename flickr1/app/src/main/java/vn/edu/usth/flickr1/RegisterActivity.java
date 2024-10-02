//package vn.edu.usth.flickr1;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import com.google.firebase.Firebase;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.Firebase;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.HashMap;
//
//public class RegisterActivity extends AppCompatActivity {
//    EditText username, fullname, email, password;
//    Button register;
//    TextView txt_login;
//
//    FirebaseAuth auth;
//    DatabaseReference reference;
//    ProgressDialog pd;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        username = findViewById(R.id.username);
//        fullname = findViewById(R.id.fullname);
//        email = findViewById(R.id.email);
//        password = findViewById(R.id.password);
//        register = findViewById(R.id.register);
//        txt_login = findViewById(R.id.txt_login);
//
//        auth = FirebaseAuth.getInstance();
//
//        txt_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//            }
//        });
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pd = new ProgressDialog(RegisterActivity.this);
//                pd.setMessage("Please wait..");
//                pd.show();
//
//                String str_username = username.getText().toString();
//                String str_fullname = fullname.getText().toString();
//                String str_email = email.getText().toString();
//                String str_password = password.getText().toString();
//
//                if (TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_fullname) ||
//                        TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)) {
//                    Toast.makeText(RegisterActivity.this, "All fileds are required!", Toast.LENGTH_SHORT).show();
//                } else if (str_password.length() < 6) {
//                    Toast.makeText(RegisterActivity.this, "Password mut have 6 characters", Toast.LENGTH_SHORT).show();
//                } else {
//                    register(str_username,str_fullname,str_email,str_password);
//                }
//
//            }
//        });
//    }
//
//    private void register(String username, String fullname, String email, String password) {
//        auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser firebaseUser = auth.getCurrentUser();
//                            String userid = firebaseUser.getUid();
//
//                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
//
//                            HashMap<String, Object> hashMap = new HashMap<>();
//                            hashMap.put("id", userid);
//                            hashMap.put("username", username.toLowerCase());
//                            hashMap.put("fullname", fullname);
//                            hashMap.put("bio", "");
//                            hashMap.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/flickr1-dae37.appspot.com/o/placeholder.png?alt=media&token=5051f0de-125b-4ba5-9537-4b4c1d73417d");
//
//                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(task.isSuccessful()){
//                                        pd.dismiss();
//                                        Intent intent = new Intent(RegisterActivity.this , MainActivity.class );
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(intent);
//                                    }
//                                }
//                            });
//                        } else {
//                            pd.dismiss();
//                            Toast.makeText(RegisterActivity.this, "You can't register with this ", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//}

package vn.edu.usth.flickr1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText username, fullname, email, password;
    Button register;
    TextView txt_login;

    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ các view
        username = findViewById(R.id.username);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        txt_login = findViewById(R.id.txt_login);

        auth = FirebaseAuth.getInstance();

        // Điều hướng đến trang đăng nhập khi người dùng nhấn vào text "Login"
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        // Xử lý khi nhấn nút "Register"
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Please wait...");
                pd.show();

                // Lấy giá trị từ các trường nhập liệu
                String str_username = username.getText().toString();
                String str_fullname = fullname.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                // Kiểm tra các trường nhập liệu
                if (TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_fullname) ||
                        TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)) {
                    pd.dismiss(); // Đóng ProgressDialog nếu nhập không đủ
                    Toast.makeText(RegisterActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else if (str_password.length() < 6) {
                    pd.dismiss(); // Đóng ProgressDialog nếu mật khẩu không hợp lệ
                    Toast.makeText(RegisterActivity.this, "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    // Đăng ký người dùng nếu mọi thông tin hợp lệ
                    register(str_username, str_fullname, str_email, str_password);
                }
            }
        });
    }

    // Hàm đăng ký người dùng
    private void register(final String username, final String fullname, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                            // Tạo một HashMap để lưu trữ thông tin người dùng
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username.toLowerCase());
                            hashMap.put("fullname", fullname);
                            hashMap.put("bio", "");
                            hashMap.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/flickr1-dae37.appspot.com/o/placeholder.png?alt=media&token=5051f0de-125b-4ba5-9537-4b4c1d73417d");

                            // Lưu thông tin người dùng vào Firebase Database
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        pd.dismiss();
                                        // Chuyển đến MainActivity sau khi đăng ký thành công
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else {
                            pd.dismiss();
                            // Hiển thị lỗi chi tiết từ Firebase khi không đăng ký được
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(RegisterActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

