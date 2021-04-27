package com.aledom.seriescalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aledom.seriescalendar.repositories.SeasonRepository;
import com.aledom.seriescalendar.repositories.SerieRepository;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import javax.security.auth.login.LoginException;

public class AddSeason extends AppCompatActivity {

    TextInputEditText EditTextName;
    Button buttonAdd;
    ProgressBar progress;
    Intent intent = getIntent();
    int idseries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_season);

        setTitle("Añadir temporada");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditTextName = findViewById(R.id.name);
        buttonAdd = findViewById(R.id.buttonAdd);
        progress = findViewById(R.id.progress);
        String stringId = getIntent().getStringExtra("id_serie");
        idseries = Integer.parseInt(stringId);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = String.valueOf(EditTextName.getText());

                if(!name.equals("")) {
                    progress.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            SerieRepository serieRepository = new SerieRepository();
                            progress.setVisibility(View.GONE);
                            try {

                                if(SeasonRepository.addSeason(name, idseries)) {
                                    //Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent (getApplicationContext(), SerieDetailActivity.class);
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}