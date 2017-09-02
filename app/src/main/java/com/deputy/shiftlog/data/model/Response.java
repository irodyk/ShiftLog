package com.deputy.shiftlog.data.model;

import java.util.ArrayList;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class Response {

    ArrayList<ShiftEntity> shiftEntities;

    public ArrayList<ShiftEntity> getShiftEntities() {
        return shiftEntities;
    }

    public void setShiftEntities(ArrayList<ShiftEntity> shiftEntities) {
        this.shiftEntities = shiftEntities;
    }
}
