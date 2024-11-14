package fr.uga.l3miage.pc.prisonersdilemma.services;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.exceptions.rest.JoueurADejaJoueRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieInexistanteRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieNbToursIncorrectRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieTermineeRestException;
import fr.uga.l3miage.pc.services.GestionDesPartiesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GestionDesPartiesServiceTest {

    @Autowired
    private GestionDesPartiesService gestionDesPartiesService;

    @AfterEach
    void cleanPartiesEnCours() {
        gestionDesPartiesService.clearPartiesEnCours();
    }

    @Test
    void creePartieNbTours0Test() {
        assertThrows(PartieNbToursIncorrectRestException.class,
                () -> gestionDesPartiesService.creerPartie(0));
    }

    @Test
    void creePartieNbTours1Test() {
        int numeroPartie = gestionDesPartiesService.creerPartie(1);
        assertThat(numeroPartie).isNotNegative();
    }

    @Test
    void jouerCoupPartieInexistanteTest() {
        assertThrows(PartieInexistanteRestException.class,
                () -> gestionDesPartiesService.jouerCoup(0, EnumIdJoueur.TINTIN, EnumStrategie.COOPERER));
    }

    @Test
    void jouerCoupDejaJoueTest() {
        int numeroPartie = gestionDesPartiesService.creerPartie(2);
        gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumStrategie.COOPERER);
        assertThrows(JoueurADejaJoueRestException.class,
                () -> gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumStrategie.TRAHIR));
    }

    @Test
    void jouerCoupPartieTermineeTest() {
        int numeroPartie = gestionDesPartiesService.creerPartie(1);
        gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumStrategie.COOPERER);
        gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.MILOU, EnumStrategie.COOPERER);
        assertThrows(
                PartieTermineeRestException.class,
                () -> gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumStrategie.COOPERER)
        );
    }
}
