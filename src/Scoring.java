import java.util.*;

/**
 * Class for determining the score associated with the hand
 *
 * @author Jared Weyer
 * @version 1.0
 */
public class Scoring{
    /**
     * Score associated with a full house
     */
    final static private int FULL_HOUSE_SCORE = 25;
    /**
     * Score associated with a small straight
     */
    final static private int SMALL_STRAIGHT_SCORE = 30;
    /**
     * Score associated with a large straight
     */
    final static private int LARGE_STRAIGHT_SCORE = 40;
    /**
     * Score associated with a yahtzee
     */
    final static private int YAHTZEE_SCORE = 50;
    /**
     * Zero value for no score
     */
    final static private int NO_SCORE = 0;
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
    private int len;
    /**
     * how many sides does the die have and how many die are in hand
     */
    private int numSides;

    /**
     * EVC for the scoring class
     *
     * @param hand {@link #hand}
     * @param numSides {@link #hand}
     */
    public Scoring(ArrayList<Die> hand, int numSides){
        this.hand = hand;
        this.numSides = numSides;
        len = hand.size();
        getVal();
        getDup();
    }

    /**
     * sort die from least to greatest based on side up value
     */
    public void printSorted(){
        Collections.sort(handVals);
        for(int el : handVals){
            System.out.print(el + " ");
        }
    }

    /**
     * get the score for upper and lower scorecard
     */
    public void getScore(){
        upperScore();
        lowerScore();
    }

    /**
     * get the value of the side that is up on the dice
     *
     * @see #handVals
     */
    private void getVal(){
        for(int dieCount = 0; dieCount < len; dieCount++){
            handVals.add( hand.get(dieCount).getValue());
        }
    }

    /**
     * determine how many duplicates are in the hand ie, how many 1's 2's 3's... in hand
     *
     * @see #dup
     */
    private void getDup(){
        int dupCount;
        for(int dieVal = 1; dieVal <= numSides; dieVal++){
            dupCount = 0;
            for(int dieCount = 0; dieCount < len; dieCount++){
                if(handVals.get(dieCount) == dieVal) dupCount++;
            }
            dup.add(dupCount);
        }
    }


    /**
     * Determine whether the roll has a full house
     *
     * @return returns a boolean
     */
    private boolean fullHouse(){
        boolean full = false;
        boolean threeDup = false;
        boolean twoDup = false;

        for (int el : dup) {
            if (el == 2) {
                twoDup = true;
            }
            if (el == 3) {
                threeDup = true;
            }
            if (threeDup && twoDup) {
                full = true;
            }
        }

    return full;
    }

    /**
     * Determine whether the roll has a small or large straight
     *
     * @return returns an <code>int</code> indicating the length of the straight
     */
    private int straits(){
        int straitLen = 0;
        int currentLen = 0;

        for (int el : dup) {
            if (el > 0) {
                currentLen++;
            } else {
                currentLen = 0;
            }
        }

        if(currentLen == 4 || currentLen== 5){
            straitLen = currentLen;
        }

        return straitLen;
    }

    /**
     * Determine whether the roll has a 3 of a kind, 4 of a kind, or a yahtzee
     *
     * @return returns an <code>int</code> indicating the max of a kind in the hand
     */
    private int maxDup(){
        int dupVal = 0;

        for (int el: dup) {
            if (el >= 3) {
                dupVal = el;
            }
        }
    return dupVal;
    }

    /**
     * Method for summing the total value of the dice rolled
     *
     * @return returns the sum of the die up values
     */
    private int sumDice(){
        int sum = 0;
        for(int dieVal = 1; dieVal <= numSides; dieVal++){
            sum += dup.get(dieVal-1)*(dieVal);
        }
        return sum;
    }


    /**
     * Prints the values for the upper scorecard
     */
    private void upperScore(){
        for (int diceVal = 1; diceVal <= numSides; diceVal++) {
            System.out.println("Score " + dup.get(diceVal-1)*diceVal + " on the " + diceVal + " line");
        }
    }

    /**
     * Prints the values for the lower scorecard
     */
    private void lowerScore(){

        //Print out scores for duplicates ie 3 of a kind, 4 of a kind, and yahtzee
        switch(maxDup()){
            case 3:
                System.out.println("Score " + sumDice() + " on the 3 of a kind line");
                break;
            case 4:
                System.out.println("Score " + sumDice() + " on the 4 of a kind line");
                break;
            case 5:
                System.out.println("Score " + YAHTZEE_SCORE + " on the Yahtzee line");
                break;
            default:
                System.out.println("Score " + NO_SCORE + " on the 3 of a kind line");
                System.out.println("Score " + NO_SCORE + " on the 4 of a kind line");
                System.out.println("Score " + NO_SCORE + " on the Yahtzee line");
        }

        //Print out score for full house
        if(fullHouse()){
            System.out.println("Score " + FULL_HOUSE_SCORE + " on the Full House line");
        }
        else {
            System.out.println("Score " + NO_SCORE + " on the Full House line");
        }


        //Print out score for small and large straigts
        switch(straits()){
            case 4:
                System.out.println("Score " + SMALL_STRAIGHT_SCORE + " on the Small Straight line");
                break;
            case 5:
                System.out.println("Score " + LARGE_STRAIGHT_SCORE + " on the Large Straight line");
                break;
            default:
                System.out.println("Score " + NO_SCORE + " on the Small Straight line");
                System.out.println("Score " + NO_SCORE + " on the Small Straight line");
                break;
        }


        //Prints out score for chance
        System.out.println("Score " + sumDice() + " on the Chance line");
    }
}
