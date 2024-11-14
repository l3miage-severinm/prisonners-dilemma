package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public class DonnantDonnant implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        return (idJoueur == EnumIdJoueur.TINTIN) ?
                historique[historique.length - 1].getJoueur2Coopere() :
                historique[historique.length - 1].getJoueur1Coopere();
    }
}
