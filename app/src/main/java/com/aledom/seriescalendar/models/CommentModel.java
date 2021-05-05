package com.aledom.seriescalendar.models;

import java.io.Serializable;

public class CommentModel implements Serializable {
    public int id;
    public String username;
    public String comment;
    public int id_chapter;

    public CommentModel(int id, String username, String comment, int id_chapter) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.id_chapter = id_chapter;
    }

    public CommentModel(int id) {
        this.id = id;
    }

    public CommentModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId_chapter() {
        return id_chapter;
    }

    public void setId_chapter(int id_chapter) {
        this.id_chapter = id_chapter;
    }
}
