import java.util.ArrayList;

public class User {
    private String userStr;
    private int len;
    private ArrayList<Character> userChoice = new ArrayList<>();

    private void createUserChoice(){
        for(char c : userStr.toCharArray()){
            userChoice.add(c);
        }
    }

    private void changeUserChoice(){
        for(int i = 0; i < userChoice.size(); i++){
            userChoice.set(i,userStr.charAt(i));
       }
    }

    //DVC
    public User(String userStr){
        this.userStr = userStr;
        len = userStr.length();
        createUserChoice();
    }

    public void setUserStr(String userStr){
        this.userStr = userStr;
        changeUserChoice();
    }

    public ArrayList<Character> getUserChoice(){return userChoice;}

    public String getUserStr(){return userStr;}

    public int getUserLen(){return len;}

}
