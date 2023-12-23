package com.example.test_spring.service;

import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class CryptoService {
    private static final String POST_URL = "http://localhost:5000";

//    @SneakyThrows
//    public static String sendGET(String JSONKey, String JSONValue) {
//        RestTemplate restTemplate = new RestTemplate();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(JSONKey, JSONValue);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(jsonObject);
//        ResponseEntity<JSONObject> response = restTemplate.getForEntity(POST_URL, JSONObject.class, request);
//        if(response.getStatusCode() == HttpStatus.OK){
//            return (String) response.getBody().get(JSONKey);
//        }
//        return JSONValue;
//    }

    @SneakyThrows
    public static String sendPOST(String JSONKey, String JSONValue, Boolean isDecrypt) {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isDecrypt", isDecrypt);
        jsonObject.put(JSONKey, JSONValue);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(jsonObject);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(POST_URL, request, JSONObject.class);
        if(response.getStatusCode() == HttpStatus.OK){
            return (String) response.getBody().get(JSONKey);
        }
        return JSONValue;
    }
}
