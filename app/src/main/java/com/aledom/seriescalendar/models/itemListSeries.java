package com.aledom.seriescalendar.models;

public class itemListSeries {
    private String name;
    private String platform;
    private String description;

    public itemListSeries(String name, String platform, String description) {
        this.name = name;
        this.platform = platform;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDescription() {
        return description;
    }
}
