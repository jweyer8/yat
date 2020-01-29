import java.util.*;

/**
 * Yahtzee main class that allows user to play the game
 *
 * @author Jared Weyer
 * @version 1.0
 * @see User
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
        /**
         * Number of sides of the die
         */
        int NUM_DICE = 5;
        /**
         * contains the die objects in the hand
         */
        final int NUM_SIDES = 6;

        ArrayList<Die> hand = new ArrayList<>();
        /**
         * contains the user input <br> "y" indicates to keep the die value / not roll the die </br> <br> "n" indicates to not keep the die value / roll the die </br>
         */
        String userStr = "nnnnn";
        Scanner kb = new Scanner(System.in);

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("|                                         |");
        System.out.println("|          Welcome to Yahtzee!            |");
        System.out.println("|                                         |");
        System.out.println("|           Enter 'y' to play             |");
        System.out.println("|                                         |");
        System.out.println("|                                         |");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        String playAgain = kb.nextLine();
        System.out.flush();

        while (playAgain.equals("y")) {
            int turn = 1;
            User user = new User(userStr);

            //create array of dice objects
            for(int dieCount = 0; dieCount < NUM_DICE; dieCount++) {
                hand.add(new Die(NUM_SIDES,user.getUserChoice().get(dieCount)));
            }

            while (turn < 4 && !(user.getUserStr().equals("yyyyy"))){
                //roll dice not kept
                for(int dieCount = 0; dieCount < NUM_DICE; dieCount++) {
                    if(hand.get(dieCount).getKeep() == 'n') hand.get(dieCount).dieRoll();
                }

                //output roll
                System.out.print("Your roll was: ");
                for (int dieCount = 0; dieCount < NUM_DICE; dieCount++) {
                    System.out.print(hand.get(dieCount).getValue() + " ");
                }
                System.out.println();

                //if not the last roll of the hand prompt the user for dice to keep
                if (turn < 3) {
                    System.out.println("enter dice to keep (y or n) ");
                    userStr = kb.nextLine();
                    user.setUserStr(userStr);

                    //set the keep attribute of dice to indicate which dice should be rolled
                    for(int dieCount = 0; dieCount < NUM_DICE; dieCount++) {
                        hand.get(dieCount).setKeep(user.getUserChoice().get(dieCount));
                    }

                    if(user.getUserLen() > NUM_DICE){
                        //throw error here
                        System.out.println("The string is too big");
                        turn = 5;
                    }
                }
                turn++;
            }

            //Get the score for the round and output the scores
            Scoring score = new Scoring(hand,NUM_SIDES);
            System.out.print("Here are your sorted dice: ");
            score.printSorted();
            System.out.println();
            score.getScore();
            System.out.println();
            System.out.println();
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("|                                         |");
            System.out.println("|               Game Over!                |");
            System.out.println("|                                         |");
            System.out.println("|        Enter 'y' to play again          |");
            System.out.println("|          Press enter to quit            |");
            System.out.println("|                                         |");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            playAgain = kb.nextLine();
            System.out.flush();

            //reset for next round
            hand.clear();
            userStr = "nnnnn";
        }
    }
}

