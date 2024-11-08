package com.example.task3;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GroupmatesActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmates);

        dbHelper = new DatabaseHelper(this);

        TextView textView = findViewById(R.id.textViewGroupmates);
        displayGroupmates(textView);
    }

    // Метод для вывода списка одногруппников
    private void displayGroupmates(TextView textView) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_GROUPMATES, null, null, null, null, null, null);
        StringBuilder builder = new StringBuilder();

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            @SuppressLint("Range") String timestamp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIMESTAMP));
            builder.append("Name: ").append(name).append(", Added on: ").append(timestamp).append("\n");
        }
        textView.setText(builder.toString());
        cursor.close();
    }
}