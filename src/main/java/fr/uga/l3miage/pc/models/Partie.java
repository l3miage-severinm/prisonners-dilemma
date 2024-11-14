package fr.uga.l3miage.pc.models;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import lombok.Data;

import java.util.List;

@Data
public class Partie {
    private final int numero;
    private final int nbTours;
    private final List<Tour> tours;

    public boolean estFinie() { return tours.size() == nbTours && tours.get(tours.size() - 1).estFini(); }

    public int getScore(EnumIdJoueur idJoueur) throws JoueurAPasJoueException {
        int score = 0;
        for(Tour tour : tours) score += tour.getScore(idJoueur);
        return score;
    }
}
