package fr.uga.l3miage.pc.components;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.enums.EnumTechniquesAuto;
import fr.uga.l3miage.pc.exceptions.technical.JoueurADejaJoueException;
import fr.uga.l3miage.pc.exceptions.technical.PartieInexistanteException;
import fr.uga.l3miage.pc.exceptions.technical.PartieNbToursIncorrectException;
import fr.uga.l3miage.pc.exceptions.technical.PartieTermineeException;
import fr.uga.l3miage.pc.models.Partie;
import fr.uga.l3miage.pc.models.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PartieComponent {

    private final Set<Partie> partiesEnCours;
    private int numeroPartieSuivante = 0;

    public int creerPartie(int nbTours) throws PartieNbToursIncorrectException {

        if (nbTours < 1)
            throw new PartieNbToursIncorrectException("Le nombre de tours d'une partie doit être strictement supérieur à 0");

        List<Tour> tours = new ArrayList<>();
        tours.add(new Tour());
        Partie p = new Partie(numeroPartieSuivante++, nbTours, tours);
        partiesEnCours.add(p);
        return p.getNumero();
    }

    public Tour jouerCoup(int numeroPartie, EnumIdJoueur idJoueur, boolean coopere)
            throws PartieInexistanteException, JoueurADejaJoueException, PartieTermineeException {

        Partie partie = getPartieByNumero(numeroPartie);

        if (partie.estFinie())
            throw new PartieTermineeException("La partie n°" + numeroPartie + " est terminée");

        List<Tour> tours = partie.getTours();
        Tour tourActuel = tours.get(tours.size() - 1);

        if (idJoueur == EnumIdJoueur.TINTIN) {
            if (tourActuel.joueur1AJoue())
                throw new JoueurADejaJoueException("Joueur 1 a déjà joué");
            tourActuel.setJoueur1Coopere(coopere);
        }
        else {
            if (tourActuel.joueur2AJoue())
                throw new JoueurADejaJoueException("Joueur 2 a déjà joué");
            tourActuel.setJoueur2Coopere(coopere);
        }

        if (!partie.estFinie() && tourActuel.estFini())
            tours.add(new Tour());

        return tours.get(tours.size() - 1);
    }

    public Partie getPartieByNumero(int numero) throws  PartieInexistanteException {
        Optional<Partie> partieOptional = partiesEnCours.stream().filter(p -> p.getNumero() == numero).findFirst();

        if (partieOptional.isEmpty())
            throw new PartieInexistanteException("Partie n°" + numero + " inexistante");

        return partieOptional.get();
    }

    public void deletePartie(int numero) throws PartieInexistanteException {
        partiesEnCours.remove(getPartieByNumero(numero));
    }

    public void clearPartiesEnCours() {
        partiesEnCours.clear();
    }

    public void jeuAutomatique(EnumTechniquesAuto technique) {
        // TODO implement here
    }
}
