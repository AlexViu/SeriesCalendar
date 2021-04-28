package com.aledom.seriescalendar.repositories;

import android.util.Log;

import com.aledom.seriescalendar.models.ChapterModel;
import com.aledom.seriescalendar.models.ResponseModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import static com.aledom.seriescalendar.Constants.ADD_CHAPTER_URL;
import static com.aledom.seriescalendar.Constants.ADD_SEASON_URL;
import static com.aledom.seriescalendar.Constants.ERROR_PETITION;
import static com.aledom.seriescalendar.Constants.GET_CHAPTER_URL;
import static com.aledom.seriescalendar.Constants.SUCCESS_PETITION;

public class ChapterRepository {

    public List<ChapterModel> getChapter(int id_season) throws LoginException, JSONException {

        String idSeason = String.valueOf(id_season);

        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = idSeason;


        PutData putData = new PutData(GET_CHAPTER_URL, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                ResponseModel responseModel = new Gson().fromJson(putData.getResult(), ResponseModel.class);
                if (responseModel.status == SUCCESS_PETITION) {
                    List<ChapterModel> seasonResource = new Gson().fromJson(responseModel.response, new TypeToken<ArrayList<ChapterModel>>() {}.getType());
                    return seasonResource;
                } else if (responseModel.status == ERROR_PETITION) {
                    Log.i("PutData", responseModel.message);
                    throw new LoginException(responseModel.message);
                }
            }
        }
        throw new LoginException("Fallo");
    }

    public static Boolean addChapter(int num_chapter, String name, int id_season, String description, String date) throws LoginException, JSONException{

        String numChapter = String.valueOf(num_chapter);
        String idSeason = String.valueOf(id_season);

        String[] field = new String[5];
        field[0] = "number_chapter";
        field[1] = "name";
        field[2] = "id_season";
        field[3] = "description";
        field[4] = "date";

        //Creating array for data
        String[] data = new String[5];
        data[0] = numChapter;
        data[1] = name;
        data[2] = idSeason;
        data[3] = description;
        data[4] = date;


        PutData putData = new PutData(ADD_CHAPTER_URL, "POST", field, data);
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
