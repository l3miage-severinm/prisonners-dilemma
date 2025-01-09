package fr.uga.l3miage.pc.domain.strategies;

import fr.uga.l3miage.pc.domain.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.domain.models.Tour;

public interface SimpleStrategy {

    boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur);
}
