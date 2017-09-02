package com.deputy.shiftlog.data.repository.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class DatabaseManager implements ShiftLocalDataStore {

    private static final String DATABASE_NAME = "shifts.db";

    private static final String SHIFTS_TABLE_NAME = "shifts";

    private static final String COMMON_COLUMN_ID = "_id";

    public static final String SHIFTS_COLUMN_SERVER_ID = "server_id";
    public static final String SHIFTS_COLUMN_START_TIME = "start_time";
    public static final String SHIFTS_COLUMN_END_TIME = "end_time";
    public static final String SHIFTS_COLUMN_START_LAT = "start_latitude";
    public static final String SHIFTS_COLUMN_START_LNG = "start_longitude";
    public static final String SHIFTS_COLUMN_END_LAT = "end_latitude";
    public static final String SHIFTS_COLUMN_END_LNG = "end_longitude";
    public static final String SHIFTS_COLUMN_IMAGE_URL = "image_url";
    public static final String SHIFTS_COLUMN_IMAGE_PATH = "image_path";

    private static final String SYNC_TABLE_NAME = "sync";

    public static final String SYNC_COLUMN_SHIFT_ID = "shift_id";


    private DbHelper dbHelper;

    @Inject
    DatabaseManager(Context context){
        dbHelper = new DbHelper(context);
    }




    @Override
    public Observable<ArrayList<Shift>> shiftList() {
        return null;
    }

    @Override
    public Observable<Void> create(Shift shift) {
        return null;
    }

    @Override
    public Observable<Void> end(Shift shift) {
        return null;
    }

    @Override
    public Observable<Boolean> storeSync(Shift shift) {
        return null;
    }

    @Override
    public Observable<Boolean> recreate(ArrayList<Shift> shifts) {
        return null;
    }


    private class DbHelper extends SQLiteOpenHelper {

        private DbHelper(Context context) {
            super(context, DATABASE_NAME , null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "create table " + SHIFTS_TABLE_NAME + "("+
                            COMMON_COLUMN_ID + " integer primary key autoincrement, " +
                            SHIFTS_COLUMN_SERVER_ID +" text, " +
                            SHIFTS_COLUMN_START_TIME +" text, " +
                            SHIFTS_COLUMN_END_TIME + " text, " +
                            SHIFTS_COLUMN_START_LAT + " text, " +
                            SHIFTS_COLUMN_START_LNG + " text, " +
                            SHIFTS_COLUMN_END_LAT + " text, " +
                            SHIFTS_COLUMN_END_LNG + " text, " +
                            SHIFTS_COLUMN_IMAGE_PATH + " text, " +
                            SHIFTS_COLUMN_IMAGE_URL + " text)"
            );

            db.execSQL(
                    "create table " + SYNC_TABLE_NAME + "("+
                            COMMON_COLUMN_ID + " integer primary key autoincrement, " +
                            SYNC_COLUMN_SHIFT_ID +" text)"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + SHIFTS_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + SYNC_TABLE_NAME);
            onCreate(db);
        }
    }
}
