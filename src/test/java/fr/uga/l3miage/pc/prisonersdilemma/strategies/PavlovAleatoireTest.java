package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PavlovAleatoireTest {

    private final SimpleStrategy STRATEGIE = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.PAVLOV_ALEATOIRE);

    @Test
    void deuxCooperer() {
        final Tour[] historique = new Tour[] { new Tour(true, true) };
        final int NB_TIRAGE = 10000;
        final double MARGE_ERREUR = 0.02;
        final double PROBABILITE_ALEATOIRE = 0.2;
        final double PROBABILITE_COOPERER = 1 - PROBABILITE_ALEATOIRE / 2;

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

    @Test
    void deuxTrahir() {
        final Tour[] historique = new Tour[] { new Tour(false, false) };
        final int NB_TIRAGE = 10000;
        final double MARGE_ERREUR = 0.01;
        final double PROBABILITE_ALEATOIRE = 0.2;
        final double PROBABILITE_COOPERER = 1 - PROBABILITE_ALEATOIRE / 2;

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

    @Test
    void tintinCoopereMilouTrahit() {
        final Tour[] historique = new Tour[] { new Tour(true, false) };
        final int NB_TIRAGE = 10000;
        final double MARGE_ERREUR = 0.01;
        final double PROBABILITE_ALEATOIRE = 0.2;
        final double PROBABILITE_COOPERER = PROBABILITE_ALEATOIRE / 2;  // 80% de false et 20% de 1 chance sur 2

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

    @Test
    void tintinTrahitMilouCoopere() {
        final Tour[] historique = new Tour[] { new Tour(false, true) };
        final int NB_TIRAGE = 10000;
        final double MARGE_ERREUR = 0.01;
        final double PROBABILITE_ALEATOIRE = 0.2;
        final double PROBABILITE_COOPERER = PROBABILITE_ALEATOIRE / 2;  // 80% de false et 20% de 1 chance sur 2

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
