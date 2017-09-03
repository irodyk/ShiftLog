package com.deputy.shiftlog.ui.view;

import com.deputy.shiftlog.domain.model.Shift;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftUpdateView {

    /**
     * Notifies view that new shift is started or last shift has ended.
     */
    void onShiftStarted(Shift shift);
    void onShiftEnded(Shift shift);

    /**
     * notifies shift was scheduled to update later.
     */
    void notifyScheduledForUpdate();
}
