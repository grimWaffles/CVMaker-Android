package com.example.cse464_1610329_midtermproject.model;

public class CV {

    String name;
    String path;
    String variant;

    public CV(String name, String path, String variant) {
        this.name = name;
        this.path = path;
        this.variant = variant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }
}
