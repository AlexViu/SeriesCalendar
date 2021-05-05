package com.aledom.seriescalendar.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aledom.seriescalendar.R;
import com.aledom.seriescalendar.SeasonDetailActivity;
import com.aledom.seriescalendar.models.CommentModel;
import com.aledom.seriescalendar.models.SeasonModel;
import com.aledom.seriescalendar.repositories.CommentRepository;
import com.aledom.seriescalendar.repositories.SeasonRepository;

import org.json.JSONException;

import java.util.List;

import javax.security.auth.login.LoginException;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.RecyclerHolder> {

    CommentRepository commentRepository  = new CommentRepository();
    private List<CommentModel> listComment;

    public CommentAdapter(List<CommentModel> items) {
        this.listComment = items;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_comment,parent, false);
        return new CommentAdapter.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {

        try {

            final CommentModel item = listComment.get(position);
            listComment = commentRepository.getComment(item.id_chapter);
            holder.tvUsername.setText(item.username);
            holder.tvComment.setText(item.comment);

        } catch (LoginException error){
            System.out.println(error.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername, tvComment;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvComment = itemView.findViewById(R.id.tvComment);

        }

    }
}
