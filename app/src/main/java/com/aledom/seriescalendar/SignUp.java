package com.aledom.seriescalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aledom.seriescalendar.models.SerieModel;
import com.aledom.seriescalendar.repositories.LoginRepository;
import com.aledom.seriescalendar.repositories.SerieRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

import java.util.List;

import javax.security.auth.login.LoginException;

public class SignUp extends AppCompatActivity {

    TextInputEditText EditTextUsername, EditTextpassword, EditTextemail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditTextUsername = findViewById(R.id.username);
        EditTextpassword = findViewById(R.id.password);
        EditTextemail = findViewById(R.id.email);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        progress = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = String.valueOf(EditTextUsername.getText());
                final String password = String.valueOf(EditTextpassword.getText());
                final String email = String.valueOf(EditTextemail.getText());

                if(!username.equals("") && !password.equals("") && !email.equals("")) {
                    progress.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            LoginRepository loginRepository = new LoginRepository();
                            progress.setVisibility(View.GONE);
                            try {
                                if(loginRepository.signUp(username, password, email)) {
                                    //Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent (getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (LoginException error){
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                           /*SerieRepository seriesRepository = new SerieRepository();
                            progress.setVisibility(View.GONE);
                            try {

                                List<SerieModel> listSeries = seriesRepository.getSeries();
                                SerieModel serie = listSeries.get(0);

                                System.out.println(serie.name);
                                System.out.println(serie.description);
                            } catch (LoginException error){
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }*/
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}