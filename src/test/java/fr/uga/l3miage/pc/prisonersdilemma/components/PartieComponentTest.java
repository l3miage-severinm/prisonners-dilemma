package fr.uga.l3miage.pc.prisonersdilemma.components;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.exceptions.technical.JoueurADejaJoueException;
import fr.uga.l3miage.pc.exceptions.technical.PartieInexistanteException;
import fr.uga.l3miage.pc.exceptions.technical.PartieNbToursIncorrectException;
import fr.uga.l3miage.pc.components.PartieComponent;
import fr.uga.l3miage.pc.exceptions.technical.PartieTermineeException;
import fr.uga.l3miage.pc.models.Tour;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PartieComponentTest {

    @Autowired
    private PartieComponent partieComponent;

    @AfterEach
    void clearPartiesEnCours() {
        partieComponent.clearPartiesEnCours();
    }

    @Test
    void creerPartieTest() throws PartieNbToursIncorrectException {
        final int numeroPartie = partieComponent.creerPartie(3);
        assertThat(numeroPartie).isNotNegative();
    }

    @Test
    void creerPartieNbTours0Test() {
        assertThrows(PartieNbToursIncorrectException.class, () -> partieComponent.creerPartie(0));
    }

    @Test
    void jouerCoupTest()
            throws PartieNbToursIncorrectException, JoueurADejaJoueException, PartieInexistanteException, PartieTermineeException {
        final int numeroPartie = partieComponent.creerPartie(3);
        final boolean coopereTintin = true;
        final boolean coopereMilou = false;

        Tour tourAfterCoup1 = partieComponent.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumStrategie.COOPERER);
        assertThat(tourAfterCoup1.getJoueur1Coopere()).isEqualTo(coopereTintin);
        assertThat(tourAfterCoup1.getJoueur2Coopere()).isNull();

        Tour tourAfterCoup2 = partieComponent.jouerCoup(numeroPartie, EnumIdJoueur.MILOU, EnumStrategie.TRAHIR);
        assertThat(tourAfterCoup2).isNotSameAs(tourAfterCoup1);
        assertThat(tourAfterCoup1.getJoueur1Coopere()).isEqualTo(coopereTintin);
        assertThat(tourAfterCoup1.getJoueur2Coopere()).isEqualTo(coopereMilou);
        assertThat(tourAfterCoup2.getJoueur1Coopere()).isNull();
        assertThat(tourAfterCoup2.getJoueur2Coopere()).isNull();
    }

    @Test
    void jouerCoupPartieInexistanteTest() {
        assertThrows(
                PartieInexistanteException.class,
                () -> partieComponent.jouerCoup(0, EnumIdJoueur.TINTIN, EnumStrategie.COOPERER)
        );
    }

    @Test
    void jouerCoupTintinADejaJoueTest()
            throws PartieNbToursIncorrectException, JoueurADejaJoueException, PartieInexistanteException, PartieTermineeException {
        final EnumIdJoueur idJoueur = EnumIdJoueur.TINTIN;
        final int numeroPartie = partieComponent.creerPartie(3);
        partieComponent.jouerCoup(numeroPartie, idJoueur, EnumStrategie.COOPERER);
        assertThrows(
                JoueurADejaJoueException.class,
                () -> partieComponent.jouerCoup(numeroPartie, idJoueur, EnumStrategie.TRAHIR)
        );
    }

    @Test
    void jouerCoupMilouADejaJoueTest()
            throws PartieNbToursIncorrectException, JoueurADejaJoueException, PartieInexistanteException, PartieTermineeException {
        final EnumIdJoueur idJoueur = EnumIdJoueur.MILOU;
        final int numeroPartie = partieComponent.creerPartie(3);
        partieComponent.jouerCoup(numeroPartie, idJoueur, EnumStrategie.COOPERER);
        assertThrows(
                JoueurADejaJoueException.class,
                () -> partieComponent.jouerCoup(numeroPartie, idJoueur, EnumStrategie.TRAHIR)
        );
    }

    @Test
    void jouerCoupDernierCoupTest()
            throws PartieNbToursIncorrectException, JoueurADejaJoueException, PartieInexistanteException, PartieTermineeException {
        final int numeroPartie = partieComponent.creerPartie(1);
        Tour tourAfterCoup1 = partieComponent.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumStrategie.COOPERER);
        Tour tourAfterCoup2 = partieComponent.jouerCoup(numeroPartie, EnumIdJoueur.MILOU, EnumStrategie.TRAHIR);
        assertThat(tourAfterCoup2).isSameAs(tourAfterCoup1);
        assertThat(tourAfterCoup2.getJoueur1Coopere()).isTrue();
        assertThat(tourAfterCoup2.getJoueur2Coopere()).isFalse();
    }

    @Test
    void jouerCoupPartieTermineeTest()
            throws PartieNbToursIncorrectException, JoueurADejaJoueException, PartieInexistanteException, PartieTermineeException {
        final int numeroPartie = partieComponent.creerPartie(1);
        partieComponent.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumStrategie.COOPERER);
        partieComponent.jouerCoup(numeroPartie, EnumIdJoueur.MILOU, EnumStrategie.COOPERER);
        assertThrows(
                PartieTermineeException.class,
                () -> partieComponent.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumStrategie.COOPERER)
        );
    }
}
