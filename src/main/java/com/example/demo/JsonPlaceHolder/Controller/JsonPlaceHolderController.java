package com.example.demo.JsonPlaceHolder.Controller;

import com.example.demo.JsonPlaceHolder.Manager.JsonPlaceHolderManager;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class JsonPlaceHolderController {

    @Autowired
    JsonPlaceHolderManager jsonPlaceHolderManager;

    @GetMapping("/getAllDatas")
    public String getAllDatas() throws ParseException {
        jsonPlaceHolderManager.getAllUsers();

        jsonPlaceHolderManager.userAlbums();

        jsonPlaceHolderManager.userPhotos();
        return "Datalar Çekildi";
    }


}
