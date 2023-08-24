package gui;

import logic.DiceList;
import logic.Scorecard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PlayerControllerTest {

    private static final class MockDiceObserver implements DiceObserver {

        int currentTry;

        @Override
        public void currentValues(DiceList dice) {
        }

        @Override
        public void triesLeft(int currentTry) {
            this.currentTry = currentTry;
        }
    }

    private final MockDiceObserver observer = new MockDiceObserver();

    @Test
    public void testMaxThreeRounds() {
        Scorecard scorecard = new Scorecard();
        PlayerController controller = new PlayerController(scorecard);
        controller.addDiceObserver(observer);

        assertEquals(2, observer.currentTry);
        controller.rollDice(true, true, true, true, true);
        assertEquals(1, observer.currentTry);
        controller.rollDice(false, false, false, false, false);
        assertEquals(1, observer.currentTry);
        controller.rollDice(true, true, true, true, true);
        assertEquals(0, observer.currentTry);
        controller.rollDice(true, true, true, true, true);
        assertEquals(0, observer.currentTry);
    }

}
