package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.strategies_g26.*;

public class FabriqueStrategie {

    private static FabriqueStrategie instance;

    private FabriqueStrategie(){}

    public static FabriqueStrategie getInstance() {
        if (instance == null) {
            instance = new FabriqueStrategie();
        }
        return instance;
    }

    public SimpleStrategy createStrategie(EnumStrategie techniqueAuto) {
        return switch (techniqueAuto) {
            case COOPERER -> new Cooperer();
            case TRAHIR -> new Trahir();
            case DONNANT_DONNANT -> new DonnantDonnant();
            case DONNANT_DONNANT_ALEATOIRE -> new DonnantDonnantAleatoire();
            case DONNANT_POUR_2_DONNANTS -> new DonnantPour2Donnants();
            case DONNANT_POUR_2_DONNANTS_ET_ALEATOIRE -> new DonnantPour2DonnantsEtAleatoire();
            case SONDEUR_NAIF -> new SondeurNaif();
            case SONDEUR_REPENTANT -> new SondeurRepentant();
            case PACIFICATEUR_NAIF -> new PacificateurNaif();
            case PACIFICATEUR -> new Pacificateur();
            case ALEATOIRE -> new Aleatoire();
            case RANCUNIER -> new Rancunier();
            case PAVLOV -> new Pavlov();
            case PAVLOV_ALEATOIRE -> new PavlovAleatoire();
            case ADAPTATIF -> new Adaptatif();
            case GRADUEL -> new Graduel();
            case DONNANT_DONNANT_SOUPCONNEUX -> new DonnantDonnantSoupconneux();
            case RANCUNIER_DOUX -> new RancunierDoux();
        };
    }

    public SimpleStrategy createStrategie_g26(EnumStrategie techniqueAuto) {
        return switch (techniqueAuto) {
            case COOPERER -> new Cooperer_g26();
            case TRAHIR -> new Trahir_g26();
            case DONNANT_DONNANT -> new DonnantDonnant_g26();
            case DONNANT_DONNANT_ALEATOIRE -> new DonnantDonnantAleatoire_g26();
            case DONNANT_POUR_2_DONNANTS -> new DonnantPour2Donnants_g26();
            case DONNANT_POUR_2_DONNANTS_ET_ALEATOIRE -> new DonnantPour2DonnantsEtAleatoire_g26();
            case SONDEUR_NAIF -> new SondeurNaif_g26();
            case SONDEUR_REPENTANT -> new SondeurRepentant_g26();
            case PACIFICATEUR_NAIF -> new PacificateurNaif_g26();
            case PACIFICATEUR -> new Pacificateur_g26();
            case ALEATOIRE -> new Aleatoire_g26();
            case RANCUNIER -> new Rancunier_g26();
            case PAVLOV -> new Pavlov_g26();
            case PAVLOV_ALEATOIRE -> new PavlovAleatoire_g26();
            case ADAPTATIF -> new Adaptatif_g26();
            case GRADUEL -> new Graduel_g26();
            case DONNANT_DONNANT_SOUPCONNEUX -> new DonnantDonnantSoupconneux_g26();
            case RANCUNIER_DOUX -> new RancunierDoux_g26();
        };
    }
}
