package com.deputy.shiftlog.data.repository.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.deputy.shiftlog.data.mapper.ShiftCursorMapper;
import com.deputy.shiftlog.model.Shift;

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

    private ShiftCursorMapper shiftCursorMapper;

    private DbHelper dbHelper;

    @Inject
    DatabaseManager(Context context, ShiftCursorMapper shiftCursorMapper){
        this.shiftCursorMapper = shiftCursorMapper;
        dbHelper = new DbHelper(context);
    }

    @Override
    public Observable<ArrayList<Shift>> shiftList() {
        return Observable.fromArray(queryAllShifts(null));
    }

    @Override
    public Observable<Void> create(Shift shift) {
        if(shift != null){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(SHIFTS_COLUMN_START_TIME, String.valueOf(shift.getStartTime()));
            contentValues.put(SHIFTS_COLUMN_START_LAT, String.valueOf(shift.getStartLatitude()));
            contentValues.put(SHIFTS_COLUMN_START_LNG, String.valueOf(shift.getStartLongitude()));
            contentValues.put(SHIFTS_COLUMN_IMAGE_URL, String.valueOf(shift.getImageUrl()));

            db.insert(SHIFTS_TABLE_NAME, null, contentValues);

            db.close();
        }
        return Observable.empty();
    }

    @Override
    public Observable<Void> end(Shift shift) {
        if(shift != null){
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String query = "SELECT last_insert_rowid()";
            Cursor c = db.rawQuery(query, null);

            long lastId = -1;

            if (c != null && c.moveToFirst()) {
                lastId = c.getLong(0);
                c.close();
            }

            ContentValues contentValues = new ContentValues();
            contentValues.put(SHIFTS_COLUMN_END_TIME, String.valueOf(shift.getStartTime()));
            contentValues.put(SHIFTS_COLUMN_END_LAT, String.valueOf(shift.getStartLatitude()));
            contentValues.put(SHIFTS_COLUMN_END_LNG, String.valueOf(shift.getStartLongitude()));

            db.update(SHIFTS_TABLE_NAME, contentValues, COMMON_COLUMN_ID+"="+lastId, null);

            db.close();
        }
        return Observable.empty();
    }

    @Override
    public Observable<Boolean> recreate(ArrayList<Shift> shifts) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(SHIFTS_TABLE_NAME, null, null);

        boolean result = true;

        for(Shift shift : shifts){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SHIFTS_COLUMN_END_TIME, String.valueOf(shift.getStartTime()));
            contentValues.put(SHIFTS_COLUMN_END_LAT, String.valueOf(shift.getStartLatitude()));
            contentValues.put(SHIFTS_COLUMN_END_LNG, String.valueOf(shift.getStartLongitude()));
            contentValues.put(SHIFTS_COLUMN_END_TIME, String.valueOf(shift.getStartTime()));
            contentValues.put(SHIFTS_COLUMN_END_LAT, String.valueOf(shift.getStartLatitude()));
            contentValues.put(SHIFTS_COLUMN_END_LNG, String.valueOf(shift.getStartLongitude()));
            contentValues.put(SHIFTS_COLUMN_IMAGE_URL, String.valueOf(shift.getImageUrl()));
            contentValues.put(SHIFTS_COLUMN_IMAGE_PATH, String.valueOf(shift.getImagePath()));

            boolean isInserted = db.insert(SHIFTS_TABLE_NAME, null, contentValues) != -1;

            result = result && isInserted;
        }

        db.close();

        return Observable.just(result);
    }

    @Override
    public Observable<Boolean> storeSync(Shift shift) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SYNC_COLUMN_SHIFT_ID, shift.getId());
        boolean result = db.insert(SYNC_TABLE_NAME, null, contentValues) != -1;

        db.close();
        return Observable.just(result);
    }

    @Override
    public Observable<ArrayList<Shift>> queryAllSync() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(SYNC_TABLE_NAME, null, null,
                null, null, null, null);

        String ids = shiftCursorMapper.transformShiftIds(cursor);
        cursor.close();

        ArrayList<Shift> shifts = queryAllShifts(ids);

        db.delete(SYNC_TABLE_NAME, null, null);
        db.close();
        return Observable.fromArray(shifts);
    }

    private ArrayList<Shift> queryAllShifts(String ids) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = null;
        if(ids == null){
            cursor = db.query(SHIFTS_TABLE_NAME, null, null,
                    null, null, null, null);
        }else{
            cursor = db.query(SHIFTS_TABLE_NAME, null, COMMON_COLUMN_ID+" IN("+ids+")",
                    null, null, null, null);
        }

        ArrayList<Shift> shifts =  shiftCursorMapper.transformShiftCollection(cursor);
        cursor.close();
        db.close();
        return shifts;
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
