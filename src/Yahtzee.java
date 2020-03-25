import javax.swing.*;

/**
 * Yahtzee main class that allows user to play the game
 * CPSC 224, Spring 2020
 * Programming assignment #4
 * No sources to cite.
 *
 * @author Jared Weyer
 * @version 4.0 3/25/20
 */
public class Yahtzee {
    /**
     * Main method for the Yahtzee program
     *
     * @param args takes in command line arguments
     */
    public static void main(String[] args) {
        //create setup JFrame
        JFrame setup = new setup("");
        setup.setVisible(true);
        setup.isResizable();
        setup.setSize(550,400);
        setup.setLocation(0,0);
    }
}

