import java.util.*;

public class Scoring{

    //Scoring values for specific rolls
    final static private int FULL_HOUSE_SCORE = 25;
    final static private int SMALL_STRAIGHT_SCORE = 30;
    final static private int LARGE_STRAIGHT_SCORE = 40;
    final static private int YAHTZEE_SCORE = 50;
    final static private int NO_SCORE = 0;

    private ArrayList<Integer> dup = new ArrayList<>();
    private ArrayList<Die> hand = new ArrayList<>();
    private ArrayList<Integer> handVals = new ArrayList<>();
    private int len, numSides;

    //Driver method
    public Scoring(ArrayList<Die> hand, int numSides){
        this.hand = hand;
        this.numSides = numSides;
        len = hand.size();
        getVal();
        getDup();
    }

    public void printSorted(){
        Collections.sort(handVals);
        for(int el : handVals){
            System.out.print(el + " ");
        }
    }

    public void getScore(){
        upperScore();
        lowerScore();
    }

    private void getVal(){
        for(int dieCount = 0; dieCount < len; dieCount++){
            handVals.add( hand.get(dieCount).getValue());
        }
    }

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


    //Function for finding whether the roll has a full house
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

    //Function for finding whether the roll has a small or large straight
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

    //Function for finding whether the roll has a 3 of a kind, 4 of a kind, or a yahtzee
    private int maxDup(){
        int dupVal = 0;

        for (int el: dup) {
            if (el >= 3) {
                dupVal = el;
            }
        }
    return dupVal;
    }

    //Function for summing the total value of the dice rolled
    private int sumDice(){
        int sum = 0;
        for(int dieVal = 1; dieVal <= numSides; dieVal++){
            sum += dup.get(dieVal-1)*(dieVal);
        }
        return sum;
    }


    //Upper ScoreCard
    private void upperScore(){
        for (int diceVal = 1; diceVal <= numSides; diceVal++) {
            System.out.println("Score " + dup.get(diceVal-1)*diceVal + " on the " + diceVal + " line");
        }
    }

    //Lower Score Card
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
