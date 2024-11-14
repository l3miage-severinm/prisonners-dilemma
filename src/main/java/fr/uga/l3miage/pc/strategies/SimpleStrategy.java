package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;

public interface SimpleStrategy {

    boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur);
}
