package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

import java.util.Random;

public class Aleatoire implements SimpleStrategy {

    private final Random random = new Random();

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        return random.nextBoolean();
    }
}
