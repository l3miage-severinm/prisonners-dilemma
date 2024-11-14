package fr.uga.l3miage.pc.prisonersdilemma.handlers;

import fr.uga.l3miage.pc.exceptions.handlers.JoueurADejaJoueHandler;
import fr.uga.l3miage.pc.exceptions.rest.JoueurADejaJoueRestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class JoueurADejaJoueHandlerTest {

    @Test
    void doitRetournerBadRequest() {
        JoueurADejaJoueHandler handler = new JoueurADejaJoueHandler();
        assertThat(handler.handle(new JoueurADejaJoueRestException("")))
                .isInstanceOf(ResponseEntity.class);
    }
}
