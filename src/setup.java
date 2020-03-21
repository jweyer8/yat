import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Setup frame which gets the games basic values from the user
 */
public class setup extends JFrame{
    private JComboBox playerCombo;
    private JComboBox dieCombo;
    private JComboBox sidesCombo;
    private JButton startButton;
    private JLabel welcomeLabel;
    private JPanel gameSetupPanel;
    private JPanel setupRoot;
    private JLabel playerLabel;
    private JLabel dieLabel;
    private JLabel sidesLabel;

    //basic game numbers
    private int numPlayers = 0;
    private int numDice = 0;
    private int numSides = 0;

    /**
     * EVC for the setup class
     *
     * @param title title of the frame
     */
    public setup(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(setupRoot);

        //setting component looks
        gameSetupPanel.setBorder(new EmptyBorder(20,20,20,20));
        setupRoot.setBorder(new EmptyBorder(20,20,20,20));
        Border border = new EtchedBorder(Color.black,Color.white);
        startButton.setBorder(new CompoundBorder(new EtchedBorder(Color.black,Color.white),new EmptyBorder(5,5,5,5)));
        sidesCombo.setBorder(border);
        dieCombo.setBorder(border);
        playerCombo.setBorder(border);
        startButton.setPreferredSize(new Dimension(50,50));
        welcomeLabel.setFont(new Font("serif",Font.PLAIN,30));
        dieLabel.setFont(new Font("serif",Font.PLAIN,15));
        sidesLabel.setFont(new Font("serif",Font.PLAIN,15));
        playerLabel.setFont(new Font("serif",Font.PLAIN,15));



        //set combo items
        for(int i = 1; i <= 4; i++){
            playerCombo.addItem(Integer.toString(i));
        }

        for(int i = 1; i <= 12; i++){
            dieCombo.addItem(Integer.toString(i));
            sidesCombo.addItem(Integer.toString(i));
        }

        //set the max length of combo and set the default game values
        dieCombo.setMaximumRowCount(3);
        dieCombo.setSelectedIndex(4);
        playerCombo.setMaximumRowCount(3);
        playerCombo.setSelectedIndex(3);
        sidesCombo.setMaximumRowCount(3);
        sidesCombo.setSelectedIndex(5);



        //set actions for when start button is pushed
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //get rid of this frame
                //get game values
                numPlayers = (Integer) (Integer.parseInt(playerCombo.getSelectedItem().toString().trim()));
                numDice = (Integer) (Integer.parseInt(dieCombo.getSelectedItem().toString().trim()));
                numSides = (Integer) (Integer.parseInt(sidesCombo.getSelectedItem().toString().trim()));

                //create the main game frame
                JFrame game = new gamePlay("", numPlayers, numDice, numSides);
            }
        });
    }

}
