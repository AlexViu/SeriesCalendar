package com.aledom.seriescalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aledom.seriescalendar.adapter.SeasonAdapter;
import com.aledom.seriescalendar.adapter.SeriesAdapter;
import com.aledom.seriescalendar.models.SeasonModel;
import com.aledom.seriescalendar.models.SerieModel;
import com.aledom.seriescalendar.repositories.SeasonRepository;
import com.aledom.seriescalendar.repositories.SerieRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.util.List;

import javax.security.auth.login.LoginException;

public class SerieDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SeasonAdapter seasonAdapter;
    private List<SeasonModel> listSeason;
    private TextView textviewName, textviewDescription, textviewPletaform;
    private SerieModel serieDetail;
    private FloatingActionButton btnAddSeason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_detail);

        serieDetail = (SerieModel) getIntent().getExtras().getSerializable("itemDetail");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(serieDetail.getName());

        textviewName = findViewById(R.id.nameDetail);
        textviewDescription = findViewById(R.id.descriptionDetail);
        textviewPletaform = findViewById(R.id.platformDetail);
        recyclerView = findViewById(R.id.rvSeason);
        btnAddSeason = findViewById(R.id.btnAddSeason);


        textviewName.setText(serieDetail.getName());
        textviewDescription.setText(serieDetail.getDescription());
        textviewPletaform.setText(serieDetail.getPlatform());

        SeasonRepository seasonRepository = new SeasonRepository();

        try {

            listSeason = seasonRepository.getSeason(serieDetail.getId());

            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);

            seasonAdapter = new SeasonAdapter(listSeason);
            recyclerView.setAdapter(seasonAdapter);

        } catch (LoginException error){
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnAddSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), AddSeason.class);
                String id_series = String.valueOf(serieDetail.getId());
                intent.putExtra("id_serie", id_series);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}