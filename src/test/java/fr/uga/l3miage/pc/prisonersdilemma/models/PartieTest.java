package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.models.Partie;
import fr.uga.l3miage.pc.models.Tour;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PartieTest {

    @Test
    void estFiniePartieFinieTest() {
        Partie partie = new Partie(0, 1, new ArrayList<>());
        Tour mockTour = mock(Tour.class);
        when(mockTour.estFini()).thenReturn(true);
        partie.getTours().add(mockTour);
        assertThat(partie.estFinie()).isTrue();
    }

    @Test
    void estFiniePartieEnCoursTest() {
        Partie partie = new Partie(0, 1, new ArrayList<>());
        Tour mockTour = mock(Tour.class);
        when(mockTour.estFini()).thenReturn(false);
        partie.getTours().add(mockTour);
        assertThat(partie.estFinie()).isFalse();
    }
}
