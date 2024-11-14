package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

import java.security.SecureRandom;

public class DonnantPour2Donnants implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        if (historique.length < 2) {
            return false;
        }

        boolean dernierCoupAdversaire = utils.getDernierCoupAdversaire(historique, idJoueur);
        boolean avantDernierCoupAdversaire = utils.getAvantDernierCoupAdversaire(historique, idJoueur);

        return (dernierCoupAdversaire == avantDernierCoupAdversaire) ?
                dernierCoupAdversaire :
                new SecureRandom().nextDouble() < 0.5;
    }
}
