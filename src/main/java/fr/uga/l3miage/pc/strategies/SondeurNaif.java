package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

import java.util.Random;

public class SondeurNaif implements SimpleStrategy {

    private final Random random = new Random();

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        if (historique.length == 0)
            return true;

        Tour dernierTour = historique[historique.length - 1];
        boolean dernierCoupAdversaireCoopere = idJoueur == EnumIdJoueur.TINTIN ?
                dernierTour.getJoueur2Coopere() :
                dernierTour.getJoueur1Coopere();

        if (dernierCoupAdversaireCoopere && random.nextInt(100) >= 50)  // probabilité de trahir de 1/2
            return false;

        return true;
    }
}
