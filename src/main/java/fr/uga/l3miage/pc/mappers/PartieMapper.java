package fr.uga.l3miage.pc.mappers;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Partie;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.response.PartieDTO;
import fr.uga.l3miage.pc.response.ScoreDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartieMapper {

    PartieDTO toDto(Partie partie);

    default Tour[] mapTours(List<Tour> tours) {
        return tours.toArray(new Tour[tours.size()]);
    }

    default ScoreDTO mapToScoreDTO(Partie partie) {
        try {
            int scoreTintin = partie.getScore(EnumIdJoueur.TINTIN);
            int scoreMilou = partie.getScore(EnumIdJoueur.MILOU);
            return new ScoreDTO(scoreTintin, scoreMilou);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du mappage du score", e);
        }
    }
}
