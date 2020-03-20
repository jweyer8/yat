import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

/**
 * Holds the die objects
 *
 * @author Jared Weyer
 * @version 3.0
 * @see Die
 */
public class Hand {
    /**
     * how many duplicate values in the hand ie, how many 1's 2's 3's... in hand
     */
    private ArrayList<Integer> dup = new ArrayList<>();
    /**
     * contains the die objects
     */
    private ArrayList<Die> hand;
    /**
     * contains the value of the up side of the die
     */
    private ArrayList<Integer> handVals = new ArrayList<>();
    /**
     * the number of die in the hand
     */
    private int numDice;
    /**
     * how many sides does the die have and how many die are in hand
     */
    private int numSides;
    /**
     * Contains the user input string which determines which die in the hand should be rolled
     */
    private String userStr;




    /**
     * EVC for the hand class
     *
     * @param numSides {@link #numSides}
     * @param numDice {@link #numDice}
     */
    public Hand(int numSides,int numDice){
        this.numSides = numSides;
        this.numDice = numDice;
        String str = "";
        hand = new ArrayList<>();

        for(int dieCount = 0; dieCount < numDice; dieCount++) {
            hand.add(new Die(numSides,'n'));
            handVals.add(hand.get(dieCount).getValue());
            str += 'n';
        }
        userStr = str;
        for(int dieVal = 1; dieVal <= numSides; dieVal++) {
            dup.add(dieVal);
        }
    }
    /**
     * Sort die from least to greatest based on side up value
     */
    public void printSorted(){
        System.out.print("Here are your sorted dice: ");
        Collections.sort(handVals);
        for(int el : handVals){
            System.out.print(el + " ");
        }
    }
    /**
     * Prints the up side values of the die in the hand
     */
    public void printHand(){
        System.out.print("Your roll was: ");
        JPanel dieButtonPanel = new JPanel();
        JButton dieButton = new JButton("Die");
        for (int dieCount = 0; dieCount < numDice; dieCount++) {
            dieButtonPanel.add(dieButton);
           // System.out.print(hand.get(dieCount).getValue() + " ");
        }
    }
    /**
     * Rolls the die in the hand that the user wants to roll
     * Resets the dup ArrayList and the handVals ArrayList
     */
    public void rollDice(){
        for(int dieCount = 0; dieCount < numDice; dieCount++) {
            if(hand.get(dieCount).getKeep() == 'n') hand.get(dieCount).dieRoll();
        }
        vals();
        duplicate();
    }

    /**
     * Method for summing the total value of the dice rolled
     *
     * @return returns the sum of the die up values
     */
    public int sumDice(){
        int sum = 0;
        for(int dieVal = 1; dieVal <= numSides; dieVal++){
            sum += dup.get(dieVal-1)*(dieVal);
        }
        return sum;
    }
    /**
     * Get the value of the side that is up on the dice
     */
    private void vals(){
        for(int dieCount = 0; dieCount < numDice; dieCount++){
            handVals.set(dieCount,hand.get(dieCount).getValue());
        }
    }
    /**
     * Determine how many duplicates are in the hand ie, how many 1's 2's 3's... in hand
     */
    private void duplicate(){
        int dupCount;
        for(int dieVal = 1; dieVal <= numSides; dieVal++){
            dupCount = 0;
            for(int dieCount = 0; dieCount < numDice; dieCount++){
                if(handVals.get(dieCount) == dieVal) dupCount++;
            }
            dup.set(dieVal-1,dupCount);
        }
    }


    public void setDieKeep(char keep, int pos){hand.get(pos).setKeep(keep);}
    /**
     * Gets the dup ArrayList which is used to score the hand
     * @return dup {@link #dup}
     */
    public ArrayList<Integer> getDup(){return dup;}
    /**
     * Returns the number of sides on the dice
     *
     * @return numSides
     */
    public int getNumSides(){return numSides;}
    /**
     * Gets the user string
     *
     * @return userStr {@link #userStr}
     */
    public String getUserStr(){return userStr;}
    /**
     * Gets the handVals ArrayList
     *
     * @return {@link #handVals}
     */
    public ArrayList<Integer> getVals(){return handVals;}
    public int getNumDice(){return numDice;}
}

