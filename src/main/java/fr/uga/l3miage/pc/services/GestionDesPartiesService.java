package fr.uga.l3miage.pc.services;

import fr.uga.l3miage.pc.components.PartieComponent;
import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.rest.JoueurADejaJoueRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieInexistanteRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieNbToursIncorrectRestException;
import fr.uga.l3miage.pc.exceptions.technical.JoueurADejaJoueException;
import fr.uga.l3miage.pc.exceptions.technical.PartieInexistanteException;
import fr.uga.l3miage.pc.exceptions.technical.PartieNbToursIncorrectException;
import fr.uga.l3miage.pc.models.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GestionDesPartiesService {

    private final PartieComponent partieComponent;

    public int creerPartie(int nbTours) {
        try {
            return partieComponent.creerPartie(nbTours);
        } catch (PartieNbToursIncorrectException e) {
            throw new PartieNbToursIncorrectRestException(e.getMessage());
        }
    }

    public Tour jouerCoup (int numeroPartie, EnumIdJoueur idJoueur, boolean coopere) {
        try {
            return partieComponent.jouerCoup(numeroPartie, idJoueur, coopere);
        }
        catch (PartieInexistanteException e) {
            throw new PartieInexistanteRestException(e.getMessage());
        }
        catch (JoueurADejaJoueException e) {
            throw new JoueurADejaJoueRestException(e.getMessage());
        }
    }

    public int obtenirNbToursPartie(int numeroPartie){
        try {
            return partieComponent.getPartieByNumero(numeroPartie).getNbTours();
        }
        catch (PartieInexistanteException e) {
            throw new PartieInexistanteRestException(e.getMessage());
        }
    }
}
