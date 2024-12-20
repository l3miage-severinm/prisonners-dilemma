package fr.uga.l3miage.pc.endpoints;

import fr.uga.l3miage.pc.enums.EnumGroupe;
import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumStrategie;
import fr.uga.l3miage.pc.models.Tour;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/dilemne-du-prisonnier")
@CrossOrigin("http://localhost")
public interface MainEndpoints {

    @Operation(description = "Créer une partie (2 joueurs) de dilemne du prisonnier")
    @ApiResponse(responseCode = "200", description = "Partie créée")
    @ApiResponse(responseCode = "400", description = "Nombre de tours incorrect")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/creer-partie/{nbTours}")
    int creerPartie(@PathVariable(name = "nbTours") int nbTours);

    @Operation(description = "Jouer un coup pour une partie donnée")
    @ApiResponse(responseCode = "200",description = "Le coup a été joué")
    @ApiResponse(responseCode = "400", description = "Partie inconnue ou coup déjà joué")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idPartie}/jouer/{idJoueur}/{strategie}")
    Tour jouerCoup(
            @PathVariable(name = "idPartie") int idPartie,
            @PathVariable(name = "idJoueur") EnumIdJoueur idJoueur,
            @PathVariable(name = "strategie") EnumStrategie strategie);

    @Operation(description = "Récupérer la longueur d'une partie")
    @ApiResponse(responseCode = "200",description = "Le nombre de coups dans la partie")
    @ApiResponse(responseCode = "404", description = "Partie inconnue")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idPartie}/longueur")
    int getLongueurHistorique(@PathVariable(name = "idPartie") int idPartie);

    @Operation(description = "Écouter les mises à jour en temps réel de l'historique de la partie")
    @ApiResponse(responseCode = "200", description = "Mises à jour SSE envoyées")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{idPartie}/historique", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    SseEmitter getHistorique(@PathVariable(name = "idPartie") int idPartie);

    @Operation(description = "Activer une stratégie automatique pour une partie et un joueur donné")
    @ApiResponse(responseCode = "200", description = "OK")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idPartie}/automatiser/{idJoueur}/{strategie}/groupe/{groupe}")
    void automatiserStrategie(@PathVariable(name = "idPartie") int idPartie,
                              @PathVariable(name = "idJoueur") EnumIdJoueur idJoueur,
                              @PathVariable(name = "strategie") EnumStrategie strategie,
                                @PathVariable(name = "groupe") EnumGroupe groupe);
}