package fr.uga.l3miage.pc.models;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumTechniquesAuto;

public class Partie {
    private final int numero;
    private final int nbTours;
    private final Tour[] historique;
    private int indexTourEnCours;

    public Partie(int numero, int nbTours) {
        this.numero = numero;
        this.nbTours = nbTours;
        historique = new Tour[nbTours];
        indexTourEnCours = 0;
        historique[indexTourEnCours] = new Tour();
    }

    public int getNumero() { return numero; }

    public int getNbTours() { return nbTours; }

    public Tour[] jouerCoup(EnumIdJoueur idJoueur, boolean coopere) throws Exception {
        if (idJoueur == EnumIdJoueur.Un)
            historique[indexTourEnCours].setJoueur1Coopere(coopere);
        else
            historique[indexTourEnCours].setJoueur2Coopere(coopere);
        historique[++indexTourEnCours] = new Tour();
        return historique;
    }

    public void jeuAutomatique(EnumTechniquesAuto technique) {
        // TODO implement here
    }
}
