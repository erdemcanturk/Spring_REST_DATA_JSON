package com.example.demo.Controller;

import com.example.demo.Repository.AlbumRepository;
import com.example.demo.Repository.PhotosRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.AllAlbumsRequest;
import com.example.demo.model.Album;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class ApiController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    PhotosRepository photosRepository;

    //SORU 1
    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    //SORU 2
    @PostMapping("/getByEmail")
    public List<User> getByEmail(@RequestParam("email") String email) {

        List<User> userList = userRepository.findAllByEmailEndingWith(email);

        return userList;
    }

    //SORU 3
    @PostMapping("/getByEmails")
    public List<User> getByEmails(@RequestParam("email") String email) {

        String[] parts = email.split(",");

        List<User> users = new ArrayList<>();
        for (String s : parts
        ) {
            List<User> userList = userRepository.findAllByEmailEndingWith(s);
            users.addAll(userList);
        }
        List<User> simplifiedUsers = users.stream().distinct().collect(Collectors.toList());

        return simplifiedUsers;
    }

    //SORU 5
    @PutMapping("/getAlbumsByUserId")
    public List<AllAlbumsRequest> getUserAlbums(@RequestParam("userAlbums") long userAlbums) {

        List<Album> albumList = albumRepository.findAllByUser(userRepository.findById(userAlbums));

        List<AllAlbumsRequest> allAlbumsRequestList = new ArrayList<>();
        for (Album album : albumList
        ) {
            AllAlbumsRequest allAlbumsRequest = new AllAlbumsRequest();
            allAlbumsRequest.setId(album.getId());
            allAlbumsRequest.setTitle(album.getTitle());
            allAlbumsRequest.setPhotos(photosRepository.findAllByAlbum(album));
            allAlbumsRequestList.add(allAlbumsRequest);
        }

        return allAlbumsRequestList;
    }

    @PutMapping("/userAlbumsWithDetails")
    public List<AllAlbumsRequest> getUserAlbums1(@RequestParam("userAlbumsWithDetails") long userAlbumsWithDetails) {

        List<Album> albumList = albumRepository.findAllByUser(userRepository.findById(userAlbumsWithDetails));

        List<AllAlbumsRequest> allAlbumsRequestList = new ArrayList<>();
        for (Album album : albumList
        ) {
            AllAlbumsRequest allAlbumsRequest = new AllAlbumsRequest();
            allAlbumsRequest.setId(album.getId());
            allAlbumsRequest.setTitle(album.getTitle());

        }

        return allAlbumsRequestList;
    }
}

