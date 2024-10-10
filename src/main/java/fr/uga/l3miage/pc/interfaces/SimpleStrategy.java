package fr.uga.l3miage.pc.interfaces;

import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import fr.uga.l3miage.pc.models.Tour;

public interface SimpleStrategy {

    public boolean doStrategy(Tour[] historique, int indexTourEnCours, int idJoueur) throws JoueurAPasJoueException;
}
