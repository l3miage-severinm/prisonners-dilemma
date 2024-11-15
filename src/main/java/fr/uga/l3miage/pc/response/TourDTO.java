package fr.uga.l3miage.pc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TourDTO {
    private final boolean joueur1Coopere;
    private final boolean joueur2Coopere;
}
