package com.example.demo.JsonPlaceHolder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    private String server;

    @Value("${api.baseUrl}")
    public void setServer(String server) {
        this.server = server;
    }


    public HttpHeaders httpHeaders() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        System.out.println(server);
        return headers;
    }

    public String get(String uri) {
        HttpHeaders httpHeaders = httpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>("", httpHeaders);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        System.out.println("Rest Client" + responseEntity.getBody());
        return responseEntity.getBody();
    }

    public String post(String uri, String json) {
        HttpHeaders httpHeaders = httpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>("", httpHeaders);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri + "?id=" + json, HttpMethod.POST, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public String postWithBody(String uri, String json) {
        HttpHeaders httpHeaders = httpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, httpHeaders);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public void put(String uri, String json) {
        HttpHeaders httpHeaders = httpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, httpHeaders);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.PUT, requestEntity, (Class<String>) null);
        this.setStatus(responseEntity.getStatusCode());
    }

    public void delete(String uri) {
        HttpHeaders httpHeaders = httpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>("", httpHeaders);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.DELETE, requestEntity, (Class<String>) null);
        this.setStatus(responseEntity.getStatusCode());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}