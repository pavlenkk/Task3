package com.example.task3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "groupmates.db"; // Имя базы данных
    private static final int DATABASE_VERSION = 1; // Версия базы данных

    // Название таблицы и столбцов
    public static final String TABLE_GROUPMATES = "Groupmates";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    // SQL-запрос для создания таблицы
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_GROUPMATES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    // Конструктор
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Метод для создания базы данных и таблицы
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE); // Создаем таблицу
        insertInitialData(db); // Вставляем начальные данные
    }

    // Метод для заполнения таблицы начальными данными
    private void insertInitialData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_GROUPMATES); // Очищаем таблицу
        for (int i = 1; i <= 5; i++) {
            db.execSQL("INSERT INTO " + TABLE_GROUPMATES + " (" + COLUMN_NAME + ") VALUES ('Student " + i + "')");
        }
    }

    // Метод для обновления структуры базы данных (не используется в первой версии)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPMATES);
        onCreate(db);
    }
}
