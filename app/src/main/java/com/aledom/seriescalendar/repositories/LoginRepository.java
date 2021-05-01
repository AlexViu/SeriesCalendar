package com.aledom.seriescalendar.repositories;

import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import com.aledom.seriescalendar.models.ChapterModel;
import com.aledom.seriescalendar.models.SeasonModel;
import com.aledom.seriescalendar.models.SesionModel;
import com.google.gson.Gson;
import android.widget.Toast;

import com.aledom.seriescalendar.MainActivity;
import com.aledom.seriescalendar.models.ResponseModel;
import com.google.gson.reflect.TypeToken;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import static com.aledom.seriescalendar.Constants.ERROR_PETITION;
import static com.aledom.seriescalendar.Constants.GET_SEASON_URL;
import static com.aledom.seriescalendar.Constants.GET_SESION_URL;
import static com.aledom.seriescalendar.Constants.LOGIN_URL;
import static com.aledom.seriescalendar.Constants.SIGN_UP_URL;
import static com.aledom.seriescalendar.Constants.SUCCESS_PETITION;

public class LoginRepository{

    /**
     * Función que valida las credenciales de un usuario
     * @param username
     * @param password
     * @return
     * @throws LoginException
     */
    public Boolean login(String username, String password) throws LoginException, JSONException {

        String[] field = new String[2];
        field[0] = "username";
        field[1] = "password";

        //Creating array for data
        String[] data = new String[2];
        data[0] = username;
        data[1] = password;

        PutData putData = new PutData(LOGIN_URL, "POST", field, data);
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

    /**
     * Función que registra un usuario
     * @param username
     * @param password
     * @param email
     * @return
     * @throws LoginException
     */
    public Boolean signUp(String username, String password, String email) throws LoginException, JSONException{

        String[] field = new String[3];
        field[0] = "username";
        field[1] = "password";
        field[2] = "email";
        //Creating array for data
        String[] data = new String[3];
        data[0] = username;
        data[1] = password;
        data[2] = email;

        PutData putData = new PutData(SIGN_UP_URL, "POST", field, data);
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

    public List<SesionModel> getSesion(String username) throws LoginException, JSONException {

        String[] field = new String[1];
        field[0] = "username";
        String[] data = new String[1];
        data[0] = username;

        PutData putData = new PutData(GET_SESION_URL, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                ResponseModel responseModel = new Gson().fromJson(putData.getResult(), ResponseModel.class);
                if (responseModel.status == SUCCESS_PETITION) {
                    List<SesionModel> sessionResource = new Gson().fromJson(responseModel.response, new TypeToken<ArrayList<SesionModel>>() {}.getType());
                    return sessionResource;
                } else if (responseModel.status == ERROR_PETITION) {
                    Log.i("PutData", responseModel.message);
                    throw new LoginException(responseModel.message);
                }
            }
        }
        throw new LoginException("Fallo");
    }

}
