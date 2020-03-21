import javax.swing.*;

/**
 * Yahtzee main class that allows user to play the game
 * CPSC 224, Spring 2020
 * Programming assignment #3
 * No sources to cite.
 *
 * @author Jared Weyer
 * @version 3.0 2/24/20
 * @see Die
 * @see Scoring
 */
public class Yahtzee {
    /**
     * Main method for the Yahtzee program
     *
     * @param args takes in command line arguments
     */
    public static void main(String[] args) {
        //GUI
        JFrame setup = new setup("");
        setup.setVisible(true);
        setup.isResizable();
        setup.setSize(550,400);
        setup.setLocation(0,0);
    }
}

