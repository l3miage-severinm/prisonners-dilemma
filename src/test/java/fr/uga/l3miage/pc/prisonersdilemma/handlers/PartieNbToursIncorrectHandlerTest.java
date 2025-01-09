package fr.uga.l3miage.pc.prisonersdilemma.handlers;

import fr.uga.l3miage.pc.web.exceptions.handlers.PartieNbToursIncorrectHandler;
import fr.uga.l3miage.pc.web.exceptions.rest.PartieNbToursIncorrectRestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class PartieNbToursIncorrectHandlerTest {

    @Test
    void doitRetournerBadRequest() {
        PartieNbToursIncorrectHandler handler = new PartieNbToursIncorrectHandler();
        assertThat(handler.handle(new PartieNbToursIncorrectRestException("")))
                .isInstanceOf(ResponseEntity.class);
    }
}
