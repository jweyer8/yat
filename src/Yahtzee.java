import java.util.*;

public class Yahtzee {
    private final static int MAX_NUM_DIE = 5;

    public static void main(String args[]){

        int[] dice = {0,0,0,0,0};
        Scanner kb = new Scanner(System.in);
        char[] userChoice = new char[MAX_NUM_DIE];
        //String playAgain = "y";

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
            String userStr = "nnnnn";

            while (turn < 4 && !(userStr.equals("yyyyy"))){
                //roll dice not kept
                for (int i = 0; i < userStr.length(); i++) {
                    userChoice[i] = userStr.charAt(i);
                }
                Die die = new Die();
                dice = die.rollDriver(userChoice, dice);

                //output roll
                System.out.print("Your roll was: ");
                for (int dieNumber = 0; dieNumber < MAX_NUM_DIE; dieNumber++) {
                    System.out.print(dice[dieNumber] + " ");
                }
                System.out.println();

                //if not the last roll of the hand prompt the user for dice to keep
                if (turn < 3) {
                    System.out.println("enter dice to keep (y or n) ");
                    userStr = kb.nextLine();

                    if(userStr.length() > MAX_NUM_DIE){
                        //throw error here
                        System.out.println("The string is too big");
                        turn = 5;
                    }
                }
                turn++;
            }
            Scoring scoring = new Scoring();
            Die die = new Die();
            die.sorter(dice);

            System.out.print("Here are your sorted dice: ");
            for(int i = 0; i< MAX_NUM_DIE; i++){
                System.out.print(" " + dice[i]);
            }
            System.out.println();
            scoring.scoringDriver(dice);
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
        }
    }
}
