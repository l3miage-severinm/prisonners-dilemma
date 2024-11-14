package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public class Utils {

    private Utils() {}

    protected static boolean getDernierCoupAdversaire(Tour[] historique, EnumIdJoueur idJoueur) {
        return idJoueur == EnumIdJoueur.TINTIN ? historique[historique.length - 1].getJoueur2Coopere()
                : historique[historique.length - 1].getJoueur1Coopere();
    }

    protected static boolean getAvantDernierCoupAdversaire(Tour[] historique, EnumIdJoueur idJoueur) {
        return idJoueur == EnumIdJoueur.TINTIN ? historique[historique.length - 2].getJoueur2Coopere()
                : historique[historique.length - 2].getJoueur1Coopere();
    }
}
