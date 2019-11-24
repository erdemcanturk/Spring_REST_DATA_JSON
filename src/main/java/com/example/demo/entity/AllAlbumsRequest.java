package com.example.demo.entity;

import com.example.demo.model.Photos;

import java.util.List;

public class AllAlbumsRequest {

    private long id;
    private String title;
    private List<Photos> photos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }
}
