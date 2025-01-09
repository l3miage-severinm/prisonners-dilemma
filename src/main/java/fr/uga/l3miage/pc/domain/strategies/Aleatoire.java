package fr.uga.l3miage.pc.domain.strategies;

import fr.uga.l3miage.pc.domain.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.domain.models.Tour;

import java.security.SecureRandom;

public class Aleatoire implements SimpleStrategy {

    private final SecureRandom random = new SecureRandom();

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        return random.nextBoolean();
    }
}
