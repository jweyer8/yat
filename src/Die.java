import java.util.*;

public class Die {

    private int numSides, value;
    private Random rand;
    private char keep;

    public Die(int numSides, char keep){
        this.numSides = numSides;
        this.keep = keep;
        rand = new Random();
    }

    //Get values randomly (roll dice) for the dice that are chosen by the user to roll
    public void dieRoll(){
        value = rand.nextInt(numSides) + 1;
    }

    public void setKeep(char keep){
        this.keep = keep;
    }

    public int getValue(){return value;}

    public char getKeep(){return keep;}
}
