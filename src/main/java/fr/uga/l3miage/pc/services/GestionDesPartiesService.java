package fr.uga.l3miage.pc.services;

import fr.uga.l3miage.pc.components.PartieComponent;
import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.exceptions.rest.JoueurADejaJoueRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieInexistanteRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieNbToursIncorrectRestException;
import fr.uga.l3miage.pc.exceptions.rest.PartieTermineeRestException;
import fr.uga.l3miage.pc.exceptions.technical.JoueurADejaJoueException;
import fr.uga.l3miage.pc.exceptions.technical.PartieInexistanteException;
import fr.uga.l3miage.pc.exceptions.technical.PartieNbToursIncorrectException;
import fr.uga.l3miage.pc.exceptions.technical.PartieTermineeException;
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
    private final Map<Integer, Sinks.Many<List<Tour>>> partieSinks = new ConcurrentHashMap<>();

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
            List<Tour> historique = obtenirHistoriquePartie(numeroPartie);
            partieSinks.computeIfAbsent(numeroPartie, k -> Sinks.many().replay().latest()).tryEmitNext(historique);
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

    public List<Tour> obtenirHistoriquePartie(int numeroPartie) {
        try {
            return partieComponent.getPartieByNumero(numeroPartie).getTours();
        } catch (PartieInexistanteException e) {
            throw new PartieInexistanteRestException(e.getMessage());
        }
    }

    public Flux<List<Tour>> obtenirFluxHistoriquePartie(int numeroPartie) {
        return partieSinks.computeIfAbsent(numeroPartie, k -> Sinks.many().replay().latest()).asFlux();
    }
}
