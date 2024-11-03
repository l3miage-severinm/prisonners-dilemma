package fr.uga.l3miage.pc.models.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.interfaces.SimpleStrategy;
import fr.uga.l3miage.pc.models.Tour;

public class ToujoursTrahir implements SimpleStrategy {

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        return false;
    }
}
