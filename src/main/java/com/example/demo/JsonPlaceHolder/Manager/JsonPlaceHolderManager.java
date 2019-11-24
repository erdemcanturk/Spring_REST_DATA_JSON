package com.example.demo.JsonPlaceHolder.Manager;

import com.example.demo.JsonPlaceHolder.RestClient;
import com.example.demo.Repository.AlbumRepository;
import com.example.demo.Repository.PhotosRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.AlbumRequest;
import com.example.demo.entity.PhotosRequest;
import com.example.demo.model.Album;
import com.example.demo.model.Photos;
import com.example.demo.model.User;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Optional;

@Component
public class JsonPlaceHolderManager {

    @Autowired
    RestClient restClient;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    PhotosRepository photosRepository;

    public void getAllUsers() {
        userRepository.deleteAll();
        Gson gson = new Gson();
        String response = restClient.get("/users");
        System.out.println(response);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response);
        for (JsonElement jsonElement : jsonArray
        ) {
            User user = gson.fromJson(jsonElement, User.class);
            userRepository.save(user);
        }
    }


    public void userAlbums() {
        albumRepository.deleteAll();
        Gson gson = new Gson();
        String response = restClient.get("/albums");
        System.out.println(response);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response);
        for (JsonElement jsonElement : jsonArray) {
            AlbumRequest albumRequest = gson.fromJson(jsonElement, (Type) AlbumRequest.class);
            Album album = new Album();
            album.setId(albumRequest.getId());
            album.setTitle(albumRequest.getTitle());
            User user = userRepository.findById(albumRequest.getUserId());
            album.setUser(user);
            albumRepository.save(album);
        }

    }

    public void userPhotos() {
        photosRepository.deleteAll();
        Gson gson = new Gson();
        String response = restClient.get("/photos");
        System.out.println(response);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response);
        for (JsonElement jsonElement : jsonArray) {
            PhotosRequest photosRequest = gson.fromJson(jsonElement, (Type) PhotosRequest.class);
            Photos photos = new Photos();
            photos.setId(photosRequest.getId());
            Album album = albumRepository.findById(photosRequest.getAlbumId());
            photos.setAlbum(album);
            photos.setThumbnailUrl(photosRequest.getThumbnailUrl());
            photos.setUrl(photosRequest.getUrl());
            photos.setTitle(photosRequest.getTitle());
            photosRepository.save(photos);
        }

    }
}
