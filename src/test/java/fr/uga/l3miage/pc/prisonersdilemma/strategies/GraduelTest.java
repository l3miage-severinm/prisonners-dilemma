package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.FabriqueStrategie;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GraduelTest {

    private final SimpleStrategy STRATEGIE = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.GRADUEL);

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
    void doitTrahir1FoisTintin() {
        final Tour[] historique1Tour = new Tour[] { new Tour(true, false) };
        final Tour[] historique2Tours = new Tour[] {
                historique1Tour[0],
                new Tour(false, true)
        };
        final Tour[] historique3Tours = new Tour[] {
                historique2Tours[0],
                historique2Tours[1],
                new Tour(true, true)
        };

        final boolean resultat1DoitTrahir = STRATEGIE.doStrategy(historique1Tour, EnumIdJoueur.TINTIN);
        final boolean resultat2DoitCooperer = STRATEGIE.doStrategy(historique2Tours, EnumIdJoueur.TINTIN);
        final boolean resultat3DoitCooperer = STRATEGIE.doStrategy(historique3Tours, EnumIdJoueur.TINTIN);

        assertThat(resultat1DoitTrahir).isFalse();
        assertThat(resultat2DoitCooperer).isTrue();
        assertThat(resultat3DoitCooperer).isTrue();
    }

    @Test
    void doitTrahir1FoisMilou() {
        final Tour[] historique1Tour = new Tour[] { new Tour(false, true) };
        final Tour[] historique2Tours = new Tour[] {
                historique1Tour[0],
                new Tour(true, false)
        };
        final Tour[] historique3Tours = new Tour[] {
                historique2Tours[0],
                historique2Tours[1],
                new Tour(true, true)
        };

        final boolean resultat1DoitTrahir = STRATEGIE.doStrategy(historique1Tour, EnumIdJoueur.MILOU);
        final boolean resultat2DoitCooperer = STRATEGIE.doStrategy(historique2Tours, EnumIdJoueur.MILOU);
        final boolean resultat3DoitCooperer = STRATEGIE.doStrategy(historique3Tours, EnumIdJoueur.MILOU);

        assertThat(resultat1DoitTrahir).isFalse();
        assertThat(resultat2DoitCooperer).isTrue();
        assertThat(resultat3DoitCooperer).isTrue();
    }
}
