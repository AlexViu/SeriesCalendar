package com.aledom.seriescalendar.repositories;

import android.util.Log;

import com.aledom.seriescalendar.models.CommentModel;
import com.aledom.seriescalendar.models.ResponseModel;
import com.aledom.seriescalendar.models.SeasonModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import static com.aledom.seriescalendar.Constants.ADD_CHAPTER_URL;
import static com.aledom.seriescalendar.Constants.ADD_COMMENT_URL;
import static com.aledom.seriescalendar.Constants.ERROR_PETITION;
import static com.aledom.seriescalendar.Constants.GET_COMMENT_URL;
import static com.aledom.seriescalendar.Constants.GET_SEASON_URL;
import static com.aledom.seriescalendar.Constants.SUCCESS_PETITION;

public class CommentRepository {

    public List<CommentModel> getComment(int id_chapter) throws LoginException, JSONException {

        String idChapter = String.valueOf(id_chapter);

        String[] field = new String[1];
        field[0] = "id_chapter";
        String[] data = new String[1];
        data[0] = idChapter;


        PutData putData = new PutData(GET_COMMENT_URL, "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                ResponseModel responseModel = new Gson().fromJson(putData.getResult(), ResponseModel.class);
                if (responseModel.status == SUCCESS_PETITION) {
                    List<CommentModel> commentResource = new Gson().fromJson(responseModel.response, new TypeToken<ArrayList<CommentModel>>() {}.getType());
                    return commentResource;
                } else if (responseModel.status == ERROR_PETITION) {
                    Log.i("PutData", responseModel.message);
                    throw new LoginException(responseModel.message);
                }
            }
        }
        throw new LoginException("Fallo");
    }

    public static Boolean addComment(String username, String comment, int id_chapter) throws LoginException, JSONException {

        String idChapter = String.valueOf(id_chapter);

        String[] field = new String[3];
        field[0] = "username";
        field[1] = "comment";
        field[2] = "id_chapter";

        //Creating array for data
        String[] data = new String[3];
        data[0] = username;
        data[1] = comment;
        data[2] = idChapter;


        PutData putData = new PutData(ADD_COMMENT_URL, "POST", field, data);
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
