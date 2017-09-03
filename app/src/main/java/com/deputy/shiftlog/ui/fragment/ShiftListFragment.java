package com.deputy.shiftlog.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deputy.shiftlog.R;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.presentation.ShiftListPresenter;
import com.deputy.shiftlog.presentation.ShiftPostPresenter;
import com.deputy.shiftlog.ui.adapter.ShiftAdapter;
import com.deputy.shiftlog.ui.view.ShiftListView;
import com.deputy.shiftlog.ui.view.ShiftUpdateView;
import com.deputy.shiftlog.ui.view.listener.ShiftListListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public class ShiftListFragment extends BaseFragment implements ShiftListView, ShiftUpdateView {

    private static final int PERMISSIONS_REQUEST_LOCATION = 13435;

    private FusedLocationProviderClient fusedLocationClient;

    @Inject ShiftPostPresenter shiftPostPresenter;
    @Inject ShiftListPresenter shiftListPresenter;
    private ShiftListListener shiftListListener;

    @Inject ShiftAdapter shiftAdapter;
    @BindView(R.id.recycler_shift_list) RecyclerView shiftRecycler;
    @BindView(R.id.fab) FloatingActionButton fab;

    private boolean isShiftStarted = false;

    public ShiftListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.shiftListListener = (ShiftListListener) activity;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getShiftComponent().inject(this);
        shiftListPresenter.onCreate();
        shiftPostPresenter.onCreate();
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View fragmentView = inflater.inflate(R.layout.fragment_shift_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shiftListPresenter.setShiftListView(this);
        shiftPostPresenter.setShiftUpdateView(this);

//        if(isUserOnline()){
//            shiftListPresenter.loadShiftFromNetwork();
//        }else{
//            shiftListPresenter.loadShiftFromLocalDatabase();
//        }

        shiftListPresenter.loadShiftFromLocalDatabase();
    }

    private void setupRecyclerView() {
        shiftAdapter.setOnItemClickListener(onItemClickListener);
        shiftRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        shiftRecycler.setAdapter(shiftAdapter);
    }

    private ShiftAdapter.OnItemClickListener onItemClickListener =
            new ShiftAdapter.OnItemClickListener() {
                @Override public void onShiftItemClicked(Shift shift) {
                    if(ShiftListFragment.this.shiftListListener != null){
                        ShiftListFragment.this.shiftListListener.onShiftDetailsDisplayed();
                        showFragmentWithSingleShift(new ShiftDetailsFragment(), shift);
                    }
                }
                @Override public void onOpenMapRequested(Shift shift) {
                    if(ShiftListFragment.this.shiftListListener != null) {
                        ShiftListFragment.this.shiftListListener.onShiftDetailsDisplayed();
                        showFragmentWithSingleShift(new MapFragment(), shift);
                    }
                }
            };

    private void showFragmentWithSingleShift(Fragment fragment, Shift shift){
        Bundle args = new Bundle();
        args.putParcelable("shift", shift);
        fragment.setArguments(args);
        replaceFragment(R.id.fragment_container, fragment);
    }

    public void openMap(){
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("shifts", shiftAdapter.getEntries());
        fragment.setArguments(args);
        replaceFragment(R.id.fragment_container, fragment);
    }

    @OnClick(R.id.fab) public void startStopShift(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //Request Location Permission
                checkLocationPermission();
                return;
            }
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location == null) {
                showToastMessage("Your location cannot be determined. Please, try again later");
                return;
            }

            String lat = String.valueOf(location.getLatitude());
            String lng = String.valueOf(location.getLongitude());
            String time = Calendar.getInstance().getTime().toString();
            if(isShiftStarted){
                fab.setImageResource(R.drawable.start);
                shiftPostPresenter.endShift(lat, lng, time);
            }else{
                fab.setImageResource(R.drawable.stop);
                shiftPostPresenter.startShift(lat, lng, time);
            }
            fab.setEnabled(false);

            isShiftStarted = !isShiftStarted;

        });
    }

    @Override
    public void renderShiftListView(ArrayList<Shift> shifts) {
        if(shifts != null && shifts.size() > 0){
            int i = shifts.size()-1;
            Shift lastShift = shifts.get(i);

            if(lastShift.getEndTime() == null) {
                fab.setImageResource(R.drawable.stop);
                isShiftStarted = true;
            }

            shiftAdapter.setShiftsCollection(shifts);
        }
    }

    @Override
    public void onLocalDataOverridden(boolean isUpdated) {
        showToastMessage(isUpdated ? "Saved locally" : "Couldn't save locally");
    }

    @Override
    public void onShiftStarted(final Shift shift) {
        shiftAdapter.getEntries().add(shift);
        shiftAdapter.notifyDataSetChanged();
        showToastMessage("New shift has been started");

        fab.setEnabled(true);
    }

    @Override
    public void onShiftEnded(Shift shift) {
        shiftAdapter.getEntries().remove(shiftAdapter.getItemCount()-1);
        shiftAdapter.getEntries().add(shift);
        shiftAdapter.notifyDataSetChanged();
        showToastMessage("Current shift has been ended");

        fab.setEnabled(true);
    }

    @Override
    public void notifyScheduledForUpdate() {
        showToastMessage("Current shift has been ended to update server later");
    }

    private boolean isUserOnline(){
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", (dialogInterface, i) -> requestPermissions(
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSIONS_REQUEST_LOCATION ))
                        .create()
                        .show();
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //call it again to complete the action
                        startStopShift();
                    }

                } else {
                    showToastMessage("permission denied");
                }
            }
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        shiftRecycler.setAdapter(null);
        shiftRecycler = null;
        fab = null;
    }

    @Override
    public void onDestroy() {
        shiftListPresenter.onDestroy();
        shiftPostPresenter.onDestroy();
        shiftPostPresenter = null;
        shiftListPresenter = null;
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        shiftListListener = null;
        super.onDetach();
    }
}