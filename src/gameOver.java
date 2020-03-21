import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class gameOver extends JFrame{
    private JPanel gameOverRoot;
    private JPanel gameOverPanel;
    private JLabel gameOverLabel1;
    private JLabel winerLabel;

    public gameOver(String title,int winner,int winnerVal){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gameOverRoot);
        this.setVisible(true);

        gameOverLabel1.setFont(new Font("Serif", Font.PLAIN, 60));
        gameOverRoot.setPreferredSize(new Dimension(50,50));
        this.setPreferredSize(new Dimension(1000,600));
        gameOverLabel1.setVisible(true);
        winerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        winerLabel.setText("<html><center>Player " + Integer.toString(winner) + " wins</center> <br/><center> with a score of " + Integer.toString(winnerVal) + "</center>");
        this.pack();
    }
}
