package com.deputy.shiftlog.ui.view;

import com.deputy.shiftlog.model.Shift;

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

    /**
     * Delivers success/failure result on local database update after fetching from a remote.
     * @param isUpdated - true if operation successful.
     */
    void onLocalDataOverridden(boolean isUpdated);
}
