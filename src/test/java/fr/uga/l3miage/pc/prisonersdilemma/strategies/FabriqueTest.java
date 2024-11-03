package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.strategies.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FabriqueTest {

    @Test
    void getInstanceTest() {
        FabriqueStrategie fabrique1 = FabriqueStrategie.getInstance();
        FabriqueStrategie fabrique2 = FabriqueStrategie.getInstance();
        assertThat(fabrique2).isSameAs(fabrique1);
    }

    @Test
    void createStrategyTest() {
        FabriqueStrategie fabrique = FabriqueStrategie.getInstance();
        assertThat(fabrique.createStrategie(EnumStrategie.COOPERER)).isInstanceOf(Cooperer.class);
        assertThat(fabrique.createStrategie(EnumStrategie.TRAHIR)).isInstanceOf(Trahir.class);
        assertThat(fabrique.createStrategie(EnumStrategie.DONNANT_DONNANT)).isInstanceOf(DonnantDonnant.class);
        assertThat(fabrique.createStrategie(EnumStrategie.DONNANT_DONNANT_ALEATOIRE)).isInstanceOf(DonnantDonnantAleatoire.class);
        assertThat(fabrique.createStrategie(EnumStrategie.DONNANT_POUR_2_DONNANTS)).isInstanceOf(DonnantPour2Donnants.class);
        assertThat(fabrique.createStrategie(EnumStrategie.DONNANT_POUR_2_DONNANTS_ET_ALEATOIRE)).isInstanceOf(DonnantPour2DonnantsEtAleatoire.class);
        assertThat(fabrique.createStrategie(EnumStrategie.SONDEUR_NAIF)).isInstanceOf(SondeurNaif.class);
        assertThat(fabrique.createStrategie(EnumStrategie.SONDEUR_REPENTANT)).isInstanceOf(SondeurRepentant.class);
        assertThat(fabrique.createStrategie(EnumStrategie.PACIFICATEUR_NAIF)).isInstanceOf(PacificateurNaif.class);
        assertThat(fabrique.createStrategie(EnumStrategie.PACIFICATEUR)).isInstanceOf(Pacificateur.class);
        assertThat(fabrique.createStrategie(EnumStrategie.ALEATOIRE)).isInstanceOf(Aleatoire.class);
        assertThat(fabrique.createStrategie(EnumStrategie.RANCUNIER)).isInstanceOf(Rancunier.class);
        assertThat(fabrique.createStrategie(EnumStrategie.PAVLOV)).isInstanceOf(Pavlov.class);
        assertThat(fabrique.createStrategie(EnumStrategie.PAVLOV_ALEATOIRE)).isInstanceOf(PavlovAleatoire.class);
        assertThat(fabrique.createStrategie(EnumStrategie.ADAPTATIF)).isInstanceOf(Adaptatif.class);
        assertThat(fabrique.createStrategie(EnumStrategie.GRADUEL)).isInstanceOf(Graduel.class);
        assertThat(fabrique.createStrategie(EnumStrategie.DONNANT_DONNANT_SOUPCONNEUX)).isInstanceOf(DonnantDonnantSoupconneux.class);
        assertThat(fabrique.createStrategie(EnumStrategie.RANCUNIER_DOUX)).isInstanceOf(RancunierDoux.class);
    }
}
