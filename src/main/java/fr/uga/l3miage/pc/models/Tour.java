package fr.uga.l3miage.pc.models;

import fr.uga.l3miage.pc.exceptions.technical.JoueurADejaJoueException;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;

public class Tour {
    private Boolean joueur1Coopere;
    private Boolean joueur2Coopere;

    public Tour() {}

    public Tour(boolean joueur1Coopere, boolean joueur2Coopere) {
        this.joueur1Coopere = joueur1Coopere;
        this.joueur2Coopere = joueur2Coopere;
    }

    public boolean getJoueur1Coopere() throws JoueurAPasJoueException {
        if (joueur1AJoue())
            return joueur1Coopere;
        throw new JoueurAPasJoueException("Le joueur 1 n'a pas joué.");
    }

    public boolean getJoueur2Coopere() throws JoueurAPasJoueException {
        if (joueur2AJoue())
            return joueur2Coopere;
        throw new JoueurAPasJoueException("Le joueur 2 n'a pas joué.");
    }

    public void setJoueur1Coopere(boolean coopere) throws JoueurADejaJoueException {
        if (joueur1AJoue())
            throw new JoueurADejaJoueException("Le joueur 1 a déjà joué.");
        joueur1Coopere = coopere;
    }

    public void setJoueur2Coopere(boolean coopere) throws JoueurADejaJoueException {
        if (joueur2AJoue())
            throw new JoueurADejaJoueException("Le joueur 2 a déjà joué.");
        joueur2Coopere = coopere;
    }

    public boolean estFini() { return joueur1AJoue() && joueur2AJoue(); }

    public boolean joueur1AJoue() { return joueur1Coopere != null; }

    public boolean joueur2AJoue() { return  joueur2Coopere != null; }
}
