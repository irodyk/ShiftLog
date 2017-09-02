package com.deputy.shiftlog.presentation;

import com.deputy.shiftlog.domain.interactor.DataObserver;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.ui.view.ShiftListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class ShiftListPresenter implements Presenter {

    private ShiftListView shiftListView;
    private ArrayList<Shift> shifts;

    @Inject ShiftListPresenter(){

    }

    public void setShiftListView(ShiftListView shiftListView){
        this.shiftListView = shiftListView;
    }


    public void loadShiftFromNetwork() {
        //execute a usecase
    }

    public void loadShiftFromLocalDatabase() {
        //execute a usecase
    }

    public void startShift(){
        //execute a usecase
    }

    public void endShift(){
        //execute a usecase
    }

    private final class ShiftListObserver extends DataObserver<ArrayList<Shift>> {

        @Override public void onNext(ArrayList<Shift> shifts) {
            ShiftListPresenter.this.shifts = shifts;
            ShiftListPresenter.this.shiftListView.renderShiftListView(shifts);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        shiftListView = null;
        shifts = null;
    }
}
