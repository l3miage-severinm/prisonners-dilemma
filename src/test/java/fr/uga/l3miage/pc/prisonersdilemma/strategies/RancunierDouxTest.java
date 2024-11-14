package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.junit.jupiter.api.Test;

import static fr.uga.l3miage.pc.prisonersdilemma.strategies.TestHelper.extendHistorique;
import static org.assertj.core.api.Assertions.assertThat;

class RancunierDouxTest {

    private final SimpleStrategy STRATEGIE = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.RANCUNIER_DOUX);

    @Test
    void doitCoopererHistoriqueVide() {
        final Tour[] historique = new Tour[0];
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void doitCooperer() {
        final Tour[] historique = new Tour[] { new Tour(true, true) };
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.TINTIN)).isTrue();
        assertThat(STRATEGIE.doStrategy(historique, EnumIdJoueur.MILOU)).isTrue();
    }

    @Test
    void doitTrahirApresTrahisonTintin() {
        final Tour[] historique1 = new Tour[] { new Tour(true, false) };
        final Tour[] historique2 = extendHistorique(historique1, new Tour(false, true));
        final Tour[] historique3 = extendHistorique(historique2, new Tour(false, true));
        final Tour[] historique4 = extendHistorique(historique3, new Tour(false, true));
        final Tour[] historique5 = extendHistorique(historique4, new Tour(false, true));

        assertThat(STRATEGIE.doStrategy(historique1, EnumIdJoueur.TINTIN)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique2, EnumIdJoueur.TINTIN)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique3, EnumIdJoueur.TINTIN)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique4, EnumIdJoueur.TINTIN)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique5, EnumIdJoueur.TINTIN)).isTrue();
    }

    @Test
    void doitTrahirApresTrahisonMilou() {
        final Tour[] historique1 = new Tour[] { new Tour(false, true) };
        final Tour[] historique2 = extendHistorique(historique1, new Tour(true, false));
        final Tour[] historique3 = extendHistorique(historique2, new Tour(true, false));
        final Tour[] historique4 = extendHistorique(historique3, new Tour(true, false));
        final Tour[] historique5 = extendHistorique(historique4, new Tour(true, false));

        assertThat(STRATEGIE.doStrategy(historique1, EnumIdJoueur.MILOU)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique2, EnumIdJoueur.MILOU)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique3, EnumIdJoueur.MILOU)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique4, EnumIdJoueur.MILOU)).isFalse();
        assertThat(STRATEGIE.doStrategy(historique5, EnumIdJoueur.MILOU)).isTrue();
    }
}
