package inditex;

import inditex.infrastructure.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = { "test" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
public class StatusFeature {

    @Value("${endpoint.status.path}")
    private String statusEndpoint;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void can_check_application_status() {
        webClient
            .get()
            .uri(statusEndpoint)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("OK");
    }
}