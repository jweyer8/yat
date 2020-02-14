import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.io.File;

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
        int numDice = 0;
        //Number of sides on the dice
        int numSides = 0;
        //Number of turns
        int maxTurns = 0;
        //Scanner to get user input on whether they would like to play and which dice they would like to keep
        Scanner kb = new Scanner(System.in);
        //String for comparing user input
        String comp = "";

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

            //Get previous game set up from text file
            try {
                Scanner inFile = new Scanner(new File("yahtzeeConfig.txt"));
                System.out.println("You are playing with " + inFile.nextInt() + " " + inFile.nextInt() + "-sided dice");
                System.out.println("You get " + inFile.nextInt() + " rolls per hand");
                inFile.close();
            } catch (FileNotFoundException e){
                System.out.println("File not found");
            }

            //Ask user if they would like to change the configuration of the game
            String config;
            System.out.println("Enter 'y' if you would like to change the configuration");
            config = kb.nextLine();

            if(config.equals("y")) {
                //Get number of sides to the dice that the user wants
                System.out.println("Enter the number of sides on each die");
                numSides = kb.nextInt();

                //Get number of dice the user wants
                System.out.println("Enter the number dice in play");
                numDice = kb.nextInt();

                //Get number of turns that the user wants
                System.out.println("Enter the number of turns per hand");
                maxTurns = kb.nextInt();
                kb.nextLine();
            }

            //Use previous game setup
            else{
                try {
                    Scanner inFile = new Scanner(new File("yahtzeeConfig.txt"));
                    numSides = inFile.nextInt();
                    numDice = inFile.nextInt();
                    maxTurns = inFile.nextInt();
                    inFile.close();
                } catch (FileNotFoundException e){
                    System.out.println("File not found");
                }
            }

            //change the yahtzee config file to the new configuration
            try {
                PrintStream outFile = new PrintStream(new File("yahtzeeConfig.txt"));
                outFile.println(numSides);
                outFile.println(numDice);
                outFile.println(maxTurns);
                outFile.close();
            }
            catch (FileNotFoundException e){
                System.out.println("unable to open file");
            }

            //Set comparison ArrayList
            for(int i = 0; i<numDice; i++){
                comp += 'y';
            }

            //Contains the die objects
            Hand hand = new Hand(numSides,numDice);

            while (turn <= maxTurns && !(hand.getUserStr().equals(comp))){
                hand.rollDice();
                hand.printHand();

                //if not the last roll of the hand prompt the user for dice to keep
                if (turn <= maxTurns - 1) {
                    boolean correctInput = false;
                    String in = "";

                    while(correctInput == false) {
                        System.out.println("enter dice to keep (y or n)");
                        in = kb.nextLine();
                        if(in.length() == numDice){correctInput = true;}
                        else{System.out.println("Invalid input");}
                    }

                    hand.setUserStr(in);
                    hand.rollWhich();
                }
                turn++;
            }

            //Get the score for the round and output the scores
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
            comp = "";
        }
    }
}

