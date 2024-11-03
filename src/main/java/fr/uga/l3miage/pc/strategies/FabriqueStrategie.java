package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumTechniquesAuto;

public class FabriqueStrategie {

    private static FabriqueStrategie instance;

    private FabriqueStrategie(){}

    public static FabriqueStrategie getInstance() {
        if (instance == null) {
            instance = new FabriqueStrategie();
        }
        return instance;
    }

    public SimpleStrategy createStrategie(EnumTechniquesAuto techniqueAuto) {
        return switch (techniqueAuto) {
            case DONNANT_DONNANT -> new DonnantDonnant();
            case DONNANT_DONNANT_ALEATOIRE -> new DonnantDonnantAleatoire();
            case SONDEUR_NAIF -> new SondeurNaif();
            case TJRS_COOP -> new ToujoursCooperer();
            case TJRS_TRAHIR -> new ToujoursTrahir();
            default -> null;
        };
    }
}
