//generate with http://www.jsonschema2pojo.org/

package com.developer.willshuffy.willstore.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Lat")
    @Expose
    private String lat;
    @SerializedName("Lng")
    @Expose
    private String lng;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("Telp")
    @Expose
    private String telp;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Open_Hour")
    @Expose
    private String openHour;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Created_At")
    @Expose
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
