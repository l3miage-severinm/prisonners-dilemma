package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoopererTest {

    @Test
    void historiqueVide() {
        Tour[] historique = new Tour[]{};
        SimpleStrategy strategie = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.COOPERER);
        assertThat(strategie.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(strategie.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }
}
