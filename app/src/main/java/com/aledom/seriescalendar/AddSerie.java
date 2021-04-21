package com.aledom.seriescalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aledom.seriescalendar.repositories.SerieRepository;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import javax.security.auth.login.LoginException;

public class AddSerie extends AppCompatActivity {

    TextInputEditText EditTextName, EditTextPlatform, EditTextDescription;
    Button buttonAdd;
    TextView textViewSerie;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_serie);
        setTitle("Añadir serie");

        EditTextName = findViewById(R.id.name);
        EditTextPlatform = findViewById(R.id.platform);
        EditTextDescription = findViewById(R.id.description);
        buttonAdd = findViewById(R.id.buttonAdd);
        textViewSerie = findViewById(R.id.seriesText);
        progress = findViewById(R.id.progress);

        textViewSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = String.valueOf(EditTextName.getText());
                final String platform = String.valueOf(EditTextPlatform.getText());
                final String description = String.valueOf(EditTextDescription.getText());

                if(!name.equals("") && !platform.equals("") && !description.equals("")) {
                    progress.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            SerieRepository serieRepository = new SerieRepository();
                            progress.setVisibility(View.GONE);
                            try {
                                if(SerieRepository.addSerie(name, platform, description)) {
                                    //Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent (getApplicationContext(), MenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (LoginException error){
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