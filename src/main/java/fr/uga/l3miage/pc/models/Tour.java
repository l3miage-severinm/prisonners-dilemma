package fr.uga.l3miage.pc.models;

import lombok.Data;

@Data
public class Tour {
    private Boolean joueur1Coopere;
    private Boolean joueur2Coopere;

    public boolean estFini() { return joueur1AJoue() && joueur2AJoue(); }

    public boolean joueur1AJoue() { return joueur1Coopere != null; }

    public boolean joueur2AJoue() { return joueur2Coopere != null; }
}
