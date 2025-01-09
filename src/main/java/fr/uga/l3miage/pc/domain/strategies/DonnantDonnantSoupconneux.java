package fr.uga.l3miage.pc.domain.strategies;

import fr.uga.l3miage.pc.domain.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.domain.models.Tour;

public class DonnantDonnantSoupconneux implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        if (historique.length == 0)
            return false;
        if (idJoueur == EnumIdJoueur.TINTIN)
            return historique[historique.length - 1].getJoueur2Coopere();
        return historique[historique.length - 1].getJoueur1Coopere();
    }
}
