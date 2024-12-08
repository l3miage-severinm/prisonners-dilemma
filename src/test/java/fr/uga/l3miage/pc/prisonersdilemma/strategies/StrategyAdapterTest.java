package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.StrategyAdapter;
import fr.uga.l3miage.pc.strategies.Utils;
import org.dillemaprisonner.ugamiage12024group2_6.strategies.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrategyAdapterTest {

    @Test
    void strategieInconnue() {
        StrategyAdapter adapter = new StrategyAdapter(Strategy.class);
        Tour[] historique = {};
        assertThrows(IllegalArgumentException.class, () -> adapter.doStrategy(historique, EnumIdJoueur.TINTIN));
    }

    @ParameterizedTest
    @MethodSource("strategyProviderG26")
    void historiqueVide(Class<? extends Strategy> strategyClass) {
        StrategyAdapter adapter = new StrategyAdapter(strategyClass);
        Tour[] historique = {};
        assertDoesNotThrow(() -> adapter.doStrategy(historique, EnumIdJoueur.TINTIN));
    }

    @ParameterizedTest
    @MethodSource("strategyProviderG26")
    void historiqueNonVide(Class<? extends Strategy> strategyClass) {
        StrategyAdapter adapter = new StrategyAdapter(strategyClass);
        Tour[] historique = {
                new Tour(true, true)
        };
        assertDoesNotThrow(() -> adapter.doStrategy(historique, EnumIdJoueur.TINTIN));
    }

    static Stream<Arguments> strategyProviderG26() {
        return Stream.of(
                Arguments.of(Adaptive.class),
                Arguments.of(AlwaysCooperate.class),
                Arguments.of(AlwaysBetray.class),
                Arguments.of(ForgivingGrudgerStrategy.class),
                Arguments.of(GradualStrategy.class),
                Arguments.of(GrimTrigger.class),
                Arguments.of(NaivePeacemaker.class),
                Arguments.of(NaiveProber.class),
                Arguments.of(Pavlov.class),
                Arguments.of(PavlovRandom.class),
                Arguments.of(RandomStrategy.class),
                Arguments.of(RemorsefulProber.class),
                Arguments.of(SuspiciousTitForTatStrategy.class),
                Arguments.of(TitForTat.class),
                Arguments.of(TitForTatRandom.class),
                Arguments.of(TitForTwoTats.class),
                Arguments.of(TitForTwoTatsRandom.class),
                Arguments.of(TruePeacemaker.class)
        );
    }
}
