package com.aledom.seriescalendar.ui.series;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aledom.seriescalendar.AddSerie;
import com.aledom.seriescalendar.MainActivity;
import com.aledom.seriescalendar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SeriesFragment extends Fragment {

    FloatingActionButton btnAddSerie;
    private SeriesViewModel seriesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        seriesViewModel =
                ViewModelProviders.of(this).get(SeriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_series, container, false);
        btnAddSerie = root.findViewById(R.id.btnAddSerie);

        btnAddSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddSerie.class);
                startActivity(intent);
            }
        });

        return root;
    }
}