package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdaptatifTest {

    private final SimpleStrategy STRATEGIE = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.ADAPTATIF);

    @Test
    void doitCoopererHistoriqueVideTest() {
        final Tour[] historique = new Tour[0];
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void doitTrahirApres6CoopererTest() {
        final Tour[] historique = new Tour[] {
                new Tour(true, true),
                new Tour(true, true),
                new Tour(true, true),
                new Tour(true, true),
                new Tour(true, true),
                new Tour(true, true),
        };
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isFalse();
    }

    @Test
    void doitCoopererTest() {
        final Tour[] historique = new Tour[] {
                new Tour(true, true),
                new Tour(true, true),
                new Tour(true, true),
                new Tour(true, true),
                new Tour(true, true),
                new Tour(true, true),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false)
        };
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void doitTrahirTintinTest() {
        final Tour[] historique = new Tour[] {
                new Tour(true, false),
                new Tour(true, false),
                new Tour(true, false),
                new Tour(true, false),
                new Tour(true, false),
                new Tour(true, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false)
        };
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isFalse();
    }

    @Test
    void doitTrahirMilouTest() {
        final Tour[] historique = new Tour[] {
                new Tour(false, true),
                new Tour(false, true),
                new Tour(false, true),
                new Tour(false, true),
                new Tour(false, true),
                new Tour(false, true),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false),
                new Tour(false, false)
        };
        // [c, c] => +3
        // [t, t] => +1
        // [c, t] => +0 et +5
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isFalse();
    }
}
