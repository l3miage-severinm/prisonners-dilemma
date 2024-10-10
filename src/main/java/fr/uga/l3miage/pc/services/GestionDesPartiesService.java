package fr.uga.l3miage.pc.services;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.rest.JoueurADejaJoueRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieInexistanteRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieNbToursIncorrectRestException;
import fr.uga.l3miage.pc.exceptions.technical.JoueurADejaJoueException;
import fr.uga.l3miage.pc.exceptions.technical.PartieNbToursIncorrectException;
import fr.uga.l3miage.pc.models.Partie;
import fr.uga.l3miage.pc.models.Tour;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GestionDesPartiesService {
    private final Map<Integer, Partie> partiesEnCours;
    private int numeroPartieSuivante;

    private GestionDesPartiesService() {
        partiesEnCours = new HashMap<>();
        numeroPartieSuivante = 0;
    }

    public int creerPartie(int nbTours) {
        Partie p;
        try { p = new Partie(numeroPartieSuivante++, nbTours); }
        catch (PartieNbToursIncorrectException e) {
            throw new PartieNbToursIncorrectRestException(e.getMessage());
        }
        partiesEnCours.put(p.getNumero(), p);
        return p.getNumero();
    }

    public Tour[] jouerCoup (int numeroPartie, EnumIdJoueur idJoueur, boolean coopere){
        Partie p = partiesEnCours.get(numeroPartie);
        if (p == null)
            throw new PartieInexistanteRestException("La partie n°" + numeroPartie + " n'existe pas");
        try {
            return p.jouerCoup(idJoueur, coopere);
        }
        catch (JoueurADejaJoueException e) {
            throw new JoueurADejaJoueRestException(e.getMessage());
        }
    }

    public int obtenirNbToursPartie(int numeroPartie){
        return partiesEnCours.get(numeroPartie).getNbTours();
    }
}
