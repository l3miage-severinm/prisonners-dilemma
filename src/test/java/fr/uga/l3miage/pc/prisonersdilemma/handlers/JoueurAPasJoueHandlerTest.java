package fr.uga.l3miage.pc.prisonersdilemma.handlers;

import fr.uga.l3miage.pc.exceptions.handlers.JoueurAPasJoueHandler;
import fr.uga.l3miage.pc.exceptions.rest.JoueurAPasJoueRestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class JoueurAPasJoueHandlerTest {

    @Test
    void doitRetournerBadRequest() {
        JoueurAPasJoueHandler handler = new JoueurAPasJoueHandler();
        assertThat(handler.handle(new JoueurAPasJoueRestException("")))
                .isInstanceOf(ResponseEntity.class);
    }
}
