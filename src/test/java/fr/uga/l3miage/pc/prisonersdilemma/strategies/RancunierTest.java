package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RancunierTest {

    private final SimpleStrategy STRATEGIE = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.RANCUNIER);

    @Test
    void doitCoopererHistoriqueVideTest() {
        final Tour[] historique = new Tour[0];
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void doitCoopererTest() {
        final Tour[] historique = new Tour[] { new Tour(true, true) };
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void doitTrahirTest() {
        final Tour[] historique = new Tour[] { new Tour(false, false) };
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isFalse();
    }
}
