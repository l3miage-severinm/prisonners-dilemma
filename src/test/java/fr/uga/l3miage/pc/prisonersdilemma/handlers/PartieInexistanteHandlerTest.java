package fr.uga.l3miage.pc.prisonersdilemma.handlers;

import fr.uga.l3miage.pc.exceptions.handlers.PartieInexistanteHandler;
import fr.uga.l3miage.pc.exceptions.rest.PartieInexistanteRestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class PartieInexistanteHandlerTest {

    @Test
    void doitRetournerBadRequest() {
        PartieInexistanteHandler handler = new PartieInexistanteHandler();
        assertThat(handler.handle(new PartieInexistanteRestException("")))
                .isInstanceOf(ResponseEntity.class);
    }
}
