package fr.uga.l3miage.pc.models.strategies;

import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import fr.uga.l3miage.pc.interfaces.SimpleStrategy;
import fr.uga.l3miage.pc.models.Tour;

public class DonnantDonnant implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, int indexTourEnCours, int idJoueur) throws JoueurAPasJoueException {
        return (idJoueur == 1) ? historique[indexTourEnCours-1].getJoueur2Coopere() : historique[indexTourEnCours].getJoueur1Coopere();
    }
}
