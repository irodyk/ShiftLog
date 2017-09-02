package com.deputy.shiftlog.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.deputy.shiftlog.di.component.ShiftComponent;
import com.deputy.shiftlog.ui.activity.MainActivity;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public abstract class BaseFragment extends Fragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.addToBackStack("default");
        fragmentTransaction.commit();
    }

    protected ShiftComponent getShiftComponent() {
        return ((MainActivity) getActivity()).getShiftComponent();
    }
}
