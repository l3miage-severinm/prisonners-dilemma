package fr.uga.l3miage.pc.models;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import lombok.Data;

import java.util.List;
import java.util.Timer;

@Data
public class Partie {
    private final int numero;
    private final int nbTours;
    private final List<Tour> tours;
    private SimpleStrategy strategieTintin;
    private SimpleStrategy strategieMilou;

    public boolean estFinie() { return tours.size() == nbTours && tours.get(tours.size() - 1).estFini(); }

    public boolean estAutomatisee(EnumIdJoueur idJoueur) {
        return (idJoueur == EnumIdJoueur.TINTIN) ? strategieTintin == null : strategieMilou == null;
    }

    public int getScore(EnumIdJoueur idJoueur) throws JoueurAPasJoueException {
        int score = 0;
        for(Tour tour : tours) score += tour.getScore(idJoueur);
        return score;
    }

    public void automatiser(EnumIdJoueur idJoueur, SimpleStrategy strategie) throws Exception {
        if (idJoueur == EnumIdJoueur.TINTIN) {
            if (this.strategieTintin != null)
                throw new Exception("Tintin a déjà automatisé sa partie");
            this.strategieTintin = strategie;
        }
        else {
            if (this.strategieMilou != null)
                throw new Exception("Milou a déjà automatisé sa partie");
            this.strategieMilou = strategie;
        }
    }
}
