package com.example.user.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.user.contentprovider.data.HotelContract;

public class HotelDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HotelDbHelper.class.getSimpleName();

    /**
     * Имя файла базы данных
     */
    private static final String DATABASE_NAME = "hotel.db";

    /**
     * Версия базы данных. При изменении схемы увеличить на единицу
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Конструктор {@link HotelDbHelper}.
     *
     * @param context Контекст приложения
     */
    public HotelDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Вызывается при создании базы данных
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Строка для создания таблицы
        String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE " + HotelContract.GuestEntry.TABLE_NAME + " ("
                + HotelContract.GuestEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HotelContract.GuestEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + HotelContract.GuestEntry.COLUMN_CITY + " TEXT NOT NULL, "
                + HotelContract.GuestEntry.COLUMN_GENDER + " INTEGER NOT NULL DEFAULT 3, "
                + HotelContract.GuestEntry.COLUMN_AGE + " INTEGER NOT NULL DEFAULT 0);";

        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    /**
     * Вызывается при обновлении схемы базы данных
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // Запишем в журнал
            Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

            // Удаляем старую таблицу и создаём новую
            db.execSQL("DROP TABLE IF  EXISTS " + DATABASE_TABLE);
            // Создаём новую таблицу
            onCreate(db);
        }
    }

