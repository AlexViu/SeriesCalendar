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

import static com.aledom.seriescalendar.Constants.ADD_FAVORITE_URL;
import static com.aledom.seriescalendar.Constants.ADD_SERIE_URL;
import static com.aledom.seriescalendar.Constants.ERROR_PETITION;
import static com.aledom.seriescalendar.Constants.GET_FAVORITE_URL;
import static com.aledom.seriescalendar.Constants.GET_SERIE_URL;
import static com.aledom.seriescalendar.Constants.SUCCESS_PETITION;

public class SerieRepository {

    public List<SerieModel> getSeries() throws LoginException, JSONException {

        String[] field = new String[1];
        field[0] = "name";
        String[] data = new String[1];
        data[0] = "name";


        PutData putData = new PutData(GET_SERIE_URL, "POST", field, data);
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

    public static Boolean addSerie(String name, String platform, String description) throws LoginException, JSONException{

        String[] field = new String[3];
        field[0] = "name";
        field[1] = "platform";
        field[2] = "description";
        //Creating array for data
        String[] data = new String[3];
        data[0] = name;
        data[1] = platform;
        data[2] = description;

        PutData putData = new PutData(ADD_SERIE_URL, "POST", field, data);
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

    public static Boolean addFavorite(String id_usuario, String id_serie) throws LoginException, JSONException{

        String[] field = new String[2];
        field[0] = "id_usuario";
        field[1] = "id_serie";

        //Creating array for data
        String[] data = new String[2];
        data[0] = id_usuario;
        data[1] = id_serie;

        PutData putData = new PutData(ADD_FAVORITE_URL, "POST", field, data);
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

    public List<SerieModel> getFavorite(String id_usuario) throws LoginException, JSONException {

        String[] field = new String[1];
        field[0] = "id_usuario";
        String[] data = new String[1];
        data[0] = id_usuario;


        PutData putData = new PutData(GET_FAVORITE_URL, "POST", field, data);
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
