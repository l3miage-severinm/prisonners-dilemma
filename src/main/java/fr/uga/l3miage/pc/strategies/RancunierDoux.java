package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public class RancunierDoux implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        int nbChecks = Math.min(4, historique.length);

        for (int i = 1; i <= nbChecks; i++) {
            Tour tour = historique[historique.length - i];

            boolean adversaireCoopere = (idJoueur == EnumIdJoueur.TINTIN) ?
                    tour.getJoueur2Coopere() :
                    tour.getJoueur1Coopere();

            if (!adversaireCoopere)
                return false;
        }

        return true;
    }
}
