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

    @SerializedName("exactLocation")
    private String exactLocation;
    @SerializedName("description")
    private String description;
    @SerializedName("imagePath")
    private String imagePath = "dummy";
    public IncidentRequest(String user, String location, String exactLocation, String description) {
        this.user = user;
        this.location = location;
        this.exactLocation = exactLocation;
        this.description = description;

    }

    public IncidentRequest() {
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
