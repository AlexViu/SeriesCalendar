package com.aledom.seriescalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.aledom.seriescalendar.adapter.SeriesAdapter;
import com.aledom.seriescalendar.models.SerieModel;

public class SerieDetailActivity extends AppCompatActivity {

    private TextView textviewName, textviewDescription;
    private SerieModel serieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_detail);

        textviewName = findViewById(R.id.nameDetail);
        textviewDescription = findViewById(R.id.descriptionDetail);

        serieDetail = (SerieModel) getIntent().getExtras().getSerializable("itemDetail");
        textviewName.setText(serieDetail.getName());
        textviewDescription.setText(serieDetail.getDescription());
    }
}