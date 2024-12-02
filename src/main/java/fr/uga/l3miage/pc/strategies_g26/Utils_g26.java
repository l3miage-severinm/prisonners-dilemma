package fr.uga.l3miage.pc.strategies_g26;

import org.dillemaprisonner.ugamiage12024group2_6.strategies.Decision;

public class Utils_g26 {

    public static boolean DecisionToBoolean(Decision decision) {
        return switch (decision) {
            case COOPERATE -> true;
            case BETRAY -> false;
        };
    }
}
