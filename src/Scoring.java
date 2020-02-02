
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
     * contains the die objects
     */
    private Hand hand;



    /**
     * EVC for the scoring class
     *
     * @param hand {@link #hand}
     */
    public Scoring(Hand hand){
        this.hand = hand;
    }
    /**
     * get the score for upper and lower scorecard
     */
    public void getScore(){
        upperScore();
        lowerScore();
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

        for (int el : hand.getDup()) {
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

        for (int el : hand.getDup()) {
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

        for (int el: hand.getDup()) {
            if (el >= 3) {
                dupVal = el;
            }
        }
    return dupVal;
    }
    /**
     * Prints the values for the upper scorecard
     */
    private void upperScore(){
        for (int diceVal = 1; diceVal <= hand.getNumSides(); diceVal++) {
            System.out.println("Score " + hand.getDup().get(diceVal-1)*diceVal + " on the " + diceVal + " line");
        }
    }
    /**
     * Prints the values for the lower scorecard
     */
    private void lowerScore(){

        //Print out scores for duplicates ie 3 of a kind, 4 of a kind, and yahtzee
        switch(maxDup()){
            case 3:
                System.out.println("Score " + hand.sumDice() + " on the 3 of a kind line");
                break;
            case 4:
                System.out.println("Score " + hand.sumDice() + " on the 4 of a kind line");
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
        System.out.println("Score " + hand.sumDice() + " on the Chance line");
    }
}
