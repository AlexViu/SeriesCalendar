package com.aledom.seriescalendar.models;

public class SeasonModel {
    public int id;
    public int id_serie;
    public String name;

    public SeasonModel(int id, int id_serie, String name) {
        this.id = id;
        this.id_serie = id_serie;
        this.name = name;
    }

    public SeasonModel(int id_serie) {
        this.id_serie = id_serie;
    }

    public SeasonModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_serie() {
        return id_serie;
    }

    public void setId_serie(int id_serie) {
        this.id_serie = id_serie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
