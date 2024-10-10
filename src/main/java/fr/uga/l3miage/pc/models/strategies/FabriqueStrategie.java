package fr.uga.l3miage.pc.models.strategies;

import fr.uga.l3miage.pc.interfaces.SimpleStrategy;
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

    public SimpleStrategy createShape(EnumTechniquesAuto techniqueAuto) {
        switch (techniqueAuto) {
            case DONNANT_DONNANT:
                return new DonnantDonnant();
            case DONNANT_DONNANT_ALEATOIRE:
                return new DonnantDonnantAleatoire();
            case SONDEUR_NAIF:
                return new SondeurNaif();
            case TJRS_COOP:
                return new ToujoursCooperer();
            case TJRS_TRAHIR:
                return new ToujoursTrahir();
            default:
                return null;
        }
    }
}
