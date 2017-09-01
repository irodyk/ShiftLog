package com.deputy.shiftlog.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.deputy.shiftlog.app.ShiftLogApp;
import com.deputy.shiftlog.di.component.ApplicationComponent;
import com.deputy.shiftlog.di.module.ActivityModule;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((ShiftLogApp) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
