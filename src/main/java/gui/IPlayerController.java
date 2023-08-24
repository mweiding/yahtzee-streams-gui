package gui;

import logic.DiceList;
import logic.Scorecard;

/**
 * Interface for Player controller classes.
 */
public interface IPlayerController {

    /**
     * Roll dice according to the selected values (representing player's choice).
     *
     * @param selected indexes of dice to roll (again)
     */
    void rollDice(Boolean... selected);

    /**
     * Returns a copy of the currently valid dice on the table
     * in front of this player.
     *
     * @return Dice values.
     */
    DiceList getDice();

    /**
     * Tries to insert the currently valid dice into the scorecard at the given
     * position in terms of category.
     *
     * @param category The category to put values into.
     * @return true iff value could be inserted, i.e. no value was already set
     * into that category.
     */
    boolean insertDiceToCategory(ScoringCategory category);

    /**
     * Getter for the score card owned by this player.
     *
     * @return Scorecard (model class).
     */
    Scorecard getMyScorecard();

    /**
     * Add a dice observer to the collection of DiceObservers.
     *
     * @param observer Observer to add.
     */
    void addDiceObserver(DiceObserver observer);

    /**
     * Add a score observer to the collection of ScoreObservers.
     *
     * @param observer Observer to add.
     */
    void addScoreObserver(ScoreObserver observer);

    /**
     * Start next round: at startup and after insertion of dice into a category.
     */
    void nextRound();

}
