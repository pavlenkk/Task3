package com.example.task3;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this); // Создаем объект базы данных

        Button btnViewGroupmates = findViewById(R.id.btnViewGroupmates);
        btnViewGroupmates.setOnClickListener(view -> viewGroupmates());

        Button btnAddGroupmate = findViewById(R.id.btnAddGroupmate);
        btnAddGroupmate.setOnClickListener(view -> addGroupmate());

        Button btnUpdateLastGroupmate = findViewById(R.id.btnUpdateLastGroupmate);
        btnUpdateLastGroupmate.setOnClickListener(view -> updateLastGroupmate());
    }

    // Метод для отображения данных о группе
    private void viewGroupmates() {
        Intent intent = new Intent(this, GroupmatesActivity.class);
        startActivity(intent);
    }

    // Метод для добавления новой записи
    private void addGroupmate() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, "New Student");
        db.insert(DatabaseHelper.TABLE_GROUPMATES, null, values);
    }

    // Метод для обновления последней записи
    private void updateLastGroupmate() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, "Иванов Иван Иванович");

        String query = "SELECT " + DatabaseHelper.COLUMN_ID + " FROM " + DatabaseHelper.TABLE_GROUPMATES +
                " ORDER BY " + DatabaseHelper.COLUMN_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int lastId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            db.update(DatabaseHelper.TABLE_GROUPMATES, values, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(lastId)});
        }
        cursor.close();
    }
}