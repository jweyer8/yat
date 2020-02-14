import java.util.ArrayList;
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
        int currentLen = 0;
        int bigLen = 0;

        for (int el : hand.getDup()) {
            if (el > 0) {
                currentLen++;
                if(currentLen >= 4){
                    bigLen = currentLen;
                }
            } else {
                currentLen = 0;
            }
        }
        return bigLen;
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
        //Print out scores for duplicates ie 3 of a kind, 4 of a kind, ect.. depending on number of die in play
        boolean hasKind; //does the hand contain 3,4,5... of a kind
        boolean yaht = false; //does the hand contain yahtzee. This will be used later in the scorecard scoring
        for(int kind = 3; kind <= hand.getNumDice(); kind++){
            hasKind = false;
            for(int i = 0; i < hand.getNumSides(); i++){
                if(hand.getDup().get(i) == kind){
                    if(kind == hand.getNumDice()){yaht = true;} //hand contains a yahtzee
                    else {
                        System.out.println("Score " + hand.sumDice() + " on the " + kind + "K line");
                        hasKind = true;
                    }
                    break;
                }
            }
            if(!(hasKind) && kind != hand.getNumDice()){System.out.println("Score " + NO_SCORE + " on the " + kind + "K line");}
        }

        //Print out score for full house
        if(fullHouse()){ System.out.println("Score " + FULL_HOUSE_SCORE + " on the FH line"); }
        else {System.out.println("Score " + NO_SCORE + " on the FH line"); }

        //Print out score for small and large straits
        if(straits() == 4) {System.out.println("Score " + SMALL_STRAIGHT_SCORE + " on the SS line");}
        else{System.out.println("Score " + NO_SCORE + " on the SS line");}
        if(straits() >= 5) {System.out.println("Score " + LARGE_STRAIGHT_SCORE + " with a straight of " + straits() + " On the LS line");}
        else{System.out.println("Score " + NO_SCORE + " on the LS line");}

        //Print Yahtzee score
        if(yaht){System.out.println("Score " + YAHTZEE_SCORE + " on the Yahtzee line");}
        else{System.out.println("Score " + NO_SCORE + " on the Yahtzee line");}

        //Print out the sum of the dice for the chance score
        System.out.println("Score " + hand.sumDice() + " on the C line");
    }
}
