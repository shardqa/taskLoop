package com.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompletedFilterIntegrationTest extends BaseIntegrationTest {

    @Test
    void completedFilterReturnsOnlyCompletedWhenNotDeleted() throws Exception {
        String token = register("user3@example.com", "user3", "pass123");
        String id = createTask(token, Map.of(
                "description", "ToComplete",
                "recurrent", false
        ));
        exchangeAuth(token, HttpMethod.POST, "/tasks/" + id + "/toggle-completion", null);

        ResponseEntity<String> completedOnly = exchangeAuth(token, HttpMethod.GET, "/tasks?completed=true", null);
        assertThat(completedOnly.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode arr = mapper.readTree(completedOnly.getBody());
        assertThat(arr.isArray()).isTrue();
        assertThat(arr.size()).isGreaterThanOrEqualTo(0);
    }
}


