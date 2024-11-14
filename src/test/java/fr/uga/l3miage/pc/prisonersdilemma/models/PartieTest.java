package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import fr.uga.l3miage.pc.exceptions.technical.PartieAutomatiseeException;
import fr.uga.l3miage.pc.models.Partie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertThat(partie.getScore(EnumIdJoueur.TINTIN)).isZero();
        assertThat(partie.getScore(EnumIdJoueur.MILOU)).isZero();
    }

    @Test
    void getScorePartie1Tour() throws JoueurAPasJoueException {
        Partie partie = new Partie(0, 1, new ArrayList<>());
        Tour mockTour = mock(Tour.class);
        when(mockTour.getScore(EnumIdJoueur.TINTIN)).thenReturn(5);
        when(mockTour.getScore(EnumIdJoueur.MILOU)).thenReturn(0);
        partie.getTours().add(mockTour);
        assertThat(partie.getScore(EnumIdJoueur.TINTIN)).isEqualTo(5);
        assertThat(partie.getScore(EnumIdJoueur.MILOU)).isZero();
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
        assertThat(partie.getScore(EnumIdJoueur.MILOU)).isZero();
    }

    @Test
    void getScoreJoueurAPasJoue() throws JoueurAPasJoueException {
        Partie partie = new Partie(0, 1, new ArrayList<>());
        Tour mockTour = mock(Tour.class);
        when(mockTour.getScore(EnumIdJoueur.TINTIN)).thenThrow(JoueurAPasJoueException.class);
        when(mockTour.getScore(EnumIdJoueur.MILOU)).thenThrow(JoueurAPasJoueException.class);
        partie.getTours().add(mockTour);
        assertThrows(JoueurAPasJoueException.class,() -> partie.getScore(EnumIdJoueur.TINTIN));
        assertThrows(JoueurAPasJoueException.class, () -> partie.getScore(EnumIdJoueur.MILOU));
    }

    @Test
    void automatiser() throws PartieAutomatiseeException {
        SimpleStrategy strategy = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.COOPERER);
        Partie partie = new Partie(0, 1, new ArrayList<>());

        assertDoesNotThrow(() -> partie.automatiser(EnumIdJoueur.TINTIN, strategy));
        assertThat(partie.getStrategieTintin()).isNotNull();
        assertThat(partie.estAutomatisee(EnumIdJoueur.TINTIN)).isTrue();

        assertDoesNotThrow(() -> partie.automatiser(EnumIdJoueur.MILOU, strategy));
        assertThat(partie.getStrategieMilou()).isNotNull();
        assertThat(partie.estAutomatisee(EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void automatiserPartieDejaAutomatisee() throws PartieAutomatiseeException {
        SimpleStrategy strategy = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.COOPERER);
        Partie partie = new Partie(0, 1, new ArrayList<>());

        partie.setStrategieTintin(strategy);
        partie.setStrategieMilou(strategy);

        assertThrows(PartieAutomatiseeException.class, () -> partie.automatiser(EnumIdJoueur.TINTIN, strategy));
        assertThrows(PartieAutomatiseeException.class, () -> partie.automatiser(EnumIdJoueur.MILOU, strategy));
    }
}
