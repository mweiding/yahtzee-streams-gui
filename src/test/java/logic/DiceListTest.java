package logic;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class DiceListTest {

    @Test
    public void testConstructorWithValues() {
        DiceList diceList = new DiceList(1, 2, 3, 4, 5);
        List<Integer> expectedValues = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expectedValues, diceList.diceValues);
    }

    @Test
    public void testConstructorWithRandomGenerator() {
        RandomGenerator randomGenerator = new RandomGenerator();
        DiceList diceList = new DiceList(randomGenerator);
        assertNotNull(diceList.diceValues);
        assertEquals(DiceList.NUMBER_OF_DICE, diceList.diceValues.size());
    }

    @Test
    public void testRoll() {
        DiceList diceList = new DiceList(1, 2, 3, 4, 5);
        DiceList rolledDiceList = diceList.roll(true, false, true, false, true);
        assertEquals(5, rolledDiceList.diceValues.size());
        assertNotEquals(diceList.diceValues, rolledDiceList.diceValues);
    }

    @Test
    public void streamTest() {
        DiceList diceList = new DiceList(1, 2, 3, 4, 5);
        Stream<Integer> result = diceList.stream();
        assertNotNull(result);
    }

    @Test
    public void testGet() {
        DiceList diceList = new DiceList(1, 2, 3, 4, 5);
        assertEquals(1, diceList.get(0));
        assertEquals(2, diceList.get(1));
        assertEquals(3, diceList.get(2));
        assertEquals(4, diceList.get(3));
        assertEquals(5, diceList.get(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetException() {
        DiceList diceList = new DiceList(1, 2, 3, 4, 5);
        diceList.get(-1);
        DiceList diceListSecond = new DiceList(1, 2, 3, 4, 5);
        diceListSecond.get(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException() {
        DiceList diceList = new DiceList(1, 2, 3, 4);
    }


}


