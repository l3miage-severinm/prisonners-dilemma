package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public class Rancunier implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        for (Tour tour : historique) {
            if (idJoueur == EnumIdJoueur.TINTIN && !tour.getJoueur2Coopere() ||
                idJoueur == EnumIdJoueur.MILOU && !tour.getJoueur1Coopere())
                return false;
        }
        return true;
    }
}
