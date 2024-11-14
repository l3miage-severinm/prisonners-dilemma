package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
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

    @Test
    void getScorePartie0Tours() throws JoueurAPasJoueException {
        Partie partie = new Partie(0, 1, new ArrayList<>());
        assertThat(partie.getScore(EnumIdJoueur.TINTIN)).isEqualTo(0);
        assertThat(partie.getScore(EnumIdJoueur.MILOU)).isEqualTo(0);
    }

    @Test
    void getScorePartie1Tour() throws JoueurAPasJoueException {
        Partie partie = new Partie(0, 1, new ArrayList<>());
        Tour mockTour = mock(Tour.class);
        when(mockTour.getScore(EnumIdJoueur.TINTIN)).thenReturn(5);
        when(mockTour.getScore(EnumIdJoueur.MILOU)).thenReturn(0);
        partie.getTours().add(mockTour);
        assertThat(partie.getScore(EnumIdJoueur.TINTIN)).isEqualTo(5);
        assertThat(partie.getScore(EnumIdJoueur.MILOU)).isEqualTo(0);
    }

    @Test
    void getScorePartie3Tour() throws JoueurAPasJoueException {
        Partie partie = new Partie(0, 1, new ArrayList<>());
        Tour mockTour1 = mock(Tour.class);
        Tour mockTour2 = mock(Tour.class);
        Tour mockTour3 = mock(Tour.class);
        when(mockTour1.getScore(EnumIdJoueur.TINTIN)).thenReturn(5);
        when(mockTour1.getScore(EnumIdJoueur.MILOU)).thenReturn(0);
        when(mockTour2.getScore(EnumIdJoueur.TINTIN)).thenReturn(5);
        when(mockTour2.getScore(EnumIdJoueur.MILOU)).thenReturn(0);
        when(mockTour3.getScore(EnumIdJoueur.TINTIN)).thenReturn(5);
        when(mockTour3.getScore(EnumIdJoueur.MILOU)).thenReturn(0);
        partie.getTours().add(mockTour1);
        partie.getTours().add(mockTour2);
        partie.getTours().add(mockTour3);
        assertThat(partie.getScore(EnumIdJoueur.TINTIN)).isEqualTo(15);
        assertThat(partie.getScore(EnumIdJoueur.MILOU)).isEqualTo(0);
    }
}
