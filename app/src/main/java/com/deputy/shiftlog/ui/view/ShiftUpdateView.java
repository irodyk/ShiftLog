package com.deputy.shiftlog.ui.view;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftUpdateView {

    /**
     * Notifies view that new shift is started or last shift has ended.
     */
    void onShiftStarted();
    void onShiftEnded();
}
