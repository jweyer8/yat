public class Scoring {

    //Scoring values for specific rolls
    final private int FULL_HOUSE_SCORE = 25;
    final private int SMALL_STRAIGHT_SCORE = 30;
    final private int LARGE_STRAIGHT_SCORE = 40;
    final private int NO_SCORE = 0;
    final private int MAX_NUM_DIE = 5;
    final private int NUM_DICE_SIDES = 6;

    private int[] dice;        //stores the values of the dice rolled
    private int[] duplicate = new int[NUM_DICE_SIDES];   //stores value of how many of each die value are in the roll

    //Driver method
    public void scoringDriver(int[] dice){
        this.dice = dice;
        counting(dice);
        upperScore();
        lowerScore();
    }


    //Function for counting how many of each die value (1,2,3,4,5,6) are in the roll
    private void counting(int[] dice){
        int pos = 0;
        for (int dieVal = 1; dieVal <=NUM_DICE_SIDES; dieVal++) {
            int currentCount = 0;
            for (int dicePos = 0; dicePos < MAX_NUM_DIE; dicePos++) {
                if (dice[dicePos] == dieVal)
                    currentCount++;
            }
            duplicate[pos] = currentCount;
            pos++;
        }
    }

    //Function for finding whether the roll has a full house
    private boolean fullHouse(){
        boolean full = false;
        boolean threeDup = false;
        boolean twoDup = false;

        for(int element : duplicate){
            if(element == 2){
                twoDup = true;
            }
            if(element == 3){
                threeDup = true;
            }
            if(threeDup && twoDup){
                full = true;
            }
        }

    return full;
    }

    //Function for finding whether the roll has a small or large straight
    private int straits(){
        int straitLen = 0;
        int currentLen = 0;

        for(int element : duplicate){
            if(element > 0){
                currentLen++;
            }
            else{
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

        for(int element : duplicate){
            if(element >= 3){
                dupVal = element;
            }
        }
    return dupVal;
    }

    //Function for summing the total value of the dice rolled
    private int sumDice(){
        int sum = 0;
        for(int dicePos = 0; dicePos < MAX_NUM_DIE; dicePos++){
            sum += dice[dicePos];
        }
        return sum;
    }


    //Upper ScoreCard
    private void upperScore(){
        for (int diceVal = 1; diceVal <= NUM_DICE_SIDES; diceVal++) {
            System.out.println("Your score for " + diceVal + "'s is: " + duplicate[diceVal-1]*diceVal);
        }
    }

    //Lower Score Card
    private void lowerScore(){

        //Print out scores for duplicates ie 3 of a kind, 4 of a kind, and yahtzee
        switch(maxDup()){
            case 3:
                System.out.println("Your score for 3 of a kind is: " + sumDice());
                break;
            case 4:
                System.out.println("Your score for 4 of a kind is: " + sumDice());
                break;
            case 5:
                System.out.println("yahtzee!!");
                break;
        }


        //Print out score for full house
        if(fullHouse()){
            System.out.println("Your score for the full house section is: " + FULL_HOUSE_SCORE);
        }
        else {
            System.out.println("Your score for the full house section is: " + NO_SCORE);
        }


        //Print out score for small and large straigts
        switch(straits()){
            case 4:
                System.out.println("Your score for small straight is: " + SMALL_STRAIGHT_SCORE);
                break;
            case 5:
                System.out.println("Your score for large straight is: " + LARGE_STRAIGHT_SCORE);
                break;
            default:
                System.out.println("Your score for large straight is: " + NO_SCORE);
                break;
        }


        //Prints out score for chance
        System.out.println("Your score for chance is: " + sumDice());
    }


}
