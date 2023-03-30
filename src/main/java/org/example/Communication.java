package org.example;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private static String cookie;
    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null
                , new ParameterizedTypeReference<List<User>>() {});
        cookie = responseEntity.getHeaders().get("Set-Cookie").get(0).split(";")[0].split("=")[1];
        return responseEntity.getBody();
    }

    public String saveUser(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", "JSESSIONID=" + cookie);
        HttpEntity<User> requestBody = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, requestBody, String.class);
        return responseEntity.getBody();

    }
    public String updateUser(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", "JSESSIONID=" + cookie);
        HttpEntity<User> requestBody = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, requestBody, String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", "JSESSIONID=" + cookie);
        HttpEntity<User> requestBody = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, requestBody, String.class);
        return responseEntity.getBody();
    }

}
