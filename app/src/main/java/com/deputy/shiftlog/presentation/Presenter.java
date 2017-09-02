package com.deputy.shiftlog.presentation;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface Presenter {

    /**
     * Callbacks tied to activity lifecycle.
     * Used by presenters for initialization and to manage resources effectively.
     */
    void onCreate();
    void onDestroy();
}
