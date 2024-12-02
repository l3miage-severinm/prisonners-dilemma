package fr.uga.l3miage.pc.strategies_g26;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.models.Tour;
import fr.uga.l3miage.pc.strategies.SimpleStrategy;
import org.apache.commons.lang3.NotImplementedException;
import org.dillemaprisonner.ugamiage12024group2_6.strategies.AlwaysCooperate;
import org.dillemaprisonner.ugamiage12024group2_6.strategies.Strategy;

public class Cooperer_g26 implements SimpleStrategy {

    private final Strategy strategy_g26 = new AlwaysCooperate();

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        return Utils_g26.DecisionToBoolean(strategy_g26.nextMove());
    }
}
