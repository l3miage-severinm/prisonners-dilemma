package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
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
        double scoreCooperation = calculerScoreMoyen(historique, idJoueur, true);
        double scoreTrahison = calculerScoreMoyen(historique, idJoueur, false);

        return scoreCooperation >= scoreTrahison;
    }

    private double calculerScoreMoyen(Tour[] historique, EnumIdJoueur idJoueur, boolean coup) {
        int scoreTotal = 0;
        int nbCoup = 0;

        for (Tour tour : historique) {
            boolean coupEffectue = idJoueur == EnumIdJoueur.TINTIN ? tour.getJoueur1Coopere() : tour.getJoueur2Coopere();
            boolean coupAdversaire = idJoueur == EnumIdJoueur.TINTIN ? tour.getJoueur2Coopere() : tour.getJoueur1Coopere();

            int score;
            if (coupEffectue && coupAdversaire) {
                score = -2;
            } else if (!coupEffectue && coupAdversaire) {
                score = 0;
            } else if (coupEffectue && !coupAdversaire) {
                score = -5;
            } else {
                score = -4;
            }

            if (coupEffectue == coup) {
                scoreTotal += score;
                nbCoup++;
            }
        }

        return nbCoup > 0 ? (double) scoreTotal / nbCoup : 0;
    }
}
