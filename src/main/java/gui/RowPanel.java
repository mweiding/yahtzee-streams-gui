package gui;

import logic.DiceList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static gui.Application.DICE_SIZE;

/**
 * Represents a single row in the score card.
 * Comprises a description, score and the dice that led to the content.
 */
public class RowPanel extends JPanel {

    public static final Color BACKGROUND_COLOR = new Color(255, 213, 115);

    private static final int MAX_NUMBER_LABEL_SIZE = Application.WINDOW_WIDTH / 10;
    private static final int MAX_TEXT_LABEL_SIZE = Application.WINDOW_WIDTH / 3;

    private final DiceRowPanel dice = new DiceRowPanel();
    private final JLabel storedScoreLabel = new JLabel("0");
    private final JLabel previewScoreLabel = new JLabel("0");
    private final ScoringCategory category;
    private boolean diceSet = false;


    /**
     * Ctor to initialize the panel.
     *
     * @param category         Scoring category displayed by this panel.
     * @param playerController Player controller that receives events.
     * @param scoreCardPanel   Underlying model class.
     */
    public RowPanel(ScoringCategory category, IPlayerController playerController, ScoreCardPanel scoreCardPanel) {
        this.category = category;
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JLabel categoryLabel = new JLabel(" " + category.name()); // better use alignment; no time for that now
        categoryLabel.setMaximumSize(new Dimension(MAX_TEXT_LABEL_SIZE, DICE_SIZE));
        categoryLabel.setPreferredSize(new Dimension(MAX_TEXT_LABEL_SIZE, DICE_SIZE));
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(categoryLabel);

        storedScoreLabel.setMaximumSize(new Dimension(MAX_NUMBER_LABEL_SIZE, DICE_SIZE));
        storedScoreLabel.setPreferredSize(new Dimension(MAX_NUMBER_LABEL_SIZE, DICE_SIZE));
        storedScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(storedScoreLabel);

        previewScoreLabel.setMaximumSize(new Dimension(MAX_NUMBER_LABEL_SIZE, DICE_SIZE));
        previewScoreLabel.setPreferredSize(new Dimension(MAX_NUMBER_LABEL_SIZE, DICE_SIZE));
        previewScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        previewScoreLabel.setForeground(Color.RED);
        this.add(previewScoreLabel);

        this.add(Box.createHorizontalGlue());

        this.setBackground(BACKGROUND_COLOR);

        dice.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.add(dice);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (playerController.insertDiceToCategory(category)) {
                    scoreCardPanel.update();
                    playerController.nextRound();
                }
            }

            @Override
            public void mousePressed(MouseEvent event) {
            }

            @Override
            public void mouseReleased(MouseEvent event) {
            }

            @Override
            public void mouseEntered(MouseEvent event) {
            }

            @Override
            public void mouseExited(MouseEvent event) {
            }
        });
    }

    /**
     * Setter for dice into category represented by this panel.
     *
     * @param dice Dice values to set.
     */
    public void setDice(DiceList dice) {
        this.dice.setDice(dice);
        this.diceSet = true;
        this.storedScoreLabel.setText(this.category.getScore(dice) + "");
        this.repaint();
    }

    /**
     * Clear the preview value.
     */
    public void clearPreview() {
        previewScoreLabel.setText("");
        this.repaint();
    }

    /**
     * Shows how many points the given dice would be for the category represented
     * by this row panel object.
     *
     * @param dice Dice whose value is to be displayed.
     */
    public void showPreview(DiceList dice) {
        previewScoreLabel.setText(this.isDiceSet() ? "0" : this.category.getScore(dice) + "");
    }

    /**
     * Indicator to tell if dice are already set.
     *
     * @return true iff dice already set.
     */
    public boolean isDiceSet() {
        return this.diceSet;
    }

    /**
     * Getter for scoring category represented.
     *
     * @return Scoring category represented.
     */
    public ScoringCategory getCategory() {
        return category;
    }
}
