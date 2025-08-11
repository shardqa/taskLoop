package com.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToggleIntegrationTest extends BaseIntegrationTest {

    @Test
    void toggleNonRecurrentTaskMarksCompletedAndUncompleted() throws Exception {
        String token = register("user2@example.com", "user2", "pass123");
        String taskId = createTask(token, Map.of(
                "description", "One-Off",
                "recurrent", false,
                "category", "Test"
        ));

        ResponseEntity<String> toggleOn = exchangeAuth(token, HttpMethod.POST, "/tasks/" + taskId + "/toggle-completion", null);
        assertThat(toggleOn.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode t1 = mapper.readTree(toggleOn.getBody());
        assertThat(t1.path("state").path("completed").asBoolean()).isTrue();
        assertThat(t1.path("state").path("deleted").asBoolean()).isFalse();

        ResponseEntity<String> toggleOff = exchangeAuth(token, HttpMethod.POST, "/tasks/" + taskId + "/toggle-completion", null);
        assertThat(toggleOff.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode t2 = mapper.readTree(toggleOff.getBody());
        assertThat(t2.path("state").path("completed").asBoolean()).isFalse();
        assertThat(t2.path("state").path("deleted").asBoolean()).isFalse();
    }
}


