package fr.uga.l3miage.pc.controllers;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.exceptions.rest.PartieNbToursIncorrectRestException;
import fr.uga.l3miage.pc.models.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fr.uga.l3miage.pc.endpoints.MainEndpoints;
import fr.uga.l3miage.pc.services.GestionDesPartiesService;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController implements MainEndpoints {

    @Autowired
    private GestionDesPartiesService gestionDesPartiesService;

    @Override
    public int creerPartie(int nbTours) throws PartieNbToursIncorrectRestException {
        return gestionDesPartiesService.creerPartie(nbTours);
    }

    @Override
    public Tour jouerCoup(int idPartie, EnumIdJoueur idJoueur, EnumStrategie strategie) {
        return gestionDesPartiesService.jouerCoup(idPartie, idJoueur, strategie);
    }

    @Override
    public int getLongueurHistorique(int idPartie){
        return gestionDesPartiesService.obtenirNbToursPartie(idPartie);
    }

    @Override
    public SseEmitter getHistorique(int idPartie) {

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        gestionDesPartiesService.obtenirFluxHistoriquePartie(idPartie)
                .doOnNext(historique -> {
                    try {
                        emitter.send(historique);
                    } catch (Exception e) {
                        emitter.completeWithError(e);
                    }
                })
                .subscribe();

        sendHeartbeats(emitter);
        return emitter;
    }

    private void sendHeartbeats(SseEmitter emitter) {
        new Thread(() -> {
            while (true) {
                try {
                    Map<String, Boolean> heartbeat = new HashMap<>();
                    heartbeat.put("heartbeat", true);
                    emitter.send(heartbeat);
                } catch (Exception e) {
                    emitter.completeWithError(e);
                    break;
                }
            }
        }).start();
    }
}
