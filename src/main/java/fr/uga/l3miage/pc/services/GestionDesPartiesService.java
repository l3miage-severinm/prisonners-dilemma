package fr.uga.l3miage.pc.services;

import fr.uga.l3miage.pc.components.PartieComponent;
import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.exceptions.rest.*;
import fr.uga.l3miage.pc.exceptions.technical.*;
import fr.uga.l3miage.pc.mappers.PartieMapper;
import fr.uga.l3miage.pc.response.PartieDTO;
import fr.uga.l3miage.pc.models.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class GestionDesPartiesService {

    private final PartieComponent partieComponent;
    private final PartieMapper partieMapper;
    private final Map<Integer, Sinks.Many<PartieDTO>> partieSinks = new ConcurrentHashMap<>();


    public int creerPartie(int nbTours) {
        try {
            return partieComponent.creerPartie(nbTours);
        } catch (PartieNbToursIncorrectException e) {
            throw new PartieNbToursIncorrectRestException(e.getMessage());
        }
    }

    public Tour jouerCoup(int numeroPartie, EnumIdJoueur idJoueur, EnumStrategie strategie) {
        try {

            Tour tour = partieComponent.jouerCoup(numeroPartie, idJoueur, strategie);

            PartieDTO partie = obtenirHistoriquePartie(numeroPartie);
            System.out.print("PARTIE" + partie);
            partieSinks.computeIfAbsent(numeroPartie, k -> Sinks.many().replay().latest()).tryEmitNext(partie);

            return tour;

        } catch (PartieInexistanteException e) {
            throw new PartieInexistanteRestException(e.getMessage());
        } catch (JoueurADejaJoueException e) {
            throw new JoueurADejaJoueRestException(e.getMessage());
        } catch (PartieTermineeException e) {
            throw new PartieTermineeRestException(e.getMessage());
        }
    }

    public void clearPartiesEnCours() {
        partieComponent.clearPartiesEnCours();
        partieSinks.clear();
    }

    public int obtenirNbToursPartie(int numeroPartie) {
        try {
            return partieComponent.getPartieByNumero(numeroPartie).getNbTours();
        } catch (PartieInexistanteException e) {
            throw new PartieInexistanteRestException(e.getMessage());
        }
    }

    public PartieDTO obtenirHistoriquePartie(int numeroPartie) {
        try {
            return partieMapper.toDto(partieComponent.getPartieByNumero(numeroPartie));
        } catch (PartieInexistanteException e) {
            throw new PartieInexistanteRestException(e.getMessage());
        }
    }

    public Flux<PartieDTO> obtenirFluxHistoriquePartie(int numeroPartie) {
        return partieSinks.computeIfAbsent(numeroPartie, k -> Sinks.many().replay().latest()).asFlux();
    }

    public void automatiserStrategie(int idPartie, EnumIdJoueur idJoueur, EnumStrategie strategie){
        try {
            partieComponent.automatiserStrategie(idPartie, idJoueur, strategie);
        } catch (PartieInexistanteException e) {
            throw new PartieInexistanteRestException(e.getMessage());
        } catch(PartieAutomatiseeException e) {
            throw new PartieAutomatiseeRestException(e.getMessage());
        }
    }
}
