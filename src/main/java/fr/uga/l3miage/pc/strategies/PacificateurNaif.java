package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

import java.security.SecureRandom;

public class PacificateurNaif implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        if(historique.length < 1){
            return true;
        }

        boolean dernierCoupAdversaire = Utils.getDernierCoupAdversaire(historique, idJoueur);
        if(!dernierCoupAdversaire){
            return new SecureRandom().nextDouble() < 0.3;
        }
        return dernierCoupAdversaire;

    }
}
