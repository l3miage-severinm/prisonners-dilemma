package fr.uga.l3miage.pc.models.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.interfaces.SimpleStrategy;
import fr.uga.l3miage.pc.models.Tour;

import java.util.Random;

public class SondeurNaif implements SimpleStrategy {

    private final Random random = new Random();

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        return random.nextInt(100) >= 20 && ((idJoueur == EnumIdJoueur.MILOU) ?
                historique[historique.length - 2].getJoueur2Coopere() :
                historique[historique.length - 1].getJoueur1Coopere());
    }
}
