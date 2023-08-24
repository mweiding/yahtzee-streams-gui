package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class DiceList extends RandomGenerator {
    public static final int NUMBER_OF_DICE = 5;
    public final List<Integer> diceValues;

    public DiceList(RandomGenerator randomGenerator) {
        List<Integer> tempDiceValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tempDiceValues.add(randomGenerator.randDiceValue());
        }
        diceValues = Collections.unmodifiableList(tempDiceValues);
    }

    public DiceList(Integer... values) {
        if (values.length != 5) {
            throw new IllegalArgumentException("Invalid number of dice values. Expected: 5, Actual: " + values.length);
        }
        List<Integer> tempDiceValues = new ArrayList<>(values.length);
        tempDiceValues.addAll(Arrays.asList(values));
        diceValues = Collections.unmodifiableList(tempDiceValues);
    }


    public DiceList roll(Boolean... toRoll) {
        List<Integer> newDiceValues = new ArrayList<>(diceValues);
        for (int i = 0; i < toRoll.length; i++) {
            if (toRoll[i]) {
                newDiceValues.set(i, randDiceValue());
            }
        }
        return new DiceList(newDiceValues.toArray(new Integer[0]));
    }

    public Stream<Integer> stream() {
        return diceValues.stream();
    }

    public int get(int index) {
        if (index < 0 || index >= diceValues.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        return diceValues.get(index);
    }
}
