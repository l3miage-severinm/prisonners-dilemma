package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import fr.uga.l3miage.pc.models.Tour;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TourTest {

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

    @Test
    void getScoreDeuxCooperent() throws JoueurAPasJoueException {
        Tour tour = new Tour(true, true);
        assertThat(tour.getScore(EnumIdJoueur.TINTIN)).isEqualTo(3);
        assertThat(tour.getScore(EnumIdJoueur.MILOU)).isEqualTo(3);
    }

    @Test
    void getScoreDeuxTrahissent() throws JoueurAPasJoueException {
        Tour tour = new Tour(false, false);
        assertThat(tour.getScore(EnumIdJoueur.TINTIN)).isEqualTo(1);
        assertThat(tour.getScore(EnumIdJoueur.MILOU)).isEqualTo(1);
    }

    @Test
    void getScoreTintinCoopereMilouTrahit() throws JoueurAPasJoueException {
        Tour tour = new Tour(true, false);
        assertThat(tour.getScore(EnumIdJoueur.TINTIN)).isZero();
        assertThat(tour.getScore(EnumIdJoueur.MILOU)).isEqualTo(5);
    }

    @Test
    void getScoreTintinTrahitMilouCoopere() throws JoueurAPasJoueException {
        Tour tour = new Tour(false, true);
        assertThat(tour.getScore(EnumIdJoueur.TINTIN)).isEqualTo(5);
        assertThat(tour.getScore(EnumIdJoueur.MILOU)).isZero();
    }

    @Test
    void getScoreJoueurAPasJoue() {
        Tour tour = new Tour();
        assertThrows(JoueurAPasJoueException.class, () -> tour.getScore(EnumIdJoueur.TINTIN));
        assertThrows(JoueurAPasJoueException.class, () -> tour.getScore(EnumIdJoueur.MILOU));
    }
}
