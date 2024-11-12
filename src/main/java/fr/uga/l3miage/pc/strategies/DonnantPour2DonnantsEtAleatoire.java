package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public class DonnantPour2DonnantsEtAleatoire implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        if(Math.random() < 0.2){
            return Math.random() < 0.5;
        }
        if (historique.length < 2) {
            return false;
        }

        boolean dernierCoupAdversaire = utils.getDernierCoupAdversaire(historique, idJoueur);
        boolean avantDernierCoupAdversaire = utils.getAvantDernierCoupAdversaire(historique, idJoueur);

        return (dernierCoupAdversaire == avantDernierCoupAdversaire) ? dernierCoupAdversaire : Math.random() < 0.5;
    }


}
