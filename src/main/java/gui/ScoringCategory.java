package gui;

import logic.DiceList;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ScoringCategory {
    Aces(diceList -> diceList.stream().filter(value -> value == 1).mapToInt(Integer::intValue).sum()),
    Twos(diceList -> diceList.stream().filter(value -> value == 2).mapToInt(Integer::intValue).sum()),
    Threes(diceList -> diceList.stream().filter(value -> value == 3).mapToInt(Integer::intValue).sum()),
    Fours(diceList -> diceList.stream().filter(value -> value == 4).mapToInt(Integer::intValue).sum()),
    Fives(diceList -> diceList.stream().filter(value -> value == 5).mapToInt(Integer::intValue).sum()),
    Sixes(diceList -> diceList.stream().filter(value -> value == 6).mapToInt(Integer::intValue).sum()),
    Chance(diceList -> diceList.stream().mapToInt(Integer::intValue).sum()),

    ThreeOfAKind(diceList -> {
        Map<Integer, Integer> counts = diceList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
        Optional<Map.Entry<Integer, Integer>> entry = counts.entrySet().stream()
                .filter(e -> e.getValue() == 3)
                .findFirst();
        if (entry.isPresent()) {
            return diceList.stream().mapToInt(Integer::intValue).sum();
        }
        return 0;
    }),

    FourOfAKind(diceList -> {
        Map<Integer, Integer> counts = diceList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
        Optional<Map.Entry<Integer, Integer>> entry = counts.entrySet().stream()
                .filter(e -> e.getValue() == 4)
                .findFirst();
        if (entry.isPresent()) {
            return diceList.stream().mapToInt(Integer::intValue).sum();
        }
        return 0;
    }),

    FullHouse(diceValues -> {
        List<Integer> distinctValues = diceValues.stream().distinct().toList();
        if (distinctValues.size() == 2) {
            long count1 = diceValues.stream().filter(value -> Objects.equals(value, distinctValues.get(0))).count();
            long count2 = diceValues.stream().filter(value -> Objects.equals(value, distinctValues.get(1))).count();
            if ((count1 == 2 && count2 == 3) || (count1 == 3 && count2 == 2)) {
                return Constants.FULL_HOUSE_SCORE;
            }
        }
        return 0;
    }),

    Small_Straight(diceValues -> {
        List<Integer> distinctValues = diceValues.stream().distinct().sorted().toList();
        if (distinctValues.size() == 4) {
            List<Integer> straightPattern1 = Arrays.asList(1, 2, 3, 4);
            List<Integer> straightPattern2 = Arrays.asList(2, 3, 4, 5);
            List<Integer> straightPattern3 = Arrays.asList(3, 4, 5, 6);
            if (distinctValues.containsAll(straightPattern1)
                    || distinctValues.containsAll(straightPattern2)
                    || distinctValues.containsAll(straightPattern3)) {
                return Constants.SMALL_STRAIGHT_SCORE;
            }
        }
        return 0;
    }),

    Large_Straight(diceValues -> {
        List<Integer> distinctValues = diceValues.stream().distinct().toList();
        if (distinctValues.size() == 5 && distinctValues.contains(3)) {
            return Constants.LARGE_STRAIGHT_SCORE;
        }
        return 0;
    }),

    Yahtzee(diceValues -> {
        List<Integer> distinctValues = diceValues.stream().distinct().toList();
        if (distinctValues.size() == 1) {
            return Constants.YAHTZEE_SCORE;
        }
        return 0;
    });

    private static class Constants {
        public static final int YAHTZEE_SCORE = 50;
        public static final int SMALL_STRAIGHT_SCORE = 30;
        public static final int LARGE_STRAIGHT_SCORE = 40;
        public static final int FULL_HOUSE_SCORE = 25;

    }

    private final StoreCalculator storeCalculator;

    ScoringCategory(StoreCalculator storeCalculator) {
        this.storeCalculator = storeCalculator;
    }

    public int getScore(DiceList diceList) {
        return storeCalculator.score(diceList);
    }
}

