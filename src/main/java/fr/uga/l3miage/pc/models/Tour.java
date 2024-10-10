package fr.uga.l3miage.pc.models;

public class Tour {
    private Boolean joueur1Coopere;
    private Boolean joueur2Coopere;

    public Tour() {}

    public Tour(boolean joueur1Coopere, boolean joueur2Coopere) {
        this.joueur1Coopere = joueur1Coopere;
        this.joueur2Coopere = joueur2Coopere;
    }

    public boolean getJoueur1Coopere() throws Exception {
        if (joueur1AJoue())
            return joueur1Coopere;
        throw new Exception("Le joueur 1 n'a pas joué.");
    }

    public boolean getJoueur2Coopere() throws Exception {
        if (joueur2AJoue())
            return joueur2Coopere;
        throw new Exception("Le joueur 2 n'a pas joué.");
    }

    public void setJoueur1Coopere(boolean coopere) throws Exception {
        if (joueur1AJoue())
            throw new Exception("Le joueur 1 a déjà joué.");
        joueur1Coopere = coopere;
    }

    public void setJoueur2Coopere(boolean coopere) throws Exception {
        if (joueur2AJoue())
            throw new Exception("Le joueur 2 a déjà joué.");
        joueur2Coopere = coopere;
    }

    public boolean estFini() { return joueur1AJoue() && joueur2AJoue(); }

    public boolean joueur1AJoue() { return joueur1Coopere != null; }

    public boolean joueur2AJoue() { return  joueur2Coopere != null; }
}