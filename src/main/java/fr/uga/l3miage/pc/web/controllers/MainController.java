package fr.uga.l3miage.pc.web.controllers;

import fr.uga.l3miage.pc.domain.enums.EnumGroupe;
import fr.uga.l3miage.pc.domain.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.domain.enums.EnumStrategie;
import fr.uga.l3miage.pc.web.api.IMainController;
import fr.uga.l3miage.pc.web.exceptions.rest.PartieNbToursIncorrectRestException;
import fr.uga.l3miage.pc.domain.models.Tour;
import fr.uga.l3miage.pc.domain.spi.IGestionDesPartiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class MainController implements IMainController {

    @Autowired
    private IGestionDesPartiesService gestionDesPartiesService;

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
    public void automatiserStrategie(int idPartie, EnumIdJoueur idJoueur, EnumStrategie strategie, EnumGroupe groupe) {
        gestionDesPartiesService.automatiserStrategie(idPartie, idJoueur, strategie, groupe);
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
                    TimeUnit.SECONDS.sleep(5);
                    Map<String, Boolean> heartbeat = new HashMap<>();
                    heartbeat.put("heartbeat", true);
                    emitter.send(heartbeat);

                } catch (Exception e) {
                    emitter.completeWithError(e);
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
