package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.models.Tour;

public class TestHelper {

    public static Tour[] extendHistorique(Tour[] historique, Tour extension) {
        Tour[] newHistorique = new Tour[historique.length + 1];
        System.arraycopy(historique, 0, newHistorique, 0, historique.length);
        newHistorique[historique.length] = extension;
        return newHistorique;
    }
}
