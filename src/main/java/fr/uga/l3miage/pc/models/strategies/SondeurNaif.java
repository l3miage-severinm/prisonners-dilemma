package fr.uga.l3miage.pc.models.strategies;

import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import fr.uga.l3miage.pc.interfaces.SimpleStrategy;
import fr.uga.l3miage.pc.models.Tour;

import java.util.Random;

public class SondeurNaif implements SimpleStrategy {

    private final Random random = new Random();

    @Override
    public boolean doStrategy(Tour[] historique, int indexTourEnCours, int idJoueur) throws JoueurAPasJoueException {
        return (random.nextInt(100) < 20) ? false : (idJoueur == 1) ? historique[indexTourEnCours - 1].getJoueur2Coopere() : historique[indexTourEnCours].getJoueur1Coopere();
    }
}
