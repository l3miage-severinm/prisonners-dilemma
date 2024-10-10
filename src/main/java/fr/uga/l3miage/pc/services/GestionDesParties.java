package fr.uga.l3miage.pc.services;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Partie;
import fr.uga.l3miage.pc.models.Tour;

import java.util.HashMap;
import java.util.Map;

// On pourrait en faire un service SpringBoot puisque par défaut ce sont des Singletons
public class GestionDesParties {
    private static GestionDesParties instance;
    private final Map<Integer, Partie> partiesEnCours;
    private int numeroPartieSuivante;

    private GestionDesParties() {
        partiesEnCours = new HashMap<>();
        numeroPartieSuivante = 0;
    }

    public static GestionDesParties getInstance() {
        if (instance == null) {
            instance = new GestionDesParties();
        }
        return instance;
    }

    public int creerPartie(int nbTours) {
        Partie p = new Partie(numeroPartieSuivante++, nbTours);
        partiesEnCours.put(p.getNumero(), p);
        return p.getNumero();
    }

    public Tour[] jouerCoup (int numeroPartie, EnumIdJoueur idJoueur, boolean coopere) throws Exception {
        Partie p = partiesEnCours.get(numeroPartie);
        return p.jouerCoup(idJoueur, coopere);
    }
}
