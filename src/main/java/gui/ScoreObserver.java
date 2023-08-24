package gui;

import logic.Scorecard;

/**
 * Observer interface for notification of components interested in score changes.
 */
@FunctionalInterface
public interface ScoreObserver {

    /**
     * To be called when score changed.
     *
     * @param scoreCard Player's card containing scoring-relevant information.
     */
    void scoreChanged(Scorecard scoreCard);

}
