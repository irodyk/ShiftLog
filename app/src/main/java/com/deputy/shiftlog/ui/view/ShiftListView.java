package com.deputy.shiftlog.ui.view;

import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftListView {

    /**
     * Delivers loaded shifts to the view.
     * @param shifts - loaded shifts;
     */
    void renderShiftListView(ArrayList<Shift> shifts);
}
