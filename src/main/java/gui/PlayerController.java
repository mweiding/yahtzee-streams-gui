package gui;

import logic.DiceList;
import logic.RandomGenerator;
import logic.Scorecard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller implementation that represents a single player.
 */

public class PlayerController implements IPlayerController {

    public static final int MAX_TRIES = 3;

    private final RandomGenerator randomGenerator = new RandomGenerator();
    private DiceList dice;
    private final Scorecard scorecard;
    private final List<DiceObserver> diceObservers = new ArrayList<>();
    private final List<ScoreObserver> scoreObservers = new ArrayList<>();

    private int currentTry = 1;

    /**
     * Ctor.
     *
     * @param scorecard Underlying scorecard (model)
     */
    public PlayerController(Scorecard scorecard) {
        dice = new DiceList(randomGenerator);
        this.scorecard = scorecard;
    }

    @Override
    public void rollDice(Boolean... selected) {
        if (currentTry >= MAX_TRIES) {
            return;
        }
        if (Arrays.stream(selected).anyMatch(isSelected -> isSelected)) {
            dice = dice.roll(selected);
            currentTry++;
        }
        notifyDiceObservers();
    }

    @Override
    public void nextRound() {
        this.currentTry = 1;
        this.dice = new DiceList(randomGenerator);
        notifyDiceObservers();
    }

    @Override
    public DiceList getDice() {
        return this.dice;
    }

    @Override
    public boolean insertDiceToCategory(ScoringCategory category) {
        if (!this.scorecard.addAsCategory(category, this.dice)) {
            return false;
        }
        scoreObservers.forEach(scoreObserver -> {
            scoreObserver.scoreChanged(this.scorecard);
        });
        return true;
    }

    @Override
    public Scorecard getMyScorecard() {
        return scorecard;
    }

    @Override
    public void addDiceObserver(DiceObserver observer) {
        this.diceObservers.add(observer);
        observer.triesLeft(MAX_TRIES - currentTry);
        observer.currentValues(dice);
    }

    @Override
    public void addScoreObserver(ScoreObserver observer) {
        this.scoreObservers.add(observer);
    }

    /**
     * Helper to notify all diceObservers.
     */
    private void notifyDiceObservers() {
        diceObservers.forEach(diceObserver -> {
            diceObserver.triesLeft(MAX_TRIES - currentTry);
            diceObserver.currentValues(dice);
        });
    }

}
