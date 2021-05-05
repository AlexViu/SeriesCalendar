package com.aledom.seriescalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aledom.seriescalendar.R;
import com.aledom.seriescalendar.adapter.ChapterAdapter;
import com.aledom.seriescalendar.adapter.CommentAdapter;
import com.aledom.seriescalendar.models.ChapterModel;
import com.aledom.seriescalendar.models.CommentModel;
import com.aledom.seriescalendar.models.SeasonModel;
import com.aledom.seriescalendar.repositories.ChapterRepository;
import com.aledom.seriescalendar.repositories.CommentRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.util.List;

import javax.security.auth.login.LoginException;

public class ChapterComment extends AppCompatActivity {

    private ChapterModel chapterDetail;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private List<CommentModel> listComment;

    private FloatingActionButton buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_comment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Comentarios");

        recyclerView = findViewById(R.id.rvComment);
        buttonAdd = findViewById(R.id.btnAddComment);
        chapterDetail = (ChapterModel) getIntent().getExtras().getSerializable("itemDetail");

        CommentRepository commentRepository = new CommentRepository();

        try {

            listComment = commentRepository.getComment(chapterDetail.getId());

            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);

            commentAdapter = new CommentAdapter(listComment);
            recyclerView.setAdapter(commentAdapter);

        } catch (LoginException error){
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), AddComment.class);
                String id_chapter = String.valueOf(chapterDetail.getId());
                intent.putExtra("id_chapter", id_chapter);
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