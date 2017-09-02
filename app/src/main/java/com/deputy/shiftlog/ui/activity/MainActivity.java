package com.deputy.shiftlog.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.deputy.shiftlog.R;
import com.deputy.shiftlog.di.component.DaggerShiftComponent;
import com.deputy.shiftlog.di.component.ShiftComponent;
import com.deputy.shiftlog.ui.fragment.ShiftListFragment;
import com.deputy.shiftlog.ui.view.listener.ShiftListListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ShiftListListener {

    private ShiftComponent shiftComponent;

    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
    @BindView(R.id.imagebutton_list_or_map_view) ImageButton btnListMapView;
    @BindView(R.id.imagebutton_back) ImageButton btnBack;
    @BindView(R.id.imageview_app_logo) ImageView appLogo;

    private boolean isListDisplayed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initializeInjector();
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new ShiftListFragment(), "shifts");
            fragmentTransaction.commit();
        }
    }

    private void initializeInjector() {
        this.shiftComponent = DaggerShiftComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    public ShiftComponent getShiftComponent() {
        return shiftComponent;
    }

    @OnClick(R.id.imagebutton_back)
    public void returnToHome() {
        super.onBackPressed();
        appLogo.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.GONE);
        btnListMapView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.imagebutton_list_or_map_view)
    public void switchListMapView(){

        FragmentManager fm = getSupportFragmentManager();

        if(isListDisplayed){
            ShiftListFragment shiftListFragment = (ShiftListFragment) fm.findFragmentByTag("shifts");
            shiftListFragment.openMap();
            btnListMapView.setImageResource(R.drawable.list);
        }else{
            getSupportFragmentManager().popBackStack();
            btnListMapView.setImageResource(R.drawable.map);
        }

        isListDisplayed = !isListDisplayed;
    }

    @Override
    public void onShiftDetailsDisplayed() {
        btnBack.setVisibility(View.VISIBLE);
        appLogo.setVisibility(View.GONE);
        btnListMapView.setVisibility(View.GONE);
    }
}
