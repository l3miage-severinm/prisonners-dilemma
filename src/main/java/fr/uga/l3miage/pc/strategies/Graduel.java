package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public class Graduel implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        if (calculerTrahisonsRestantes(historique, idJoueur) > 0)
            return false;

        if (calculerCooperationsRestantes(historique, idJoueur) > 0)
            return true;

        return ! hasAdversaireTrahiDernierTour(historique, idJoueur);   // retourner false si l'adversaire a trahi
    }

    private int countAdversaireTrahisons(Tour[] historique, EnumIdJoueur idJoueur) {
        int count = 0;
        for (Tour tour : historique) {
            if (idJoueur == EnumIdJoueur.TINTIN && Boolean.FALSE.equals(tour.getJoueur2Coopere())
             || idJoueur == EnumIdJoueur.MILOU && Boolean.FALSE.equals(tour.getJoueur1Coopere()))
                count++;
        }
        return count;
    }

    private int calculerTrahisonsRestantes(Tour[] historique, EnumIdJoueur idJoueur) {
        return hasAdversaireTrahiDernierTour(historique, idJoueur) ?
                countAdversaireTrahisons(historique, idJoueur) :
                0;
    }

    private int calculerCooperationsRestantes(Tour[] historique, EnumIdJoueur idJoueur) {
        return hasAdversaireTrahiDernierTour(historique, idJoueur) ? 2 : 0;
    }

    private boolean hasAdversaireTrahiDernierTour(Tour[] historique, EnumIdJoueur idJoueur) {
        if (historique.length == 0)
            return false;

        Tour dernierTour = historique[historique.length - 1];
        return (idJoueur == EnumIdJoueur.TINTIN && Boolean.FALSE.equals(dernierTour.getJoueur2Coopere())) ||
                (idJoueur == EnumIdJoueur.MILOU && Boolean.FALSE.equals(dernierTour.getJoueur1Coopere()));
    }
}
