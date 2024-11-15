package fr.uga.l3miage.pc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PartieDTO {
    private final TourDTO[] historique;
    private final ScoreDTO score;
}
