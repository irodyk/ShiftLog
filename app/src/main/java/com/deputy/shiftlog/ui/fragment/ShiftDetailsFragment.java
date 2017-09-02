package com.deputy.shiftlog.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.deputy.shiftlog.R;
import com.deputy.shiftlog.domain.model.Shift;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public class ShiftDetailsFragment extends BaseFragment {

    @BindView(R.id.imageview_shift_photo) ImageView ivPhoto;
    @BindView(R.id.textview_start_time) TextView tvStartTime;
    @BindView(R.id.textview_end_time) TextView tvEndTime;
    @BindView(R.id.textview_shift_duration) TextView tvDuration;

    private Shift shift;

    public ShiftDetailsFragment(){
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            shift = bundle.getParcelable("shift");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_shift_details, container, false);
        ButterKnife.bind(this, fragmentView);

        if(shift != null){
            setupShiftView(shift);
        }
        return fragmentView;
    }

    private void setupShiftView(Shift shift){
        ivPhoto.setImageBitmap(shift.getImage());
        tvStartTime.setText(shift.getStartTime());
        tvEndTime.setText(shift.getEndTime());
        tvDuration.setText(shift.getDuration());
    }
}
