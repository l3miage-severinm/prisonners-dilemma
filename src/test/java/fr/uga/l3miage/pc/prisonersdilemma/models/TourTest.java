package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.models.Tour;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TourTest {

    @Test
    void joueur1AJoueTrue() {
        Tour tour = new Tour();
        tour.setJoueur1Coopere(true);
        assertThat(tour.joueur1AJoue()).isTrue();
    }

    @Test
    void joueur1AJoueFalse() {
        Tour tour = new Tour();
        assertThat(tour.joueur1AJoue()).isFalse();
    }

    @Test
    void joueur2AJoueTrue() {
        Tour tour = new Tour();
        tour.setJoueur2Coopere(true);
        assertThat(tour.joueur2AJoue()).isTrue();
    }

    @Test
    void joueur2AJoueFalse() {
        Tour tour = new Tour();
        assertThat(tour.joueur2AJoue()).isFalse();
    }

    @Test
    void estFiniTrue() {
        Tour tour = new Tour();
        tour.setJoueur1Coopere(true);
        tour.setJoueur2Coopere(true);
        assertThat(tour.estFini()).isTrue();
    }

    @Test
    void estFiniFalse() {
        Tour tour = new Tour();
        assertThat(tour.estFini()).isFalse();
    }
}
