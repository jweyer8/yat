import java.util.*;

/**
 * Yahtzee main class that allows user to play the game
 * CPSC 224, Spring 2020
 * Programming assignment #1
 * No sources to cite.
 *
 * @author Jared Weyer
 * @version 1.0 1/30/20
 * @see Die
 * @see Scoring
 */
public class Yahtzee {
    /**
     * Main method for the Yahtzee program
     *
     * @param args takes in command line arguments
     */
    public static void main(String[] args){
        //Number of dice in the hand
        int NUM_DICE = 5;
        //Number of sides on the dice
        final int NUM_SIDES = 6;
        //Scanner to get user input on whether they would like to play and which dice they would like to keep
        Scanner kb = new Scanner(System.in);

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("|                                         |");
        System.out.println("|          Welcome to Yahtzee!            |");
        System.out.println("|                                         |");
        System.out.println("|           Enter 'y' to play             |");
        System.out.println("|                                         |");
        System.out.println("|                                         |");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        //Determines if the user wants to play
        String playAgain = kb.nextLine();
        System.out.flush();

        while (playAgain.equals("y")) {
            //Keeps track of how many rolls they player has used
            int turn = 1;
            //Contains the die objects
            Hand hand = new Hand(NUM_SIDES,NUM_DICE,"nnnnn");

            while (turn < 4 && !(hand.getUserStr().equals("yyyyy"))){
                hand.rollDice();
                hand.printHand();

                //if not the last roll of the hand prompt the user for dice to keep
                if (turn < 3) {
                    System.out.println("enter dice to keep (y or n) ");
                    hand.setUserStr(kb.nextLine());
                    hand.rollWhich();
                }
                turn++;
            }

            //Get the score for the round and output the scores
            System.out.print("Here are your sorted dice: ");
            hand.printSorted();
            Scoring score = new Scoring(hand);
            System.out.println();
            score.getScore();
            System.out.println();
            System.out.println();
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("|                                         |");
            System.out.println("|               Game Over!                |");
            System.out.println("|                                         |");
            System.out.println("|        Enter 'y' to play again          |");
            System.out.println("|                                         |");
            System.out.println("|                                         |");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            playAgain = kb.nextLine();
            System.out.flush();

            //reset for next round
            hand = null;
        }
    }
}

