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
        //Number of rounds in a game
        int numRounds = 1;
        //Number of dice in the hand
        int numDice = 0;
        //Number of players in game
        int numPlayers = 0;
        //Number of sides on the dice
        int numSides = 0;
        //Number of turns
        int maxTurns = 0;
        //Scanner to get user input on whether they would like to play and which dice they would like to keep
        Scanner kb = new Scanner(System.in);
        //String for comparing user input
        String comp = "";

        System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
        System.out.println("|                           |");
        System.out.println("|    Welcome to Yahtzee!    |");
        System.out.println("|                           |");
        System.out.println("|     Enter 'y' to play     |");
        System.out.println("|                           |");
        System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");

        //Determines if the user wants to play
        String playAgain = kb.nextLine();
        System.out.flush();

        while (playAgain.equals("y")) {
            //Get previous game set up from text file
            try {
                Scanner inFile = new Scanner(new File("yahtzeeConfig.txt"));
                System.out.println("You are playing with " + inFile.nextInt() + " players");
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
                //Get number of player in the game
                System.out.println("Enter the number of people playing in the game");
                numPlayers = kb.nextInt();

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
                    numPlayers = inFile.nextInt();
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
                outFile.println(numPlayers);
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

            //Holds the player objects
            ArrayList<Player> players = new ArrayList<>();

            for(int i = 0; i < numPlayers; i++){
                players.add(new Player(numSides,numDice));
            }

            for(int round = 0; round < numRounds; round++) {
                for (int player = 0; player < numPlayers; player++) {
                    //Contains the die objects
                    Hand hand = new Hand(numSides,numDice);
                    System.out.println();
                    System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
                    System.out.println("|      PLAYER " + (player + 1) + "'S TURN      |");
                    System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
                    System.out.println();
                    for (int turn = 1; turn <= maxTurns; turn++) {
                        if(hand.getUserStr().equals(comp))break;
                        hand.printHand();

                        //if not the last roll of the hand prompt the user for dice to keep
                        if (turn < maxTurns) {
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
                        hand.rollDice();
                    }


                    //Get the score for the round and output the scores
                    hand.printSorted();
                    Scoring score = new Scoring(hand,players.get(player));
                    System.out.println();
                    score.printScore();


                    //Get players choice for what part of the scorecard he wants to fill
                    boolean used = false;
                    boolean validRow = false;
                    String row;

                    do {
                        System.out.println("What score will you take for this round");
                        row = kb.next();

                        for(int i = 0; i < players.get(player).getChoices().size(); i++) {
                            if (players.get(player).getChoices().get(i).equals(row)) {
                                validRow = true;
                            }
                        }

                        if(!(validRow)){
                            System.out.println("This it not a valid row");
                            System.out.println("Enter a different row");
                        }

                        //Check if this row has already been used
                        for(int i = 0; i < players.get(player).getUsed().size(); i++) {
                            if (players.get(player).getUsed().get(i).equals(row)) {
                                used = true;
                                System.out.println("This row has already been used");
                                System.out.println("Enter a different row");
                            }
                        }
                    }while(used || !(validRow));

                    players.get(player).setChosenRow(row,score.getScores());

                    //reset for next player
                    hand = null;
                    kb.nextLine();
                    System.out.flush();
                }
            }

            int winner = 0;
            boolean tie = false;

            for (int player = 0; player < numPlayers; player++) {
                tie = false;
                if(players.get(player).getFinalScore() == winner) tie = true;
                if(players.get(player).getFinalScore() > winner) winner = player;
                System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
                System.out.println("|        PLAYER " + (player + 1) + "'S         |");
                System.out.println("|      FINAL SCORECARD      |");
                System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
                players.get(player).printFinalCard();

                if(players.get(player).getFinalScore() >= 10 && players.get(player).getFinalScore() <100){
                    System.out.println("|     TOTAL SCORE: " + players.get(player).getFinalScore() + "       |");
                }
                else if(players.get(player).getFinalScore() >= 100){
                    System.out.println("|    TOTAL SCORE: " + players.get(player).getFinalScore() + "      |");
                }
                else{
                    System.out.println("|     TOTAL SCORE: " + players.get(player).getFinalScore() + "        |");
                }
                System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
                System.out.println();
                System.out.println();
            }

            if(tie){
                System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
                System.out.println("|        TIE GAME!!!        |");
                System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
            }
            else{
                System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
                System.out.println("|      PLAYER " + winner + " WINS!!!     |");
                System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
            }

            System.out.println();
            System.out.println();
            System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
            System.out.println("|                           |");
            System.out.println("|         GAME OVER!        |");
            System.out.println("|                           |");
            System.out.println("|     Enter 'y' to play     |");
            System.out.println("|                           |");
            System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");

            playAgain = kb.nextLine();
            System.out.flush();
        }
    }
}

