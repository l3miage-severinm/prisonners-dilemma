package fr.uga.l3miage.pc.domain.spi;

import fr.uga.l3miage.pc.domain.enums.EnumGroupe;
import fr.uga.l3miage.pc.domain.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.domain.enums.EnumStrategie;
import fr.uga.l3miage.pc.domain.models.Partie;
import fr.uga.l3miage.pc.domain.models.Tour;
import fr.uga.l3miage.pc.web.exceptions.technical.*;

public interface IPartieComponent {

    int creerPartie(int nbTours) throws PartieNbToursIncorrectException;
    Tour jouerCoup(int numeroPartie, EnumIdJoueur idJoueur, EnumStrategie technique)
            throws PartieInexistanteException, JoueurADejaJoueException, PartieTermineeException;
    Partie getPartieByNumero(int numero) throws  PartieInexistanteException;
    void clearPartiesEnCours();
    void automatiserStrategie(int idPartie, EnumIdJoueur idJoueur, EnumStrategie strategie, EnumGroupe groupe)
            throws PartieInexistanteException, PartieAutomatiseeException;
}
