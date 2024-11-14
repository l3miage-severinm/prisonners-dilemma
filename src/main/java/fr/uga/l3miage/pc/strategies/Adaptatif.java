package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import fr.uga.l3miage.pc.models.Tour;

public class Adaptatif implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        if (historique.length < 5)
            return true;
        if (historique.length < 10)
            return false;
        return calculerMeilleurChoix(historique, idJoueur);
    }

    private boolean calculerMeilleurChoix(Tour[] historique, EnumIdJoueur idJoueur) {

        int scoreCooperation = 0;
        int scoreTrahison = 0;
        int nbToursCooperation = 0;
        int nbToursTrahison = 0;

        for (Tour tour : historique) {
            try {
                boolean aCoopere = idJoueur == EnumIdJoueur.TINTIN ? tour.getJoueur1Coopere() : tour.getJoueur2Coopere();
                int score = tour.getScore(idJoueur);

                if (aCoopere) {
                    scoreCooperation += score;
                    nbToursCooperation++;
                } else {
                    scoreTrahison += score;
                    nbToursTrahison++;
                }
            } catch (JoueurAPasJoueException e) {
                throw new RuntimeException("Un joueur n'a pas joué le tour " + tour);
            }
        }

        double moyenneCooperation = nbToursCooperation > 0 ? (double) scoreCooperation / nbToursCooperation : 0;
        double moyenneTrahison = nbToursTrahison > 0 ? (double) scoreTrahison / nbToursTrahison : 0;
        return moyenneCooperation >= moyenneTrahison;
    }
}
