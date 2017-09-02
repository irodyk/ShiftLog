package com.deputy.shiftlog;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.deputy.shiftlog.domain.model.Shift;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public final class MockShifts {

    public static ArrayList<Shift> createShifts(Context ctx){
        ArrayList<Shift> shifts = new ArrayList<>();

        String date = DateFormat.getDateTimeInstance().format(new Date());


        Shift shift;
        for(int i = 0; i <= 5; i++){
            shift = new Shift();
            shift.setId(""+i);
            shift.setStartTime(date.substring(0, date.length()-2));
            shift.setEndTime(date.substring(0, date.length()));
            shift.setDuration(i+"h "+(i*i)+"m");
            shift.setStartLatitude("50."+i+"33");
            shift.setStartLongitude("20."+i+"22");
            shift.setStartLatitude("50."+(i*i)+"33");
            shift.setStartLongitude("20."+(i*i)+"22");
            shift.setImage(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.no_image));

            shifts.add(shift);
        }

        return shifts;
    }
}
