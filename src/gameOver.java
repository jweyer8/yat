import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * JFrame that displays the end game window
 *
 * @author Jared Weyer
 * @version 1.0
 */
public class gameOver extends JFrame{
    //game of fields

    /**
     * gameover Panel
     */
    private JPanel gameOverPanel;
    /**
     * main panel for frame
     */
    private JPanel gameOverRoot;
    /**
     * label for game over txt
     */
    private JLabel gameOverLabel1;
    /**
     * label for displaying winning player and his score
     */
    private JLabel winerLabel;
    /**
     * contains all labels for players final scores
     */
    private JPanel statsPanel;
    /**
     * label for player 1 final score
     */
    private JLabel p1;
    /**
     * label for player 2 final score
     */
    private JLabel p2;
    /**
     * label for player 3 final score
     */
    private JLabel p3;
    /**
     * label for player 4 final score
     */
    private JLabel p4;
    /**
     * list of players final scores
     */
    private ArrayList<Integer> playerScore = new ArrayList<>();
    /**
     * array of player objects
     */
    private ArrayList<Player> players = new ArrayList<>();



    /**
     * EVC for the gameOver class
     *
     * @param title title of frame
     * @param playerScores array of player final scores
     * @param players array of Player objects
     * @param winner the position of the winning player in the playerScores array {link: #playerScores}
     * @param winnerVal the score of the winning player
     */
    public gameOver(String title, ArrayList<Integer> playerScores,ArrayList<Player> players,int winner,int winnerVal){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gameOverRoot);
        this.setVisible(true);
        this.players = players;
        this.playerScore = playerScores;

        //set the look of the frame and components
        gameOverLabel1.setFont(new Font("Serif", Font.PLAIN, 60));
        gameOverRoot.setPreferredSize(new Dimension(50,50));
        this.setPreferredSize(new Dimension(1000,600));
        gameOverLabel1.setVisible(true);
        winerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        winerLabel.setText("<html><center>Player " + Integer.toString(winner + 1) + " wins</center> <br/><center> with a score of " + Integer.toString(winnerVal) + "</center>");
        p1.setFont(new Font("Serif", Font.PLAIN, 15));
        p2.setFont(new Font("Serif", Font.PLAIN, 15));
        p3.setFont(new Font("Serif", Font.PLAIN, 15));
        p4.setFont(new Font("Serif", Font.PLAIN, 15));

        //display the final scores of all players
        int counter = 0;
        for(Component c : statsPanel.getComponents()){
            if(c instanceof JLabel && counter < players.size()){
                ((JLabel)c).setText("<html><center>PLAYER " + Integer.toString(counter + 1) + "'S SCORE </center><br/><center>" + Integer.toString(playerScores.get(counter)) + "</center> </html>");
                ((JLabel)c).setBorder(new CompoundBorder(new EtchedBorder(Color.black,Color.white), new EmptyBorder(20,20,20,20)));
            }
            else{
                c.setVisible(false);
            }
            counter++;
        }
        this.pack();
    }
}
