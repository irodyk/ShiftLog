package com.deputy.shiftlog.presentation;

import com.deputy.shiftlog.domain.interactor.DataObserver;
import com.deputy.shiftlog.domain.interactor.GetShiftsFromLocal;
import com.deputy.shiftlog.domain.interactor.GetShiftsFromRemote;
import com.deputy.shiftlog.domain.interactor.StoreAllShiftsLocally;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.ui.view.ShiftListView;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class ShiftListPresenter implements Presenter {

    private GetShiftsFromLocal getShiftsFromLocal;
    private GetShiftsFromRemote getShiftsFromRemote;
    private StoreAllShiftsLocally storeAllShiftsLocally;

    private ShiftListView shiftListView;

    @Inject ShiftListPresenter(GetShiftsFromLocal getShiftsFromLocal,
                               GetShiftsFromRemote getShiftsFromRemote,
                               StoreAllShiftsLocally storeAllShiftsLocally){
        this.getShiftsFromLocal = getShiftsFromLocal;
        this.getShiftsFromRemote = getShiftsFromRemote;
        this.storeAllShiftsLocally = storeAllShiftsLocally;
    }

    public void setShiftListView(ShiftListView shiftListView){
        this.shiftListView = shiftListView;
    }


    public void loadShiftFromNetwork() {
        getShiftsFromRemote.execute(new ShiftListFromRemoteObserver(), null);
    }

    public void loadShiftFromLocalDatabase() {
        getShiftsFromLocal.execute(new ShiftListFromLocalObserver(), null);
    }

    private final class ShiftListFromLocalObserver extends DataObserver<ArrayList<Shift>, Void> {

        @Override public void onNext(ArrayList<Shift> shifts) {
            ShiftListPresenter.this.shiftListView.renderShiftListView(shifts);
        }
    }

    private final class ShiftListFromRemoteObserver extends DataObserver<ArrayList<Shift>, Void> {

        @Override public void onNext(ArrayList<Shift> shifts) {
            ShiftListPresenter.this.shiftListView.renderShiftListView(shifts);
            ShiftListPresenter.this.storeAllShiftsLocally.execute(new ShiftListStoreLocallyObserver(), null);
        }
    }

    private final class ShiftListStoreLocallyObserver extends DataObserver<Boolean, Void> {

        @Override public void onNext(Boolean isUpdated) {
            ShiftListPresenter.this.shiftListView.onLocalDataOverridden(isUpdated);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        shiftListView = null;
        getShiftsFromRemote.dispose();
        getShiftsFromLocal.dispose();
        storeAllShiftsLocally.dispose();
    }
}
