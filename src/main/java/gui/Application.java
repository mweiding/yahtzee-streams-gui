package gui;

import logic.Scorecard;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 1200;
    private static final int DEFAULT_FONT_SIZE = 40;
    public static final int DICE_SIZE = 80;

    private static final Font THE_FONT = new Font("Arial", Font.PLAIN, DEFAULT_FONT_SIZE);

    static {
        UIManager.getDefaults().keySet().stream()
                .filter(key -> key.toString().contains(".font"))
                .forEach(key -> UIManager.put(key, THE_FONT));
    }


    /**
     * Initializes the GUI with the panel for displaying the dices.
     *
     * @param scoreCardPanel Main panel to be displayed in Application's frame.
     */
    public Application(ScoreCardPanel scoreCardPanel) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(scoreCardPanel);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setVisible(true);
        this.setTitle("Kniffel");
        this.pack();
    }

    /**
     * @param args Unused.
     */
    public static void main(String... args) {
        Scorecard scorecard = new Scorecard();
        PlayerController playerController = new PlayerController(scorecard);
        ScorePanel scorePanel = new ScorePanel();
        playerController.addScoreObserver(scorePanel);
        ScoreCardPanel scoreCardPanel = new ScoreCardPanel(new RollDicePanel(playerController), scorePanel);
        playerController.addDiceObserver(scoreCardPanel);
        new Application(scoreCardPanel);
        playerController.nextRound();
    }

}
