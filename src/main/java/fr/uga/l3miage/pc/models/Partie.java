package fr.uga.l3miage.pc.models;

import lombok.Data;

import java.util.List;

@Data
public class Partie {
    private final int numero;
    private final int nbTours;
    private final List<Tour> tours;

    public boolean estFinie() { return tours.size() == nbTours && tours.get(tours.size() - 1).estFini(); }
}
