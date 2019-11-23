package com.example.demo.JsonPlaceHolder.Manager;

import com.example.demo.JsonPlaceHolder.RestClient;
import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonPlaceHolderManager {

    @Autowired
    RestClient restClient;

    @Autowired
    UserRepository userRepository;

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


}
