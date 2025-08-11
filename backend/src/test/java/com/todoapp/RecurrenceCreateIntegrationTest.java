package com.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecurrenceCreateIntegrationTest extends BaseIntegrationTest {

    @Test
    void completingRecurrentTaskCreatesNextInstance() throws Exception {
        String token = register("dev@example.com", "dev", "devpass");
        String taskId = createTask(token, Map.of(
                "description", "Recurrent Daily",
                "recurrent", true,
                "category", "Work",
                "recurrenceType", "DAILY",
                "recurrenceInterval", 1
        ));

        ResponseEntity<String> completeResp = exchangeAuth(token, HttpMethod.POST, "/tasks/" + taskId + "/complete", null);
        assertThat(completeResp.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<String> deletedResp = exchangeAuth(token, HttpMethod.GET, "/tasks?deleted=true", null);
        assertThat(deletedResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode deleted = mapper.readTree(deletedResp.getBody());
        assertThat(deleted.isArray()).isTrue();
        assertThat(deleted.size()).isGreaterThanOrEqualTo(1);

        ResponseEntity<String> activeRecurrentResp = exchangeAuth(token, HttpMethod.GET, "/tasks?deleted=false&recurrence=recurrent", null);
        assertThat(activeRecurrentResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode activeRecurrent = mapper.readTree(activeRecurrentResp.getBody());
        boolean hasNewActive = false;
        for (JsonNode node : activeRecurrent) {
            if (node.path("recurrent").asBoolean() && !node.path("state").path("deleted").asBoolean()) {
                hasNewActive = true;
                break;
            }
        }
        assertThat(hasNewActive).isTrue();
    }
}


