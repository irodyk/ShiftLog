package com.deputy.shiftlog.data.mapper;

import android.database.Cursor;

import com.deputy.shiftlog.model.Shift;
import com.deputy.shiftlog.data.repository.database.DatabaseManager;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class ShiftCursorMapper {

    @Inject
    public ShiftCursorMapper(){
    }

    public ArrayList<Shift> transformShiftCollection(Cursor cursor) {
        ArrayList<Shift> shifts = new ArrayList<>();
        if (cursor.moveToFirst()){
            Shift s;
            do{
                s = new Shift();

                s.setId(cursor.getString(cursor.getColumnIndex(DatabaseManager.SHIFTS_COLUMN_SERVER_ID)));
                s.setStartTime(cursor.getString(cursor.getColumnIndex(DatabaseManager.SHIFTS_COLUMN_START_TIME)));
                s.setEndTime(cursor.getString(cursor.getColumnIndex(DatabaseManager.SHIFTS_COLUMN_END_TIME)));
                s.setStartLatitude(cursor.getString(cursor.getColumnIndex(DatabaseManager.SHIFTS_COLUMN_START_LAT)));
                s.setStartLongitude(cursor.getString(cursor.getColumnIndex(DatabaseManager.SHIFTS_COLUMN_START_LNG)));
                s.setEndtLatitude(cursor.getString(cursor.getColumnIndex(DatabaseManager.SHIFTS_COLUMN_END_LAT)));
                s.setEndLongitude(cursor.getString(cursor.getColumnIndex(DatabaseManager.SHIFTS_COLUMN_END_LNG)));
                s.setImagePath(cursor.getString(cursor.getColumnIndex(DatabaseManager.SHIFTS_COLUMN_IMAGE_PATH)));
                s.setImageUrl(cursor.getString(cursor.getColumnIndex(DatabaseManager.SHIFTS_COLUMN_IMAGE_URL)));

                shifts.add(s);
            }while(cursor.moveToNext());
        }

        return shifts;
    }

    public String transformShiftIds(Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        if (cursor.moveToFirst()){
            do{
                sb.append(cursor.getString(cursor.getColumnIndex(DatabaseManager.SYNC_COLUMN_SHIFT_ID)));
                sb.append(",");
            }while(cursor.moveToNext());
        }
        if(sb.length() > 0)
            sb.replace(sb.length()-1, sb.length(), "");

        return sb.toString();
    }
}
