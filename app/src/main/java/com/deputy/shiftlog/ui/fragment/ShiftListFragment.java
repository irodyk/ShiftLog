package com.deputy.shiftlog.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deputy.shiftlog.MockShifts;
import com.deputy.shiftlog.R;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.ui.adapter.ShiftAdapter;
import com.deputy.shiftlog.ui.view.listener.ShiftListListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public class ShiftListFragment extends BaseFragment {

    private ShiftListListener shiftListListener;

    private ArrayList<Shift> mockShiftList;

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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getShiftComponent().inject(this);
        mockShiftList = MockShifts.createShifts(getActivity());
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View fragmentView = inflater.inflate(R.layout.fragment_shift_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    private void setupRecyclerView() {
        shiftAdapter.setShiftsCollection(mockShiftList);
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
        if(isShiftStarted){
            fab.setImageResource(R.drawable.start);
        }else{
            fab.setImageResource(R.drawable.stop);
        }

        isShiftStarted = !isShiftStarted;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        this.shiftRecycler.setAdapter(null);
        this.shiftRecycler = null;
    }

}