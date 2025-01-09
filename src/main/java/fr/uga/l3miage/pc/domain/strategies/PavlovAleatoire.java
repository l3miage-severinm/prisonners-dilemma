package fr.uga.l3miage.pc.domain.strategies;

import fr.uga.l3miage.pc.domain.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.domain.enums.EnumStrategie;
import fr.uga.l3miage.pc.domain.models.Tour;

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
