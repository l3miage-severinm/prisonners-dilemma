package fr.uga.l3miage.pc.endpoints;

import fr.uga.l3miage.pc.models.Tour;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "", description = "")
@RestController
@RequestMapping("/api/dilemne-du-prisonnier")
public interface MainEndpoints {

    @Operation(description = "Créer une partie (2 joueurs) de dilemne du prisonnier")
    @ApiResponse(responseCode = "200",description = "")
    @ApiResponse(responseCode = "404", description = "")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/creer-partie/{nbTours}")
    int creerPartie(@PathVariable(name = "nbTours") int nbTours);

    @Operation(description = "Jouer un coup pour une partie donnée")
    @ApiResponse(responseCode = "200",description = "Le coup a été joué")
    @ApiResponse(responseCode = "404", description = "Partie inconnue")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idPartie}/jouer/{idJoueur}/{coopere}")
    Tour[] jouerCoup(@PathVariable(name = "idPartie") int idPartie, @PathVariable(name = "idJoueur") int idJoueur, @PathVariable(name = "coopere") boolean coopere);

    @Operation(description = "Récupérer l'historique d'une partie")
    @ApiResponse(responseCode = "200",description = "L'historique des coups de la partie")
    @ApiResponse(responseCode = "404", description = "Partie inconnue")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idPartie}/historique")
    Tour[] getHistorique(@PathVariable(name = "idPartie") int idPartie);

    @Operation(description = "Récupérer la longueur d'une partie")
    @ApiResponse(responseCode = "200",description = "Le nombre de coups dans la partie")
    @ApiResponse(responseCode = "404", description = "Partie inconnue")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idPartie}/longueur")
    int getLongueurHistorique(@PathVariable(name = "idPartie") int idPartie);
}