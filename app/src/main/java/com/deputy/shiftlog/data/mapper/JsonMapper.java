package com.deputy.shiftlog.data.mapper;

import com.deputy.shiftlog.data.model.Response;
import com.deputy.shiftlog.data.model.ShiftEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class JsonMapper {

    private Gson gson;

    @Inject JsonMapper(){
        gson = new Gson();
    }

    public ArrayList<ShiftEntity> transformToShiftList(String jsonResponse) throws JsonSyntaxException {
        final Type responseType = new TypeToken<Response>() {}.getType();
        Response response = this.gson.fromJson(jsonResponse, responseType);
        return response.getShiftEntities();
    }
}
