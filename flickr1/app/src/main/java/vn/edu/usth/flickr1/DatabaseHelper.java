package vn.edu.usth.flickr1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "firebase_data.db";
    private static final int DATABASE_VERSION = 1;

    // Tên các bảng
    public static final String TABLE_FOLLOW = "Follow";
    public static final String TABLE_NOTIFICATIONS = "Notifications";
    public static final String TABLE_USERS = "Users";

    // Cấu trúc các bảng
    private static final String CREATE_TABLE_FOLLOW = "CREATE TABLE " + TABLE_FOLLOW + " (id TEXT PRIMARY KEY, data TEXT);";
    private static final String CREATE_TABLE_NOTIFICATIONS = "CREATE TABLE " + TABLE_NOTIFICATIONS + " (id TEXT PRIMARY KEY, data TEXT);";
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (id TEXT PRIMARY KEY, data TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FOLLOW);
        db.execSQL(CREATE_TABLE_NOTIFICATIONS);
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOLLOW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Phương thức để chèn dữ liệu vào bảng với xung đột
    public void insertData(String tableName, String id, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("data", data);
        db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }
}
