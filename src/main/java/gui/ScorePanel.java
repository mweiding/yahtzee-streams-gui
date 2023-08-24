package gui;

import logic.Scorecard;

import javax.swing.*;
import java.awt.*;

/**
 * Panel to display current scores: upper, lower, bonus, total.
 */
public class ScorePanel extends JPanel implements ScoreObserver {

    private static final String UPPER_TEXT = "Upper: ";
    private static final String BONUS_TEXT = "Bonus: ";
    private static final String LOWER_TEXT = "Lower: ";
    private static final String TOTAL_TEXT = "Total: ";

    private final JLabel upperLabel = new JLabel(UPPER_TEXT);
    private final JLabel bonusLabel = new JLabel(BONUS_TEXT);
    private final JLabel lowerLabel = new JLabel(LOWER_TEXT);
    private final JLabel totalLabel = new JLabel(TOTAL_TEXT);

    /**
     * ctor to initialize everything.
     */
    public ScorePanel() {
        this.setLayout(new GridLayout(1, 4));
        this.setBackground(RollDicePanel.BACKGROUND_COLOR);
        this.add(upperLabel);
        this.add(bonusLabel);
        this.add(lowerLabel);
        this.add(totalLabel);
    }

    @Override
    public void scoreChanged(Scorecard scoreCard) {
        this.totalLabel.setText(TOTAL_TEXT + scoreCard.totalScore());
        this.bonusLabel.setText(BONUS_TEXT + scoreCard.bonus());
        this.upperLabel.setText(UPPER_TEXT + scoreCard.scoreUpper());
        this.lowerLabel.setText(LOWER_TEXT + scoreCard.scoreLower());
        this.repaint();
    }
}
