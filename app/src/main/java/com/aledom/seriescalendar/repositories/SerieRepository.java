package com.aledom.seriescalendar.repositories;

import android.util.Log;

import com.aledom.seriescalendar.models.ResponseModel;
import com.aledom.seriescalendar.models.SerieModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import static com.aledom.seriescalendar.Constants.ERROR_PETITION;
import static com.aledom.seriescalendar.Constants.SIGN_UP_URL;
import static com.aledom.seriescalendar.Constants.SUCCESS_PETITION;

public class SerieRepository {

    public List<SerieModel> getSeries() throws LoginException, JSONException {

        String[] field = new String[3];
        field[0] = "username";
        field[1] = "password";
        field[2] = "email";
        //Creating array for data
        String[] data = new String[3];
        data[0] = "";
        data[1] = "";
        data[2] = "";

        PutData putData = new PutData(SIGN_UP_URL, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                ResponseModel responseModel = new Gson().fromJson(putData.getResult(), ResponseModel.class);
                if (responseModel.status == SUCCESS_PETITION) {
                    List<SerieModel> seriesResource = new Gson().fromJson(responseModel.response, new TypeToken<ArrayList<SerieModel>>() {}.getType());
                    return seriesResource;
                } else if (responseModel.status == ERROR_PETITION) {
                    Log.i("PutData", responseModel.message);
                    throw new LoginException(responseModel.message);
                }
            }
        }
        throw new LoginException("Fallo");
    }
}
