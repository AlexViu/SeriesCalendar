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

import com.aledom.seriescalendar.repositories.LoginRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;

import javax.security.auth.login.LoginException;

import static com.aledom.seriescalendar.Constants.LOGIN_URL;

public class Login extends AppCompatActivity {

    TextInputEditText EditTextUsername, EditTextpassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditTextUsername = findViewById(R.id.username);
        EditTextpassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btnLogin);
        textViewSignUp = findViewById(R.id.signUpText);
        progress = findViewById(R.id.progress);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = String.valueOf(EditTextUsername.getText());
                final String password = String.valueOf(EditTextpassword.getText());

                if(!username.equals("") && !password.equals("")) {
                    progress.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                LoginRepository loginRepository = new LoginRepository();
                                progress.setVisibility(View.GONE);
                                if(loginRepository.login(username, password)) {
                                    Intent intent = new Intent (getApplicationContext(), MenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch(LoginException error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}