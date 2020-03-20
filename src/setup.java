import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class setup extends JFrame{
    private JComboBox playerCombo;
    private JComboBox dieCombo;
    private JComboBox sidesCombo;
    private JButton startButton;
    private JLabel welcomeLabel;
    private JPanel gameSetupPanel;
    private JPanel setupRoot;

    public setup(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(setupRoot);



        playerCombo.setVisible(true);
        dieCombo.setVisible(true);
        sidesCombo.setVisible(true);
        welcomeLabel.setVisible(true);
        Border border = new EtchedBorder(Color.black,Color.white);
        startButton.setBorder(border);
        repaint();

        this.pack();
    }
}
