package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

import java.security.SecureRandom;

public class DonnantDonnantAleatoire implements SimpleStrategy {

    private final SecureRandom random = new SecureRandom();

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        if(historique.length == 0)
            return true;
        else if (random.nextInt(100) < 20)
            return random.nextBoolean();
        else if (idJoueur == EnumIdJoueur.MILOU)
            return historique[historique.length - 1].getJoueur2Coopere();
        else
            return historique[historique.length - 1].getJoueur1Coopere();
    }
}
