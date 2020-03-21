import java.util.ArrayList;

/**
 * Player class for the each player playing Yahtzee
 *
 * @author Jared Weyer
 * @version 1.0
 */
public class Player {
    /**
     * Zero value for a score of zero on a row on scorecard
     */
    private final static int NO_SCORE = 0;
    /**
     * If sum of upper scorecard is greater than the cutoff then the player gets the bonus
     */
    private int bonusCutoff = 63;
    /**
     * Value of the bonus row
     */
    private final static int BONUS_VALUE = 35;
    /**
     * Number of sides on a die
     */
    private int numSides;
    /**
     * Number of die in a hand
     */
    private int numDice;
    /**
     * Contains the value of the bonus row, the value of this will be 35 if the player meets the cutoff
     */
    private int bonus = 0;
    /**
     * Contains the string representation of all the rows on the players scorecard
     */
    private ArrayList<String> choices = new ArrayList<>();
    /**
     * Contains the string representation of all the rows that a player has filled on his/her scorecard
     */
    private ArrayList<String> used = new ArrayList<>();
    /**
     * Contains the final scores that a player has received on his/her scorecard
     */
    private ArrayList<Integer> finalScores = new ArrayList<>();
    /**
     * string to print on GUI
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * EVC for the player class
     *
     * @param numSides {link: #numSides}
     * @param numDice {link: #numDice}
     */
    public Player(int numSides, int numDice){
        this.numSides = numSides;
        this.numDice = numDice;
        setBonusCut();

        //set upper Score card
        for(int side = 0; side < numSides; side++){
            choices.add(String.valueOf(side + 1));
            finalScores.add(NO_SCORE);
        }

        //set #of a kind
        for(int kind = 3; kind < numDice; kind++){
            choices.add(String.valueOf(kind) + "K");
            finalScores.add(NO_SCORE);
        }

        //set Lower Score card not including #of a kind
        choices.add("FH");
        finalScores.add(NO_SCORE);
        choices.add("SS");
        finalScores.add(NO_SCORE);
        choices.add("LS");
        finalScores.add(NO_SCORE);
        choices.add("Y");
        finalScores.add(NO_SCORE);
        choices.add("C");
        finalScores.add(NO_SCORE);
    }

    /**
     * Sets the final scorecard value to the value that the player received on a given hand on a given row that they want to use for the round
     *
     * @param chosen {link: #chosen}
     * @param scores {link: #chosen}
     */
    public void setChosenRow(String chosen, ArrayList<Integer> scores){
        //Set the final score of the row chosen by the user by comparing the user input with the chosen row
        for(int i = 0; i < choices.size(); i++){
            if(choices.get(i).equals(chosen)){
                finalScores.set(i,scores.get(i));
                used.add(chosen);
            }
        }
    }

    /**
     * Gets the used string ArrayList
     *
     * @return used {link: #used}
     */
    public ArrayList<String> getUsed(){return used;}

    /**
     * Gets choices string ArrayList
     *
     * @return choices {link: #choices}
     */
    public ArrayList<String> getChoices(){return choices;}

    public void clearSb(){sb.setLength(0);}

    /**
     * Determine and return the final total score for the player (This is the sum of the scores on each row of scorecard)
     *
     * @return Returns the total scores on each row
     */
    public int getFinalScore(){
        int sum = 0;
        for(int el : finalScores){
            sum += el;
        }
        return sum;
    }

    /**
     * Set the bonus cutoff value (this value corresponds to an average value of three dice for each of the upper scorecard rows)
     */
    private void setBonusCut(){
        int bval = 0;
        for(int side = 1; side <= numSides; side++){
            bval += side * 3;
        }
        bonusCutoff = bval;
    }

    /**
     * Determine if the player reaches the cutoff value needed to get points for the bonus row
     */
    private void bonusRow(){
        int upperSum = 0;
        for (int die = 1; die <= numSides; die++) {
            upperSum += finalScores.get(die -1);
        }
        if(upperSum >= bonusCutoff){
            bonus = BONUS_VALUE;
        }
    }

    /**
     * Prints the final scorecard for the player
     */
    public StringBuilder printFinalCard(){
        //upper scoreCard
        sb.append("<html><BR/><CENTER><b> FINAL SCORECARD </b></CENTER>");
        for(int side = 1; side <= numSides; side++){
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(side - 1) + " on the " + side + "'s line  ");
        }

        //Bonus Row
        bonusRow();
        if(bonus == 0) {
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + bonus + " on the bonus      ");
        }
        else{
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + bonus + " on the bonus line");
        }

        //lower score card
        //#of a kind
        int maxKind = 0;
        for(int kind = 3; kind < numDice; kind++){
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind) + " on the " + kind + "K line   ");
            maxKind++;
        }

        //Print Full House
        if(finalScores.get(numSides + maxKind) >= 10){
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind) + " on the FH line   ");
        }
        else{
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind) + " on the FH line    ");
        }

        //Print Small Straight
        if(finalScores.get(numSides + maxKind + 1) >= 10){
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind + 1) + " on the SS line   ");
        }
        else{
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind + 1) + " on the SS line    ");
        }

        //Print Large Straight
        if(finalScores.get(numSides + maxKind + 2) >= 10){
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind + 2) + " on the LS line   ");
        }
        else{
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind + 2) + " on the LS line    ");
        }

        //Print Yahtzee
        if(finalScores.get(numSides + maxKind + 3) >= 10){
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind + 3) + " on the Y line   ");
        }
        else{
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind + 3) + " on the Y line     ");
        }

        //Print chance line
        if(finalScores.get(numSides + maxKind + 4) >= 10 && finalScores.get(numSides +  maxKind + 4) < 100){
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind + 4) + " on the C line    ");
        }
        else if(finalScores.get(numSides + maxKind + 4) >= 100){
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind + 4) + " on the C line      ");
        }
        else {
            sb.append("<br/>");
            sb.append("<hr>");
            sb.append(" Score " + finalScores.get(numSides + maxKind + 4) + " on the C line     ");
        }
        sb.append("</html>");
        return sb;
    }
}
