package logic;

import gui.ScoringCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterTests {

    @Parameterized.Parameter
    public ScoringCategory category;
    @Parameterized.Parameter(1)
    public DiceList diceList;
    @Parameterized.Parameter(2)
    public int expectedScore;

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {ScoringCategory.Aces, new DiceList(1, 1, 1, 1, 1), 5},
                {ScoringCategory.Aces, new DiceList(2, 3, 4, 5, 6), 0}
        });
    }

    @Test
    public void testScores() {
        // Act
        int score = category.getScore(diceList);

        // Assert
        assertEquals(expectedScore, score);
    }
}
