package logic;

import gui.ScoringCategory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoringCategoryTest {

    @Test
    public void testAces() {
        DiceList diceList = new DiceList(1, 1, 2, 3, 4);
        Assert.assertEquals(2, ScoringCategory.Aces.getScore(diceList));
    }

    @Test
    public void testTwos() {
        DiceList diceList = new DiceList(2, 2, 3, 4, 5);
        assertEquals(4, ScoringCategory.Twos.getScore(diceList));
    }

    @Test
    public void testThrees() {
        DiceList diceList = new DiceList(3, 3, 3, 4, 5);
        assertEquals(9, ScoringCategory.Threes.getScore(diceList));
    }

    @Test
    public void testFours() {
        DiceList diceList = new DiceList(3, 3, 4, 4, 5);
        assertEquals(8, ScoringCategory.Fours.getScore(diceList));
    }

    @Test
    public void testFives() {
        DiceList diceList = new DiceList(3, 4, 5, 5, 5);
        assertEquals(15, ScoringCategory.Fives.getScore(diceList));
    }

    @Test
    public void testSixes() {
        DiceList diceList = new DiceList(3, 3, 6, 6, 6);
        assertEquals(18, ScoringCategory.Sixes.getScore(diceList));
    }

    @Test
    public void testChance() {
        DiceList diceList = new DiceList(3, 3, 3, 4, 5);
        assertEquals(18, ScoringCategory.Chance.getScore(diceList));
    }

    @Test
    public void testThreeOfAKind() {
        DiceList diceList = new DiceList(3, 3, 3, 6, 5);
        assertEquals(20, ScoringCategory.ThreeOfAKind.getScore(diceList));
    }

    @Test
    public void testFourOfAKind() {
        DiceList diceList = new DiceList(4, 4, 4, 4, 5);
        assertEquals(21, ScoringCategory.FourOfAKind.getScore(diceList));
    }

    @Test
    public void testFullHouse() {
        DiceList diceList = new DiceList(3, 3, 3, 4, 4);
        assertEquals(25, ScoringCategory.FullHouse.getScore(diceList));
    }


    @Test
    public void testSmallStraightStartWithOne() {
        DiceList diceList = new DiceList(1, 2, 3, 4);
        assertEquals(30, ScoringCategory.Small_Straight.getScore(diceList));
    }

    @Test
    public void testSmallStraightStartWithTwo() {
        DiceList diceList = new DiceList(2, 3, 4, 5);
        assertEquals(30, ScoringCategory.Small_Straight.getScore(diceList));
    }

    @Test
    public void testSmallStraightStartWithThree() {
        DiceList diceList = new DiceList(3, 4, 5, 6);
        assertEquals(30, ScoringCategory.Small_Straight.getScore(diceList));
    }

    @Test
    public void testLargeStraightStartWithOne() {
        DiceList diceList = new DiceList(1, 2, 3, 4, 5);
        assertEquals(40, ScoringCategory.Large_Straight.getScore(diceList));
    }

    @Test
    public void testLargeStraightStartWithTwo() {
        DiceList diceList = new DiceList(2, 3, 4, 5, 6);
        assertEquals(40, ScoringCategory.Large_Straight.getScore(diceList));
    }

    @Test
    public void testYahtzee() {
        DiceList diceList = new DiceList(1, 1, 1, 1, 1);
        assertEquals(50, ScoringCategory.Yahtzee.getScore(diceList));
    }

    @Test
    public void testZeroNumbers() {
        DiceList diceList = new DiceList(3, 2, 5, 2, 1);
        assertEquals(0, ScoringCategory.Yahtzee.getScore(diceList));
        assertEquals(0, ScoringCategory.ThreeOfAKind.getScore(diceList));
        assertEquals(0, ScoringCategory.FourOfAKind.getScore(diceList));
        assertEquals(0, ScoringCategory.Small_Straight.getScore(diceList));
        assertEquals(0, ScoringCategory.Large_Straight.getScore(diceList));
        assertEquals(0, ScoringCategory.FullHouse.getScore(diceList));

    }
}