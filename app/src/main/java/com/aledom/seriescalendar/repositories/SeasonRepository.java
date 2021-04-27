package com.aledom.seriescalendar.repositories;

import android.util.Log;

import com.aledom.seriescalendar.models.ResponseModel;
import com.aledom.seriescalendar.models.SeasonModel;
import com.aledom.seriescalendar.models.SerieModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import static com.aledom.seriescalendar.Constants.ADD_SEASON_URL;
import static com.aledom.seriescalendar.Constants.ADD_SERIE_URL;
import static com.aledom.seriescalendar.Constants.ERROR_PETITION;
import static com.aledom.seriescalendar.Constants.GET_SEASON_URL;
import static com.aledom.seriescalendar.Constants.SUCCESS_PETITION;

public class SeasonRepository {

    public List<SeasonModel> getSeason(int id_serie) throws LoginException, JSONException {

        String idSerie = String.valueOf(id_serie);

        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = idSerie;


        PutData putData = new PutData(GET_SEASON_URL, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                ResponseModel responseModel = new Gson().fromJson(putData.getResult(), ResponseModel.class);
                if (responseModel.status == SUCCESS_PETITION) {
                    List<SeasonModel> seasonResource = new Gson().fromJson(responseModel.response, new TypeToken<ArrayList<SeasonModel>>() {}.getType());
                    return seasonResource;
                } else if (responseModel.status == ERROR_PETITION) {
                    Log.i("PutData", responseModel.message);
                    throw new LoginException(responseModel.message);
                }
            }
        }
        throw new LoginException("Fallo");
    }

    public static Boolean addSeason(String name, int id_serie) throws LoginException, JSONException{

        String idSerie = String.valueOf(id_serie);

        String[] field = new String[2];
        field[0] = "name";
        field[1] = "id_serie";

        //Creating array for data
        String[] data = new String[2];
        data[0] = name;
        data[1] = idSerie;

        PutData putData = new PutData(ADD_SEASON_URL, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                ResponseModel responseModel = new Gson().fromJson(putData.getResult(), ResponseModel.class);

                if (responseModel.status == 1) {
                    return true;
                }
                Log.i("PutData", responseModel.message);
                throw new LoginException(responseModel.message);
            }
        }
        throw new LoginException("Fallo");
    }
}
