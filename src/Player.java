import java.util.ArrayList;

public class Player {
    private final static int NO_SCORE = 0;
    private int numSides;
    private int numDice;
    private ArrayList<String> choices = new ArrayList<>();
    private ArrayList<String> used = new ArrayList<>();
    private ArrayList<Integer> finalScores = new ArrayList<>();

    public Player(int numSides, int numDice){
        this.numSides = numSides;
        this.numDice = numDice;

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

    public void setChosenRow(String chosen, ArrayList<Integer> scores){
        for(int i = 0; i < choices.size(); i++){
            if(choices.get(i).equals(chosen)){
                finalScores.set(i,scores.get(i));
                used.add(chosen);
            }
        }
    }

    //get the used ArrayList
    public ArrayList<String> getUsed(){return used;}

    //get the choices arraylist
    public ArrayList<String> getChoices(){return choices;}

    //get the final total score of the scorecard for the player
    public int getFinalScore(){
        int sum = 0;
        for(int el : finalScores){
            sum += el;
        }
        return sum;
    }

    //Print final scorecard
    public void printFinalCard(){
        //upper scoreCard
        for(int die = 1; die <= numDice; die++){
            if(finalScores.get(die -1) >= 10){
                System.out.println("| Score " + finalScores.get(die - 1) + " on the " + die + "'s line  |");
            }
            else {
                System.out.println("| Score " + finalScores.get(die - 1) + " on the " + die + "'s line   |");
            }
        }

        //lower score card

        //#of a kind
        int maxKind = 0;
        for(int kind = 3; kind < numDice; kind++){
            if(finalScores.get(numSides + maxKind) >= 10 && finalScores.get(numSides + maxKind) < 100){
                System.out.println("| Score " + finalScores.get(numSides + maxKind) + " on the " + kind + "K line   |");
            }
            else if(finalScores.get(numSides + maxKind) >= 100){
                System.out.println("| Score " + finalScores.get(numSides + maxKind) + " on the " + kind + "K line  |");
            }
            else{
                System.out.println("| Score " + finalScores.get(numSides + maxKind) + " on the " + kind + "K line    |");
            }
            maxKind++;
        }


        System.out.println("| Score " + finalScores.get(numSides + maxKind) + " on the FH line    |");
        System.out.println("| Score " + finalScores.get(numSides + 1 + maxKind) + " on the SS line    |");
        System.out.println("| Score " + finalScores.get(numSides + 2 + maxKind) + " on the LS line    |");
        System.out.println("| Score " + finalScores.get(numSides + 3 + maxKind) + " on the Y line     |");

        if(finalScores.get(numDice + 1) >= 10 && finalScores.get(numSides + 1 + maxKind) < 100){
            System.out.println("| Score " + finalScores.get(numSides + 4 + maxKind) + " on the C line    |");
        }
        else if(finalScores.get(numDice + 1) >= 100){
            System.out.println("| Score " + finalScores.get(numSides + 4 + maxKind) + " on the C line      |");
        }
        else {
            System.out.println("| Score " + finalScores.get(numSides + 4 + maxKind) + " on the C line     |");
        }

        System.out.println("+-=-=-=-=-=-=-=-=-=-=-=-=-=-+");
    }
}
