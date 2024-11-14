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
    void doitTrahirTintinTest() {
        final Tour[] historique1 = new Tour[] { new Tour(true, false) };
        final Tour[] historique2 = new Tour[] {
                historique1[0],
                new Tour(false, true)
        };
        assertThat(STRATEGIE.doStrategy(historique1, EnumIdJoueur.TINTIN)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique2, EnumIdJoueur.TINTIN)).isFalse();
    }

    @Test
    void doitTrahirMilouTest() {
        final Tour[] historique1 = new Tour[] { new Tour(false, true) };
        final Tour[] historique2 = new Tour[] {
                historique1[0],
                new Tour(true, false)
        };
        assertThat(STRATEGIE.doStrategy(historique1, EnumIdJoueur.MILOU)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique2, EnumIdJoueur.MILOU)).isFalse();
    }
}
