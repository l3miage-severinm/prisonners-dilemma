package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AleatoireTest {

    private final SimpleStrategy STRATEGIE = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.ALEATOIRE);

    @Test
    void historiqueVideTest() {
        final int NB_TIRAGE = 10000;
        final double INTERVALLE_CONFIANCE = 0.95;
        final double MARGE_ERREUR = 1 - INTERVALLE_CONFIANCE;
        final double PROBABILITE_COOPERER = 0.5;
        final Tour[] historique = new Tour[0];
        int nbCoopererTintin = 0;
        int nbCoopererMilou = 0;
        for (int i = 0; i < NB_TIRAGE; i++) {
            if (STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN))
                nbCoopererTintin++;
            if (STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU))
                nbCoopererMilou++;
        }
        final double tauxTrueTintin = (double)nbCoopererTintin / NB_TIRAGE;
        final double tauxTrueMilou = (double)nbCoopererMilou / NB_TIRAGE;
        assertThat(tauxTrueTintin).isCloseTo(PROBABILITE_COOPERER, Offset.offset(MARGE_ERREUR));
        assertThat(tauxTrueMilou).isCloseTo(PROBABILITE_COOPERER, Offset.offset(MARGE_ERREUR));
    }
}
