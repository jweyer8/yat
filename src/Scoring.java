
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

/**
 * Class for determining the scores associated with each row on the scorecard for a given hand
 *
 * @author Jared Weyer
 * @version 3.0
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
     * Contains the string representation of the rows that the player has already used
     */
    private ArrayList<String> used = new ArrayList<>();
    /**
     * Contains the scores for each row of the scorecard for the given hand
     */
    private ArrayList<Integer> scores = new ArrayList<>();
    /**
     * builds string which contains the scores for the hand which can be printed on GUI
     */
    private StringBuilder sb = new StringBuilder();




    /**
     * EVC for the scoring class
     *
     * @param hand {@link #hand}
     * @param player Player object need to determine which rows the given player has already used
     */
    public Scoring(Hand hand, @NotNull Player player){
        this.hand = hand;
        used = player.getUsed();
    }
    /**
     * Print the score for upper and lower scorecard
     *
     * @return {@link #sb}
     */
    public StringBuilder printScore(){
        sb.append("<html>");
        sb.append("<br/>");
        sb.append("<center><b> &nbsp  &nbsp  &nbsp HAND SCORES  &nbsp  &nbsp  &nbsp </b></center>");
        upperScore();
        lowerScore();
        sb.append(System.getProperty("line.separator"));
        sb.append("</html>");
        return sb;
    }
    /**
     * Clears scores so that the new hand can have new score values associated with the scores for that hand
     */
    public void clearScores(){scores.clear();}
    /**
     *Gets the scores corresponding to each row on the scorecard for the given hand
     *
     * @return scores {@link #scores}
     */
    public ArrayList<Integer> getScores(){return scores;}
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
        boolean isUsed = false;
        for (int diceVal = 1; diceVal <= hand.getNumSides(); diceVal++) {
            for(int i = 0; i < used.size(); i++){
                if(String.valueOf(diceVal).equals(used.get(i))){
                    scores.add(-1);
                   isUsed = true;
                }
            }
            if(!(isUsed)){
                scores.add(hand.getDup().get(diceVal - 1) * diceVal);
                if(hand.getDup().get(diceVal - 1) * diceVal >= 10){
                    sb.append("<br/>");
                    sb.append("<hr>");
                    sb.append(" Score " + hand.getDup().get(diceVal - 1) * diceVal + " on the " + diceVal + "'s line  ");
                }
                else {
                    sb.append("<br/>");
                    sb.append("<hr>");
                    sb.append(" Score " + hand.getDup().get(diceVal - 1) * diceVal + " on the " + diceVal + "'s line   ");
                }
            }
            isUsed = false;
        }
    }
    /**
     * Prints the values for the lower scorecard
     */
    private void lowerScore(){

        //Print out scores for duplicates ie 3 of a kind, 4 of a kind, ect.. depending on number of die in play
        boolean isUsed ; //has the player already used this row on their scorecard
        boolean hasKind; //does the hand contain 3,4,5... of a kind
        boolean yaht = false; //does the hand contain yahtzee. This will be used later in the scorecard scoring

        for(int kind = 3; kind <= hand.getNumDice(); kind++){
            hasKind = false;
            isUsed = false;

            //check if the player already used row on their scorecard (this row will not be printed if the player has used it)
            for(int j = 0; j < used.size(); j++){
                if((Integer.toString(kind) + "K").equals(used.get(j))){
                    scores.add(-1);
                    isUsed = true;
                }
            }

            //Print all the #kind that the player has not used and is not a yahtzee
            for(int i = 0; i < hand.getNumSides(); i++){
                if(hand.getDup().get(i) == kind && !(isUsed)){
                    if(kind == hand.getNumDice()){yaht = true;} //hand contains a yahtzee
                    else {
                        scores.add(hand.sumDice());
                        sb.append("<br/>");
                        sb.append("<hr>");
                        sb.append(" Score " + hand.sumDice() + " on the " + kind + "K line   ");

                        hasKind = true;
                    }
                    break;
                }
            }
            if(!(hasKind) && kind != hand.getNumDice() && !(isUsed)){
                scores.add(NO_SCORE);
                sb.append("<br/>");
                sb.append("<hr>");
                sb.append(" Score " + NO_SCORE + " on the " + kind + "K line    ");
            }
        }

        //Check whether fullhouse, straights or yahtzee is used
        boolean fh = false;
        boolean y = false;
        boolean ss = false;
        boolean ls = false;
        boolean c = false;

        for(int j = 0; j < used.size(); j++){
            switch(used.get(j)){
                case("FH"):
                    fh = true;
                    break;
                case("SS"):
                    ss = true;
                    break;
                case("LS"):
                    ls = true;
                    break;
                case("Y"):
                    y = true;
                    break;
                case("C"):
                    c = true;
                    break;
            }
        }

        //Print out score for full house
        if(!(fh)) {
            if(fullHouse()){
                scores.add(FULL_HOUSE_SCORE);
                sb.append("<br/>");
                sb.append("<hr>");
                sb.append(" Score " + FULL_HOUSE_SCORE + " on the FH line   ");
            }
            else{
                scores.add(NO_SCORE);
                sb.append("<br/>");
                sb.append("<hr>");
                sb.append(" Score " + NO_SCORE + " on the FH line    ");
            }
        }
        else{
            scores.add(-1);
        }

        //Print out score for small straight
        if(!(ss)) {
            if(straits() == 4){
                scores.add(SMALL_STRAIGHT_SCORE);
                sb.append("<br/>");
                sb.append("<hr>");
                sb.append(" Score " + SMALL_STRAIGHT_SCORE + " on the SS line   ");
            }
            else{
                scores.add(NO_SCORE);
                sb.append("<br/>");
                sb.append("<hr>");
                sb.append(" Score " + NO_SCORE + " on the SS line    ");
            }
        }
        else{
            scores.add(-1);
        }

        //Print out score for large straight
        if(!(ls)) {
            if(straits() >= 5){
                scores.add(LARGE_STRAIGHT_SCORE);
                sb.append("<br/>");
                sb.append("<hr>");
                sb.append(" Score " + LARGE_STRAIGHT_SCORE + " on the LS line   ");
            }
            else{
                scores.add(NO_SCORE);
                sb.append("<br/>");
                sb.append("<hr>");
                sb.append(" Score " + NO_SCORE + " on the LS line    ");}
        }
        else{
            scores.add(-1);
        }

        //Print Yahtzee score
        if(!(y)) {
            if (yaht){
                scores.add(YAHTZEE_SCORE);
                sb.append("<br/>");
                sb.append("<hr>");
                sb.append(" Score " + YAHTZEE_SCORE + " on the Y line    ");
            }
            else{
                scores.add(NO_SCORE);
                sb.append("<br/>");
                sb.append("<hr>");
                sb.append(" Score " + NO_SCORE + " on the Y line     ");
            }
        }
        else{
            scores.add(-1);
        }

        //Print out the sum of the dice for the chance score
        if(!(c)){
            scores.add(hand.sumDice());
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + hand.sumDice() + " on the C line    ");
        }
        else{
            scores.add(-1);
        }
    }
}
