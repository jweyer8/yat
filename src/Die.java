import java.util.*;

public class Die {

    private final static int MAX_NUM_DIE = 5;

    private Random rand = new Random();
    private int[] dice = new int[MAX_NUM_DIE];

    //Get values randomly (roll dice) for the dice that are chosen by the user to roll
    private void dieRoll(boolean[] choice){
         for(int i = 0; i < MAX_NUM_DIE; i++) {
             if (choice[i]) {
                 dice[i] = rand.nextInt(6) + 1;
             }
         }
    }

    //Function to turn the character spring from user into bool values
    private void diceToRoll(char[] choice){
        boolean[] choiceBool = new boolean[MAX_NUM_DIE];

        for(int i = 0; i < MAX_NUM_DIE; i++){
            if(choice[i] == 'n'){
                choiceBool[i] = true;
            }
            else if (choice[i] == 'y'){
                choiceBool[i] = false;
            }
            else{
               //should throw error here
                choiceBool[i] = false;
            }
        }
        dieRoll(choiceBool);
    }

    //Driver method to roll dice
    public int[] rollDriver(char[] userChoice, int dice[]){
        this.dice = dice;
        diceToRoll(userChoice);

        return this.dice;
    }

    public int[] sorter(int[] dice){
        boolean swap;
        int temp;

        do {
            swap = false;

            for (int count = 0; count < (MAX_NUM_DIE - 1); count++) {
                if (dice[count] > dice[count + 1]) {
                    temp = dice[count];
                    dice[count] = dice[count + 1];
                    dice[count + 1] = temp;
                    swap = true;
                }
            }
        } while (swap);

        return dice;
    }


}
