package logic;

import gui.ScoringCategory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScorecardTest {

    @Test
    public void testScoreUpper() {
        Scorecard scorecard = new Scorecard();
        scorecard.addAsCategory(ScoringCategory.Aces, new DiceList(1, 1, 1, 4, 5));
        scorecard.addAsCategory(ScoringCategory.Twos, new DiceList(2, 2, 2, 2, 2));
        scorecard.addAsCategory(ScoringCategory.Threes, new DiceList(5, 4, 3, 3, 3));

        int expectedScore = 3 + 10 + 9;
        int actualScore = scorecard.scoreUpper();

        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testScoreLower() {
        Scorecard scorecard = new Scorecard();
        scorecard.addAsCategory(ScoringCategory.ThreeOfAKind, new DiceList(3, 3, 3, 4, 5));
        scorecard.addAsCategory(ScoringCategory.FourOfAKind, new DiceList(2, 2, 2, 2, 3));
        scorecard.addAsCategory(ScoringCategory.FullHouse, new DiceList(1, 1, 1, 2, 2));

        int expectedScore = 18 + 11 + 25;
        int actualScore = scorecard.scoreLower();

        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testBonus() {
        Scorecard scorecard = new Scorecard();
        scorecard.addAsCategory(ScoringCategory.Aces, new DiceList(1, 1, 1, 4, 5));
        scorecard.addAsCategory(ScoringCategory.Twos, new DiceList(2, 2, 2, 2, 2));
        scorecard.addAsCategory(ScoringCategory.Threes, new DiceList(3, 3, 3, 3, 3));
        scorecard.addAsCategory(ScoringCategory.Fives, new DiceList(5, 5, 5, 5, 3));
        scorecard.addAsCategory(ScoringCategory.Sixes, new DiceList(6, 6, 6, 6, 6));

        int expectedBonus = 35;
        int actualBonus = scorecard.bonus();

        assertEquals(expectedBonus, actualBonus);
    }

    @Test
    public void testTotalScore() {
        Scorecard scorecard = new Scorecard();
        scorecard.addAsCategory(ScoringCategory.Aces, new DiceList(1, 1, 1, 4, 5));
        scorecard.addAsCategory(ScoringCategory.Twos, new DiceList(2, 2, 2, 2, 2));
        scorecard.addAsCategory(ScoringCategory.Threes, new DiceList(3, 3, 3, 4, 6));
        scorecard.addAsCategory(ScoringCategory.ThreeOfAKind, new DiceList(3, 3, 3, 4, 5));
        scorecard.addAsCategory(ScoringCategory.FourOfAKind, new DiceList(2, 2, 2, 2, 3));
        scorecard.addAsCategory(ScoringCategory.FullHouse, new DiceList(1, 1, 1, 2, 2));

        int expectedTotalScore = 3 + 10 + 9 + 18 + 11 + 25;
        int actualTotalScore = scorecard.totalScore();

        assertEquals(expectedTotalScore, actualTotalScore);
    }

    @Test
    public void testDiceInCategory() {
        Scorecard scorecard = new Scorecard();
        DiceList dice = new DiceList(3, 3, 3, 3, 3);

        scorecard.addAsCategory(ScoringCategory.Threes, dice);
        DiceList result = scorecard.diceInCategory(ScoringCategory.Threes);

        assertEquals(dice, result);
    }

    @Test
    public void testIsFull() {
        Scorecard scorecard = new Scorecard();

        scorecard.addAsCategory(ScoringCategory.Aces, new DiceList(1, 1, 1, 4, 5));
        scorecard.addAsCategory(ScoringCategory.Twos, new DiceList(2, 2, 2, 2, 2));
        scorecard.addAsCategory(ScoringCategory.Threes, new DiceList(3, 3, 3, 3, 3));
        scorecard.addAsCategory(ScoringCategory.Fours, new DiceList(4, 4, 4, 4, 3));
        scorecard.addAsCategory(ScoringCategory.Fives, new DiceList(5, 5, 5, 5, 3));
        scorecard.addAsCategory(ScoringCategory.Sixes, new DiceList(6, 6, 6, 6, 6));

        scorecard.addAsCategory(ScoringCategory.ThreeOfAKind, new DiceList(3, 3, 3, 4, 5));
        scorecard.addAsCategory(ScoringCategory.FourOfAKind, new DiceList(2, 2, 2, 2, 3));
        scorecard.addAsCategory(ScoringCategory.FullHouse, new DiceList(1, 1, 1, 2, 2));
        scorecard.addAsCategory(ScoringCategory.Small_Straight, new DiceList(1, 1, 1, 2, 2));
        scorecard.addAsCategory(ScoringCategory.Large_Straight, new DiceList(1, 1, 1, 2, 2));
        scorecard.addAsCategory(ScoringCategory.Yahtzee, new DiceList(1, 1, 1, 1, 1));
        scorecard.addAsCategory(ScoringCategory.Chance, new DiceList(1, 1, 1, 2, 2));

        boolean result = scorecard.isFull();

        assertTrue(result);
    }


}