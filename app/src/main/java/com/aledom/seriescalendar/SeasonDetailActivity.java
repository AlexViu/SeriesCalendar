package com.aledom.seriescalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aledom.seriescalendar.R;
import com.aledom.seriescalendar.adapter.ChapterAdapter;
import com.aledom.seriescalendar.adapter.SeasonAdapter;
import com.aledom.seriescalendar.models.ChapterModel;
import com.aledom.seriescalendar.models.SeasonModel;
import com.aledom.seriescalendar.models.SerieModel;
import com.aledom.seriescalendar.repositories.ChapterRepository;
import com.aledom.seriescalendar.repositories.SeasonRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.util.List;

import javax.security.auth.login.LoginException;

public class SeasonDetailActivity extends AppCompatActivity {

    private SeasonModel seasonDetail;
    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;
    private List<ChapterModel> listChapter;

    private FloatingActionButton buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_detail);

        seasonDetail = (SeasonModel) getIntent().getExtras().getSerializable("itemDetail");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(seasonDetail.getName());

        recyclerView = findViewById(R.id.rvLista);
        buttonAdd = findViewById(R.id.btnAddChapter);

        ChapterRepository chapterRepository = new ChapterRepository();

        try {

            listChapter = chapterRepository.getChapter(seasonDetail.getId());

            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);

            chapterAdapter = new ChapterAdapter(listChapter);
            recyclerView.setAdapter(chapterAdapter);

        } catch (LoginException error){
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), AddChapter.class);
                String id_season = String.valueOf(seasonDetail.getId());
                intent.putExtra("id_season", id_season);
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