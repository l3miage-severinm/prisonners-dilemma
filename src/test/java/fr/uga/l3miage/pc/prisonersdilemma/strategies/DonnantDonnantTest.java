package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.domain.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.domain.enums.EnumStrategie;
import fr.uga.l3miage.pc.domain.models.Tour;
import fr.uga.l3miage.pc.domain.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.domain.strategies.SimpleStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DonnantDonnantTest {

    private final SimpleStrategy STRATEGIE = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.DONNANT_DONNANT);

    @Test
    void doitCoopererTest() {
        final Tour[] historique = { new Tour(true, true) };
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void doitTrahirTest() {
        final Tour[] historique = { new Tour(false, false) };
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isFalse();
    }
}
