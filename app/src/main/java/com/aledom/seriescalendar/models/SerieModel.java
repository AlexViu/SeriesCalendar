package com.aledom.seriescalendar.models;

public class SerieModel {
    public int id;
    public String name;
    public String platform;
    public  String description;

    public SerieModel(String name, String platform, String description) {
        this.name = name;
        this.platform = platform;
        this.description = description;
    }

    public SerieModel() {

    }
}
