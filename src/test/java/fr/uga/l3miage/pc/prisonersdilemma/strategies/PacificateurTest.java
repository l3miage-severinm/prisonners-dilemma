package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PacificateurTest {

    private final SimpleStrategy STRATEGIE = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.PACIFICATEUR);

    @Test
    void historiqueVide(){
        final Tour[] historique = new Tour[0];
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void doitCooperer(){
        final Tour[] historique = {new Tour(true, true), new Tour(true, true)};
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void doitTrahir(){
        final int NB_TIRAGE = 100000;
        final double INTERVALLE_CONFIANCE = 0.95;
        final double MARGE_ERREUR = 1 - INTERVALLE_CONFIANCE;
        final double PROBABILITE_COOPERER = 0.2;
        final Tour[] historique = {new Tour(false, false), new Tour(false, false)};
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
