package fr.uga.l3miage.pc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ScoreDTO {
    private final int scoreTintin;
    private final int scoreMilou;
}

