package com.aledom.seriescalendar.repositories;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;
import android.widget.Toast;

import com.aledom.seriescalendar.MainActivity;
import com.aledom.seriescalendar.models.ResponseModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

import javax.security.auth.login.LoginException;

import static com.aledom.seriescalendar.Constants.LOGIN_URL;
import static com.aledom.seriescalendar.Constants.SIGN_UP_URL;

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

}
