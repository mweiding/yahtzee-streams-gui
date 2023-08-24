package gui;

import logic.DiceList;

import javax.swing.*;
import java.awt.*;

import static gui.Application.DICE_SIZE;

/**
 * represents a row of 5 dice.
 */
public class DiceRowPanel extends JPanel {

    public static final int NUM_COLS = 5;
    private final SingleDicePanel[] grid = new SingleDicePanel[NUM_COLS];

    /**
     * Ctor to initialize the panel.
     */
    public DiceRowPanel() {
        /* border to be drawn around a dice */
        GridLayout layout = new GridLayout(1, NUM_COLS);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(NUM_COLS * DICE_SIZE, DICE_SIZE));
        this.setMaximumSize(new Dimension(NUM_COLS * DICE_SIZE, DICE_SIZE));
        for (int col = 0; col < NUM_COLS; col++) {
            grid[col] = new SingleDicePanel();
            grid[col].setBorder(BorderFactory.createLineBorder(Color.black));
            grid[col].setBackground(ScoreCardPanel.BACKGROUND_COLOR);
            this.add(grid[col]);
        }
    }

    /**
     * Setter to set dice values for the panel.
     *
     * @param dice Values to set (must be 5values; not checked).
     */
    public void setDice(DiceList dice) {
        for (int index = 0; index < NUM_COLS; index++) {
            grid[index].setValue(dice.get(index));
        }
        this.repaint();
    }

}
