package fr.uga.l3miage.pc.models.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.interfaces.SimpleStrategy;
import fr.uga.l3miage.pc.models.Tour;

public class DonnantDonnant implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        return (idJoueur == EnumIdJoueur.MILOU) ?
                historique[historique.length - 1].getJoueur2Coopere() :
                historique[historique.length - 1].getJoueur1Coopere();
    }
}
