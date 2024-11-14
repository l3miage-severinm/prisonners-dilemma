package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;

import java.security.SecureRandom;

public class PavlovAleatoire implements SimpleStrategy {

    private final SimpleStrategy pavlov = FabriqueStrategie.getInstance().createStrategie(EnumStrategie.PAVLOV);
    private final SecureRandom random = new SecureRandom();

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {

        if (random.nextInt(100) < 20)
            return random.nextBoolean();

        return pavlov.doStrategy(historique, idJoueur);
    }
}
