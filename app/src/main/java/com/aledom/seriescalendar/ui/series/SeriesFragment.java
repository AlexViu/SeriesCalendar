package com.aledom.seriescalendar.ui.series;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aledom.seriescalendar.AddSerie;
import com.aledom.seriescalendar.R;
import com.aledom.seriescalendar.adapter.SeriesAdapter;
import com.aledom.seriescalendar.models.SerieModel;
import com.aledom.seriescalendar.repositories.SerieRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.util.Collections;
import java.util.List;

import javax.security.auth.login.LoginException;

public class SeriesFragment extends Fragment {
    SerieRepository seriesRepository = new SerieRepository();
    FloatingActionButton btnAddSerie;
    private ListView listView;
    private SeriesViewModel seriesViewModel;
    private RecyclerView recyclerView;
    private SeriesAdapter seriesAdapter;
    private List<SerieModel> listSeries;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        seriesViewModel =
                ViewModelProviders.of(this).get(SeriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_series, container, false);
        //LinearLayoutManager manager = new LinearLayoutManager(this);
        btnAddSerie = root.findViewById(R.id.btnAddSerie);
        recyclerView = root.findViewById(R.id.list_item);
        //listView = root.findViewById(R.id.listview);

        SerieRepository seriesRepository = new SerieRepository();
            try {

                listSeries = seriesRepository.getSeries();
                SerieModel serie = listSeries.get(0);

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);

                seriesAdapter = new SeriesAdapter(listSeries);
                recyclerView.setAdapter(seriesAdapter);

                //System.out.println(series.name);
                //System.out.println(series.platform);
                //System.out.println(series.description);
            } catch (LoginException error){
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                                e.printStackTrace();
            }

        btnAddSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddSerie.class);
                startActivity(intent);
            }
        });

        return root;
    }

    private void initViews() {

    }
}