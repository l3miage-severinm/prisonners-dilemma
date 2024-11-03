package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumStrategie;

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
}
