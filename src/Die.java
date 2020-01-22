import java.util.*;

public class Die {

    private int numSides;
    private int upSide;
    private Random rand = new Random();
    //private int[] dice = new int[numSides];

    //Get values randomly (roll dice) for the dice that are chosen by the user to roll
    public void dieRoll(boolean[] choice){
       upSide = rand.nextInt(numSides) + 1;
    }

    //Function to turn the character spring from user into bool values
    //This should be in the yahtzee class or possible a seperate class
    
    /*
    private void diceToRoll(char[] choice){
        boolean[] choiceBool = new boolean[numSides];

        for(int i = 0; i < numSides; i++){
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

     */


    //move this method to the yat class
    public int[] sortDie(int[] dice){
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
