package com.aledom.seriescalendar.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aledom.seriescalendar.R;
import com.aledom.seriescalendar.adapter.FavoriteAdapter;
import com.aledom.seriescalendar.adapter.SeriesAdapter;
import com.aledom.seriescalendar.models.SerieModel;
import com.aledom.seriescalendar.repositories.SerieRepository;
import com.aledom.seriescalendar.ui.series.SeriesViewModel;

import org.json.JSONException;

import java.util.List;

import javax.security.auth.login.LoginException;

public class HomeFragment extends Fragment {

    private TextView textView;
    private HomeViewModel homeViewModel;
    private SeriesViewModel seriesViewModel;
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private List<SerieModel> listSeries;
    private String id_usuarios;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.rvFavorite);
        textView = root.findViewById(R.id.tv);

        SerieRepository seriesRepository = new SerieRepository();
        cargarPreferencias();
        /**
         * Añadir y mostrar la lista de series
         */
        try {

            listSeries = seriesRepository.getFavorite(id_usuarios);

            if (listSeries.size() == 0) {
                textView.setText("No tienes ninguna serie en favoritos");
            } else {

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);

                favoriteAdapter = new FavoriteAdapter(listSeries);
                recyclerView.setAdapter(favoriteAdapter);
            }
        } catch (LoginException error){
            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return root;
    }

    /**
     * Cargar sesion guardada
     */
    private void cargarPreferencias() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String id = preferences.getString("id", "No existe");

        id_usuarios = id;

    }
}