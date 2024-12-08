package fr.uga.l3miage.pc.strategies;

import fr.uga.l3miage.pc.enums.EnumIdJoueur;
import fr.uga.l3miage.pc.exceptions.rest.JoueurAPasJoueRestException;
import fr.uga.l3miage.pc.exceptions.rest.StrategyAdaptingRestException;
import fr.uga.l3miage.pc.exceptions.technical.JoueurAPasJoueException;
import fr.uga.l3miage.pc.models.Tour;
import org.dillemaprisonner.ugamiage12024group2_6.strategies.Decision;
import org.dillemaprisonner.ugamiage12024group2_6.strategies.Strategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class StrategyAdapter implements SimpleStrategy {

    private final Class<? extends Strategy> strategyClass;

    public StrategyAdapter(Class<? extends Strategy> strategyClassG26) {
        this.strategyClass = strategyClassG26;
    }

    @Override
    public boolean doStrategy(Tour[] historique, EnumIdJoueur idJoueur) {
        EnumIdJoueur idAdversaire = idJoueur == EnumIdJoueur.TINTIN ? EnumIdJoueur.MILOU : EnumIdJoueur.TINTIN;
        Strategy strategy = switch (strategyClass.getSimpleName()) {
            case "AlwaysCooperate", "AlwaysBetray", "RandomStrategy" ->
                    instantiate();
            case "ForgivingGrudgerStrategy", "GradualStrategy", "NaivePeacemaker", "NaiveProber", "SuspiciousTitForTatStrategy", "TitForTat", "TitForTatRandom" ->
                    instantiate(toursGetLastDecisionOpponent(historique, idJoueur));
            case "Adaptive" ->
                    instantiate(toursToListScore(historique, idJoueur));
            case "GrimTrigger" ->
                    instantiate(toursToListDecision(historique, idAdversaire));
            case "Pavlov", "PavlovRandom" ->
                    instantiate(historique.length == 0 ? 0 : toursToListScore(historique, idJoueur).get(historique.length - 1), toursGetLastDecision(historique, idJoueur));
            case "RemorsefulProber" ->
                    instantiate(toursGetLastDecisionOpponent(historique, idJoueur), toursGetLastDecision(historique, idJoueur));
            case "TitForTwoTats", "TitForTwoTatsRandom", "TruePeacemaker" ->
                    instantiate(historique.length < 2 ? Decision.COOPERATE : toursToListDecision(historique, idAdversaire).get(historique.length - 2), toursGetLastDecisionOpponent(historique, idJoueur));
            default ->
                    throw new IllegalArgumentException("Unsupported strategy class: " + strategyClass.getSimpleName());
        };
        return decisionToBoolean(strategy.nextMove());
    }

    private Strategy instantiate(Object... args) {
        try {
            Class<?>[] parameterTypes = Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == Integer.class)
                    parameterTypes[i] = int.class;
            }
            Constructor<? extends Strategy> constructor = strategyClass.getConstructor(parameterTypes);
            return constructor.newInstance(args);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new StrategyAdaptingRestException("Failed to instantiate strategy: " + strategyClass.getName());
        }
    }

    private static boolean decisionToBoolean(Decision decision) {
        return switch (decision) {
            case COOPERATE -> true;
            case BETRAY -> false;
        };
    }

    private static Decision booleanToDecision(boolean coopere) {
        return coopere ? Decision.COOPERATE : Decision.BETRAY;
    }

    private static Decision tourToDecision(Tour tour, EnumIdJoueur joueur) {
        boolean coopere;
        try {
            coopere = tour.getCoopere(joueur);
        } catch (JoueurAPasJoueException e) {
            throw new JoueurAPasJoueRestException(e.getMessage());
        }
        return booleanToDecision(coopere);
    }

    private static ArrayList<Decision> toursToListDecision(Tour[] tours, EnumIdJoueur joueur) {
        ArrayList<Decision> decisions = new ArrayList<>();
        for (Tour tour : tours)
            decisions.add(tourToDecision(tour, joueur));
        return decisions;
    }

    private static Decision toursGetLastDecision(Tour[] tours, EnumIdJoueur joueur) {
        if (tours.length == 0)
            return Decision.COOPERATE;
        return tourToDecision(tours[tours.length - 1], joueur);
    }

    private static Decision toursGetLastDecisionOpponent(Tour[] tours, EnumIdJoueur joueur) {
        return toursGetLastDecision(tours, joueur == EnumIdJoueur.TINTIN ? EnumIdJoueur.MILOU : EnumIdJoueur.TINTIN);
    }

    private static ArrayList<Integer> toursToListScore(Tour[] tours, EnumIdJoueur joueur) {
        ArrayList<Integer> scores = new ArrayList<>();
        for (Tour tour : tours) {
            try {
                scores.add(tour.getScore(joueur));
            } catch (JoueurAPasJoueException e) {
                throw new JoueurAPasJoueRestException(e.getMessage());
            }
        }
        return scores;
    }
}
