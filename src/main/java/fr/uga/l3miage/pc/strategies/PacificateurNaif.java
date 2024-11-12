package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public class PacificateurNaif implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        return historique.length < 1 || (Math.random() < 0.2)  ? Math.random() < 0.5 : utils.getDernierCoupAdversaire(historique, idJoueur);
    }
}
