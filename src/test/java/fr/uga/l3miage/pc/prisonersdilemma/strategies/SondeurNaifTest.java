package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SondeurNaifTest {

    private final SimpleStrategy STRATEGIE = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.SONDEUR_NAIF);

    @Test
    void historique1TourCooperer() {
        final int NB_TIRAGE = 10000;
        final double INTERVALLE_CONFIANCE = 0.95;
        final double MARGE_ERREUR = 1 - INTERVALLE_CONFIANCE;
        final double PROBABILITE_TRAHIR = 0.5;
        final Tour[] historique = new Tour[] { new Tour(true, true) };
        int nbTrahirTintin = 0;
        int nbTrahirMilou = 0;
        for (int i = 0; i < NB_TIRAGE; i++) {
            if (!STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN))
                nbTrahirTintin++;
            if (!STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU))
                nbTrahirMilou++;
        }
        final double tauxTrahisonTintin = (double)nbTrahirTintin / NB_TIRAGE;
        final double tauxTrahisonMilou = (double)nbTrahirMilou / NB_TIRAGE;
        assertThat(tauxTrahisonTintin).isCloseTo(PROBABILITE_TRAHIR, Offset.offset(MARGE_ERREUR));
        assertThat(tauxTrahisonMilou).isCloseTo(PROBABILITE_TRAHIR, Offset.offset(MARGE_ERREUR));
    }

    @Test
    void historique1TourTrahir() {
        final int NB_TIRAGE = 10000;
        final Tour[] historique = new Tour[] { new Tour(false, false) };
        int nbTrahirTintin = 0;
        int nbTrahirMilou = 0;
        for (int i = 0; i < NB_TIRAGE; i++) {
            if (!STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN))
                nbTrahirTintin++;
            if (!STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU))
                nbTrahirMilou++;
        }
        assertThat(nbTrahirTintin).isEqualTo(NB_TIRAGE);
        assertThat(nbTrahirMilou).isEqualTo(NB_TIRAGE);
    }
}
