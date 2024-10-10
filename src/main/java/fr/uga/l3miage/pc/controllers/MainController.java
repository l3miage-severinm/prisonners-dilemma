package fr.uga.l3miage.pc.controllers;

import fr.uga.l3miage.pc.exceptions.JoueurADejaJoueRestException;
import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;
import org.springframework.web.bind.annotation.RestController;

import fr.uga.l3miage.pc.endpoints.MainEndpoints;
import fr.uga.l3miage.pc.services.GestionDesParties;

@RestController
public class MainController implements MainEndpoints {

    private GestionDesParties gestionDesParties;

    public MainController() {
        gestionDesParties = GestionDesParties.getInstance();
    }

    @Override
    public int creerPartie(int nbTours) {
        return gestionDesParties.creerPartie(nbTours);
    }

    @Override
    public Tour[] jouerCoup(int idPartie, int idJoueur, boolean coup) {
        try {
            return gestionDesParties.jouerCoup(idPartie, EnumIdJoueur.values()[idJoueur], coup);
        } catch (Exception e) {
            throw new JoueurADejaJoueRestException(e.getMessage());
        }
    }

    @Override
    public Tour[] getHistorique(int idPartie) {
        return null;
    }

}
