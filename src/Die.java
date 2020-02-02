import java.util.*;

/**
 * Class for creating the die object
 *
 * @author Jared Weyer
 * @version 1.0
 */
public class Die {
    /**
     * numSides is the number of sides on the given die
     */
    private int numSides;
    /**
     * value is the number that is associated with the upside of the die this is set using a random number generator
     */
    private int value;
    private Random rand;
    /**
     * keep indicated whether the die object should be rolled ie. value should be changed
     */
    private char keep;



    /**
     * EVC for the Die class
     *
     * @param numSides {@link #numSides}
     * @param keep {@link #keep}
     */
    public Die(int numSides, char keep){
        this.numSides = numSides;
        this.keep = keep;
        rand = new Random();
        dieRoll();
    }

    /**
     * Get values randomly (roll dice) for the die that are chosen by the user to roll
     */
    public void dieRoll(){
        value = rand.nextInt(numSides) + 1;
    }

    /**
     * Sets the keep field
     *
     * @param keep {@link #keep}
     */
    public void setKeep(char keep){
        this.keep = keep;
    }

    /**
     * getter for die value (ie which side of die is up)
     *
     * @return return the value of the up side of the die
     */
    public int getValue(){return value;}

    /**
     * getter for keep indicator (ie should the die be rolled)
     *
     * @return returns indicator on whether to roll die or not
     */
    public char getKeep(){return keep;}
}
