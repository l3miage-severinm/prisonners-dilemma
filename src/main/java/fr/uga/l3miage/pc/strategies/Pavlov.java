package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.rest.JoueurAPasJoueRestException;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import fr.uga.l3miage.pc.models.Tour;

public class Pavlov implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        Tour dernierTour = historique[historique.length - 1];
        boolean coupDernierTour = idJoueur == EnumIdJoueur.TINTIN ?
                dernierTour.getJoueur1Coopere() :
                dernierTour.getJoueur2Coopere();
        int scoreDernierTour;
        try {
            scoreDernierTour = dernierTour.getScore(idJoueur);
        } catch (JoueurAPasJoueException e) {
            throw new JoueurAPasJoueRestException(e.getMessage());
        }
        if (scoreDernierTour == 5 || scoreDernierTour == 3)
            return coupDernierTour;
        return !coupDernierTour;
    }
}
