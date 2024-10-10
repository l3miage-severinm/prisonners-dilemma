package fr.uga.l3miage.pc.controllers;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.rest.PartieNbToursIncorrectRestException;
import fr.uga.l3miage.pc.models.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fr.uga.l3miage.pc.endpoints.MainEndpoints;
import fr.uga.l3miage.pc.services.GestionDesPartiesService;

@RestController
public class MainController implements MainEndpoints {

    @Autowired
    private GestionDesPartiesService gestionDesPartiesService;

    @Override
    public int creerPartie(int nbTours) throws PartieNbToursIncorrectRestException {
        return gestionDesPartiesService.creerPartie(nbTours);
    }

    @Override
    public Tour[] jouerCoup(int idPartie, int idJoueur, boolean coup) {
        return gestionDesPartiesService.jouerCoup(idPartie, EnumIdJoueur.values()[idJoueur], coup);
    }

    @Override
    public Tour[] getHistorique(int idPartie) {
        return new Tour[0]; // TODO
    }

}
