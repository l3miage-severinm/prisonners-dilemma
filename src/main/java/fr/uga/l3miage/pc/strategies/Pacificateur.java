package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

import java.security.SecureRandom;

public class Pacificateur implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        if (historique.length < 2) {
            return true;
        }

        boolean dernierCoupAdversaire = Utils.getDernierCoupAdversaire(historique, idJoueur);
        boolean avantDernierCoupAdversaire = Utils.getAvantDernierCoupAdversaire(historique, idJoueur);

        if (!dernierCoupAdversaire && !avantDernierCoupAdversaire) {
            return new SecureRandom().nextDouble() < 0.2;
        }

        return true;
    }
}
