package com.deputy.shiftlog.data.mapper;

import android.database.Cursor;

import com.deputy.shiftlog.data.model.ShiftEntity;
import com.deputy.shiftlog.data.repository.database.DatabaseManager;
import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class ObjectMapper {

    @Inject ObjectMapper(){
    }

    public ArrayList<Shift> transformShiftResponse(ArrayList<ShiftEntity> shiftEntities) {

        ArrayList<Shift> shifts = new ArrayList<>();

        Shift s;
        for(int i = 0; i < shiftEntities.size(); i++){
            s = new Shift();
            s.setStartTime(shiftEntities.get(i).getStartTime());
            s.setEndTime(shiftEntities.get(i).getEndTime());
            s.setStartLatitude(shiftEntities.get(i).getStartLatitude());
            s.setStartLongitude(shiftEntities.get(i).getStartLongitude());
            s.setEndtLatitude(shiftEntities.get(i).getEndtLatitude());
            s.setEndLongitude(shiftEntities.get(i).getEndLongitude());
            s.setImageUrl(shiftEntities.get(i).getImageUrl());
            
            shifts.add(s);
        }

        return shifts;
    }
}
