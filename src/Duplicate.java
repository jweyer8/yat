import java.util.*;

public class Duplicate {
    private int sides;

    private ArrayList<Integer> dup = new ArrayList<Integer>(sides);
    private ArrayList<Integer> dice = new ArrayList<Integer>(sides);

    private void getDup(){
        int pos = 0;
        for (int dieVal = 1; dieVal <= sides; dieVal++) {
            int currentCount = 0;
            for (int dicePos = 0; dicePos < sides; dicePos++) {
                if (dice.get(dicePos)== dieVal)
                    currentCount++;
            }
            dup.add(currentCount);
            pos++;
        }
    }

    public Duplicate(int sides,int[] dice){
        this.sides = sides;

        for(int i = 0; i<sides; i++){
            this.dice.add(dice[i]);
        }
    }

    public getDup(){

    }
}
