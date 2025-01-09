package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.domain.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.domain.enums.EnumStrategie;
import fr.uga.l3miage.pc.domain.models.Tour;
import fr.uga.l3miage.pc.domain.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.domain.strategies.SimpleStrategy;
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
