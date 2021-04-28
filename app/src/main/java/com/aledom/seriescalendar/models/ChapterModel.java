package com.aledom.seriescalendar.models;

import java.io.Serializable;

public class ChapterModel implements Serializable {

    public int id;
    public int number_chapter;
    public String name;
    public int id_season;
    public String description;
    public String date;

    public ChapterModel(int id, int number_chapter, String name, int id_season, String description, String date) {
        this.id = id;
        this.number_chapter = number_chapter;
        this.name = name;
        this.id_season = id_season;
        this.description = description;
        this.date = date;
    }

    public ChapterModel(int id_season) {
        this.id_season = id_season;
    }

    public ChapterModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_chapter() {
        return number_chapter;
    }

    public void setNumber_chapter(int number_chapter) {
        this.number_chapter = number_chapter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_season() {
        return id_season;
    }

    public void setId_season(int id_season) {
        this.id_season = id_season;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
