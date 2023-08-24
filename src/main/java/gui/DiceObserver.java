package gui;

import logic.DiceList;

/**
 * Observer interface for dice rolled by user.
 */
public interface DiceObserver {

    /**
     * Informs an observer of the lately rolled dice values.
     *
     * @param dice Dice values as rolled.
     */
    void currentValues(DiceList dice);

    /**
     * Informs an observer of the currently active try.
     *
     * @param currentTry Number of try (1...3).
     */
    void triesLeft(int currentTry);

}
