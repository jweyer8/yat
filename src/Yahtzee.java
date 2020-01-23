import java.util.*;

public class Yahtzee {

    public static void main(String args[]){
        final int NUM_DICE = 5, NUM_SIDES = 6;

        ArrayList<Die> hand = new ArrayList<>();
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
            System.out.println("|                                         |");
            System.out.println("|                                         |");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            playAgain = kb.nextLine();
            System.out.flush();
            hand.clear();
            userStr = "nnnnn";
        }
    }
}

