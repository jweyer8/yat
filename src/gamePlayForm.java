import javax.swing.*;
import java.awt.*;


public class yahtzeeGUI extends JFrame{

    private JPanel mainPanel;
    private JPanel dieButtonPanel;

    private JButton dieButton1;
    private JButton dieButton2;
    private JButton dieButton3;
    private JButton dieButton4;
    private JButton dieButton5;
    private JButton dieButton6;
    private JButton dieButton7;
    private JButton dieButton8;
    private JButton dieButton9;
    private JButton dieButton10;
    private JButton dieButton11;
    private JButton dieButton12;


    public yahtzeeGUI(String title) {
        super(title);
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        dieButtonPanel.setVisible(false);
    }


    public void setDieButton(int numDie){
        int count = 1;
        for (Component c : dieButtonPanel.getComponents()) {
            if (c instanceof JButton){
                if(count <= numDie){
                    c.setBackground(Color.BLUE.PINK);
                    count++;
                }
                else{
                    c.setBackground(Color.YELLOW);
                    count++;
                }
            }
        }
    }
}
