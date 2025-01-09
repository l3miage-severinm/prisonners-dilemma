package fr.uga.l3miage.pc.components;

import fr.uga.l3miage.pc.enums.EnumGroupe;
import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.exceptions.technical.*;
import fr.uga.l3miage.pc.models.Partie;
import fr.uga.l3miage.pc.models.Tour;

public interface IPartieComponent {

    int creerPartie(int nbTours) throws PartieNbToursIncorrectException;
    Tour jouerCoup(int numeroPartie, EnumIdJoueur idJoueur, EnumStrategie technique)
            throws PartieInexistanteException, JoueurADejaJoueException, PartieTermineeException;
    Partie getPartieByNumero(int numero) throws  PartieInexistanteException;
    void clearPartiesEnCours();
    void automatiserStrategie(int idPartie, EnumIdJoueur idJoueur, EnumStrategie strategie, EnumGroupe groupe)
            throws PartieInexistanteException, PartieAutomatiseeException;
}
