package com.aledom.seriescalendar.adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aledom.seriescalendar.R;
import com.aledom.seriescalendar.SerieDetailActivity;
import com.aledom.seriescalendar.models.SerieModel;
import com.aledom.seriescalendar.repositories.SerieRepository;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.RecyclerHolder> {
    SerieRepository seriesRepository = new SerieRepository();
    private List<SerieModel> listSeries;
    private  List<SerieModel> originalSeries;

    public SeriesAdapter(List<SerieModel> items) {
        this.listSeries = items;
        this.originalSeries = new ArrayList<>();
        originalSeries.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_series, parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, int position) {
        try {

            listSeries = seriesRepository.getSeries();
            final SerieModel item = listSeries.get(position);
            holder.txtName.setText(item.name);
            holder.txtPlatform.setText(item.platform);
            holder.txtDescription.setText(item.description);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), SerieDetailActivity.class);
                    intent.putExtra("itemDetail", item);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        } catch (LoginException error){
            System.out.println(error.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listSeries.size();
    }

    /**
     * Metodo para hacer busqueda
     * @param strSearch
     */
    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            listSeries.clear();
            listSeries.addAll(originalSeries);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listSeries.clear();
                List<SerieModel> collect = originalSeries.stream()
                        .filter(i -> i.getName().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                listSeries.addAll(collect);
            } else {
                for (SerieModel i : originalSeries) {
                    if (i.getName().toLowerCase().contains(strSearch)) {
                        listSeries.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView txtName, txtPlatform, txtDescription;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.image);
            txtName = itemView.findViewById(R.id.name);
            txtPlatform = itemView.findViewById(R.id.platform);
            txtDescription = itemView.findViewById(R.id.description);
        }
    }
}
