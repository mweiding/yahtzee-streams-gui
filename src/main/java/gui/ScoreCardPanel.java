package gui;

import logic.DiceList;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.stream.Stream;

/**
 * Contains score card AND panel to roll dice.
 */
public class ScoreCardPanel extends JPanel implements DiceObserver {

    public static final Color BACKGROUND_COLOR = new Color(166, 116, 0);

    /**
     * The number of rows, a scorecard is composed of.
     */
    public static final int NUM_ROWS = ScoringCategory.values().length;
    /**
     * The number of dice, a row is composed of.
     */
    public static final int NUM_COLS = 5;

    private final RowPanel[] grid = new RowPanel[NUM_ROWS];
    private final RollDicePanel rollDicePanel;

    /**
     * Creates a panel for dice.
     *
     * @param rollDicePanel Reference to the roll-panel.
     * @param scorePanel    Reference to the score-displaying panel.
     */
    public ScoreCardPanel(RollDicePanel rollDicePanel, ScorePanel scorePanel) {
        this.rollDicePanel = rollDicePanel;
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        // +1 for RollDicePanel; +1 for score panel
        GridLayout layout = new GridLayout(NUM_ROWS + 1 + 1, 1);
        setLayout(layout);
        setBackground(BACKGROUND_COLOR);
        for (int row = 0; row < NUM_ROWS; row++) {
            grid[row] = new RowPanel(ScoringCategory.values()[row], rollDicePanel.getPlayerController(), this);
            grid[row].setBorder(BorderFactory.createLineBorder(Color.black));
            this.add(grid[row]);
        }
        this.add(scorePanel);
        this.add(rollDicePanel);
    }

    /**
     * Clear all the previews.
     */
    public void clearPreviews() {
        Stream.of(grid).forEach(RowPanel::clearPreview);
    }

    @Override
    public void currentValues(DiceList dice) {
        Stream.of(grid).forEach(panel -> panel.showPreview(dice));
    }

    @Override
    public void triesLeft(int currentTry) {
        // don't care for that information
    }

    /**
     * Callback-method to update the panel.
     */
    public void update() {
        for (RowPanel aGrid : grid) {
            DiceList dice = rollDicePanel
                    .getPlayerController()
                    .getMyScorecard()
                    .diceInCategory(aGrid.getCategory());
            if (dice != null) {
                aGrid.setDice(dice);
            }
        }
    }

}
