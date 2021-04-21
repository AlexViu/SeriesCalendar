package com.aledom.seriescalendar.models;

import java.io.Serializable;

public class SerieModel implements Serializable {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
