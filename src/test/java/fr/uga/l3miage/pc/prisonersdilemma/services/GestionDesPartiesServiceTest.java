package fr.uga.l3miage.pc.prisonersdilemma.services;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumTechniquesAuto;
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
        assertThat(numeroPartie >= 0).isTrue();
    }

    @Test
    void jouerCoupPartieInexistanteTest() {
        assertThrows(PartieInexistanteRestException.class,
                () -> gestionDesPartiesService.jouerCoup(0, EnumIdJoueur.TINTIN, EnumTechniquesAuto.TJRS_COOP));
    }

    @Test
    void jouerCoupDejaJoueTest() {
        int numeroPartie = gestionDesPartiesService.creerPartie(2);
        gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumTechniquesAuto.TJRS_COOP);
        assertThrows(JoueurADejaJoueRestException.class,
                () -> gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumTechniquesAuto.TJRS_TRAHIR));
    }

    @Test
    void jouerCoupPartieTermineeTest() {
        int numeroPartie = gestionDesPartiesService.creerPartie(1);
        gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumTechniquesAuto.TJRS_COOP);
        gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.MILOU, EnumTechniquesAuto.TJRS_COOP);
        assertThrows(
                PartieTermineeRestException.class,
                () -> gestionDesPartiesService.jouerCoup(numeroPartie, EnumIdJoueur.TINTIN, EnumTechniquesAuto.TJRS_COOP)
        );
    }
}
