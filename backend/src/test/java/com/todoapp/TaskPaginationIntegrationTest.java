package com.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskPaginationIntegrationTest extends BaseIntegrationTest {

    @Test
    void userTasksPaginated_returnsPagingMetadata() throws Exception {
        String token = register("p1@example.com", "p1", "pass");
        for (int i = 0; i < 5; i++) {
            createTask(token, Map.of("description", "T" + i, "recurrent", false, "category", "A"));
        }
        ResponseEntity<String> page0 = exchangeAuth(token, HttpMethod.GET, "/tasks/paginated?page=0&size=2", null);
        ResponseEntity<String> page1 = exchangeAuth(token, HttpMethod.GET, "/tasks/paginated?page=1&size=2", null);
        assertThat(page0.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(page1.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode n0 = mapper.readTree(page0.getBody());
        JsonNode n1 = mapper.readTree(page1.getBody());
        assertThat(n0.path("content").size()).isEqualTo(2);
        assertThat(n1.path("content").size()).isEqualTo(2);
        assertThat(n0.path("totalElements").asInt()).isEqualTo(5);
        assertThat(n0.path("totalPages").asInt()).isGreaterThanOrEqualTo(3);
        assertThat(n0.path("page").asInt()).isEqualTo(0);
        assertThat(n1.path("page").asInt()).isEqualTo(1);
    }

    @Test
    void completedTasksPaginated_filtersCompleted() throws Exception {
        String token = register("p2@example.com", "p2", "pass");
        String id1 = createTask(token, Map.of("description", "C1", "recurrent", false));
        createTask(token, Map.of("description", "C2", "recurrent", false));
        exchangeAuth(token, HttpMethod.POST, "/tasks/" + id1 + "/toggle-completion", null);
        ResponseEntity<String> resp = exchangeAuth(token, HttpMethod.GET, "/tasks/completed/paginated?page=0&size=10", null);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode n = mapper.readTree(resp.getBody());
        assertThat(n.path("content").size()).isGreaterThanOrEqualTo(1);
        boolean allCompleted = true;
        for (JsonNode t : n.path("content")) {
            if (!t.path("state").path("completed").asBoolean()) allCompleted = false;
        }
        assertThat(allCompleted).isTrue();
    }

    @Test
    void recurrentTasksByCategoryPaginated_filtersProperly() throws Exception {
        String token = register("p3@example.com", "p3", "pass");
        createTask(token, Map.of("description", "R1", "recurrent", true, "category", "Work"));
        createTask(token, Map.of("description", "R2", "recurrent", true, "category", "Home"));
        createTask(token, Map.of("description", "N1", "recurrent", false, "category", "Work"));
        ResponseEntity<String> rec = exchangeAuth(token, HttpMethod.GET, "/tasks/recurrent/paginated?page=0&size=10", null);
        ResponseEntity<String> work = exchangeAuth(token, HttpMethod.GET, "/tasks/category/Work/paginated?page=0&size=10", null);
        assertThat(rec.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(work.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode recN = mapper.readTree(rec.getBody());
        JsonNode workN = mapper.readTree(work.getBody());
        boolean allRec = true;
        for (JsonNode t : recN.path("content")) if (!t.path("recurrent").asBoolean()) allRec = false;
        boolean allWork = true;
        for (JsonNode t : workN.path("content")) if (!"Work".equals(t.path("metadata").path("category").asText())) allWork = false;
        assertThat(allRec).isTrue();
        assertThat(allWork).isTrue();
    }
}


