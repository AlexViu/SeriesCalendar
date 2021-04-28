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

import com.aledom.seriescalendar.R;
import com.aledom.seriescalendar.repositories.ChapterRepository;
import com.aledom.seriescalendar.repositories.SeasonRepository;
import com.aledom.seriescalendar.repositories.SerieRepository;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import javax.security.auth.login.LoginException;

public class AddChapter extends AppCompatActivity {

    TextInputEditText EditTextName, EditTextNumber, EditTextDescription, EditTextDate;
    Button buttonAdd;
    ProgressBar progress;
    Intent intent = getIntent();
    int idseason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chapter);

        setTitle("Añadir capitulo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditTextName = findViewById(R.id.name);
        EditTextNumber = findViewById(R.id.number);
        EditTextDescription = findViewById(R.id.description);
        EditTextDate = findViewById(R.id.date);
        buttonAdd = findViewById(R.id.buttonAdd);
        progress = findViewById(R.id.progress);

        String stringId = getIntent().getStringExtra("id_season");
        idseason = Integer.parseInt(stringId);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = String.valueOf(EditTextName.getText());
                final String number = String.valueOf(EditTextNumber.getText());
                final int numberInt = Integer.parseInt(number);
                final String description = String.valueOf(EditTextDescription.getText());
                final String date = String.valueOf(EditTextDate.getText());

                if(!name.equals("")) {
                    progress.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ChapterRepository chapterRepository = new ChapterRepository();
                            progress.setVisibility(View.GONE);
                            try {

                                if(chapterRepository.addChapter(numberInt, name, idseason, description, date)) {
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}