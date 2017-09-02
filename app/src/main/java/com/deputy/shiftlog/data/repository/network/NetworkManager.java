package com.deputy.shiftlog.data.repository.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deputy.shiftlog.BuildConfig;
import com.deputy.shiftlog.data.mapper.JsonMapper;
import com.deputy.shiftlog.data.mapper.ObjectMapper;
import com.deputy.shiftlog.data.model.ShiftEntity;
import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class NetworkManager implements ShiftRemoteDataStore {

    private RequestQueue queue;
    private JsonMapper jsonMapper;
    private ObjectMapper objectMapper;

    @Inject
    NetworkManager(Context context, JsonMapper jsonMapper, ObjectMapper objectMapper){
        this.jsonMapper = jsonMapper;
        this.objectMapper = objectMapper;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public Observable<ArrayList<Shift>> shiftList() {
        return Observable.create(sibscriber -> {
            String url = BuildConfig.BASE_URL + "shifts";
            MyStringRequest stringRequest = new MyStringRequest(Request.Method.GET, url,
                    response -> {
                        ArrayList<ShiftEntity> shiftEntities =
                                jsonMapper.transformToShiftList(response);

                        ArrayList<Shift> shiftsTransformed =
                                objectMapper.transformShiftResponse(shiftEntities);

                        sibscriber.onNext(shiftsTransformed);
                    }, error -> Log.d("DEBUG", error.getMessage()));

            queue.add(stringRequest);
        });
    }

    @Override
    public Observable<Shift> create(final Shift s) {
        return Observable.create(subscriber -> {
            String url = BuildConfig.BASE_URL + "shift/start";
            MyStringRequest stringRequest = new MyStringRequest(Request.Method.POST, url,
                    response -> {
                        Log.d("DEBUG", response);

                    }, error -> {
                Log.d("DEBUG", error.getMessage());
                subscriber.onNext(null);
            }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("time", s.getStartTime());
                    params.put("startLatitude", s.getStartLatitude());
                    params.put("startLongitude", s.getStartLongitude());

                    return params;
                }
            };

            queue.add(stringRequest);
        });
    }

    @Override
    public Observable<Shift> end(final Shift s) {
        return Observable.create(subscriber -> {
            String url = BuildConfig.BASE_URL + "shift/end";
            MyStringRequest stringRequest = new MyStringRequest(Request.Method.POST, url,
                    response -> {
                Log.d("DEBUG", response);
                    }, error -> {
                Log.d("DEBUG", error.getMessage());
                subscriber.onNext(null);
            }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("time", s.getStartTime());
                    params.put("endLatitude", s.getStartLatitude());
                    params.put("endLongitude", s.getStartLongitude());

                    return params;
                }
            };

            queue.add(stringRequest);
        });
    }

    private class MyStringRequest extends StringRequest {

        public MyStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String>  params = new HashMap<>();
            params.put("Authorization", "Deputy " + BuildConfig.SHA1);

            return params;
        }
    }
}
