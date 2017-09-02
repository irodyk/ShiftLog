package com.deputy.shiftlog.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deputy.shiftlog.R;
import com.deputy.shiftlog.domain.model.Shift;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private SupportMapFragment supportMapFragment;

    private List<Shift> shifts;

    public MapFragment(){
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            Shift shift = bundle.getParcelable("shift");
            if(shift != null){
                shifts = new ArrayList<>();
                shifts.add(shift);
            }else{
                shifts = bundle.getParcelableArrayList("shifts");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        supportMapFragment =
                ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_container));
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(googleMap == null){
            supportMapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        if(shifts != null){
            drawMarkers();
        }
    }

    private void drawMarkers() {
        LatLng latLngStart, latLngEnd;
        Marker markerStart, markerEnd;

        for (Shift shift : shifts) {
            latLngStart = new LatLng(Double.parseDouble(shift.getStartLatitude()), Double.parseDouble(shift.getStartLongitude()));
            markerStart = googleMap.addMarker(new MarkerOptions()
                    .position(latLngStart)
                    .snippet(latLngStart.toString()));
            markerStart.setTitle("id: " + shift.getId()+". Start");

            latLngEnd = new LatLng(Double.parseDouble(shift.getEndtLatitude()), Double.parseDouble(shift.getEndLongitude()));
            markerEnd = googleMap.addMarker(new MarkerOptions()
                    .position(latLngEnd)
                    .snippet(latLngEnd.toString()));
            markerEnd.setTitle("id: " + shift.getId()+". End");
        }
    }

    @Override
    public void onDestroy() {
        googleMap = null;
        shifts = null;
        supportMapFragment = null;
        super.onDestroy();
    }
}