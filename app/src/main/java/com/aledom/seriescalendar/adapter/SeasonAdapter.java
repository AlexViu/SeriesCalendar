package com.aledom.seriescalendar.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aledom.seriescalendar.R;

import com.aledom.seriescalendar.SerieDetailActivity;
import com.aledom.seriescalendar.models.SeasonModel;
import com.aledom.seriescalendar.models.SerieModel;
import com.aledom.seriescalendar.repositories.SeasonRepository;


import org.json.JSONException;

import java.util.List;

import javax.security.auth.login.LoginException;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.RecyclerHolder> {

    SeasonRepository seasonRepository  = new SeasonRepository();
    private List<SeasonModel> listSeason;

    public SeasonAdapter(List<SeasonModel> items) {
        this.listSeason = items;
    }
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_season,parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {

        try {

            final SeasonModel item = listSeason.get(position);
            listSeason = seasonRepository.getSeason(item.id_serie);
            holder.tvName.setText(item.name);

        } catch (LoginException error){
            System.out.println(error.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listSeason.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvSeason);

        }

    }
}
