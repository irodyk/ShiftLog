package com.deputy.shiftlog.ui.view.listener;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public interface ShiftListListener {
    /**
     * Method to notify activity regarding currently displayed group of fragments.
     * Triggered by fragments displaying shift details (including map for a single item).
     */
    void onShiftDetailsDisplayed();
}