package gui;

import logic.RandGen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static gui.Application.DICE_SIZE;

/**
 * Panel that represents a single dice.
 */
public class SingleDicePanel extends JPanel {

    private BufferedImage img = null;
    private boolean selected = false;
    private int value = 0;

    /**
     * default ctor.
     */
    public SingleDicePanel() {
        this.setPreferredSize(new Dimension(DICE_SIZE, DICE_SIZE));
        this.setMaximumSize(new Dimension(DICE_SIZE, DICE_SIZE));
        this.setBackground(RollDicePanel.BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (img != null) {
            int size = Integer.min(this.getWidth(), this.getHeight());
            graphics.drawImage(img, 0, 0, size, size, this);
        }
    }

    /**
     * Sets the value to be displayed for the dice.
     * Note that 0 as value is legal to indicate a dice value that was
     * replaced by setting a score to 0.
     *
     * @param value Value to display: 0...6
     */
    public void setValue(int value) {
        if (value < 0 || value > RandGen.MAX_DICE_VALUE) {
            throw new IllegalArgumentException("Dice value must be between 0 (inclusive) and 6 (inclusive)");
        }
        this.value = value;
        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/" + this.value + (this.selected ? "_.png" : ".png"))));
            this.repaint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Flips image of a dice. Used to mark dice for a re-roll.
     */
    public void flipSelected() {
        this.selected = !this.selected;
        this.setValue(this.value);
    }

    /**
     * Indicates if a dice is selected (for re-roll).
     *
     * @return true iff is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * "Setter" to hard-unselect a dice.
     */
    public void setUnselected() {
        this.selected = false;
    }

    /**
     * Getter for dice value.
     *
     * @return dice value.
     */
    public int getValue() {
        return value;
    }

}
