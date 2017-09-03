package com.deputy.shiftlog.presentation;

import com.deputy.shiftlog.domain.interactor.DataObserver;
import com.deputy.shiftlog.domain.interactor.EndShiftLocal;
import com.deputy.shiftlog.domain.interactor.EndShiftRemote;
import com.deputy.shiftlog.domain.interactor.StartShiftLocal;
import com.deputy.shiftlog.domain.interactor.StartShiftRemote;
import com.deputy.shiftlog.domain.interactor.StoreShiftForUpdate;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.ui.view.ShiftUpdateView;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class ShiftPostPresenter implements Presenter {

    private StartShiftLocal startShiftLocal;
    private EndShiftLocal endShiftLocal;
    private StartShiftRemote startShiftRemote;
    private EndShiftRemote endShiftRemote;
    private StoreShiftForUpdate storeShiftForUpdate;

    private ShiftUpdateView shiftUpdateView;

    @Inject
    ShiftPostPresenter(StartShiftLocal startShiftLocal, EndShiftLocal endShiftLocal,
                       StartShiftRemote startShiftRemote, EndShiftRemote endShiftRemote,
                       StoreShiftForUpdate storeShiftForUpdate) {
        this.startShiftLocal = startShiftLocal;
        this.endShiftLocal = endShiftLocal;
        this.startShiftRemote = startShiftRemote;
        this.endShiftRemote = endShiftRemote;
        this.storeShiftForUpdate = storeShiftForUpdate;
    }

    public void setShiftUpdateView(ShiftUpdateView shiftUpdateView){
        this.shiftUpdateView = shiftUpdateView;
    }

    public void startShift(String latitude, String longitude, String time){
        Shift shift = new Shift();
        shift.setStartLatitude(latitude);
        shift.setStartLongitude(longitude);
        shift.setStartTime(time);
        int random = new Random().nextInt((500 - 100) + 1) + 100; //todo for Glide to not think it's the same image
        shift.setImageUrl("https://unsplash.it/"+random+"/"+random+"/?random");//todo temp, while server not available
        startShiftLocal.execute(new StartShiftLocalObserver(), shift);
//        startShiftRemote.execute(new StartShiftRemoteObserver(), shift);
    }

    public void endShift(String latitude, String longitude, String time){
        Shift shift = new Shift();
        shift.setEndtLatitude(latitude);
        shift.setEndLongitude(longitude);
        shift.setEndTime(time);
        endShiftLocal.execute(new EndShiftLocalObserver(), shift);
//        endShiftRemote.execute(new EndShiftRemoteObserver(), shift);
    }

    private final class StartShiftLocalObserver extends DataObserver<Shift, Shift> {

        @Override public void onNext(Shift shift) {
            ShiftPostPresenter.this.shiftUpdateView.onShiftStarted(shift);
        }
    }

    private final class EndShiftLocalObserver extends DataObserver<Shift, Shift> {
        @Override public void onNext(Shift shift) {
            ShiftPostPresenter.this.shiftUpdateView.onShiftEnded(shift);
        }
    }

    private final class StartShiftRemoteObserver extends DataObserver<Shift, Shift> {
        @Override public void onNext(Shift shift) {
            if(shift != null){
                storeShiftForUpdate.execute(new StoreLocallyToUpdateObserver(), shift);
            }
        }
    }

    private final class EndShiftRemoteObserver extends DataObserver<Shift, Shift> {
        @Override public void onNext(Shift shift) {
            if(shift != null){
                storeShiftForUpdate.execute(new StoreLocallyToUpdateObserver(), shift);
            }
        }
    }

    private final class StoreLocallyToUpdateObserver extends DataObserver<Boolean, ArrayList<Shift>> {
        @Override public void onNext(Boolean isUpdated) {
            ShiftPostPresenter.this.shiftUpdateView.notifyScheduledForUpdate();
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        shiftUpdateView = null;
        startShiftLocal.dispose();
        endShiftLocal.dispose();
        startShiftRemote.dispose();
        endShiftRemote.dispose();
        storeShiftForUpdate.dispose();
    }
}
