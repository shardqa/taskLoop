package com.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.repository.TaskRepository;
import com.todoapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Map;

abstract class BaseIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void resetDatabase() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }

    String register(String email, String name, String password) throws Exception {
        ResponseEntity<String> resp = rest.postForEntity(baseUrl("/auth/register"), Map.of(
                "name", name,
                "email", email,
                "password", password
        ), String.class);
        if (resp.getStatusCode() != HttpStatus.OK) {
            ResponseEntity<String> login = rest.postForEntity(baseUrl("/auth/login"), Map.of(
                    "email", email,
                    "password", password
            ), String.class);
            JsonNode node = mapper.readTree(login.getBody());
            return node.path("token").asText();
        } else {
            JsonNode node = mapper.readTree(resp.getBody());
            return node.path("token").asText();
        }
    }

    String createTask(String token, Map<String, Object> body) throws Exception {
        ResponseEntity<String> resp = exchangeAuth(token, HttpMethod.POST, "/tasks", body);
        JsonNode node = mapper.readTree(resp.getBody());
        return node.path("id").asText();
    }

    ResponseEntity<String> exchangeAuth(String token, HttpMethod method, String path, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return rest.exchange(baseUrl(path), method, entity, String.class);
    }

    String baseUrl(String path) {
        return "http://localhost:" + port + path;
    }
}


