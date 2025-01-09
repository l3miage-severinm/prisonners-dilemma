package fr.uga.l3miage.pc.services;

import fr.uga.l3miage.pc.enums.EnumGroupe;
import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IGestionDesPartiesService {
    int creerPartie(int nbTours);
    Tour jouerCoup(int numeroPartie, EnumIdJoueur idJoueur, EnumStrategie strategie);
    void clearPartiesEnCours();
    int obtenirNbToursPartie(int numeroPartie);
    List<Tour> obtenirHistoriquePartie(int numeroPartie);
    Flux<List<Tour>> obtenirFluxHistoriquePartie(int numeroPartie);
    void automatiserStrategie(int idPartie, EnumIdJoueur idJoueur, EnumStrategie strategie, EnumGroupe groupe);
}
