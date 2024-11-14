package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public class PacificateurNaif implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        if(historique.length < 1){
            return true;
        }

        boolean dernierCoupAdversaire = utils.getDernierCoupAdversaire(historique, idJoueur);
        if(!dernierCoupAdversaire){
            return Math.random() < 0.3;
        }
        return dernierCoupAdversaire;

    }
}
