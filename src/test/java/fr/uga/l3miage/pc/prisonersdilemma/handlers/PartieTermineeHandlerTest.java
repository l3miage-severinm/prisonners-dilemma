package fr.uga.l3miage.pc.prisonersdilemma.handlers;

import fr.uga.l3miage.pc.web.exceptions.handlers.PartieTermineeHandler;
import fr.uga.l3miage.pc.web.exceptions.rest.PartieTermineeRestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class PartieTermineeHandlerTest {

    @Test
    void doitRetournerBadRequest() {
        PartieTermineeHandler handler = new PartieTermineeHandler();
        assertThat(handler.handle(new PartieTermineeRestException("")))
                .isInstanceOf(ResponseEntity.class);
    }
}
