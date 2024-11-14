package fr.uga.l3miage.pc.models;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
    private Boolean joueur1Coopere;
    private Boolean joueur2Coopere;

    public boolean estFini() { return joueur1AJoue() && joueur2AJoue(); }

    public boolean joueur1AJoue() { return joueur1Coopere != null; }

    public boolean joueur2AJoue() { return joueur2Coopere != null; }

    public int getScore(EnumIdJoueur idJoueur) throws JoueurAPasJoueException {

        if (!estFini()) throw new JoueurAPasJoueException("Un des joueurs n'a pas joué");

        if(idJoueur == EnumIdJoueur.TINTIN){
            if (!joueur1Coopere && joueur2Coopere) return 5;
            else if (joueur1Coopere && !joueur2Coopere) return 0;
        }else{
            if (joueur1Coopere && !joueur2Coopere) return 5;
            else if (!joueur1Coopere && joueur2Coopere) return 0;
        }
        if (joueur1Coopere && joueur2Coopere) return 3;
        return 1;
    }
}
