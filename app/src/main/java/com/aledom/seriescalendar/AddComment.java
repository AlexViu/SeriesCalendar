package com.aledom.seriescalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aledom.seriescalendar.repositories.ChapterRepository;
import com.aledom.seriescalendar.repositories.CommentRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import javax.security.auth.login.LoginException;

public class AddComment extends AppCompatActivity {

    TextInputEditText editTextComment;
    FloatingActionButton buttonAdd;
    ProgressBar progress;
    Intent intent = getIntent();

    String username;
    int idchapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        setTitle("Añadir comentario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextComment = findViewById(R.id.textInputEditText);
        buttonAdd = findViewById(R.id.btnAddComment);
        progress = findViewById(R.id.progress);

        String stringId = getIntent().getStringExtra("id_chapter");
        idchapter = Integer.parseInt(stringId);
        progress.setVisibility(View.GONE);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String comment = String.valueOf(editTextComment.getText());
                cargarPreferencias();

                if(!comment.equals("")) {
                    progress.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            CommentRepository commentRepository = new CommentRepository();
                            progress.setVisibility(View.GONE);
                            try {

                                if(commentRepository.addComment(username, comment, idchapter)) {
                                    //Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent (getApplicationContext(), ChapterComment.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (LoginException error){
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    /**
     * Cargar sesion guardada
     */
    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String user = preferences.getString("user", "No existe");

        username = user;
    }
}