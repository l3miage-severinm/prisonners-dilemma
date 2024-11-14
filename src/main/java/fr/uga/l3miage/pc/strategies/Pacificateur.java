package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public class Pacificateur implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        if (historique.length < 2) {
            return true;
        }

        boolean dernierCoupAdversaire = utils.getDernierCoupAdversaire(historique, idJoueur);
        boolean avantDernierCoupAdversaire = utils.getAvantDernierCoupAdversaire(historique, idJoueur);

        if (!dernierCoupAdversaire && !avantDernierCoupAdversaire) {
            return Math.random() < 0.2;
        }

        return true;
    }
}
