import java.util.ArrayList;
/**
 * This class receives user input and processes this input
 *
 * @author Jared Weyer
 * @version 1.0
 */
public class User {
    /**
     * userStr is the user string input <br> "y" indicates not to roll the die </br> <br> "n" indicates to roll the die</br>
     */
    private String userStr;
    /**
     * length of the user string
     */
    private int len;
    /**
     * <code>ArrayList<Character</code> that is created by the user string array
     */
    private ArrayList<Character> userChoice = new ArrayList<>();

    /**
     * EVC for the user class
     *
     * @param userStr {@link #userStr}
     */
    public User(String userStr){
        this.userStr = userStr;
        len = userStr.length();
        createUserChoice();
    }

    /**
     * Sets the user choice <code>ArrayList<Character></code>
     *
     * @param userStr {@link #userStr}
     */
    public void setUserStr(String userStr){
        this.userStr = userStr;
        changeUserChoice();
    }

    /**
     *
     * @return returns the char <code>ArrayList</code> which indicates whether to roll the die
     */
    public ArrayList<Character> getUserChoice(){return userChoice;}

    /**
     *
     * @return returns the user input string
     */
    public String getUserStr(){return userStr;}

    /**
     *
     * @return returns the length of the user input string
     */
    public int getUserLen(){return len;}

    /**
     * turns the user input string into a <code>ArrayList<Character></code>
     */
    private void createUserChoice(){
        for(char c : userStr.toCharArray()){
            userChoice.add(c);
        }
    }

    /**
     * changes the <code>ArrayList<Character></code> based off new user input for the new roll
     */
    private void changeUserChoice(){
        for(int i = 0; i < userChoice.size(); i++){
            userChoice.set(i,userStr.charAt(i));
        }
    }
}
