package gui;

import logic.DiceList;

@FunctionalInterface
public interface StoreCalculator {
    /**
     * An object of this class represents a single rule on how to calculate for a scoring category
     * points are to be calculated. It calculates the corresponding score value from the dice values passed as parameters
     *
     * @param diceList list of dices
     * @return int
     */
    int score(DiceList diceList);
}
