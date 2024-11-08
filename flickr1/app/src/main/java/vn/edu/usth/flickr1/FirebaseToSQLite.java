package vn.edu.usth.flickr1;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseToSQLite {

    private DatabaseHelper dbHelper;

    public FirebaseToSQLite(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void fetchDataAndSave() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // Lấy dữ liệu từ "Follow"
        databaseReference.child("Follow").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String id = snapshot.getKey();
                    String data = snapshot.getValue().toString();
                    dbHelper.insertData("Follow", id, data);
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
                    dbHelper.insertData("Notifications", id, data);
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
                    dbHelper.insertData("Users", id, data);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });
    }
}

