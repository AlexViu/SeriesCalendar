package com.aledom.seriescalendar.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aledom.seriescalendar.ChapterComment;
import com.aledom.seriescalendar.R;
import com.aledom.seriescalendar.models.ChapterModel;
import com.aledom.seriescalendar.repositories.ChapterRepository;

import org.json.JSONException;

import java.util.List;

import javax.security.auth.login.LoginException;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.RecyclerHolder> {

    ChapterRepository chapterRepository  = new ChapterRepository();
    private List<ChapterModel> listChapter;

    public ChapterAdapter(List<ChapterModel> items) {
        this.listChapter = items;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chapter,parent, false);
        return new ChapterAdapter.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, int position) {

        try {

            final ChapterModel item = listChapter.get(position);
            listChapter = chapterRepository.getChapter(item.id_season);

            String numberc = String.valueOf(item.number_chapter);

            holder.tvNumber.setText("Capitulo: "+ numberc);
            holder.tvName.setText(item.name);
            holder.tvDescription.setText(item.description);
            holder.tvDate.setText(item.date);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), ChapterComment.class);
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
        return listChapter.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tvNumber, tvName, tvDescription, tvDate;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.number);
            tvName = itemView.findViewById(R.id.name);
            tvDescription = itemView.findViewById(R.id.description);
            tvDate = itemView.findViewById(R.id.date);

        }

    }
}
