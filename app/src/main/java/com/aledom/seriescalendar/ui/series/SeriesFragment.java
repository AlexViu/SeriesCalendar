package com.aledom.seriescalendar.ui.series;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
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

public class SeriesFragment extends Fragment implements SearchView.OnQueryTextListener {
    FloatingActionButton btnAddSerie;
    private SeriesViewModel seriesViewModel;
    private SearchView svSearch;
    private RecyclerView recyclerView;
    private SeriesAdapter seriesAdapter;
    private List<SerieModel> listSeries;
    private ProgressBar progress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        seriesViewModel =
                ViewModelProviders.of(this).get(SeriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_series, container, false);

        btnAddSerie = root.findViewById(R.id.btnAddSerie);
        recyclerView = root.findViewById(R.id.list_item);
        progress = root.findViewById(R.id.progressBar);
        svSearch = root.findViewById(R.id.svSearch);

        SerieRepository seriesRepository = new SerieRepository();
        /**
         * Añadir y mostrar la lista de series
         */
        try {

                listSeries = seriesRepository.getSeries();
                SerieModel serie = listSeries.get(0);

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);

                seriesAdapter = new SeriesAdapter(listSeries);
                recyclerView.setAdapter(seriesAdapter);
                progress.setVisibility(View.GONE);

            } catch (LoginException error){
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                                e.printStackTrace();
            }

        /**
         * Boton para añadir series
         */
        btnAddSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddSerie.class);
                startActivity(intent);
            }
        });

        svSearch.setOnQueryTextListener(this);

        return root;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Cada vez que se escriba se actualiza la lista
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        seriesAdapter.filter(newText);
        return false;
    }
}