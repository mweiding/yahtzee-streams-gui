package logic;

import gui.ScoringCategory;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;

public class Scorecard {
    EnumMap<ScoringCategory, DiceList> scoringCategoryDiceListEnumMap;

    public Scorecard() {
        scoringCategoryDiceListEnumMap = new EnumMap<>(ScoringCategory.class);
        for (ScoringCategory category : ScoringCategory.values()) {
            scoringCategoryDiceListEnumMap.put(category, null);
        }
    }

    private boolean isUpperCategory(ScoringCategory category) {
        return category == ScoringCategory.Aces ||
                category == ScoringCategory.Twos ||
                category == ScoringCategory.Threes ||
                category == ScoringCategory.Fours ||
                category == ScoringCategory.Fives ||
                category == ScoringCategory.Sixes;
    }

    private int getScoreForCategory(ScoringCategory category) {
        DiceList diceList = scoringCategoryDiceListEnumMap.get(category);
        return diceList != null ? category.getScore(diceList) : 0;
    }

    public int scoreUpper() {
        return Arrays.stream(ScoringCategory.values())
                .filter(this::isUpperCategory)
                .mapToInt(this::getScoreForCategory)
                .sum();
    }

    public int scoreLower() {
        return Arrays.stream(ScoringCategory.values())
                .filter(category -> !isUpperCategory(category))
                .mapToInt(this::getScoreForCategory)
                .sum();
    }

    public int bonus() {
        int bonus = 35;
        if (scoreUpper() >= 63) {
            return bonus;
        }
        return 0;
    }

    public int totalScore() {
        return scoreUpper() + scoreLower() + bonus();
    }

    public boolean addAsCategory(ScoringCategory category, DiceList dice) {
        if (scoringCategoryDiceListEnumMap.get(category) == null) {
            scoringCategoryDiceListEnumMap.put(category, dice);
            return true;
        }
        return false;
    }

    public DiceList diceInCategory(ScoringCategory category) {
        return scoringCategoryDiceListEnumMap.get(category);
    }

    public boolean isFull() {
        return scoringCategoryDiceListEnumMap.values().stream().allMatch(Objects::nonNull);
    }
}

