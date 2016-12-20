package com.example.d062654.faciliman.Requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by D062654 on 14.12.2016.
 */

public class IncidentRequest {
    @SerializedName("user")
    private String user;
    @SerializedName("location")
    private String location;
    @SerializedName("titel")
    private String title;
    @SerializedName("exactLocation")
    private String exactLocation;
    @SerializedName("description")
    private String description;



    @SerializedName("imagePath")
    private String imagePath;
    @SerializedName("active")
    private boolean active;
    @SerializedName("id")
    private int id;

    public IncidentRequest(String title, String user, String description, String exactLocation, String location, String imagePath) {
        this.imagePath = imagePath;
        this.description = description;
        this.exactLocation = exactLocation;
        this.title = title;
        this.location = location;
        this.user = user;
    }

    public IncidentRequest(String user, String location, String exactLocation, String description, String imagePath, boolean active) {
        this.user = user;
        this.location = location;
        this.exactLocation = exactLocation;
        this.description = description;
        this.imagePath = imagePath;
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;

    }

    public IncidentRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExactLocation() {
        return exactLocation;
    }


    public void setExactLocation(String exactLocation) {
        this.exactLocation = exactLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
