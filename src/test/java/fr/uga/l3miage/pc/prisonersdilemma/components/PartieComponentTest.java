package fr.uga.l3miage.pc.prisonersdilemma.components;

import fr.uga.l3miage.pc.exceptions.technical.PartieNbToursIncorrectException;
import fr.uga.l3miage.pc.components.PartieComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PartieComponentTest {

    @Autowired
    private PartieComponent partieComponent;

    @AfterEach
    void clearPartiesEnCours() {
        partieComponent.clearPartiesEnCours();
    }

    @Test
    void creerPartieTest() throws PartieNbToursIncorrectException {
        int numeroPartie = partieComponent.creerPartie(3);
        assertThat(numeroPartie).isGreaterThanOrEqualTo(0);
    }

    @Test
    void creerPartieNbTours0Test() {
        assertThrows(PartieNbToursIncorrectException.class, () -> partieComponent.creerPartie(0));
    }
}
