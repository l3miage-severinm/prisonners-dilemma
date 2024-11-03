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
            case DONNANT_DONNANT -> new DonnantDonnant();
            case DONNANT_DONNANT_ALEATOIRE -> new DonnantDonnantAleatoire();
            case SONDEUR_NAIF -> new SondeurNaif();
            case COOPERER -> new Cooperer();
            case TRAHIR -> new Trahir();
            default -> null;
        };
    }
}
