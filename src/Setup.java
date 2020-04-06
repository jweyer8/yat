import sun.plugin2.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Setup frame which gets the games basic values from the user
 *
 * @author Jared Weyer
 * @version 1.0
 */
public class Setup extends JFrame{
    /**
     * combo box for selecting the amount of players in the game
     */
    private JComboBox playerCombo;
    /**
     * combo box for selecting the amount of die in the game
     */
    private JComboBox dieCombo;
    /**
     * combo box for selecting the amount of sides on a die
     */
    private JComboBox sidesCombo;
    /**
     * button for starting game
     */
    private JButton startButton;
    /**
     * label for welcome txt
     */
    private JLabel welcomeLabel;
    /**
     * holds the combo boxes for making game selections
     */
    private JPanel gameSetupPanel;
    /**
     * main panel for frame
     */
    private JPanel setupRoot;
    /**
     * label for player combo box
     */
    private JLabel playerLabel;
    /**
     * label for die combo box
     */
    private JLabel dieLabel;
    /**
     * label for sides combo box
     */
    private JLabel sidesLabel;

    //basic game numbers
    /**
     * number of players in game, is set by player combo box
     */
    private int numPlayers = 0;
    /**
     * number of dice in game, is set by die combo box
     */
    private int numDice = 0;
    /**
     * number of sides on a die, is set by sides combo box
     */
    private int numSides = 0;
    //Icon for spicy die
    private Icon spice = new ImageIcon("src/dieImages/spice.png");




    /**
     * EVC for the setup class
     *
     * @param title title of the frame
     */
    public Setup(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(setupRoot);

        //set visibility (change if we want user to be able to adjust number of dice and number of sides)
        dieCombo.setVisible(false);
        dieLabel.setVisible(false);
        sidesCombo.setVisible(false);
        sidesLabel.setVisible(false);
        playerCombo.setPrototypeDisplayValue("        ");


        //setting component looks
        Font font = new Font("forte",Font.PLAIN,15);
        Color fColor = new Color(193,34,11);
        gameSetupPanel.setBorder(new EmptyBorder(20,20,20,20));
        setupRoot.setBorder(new EmptyBorder(20,20,20,20));
        Border border = new EtchedBorder(fColor,Color.white);
        startButton.setBorder(new CompoundBorder(new EtchedBorder(fColor,Color.white),new EmptyBorder(5,5,5,5)));
        sidesCombo.setBorder(border);
        sidesCombo.setForeground(fColor);
        sidesCombo.setFont(font);
        dieCombo.setBorder(border);
        dieCombo.setFont(font);
        dieCombo.setForeground(fColor);
        dieCombo.setFont(font);
        playerCombo.setBorder(border);
        playerCombo.setForeground(fColor);
        playerCombo.setFont(font);
        startButton.setPreferredSize(new Dimension(50,50));
        dieLabel.setFont(font);
        dieLabel.setForeground(Color.white);
        sidesLabel.setFont(font);
        sidesLabel.setForeground(Color.white);
        playerLabel.setFont(font);
        playerLabel.setForeground(Color.white);
        welcomeLabel.setText("");
        welcomeLabel.setIcon(spice);
        startButton.setFont( new Font("forte",Font.PLAIN,30));
        startButton.setForeground(Color.white);

        playerCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setBackground(Color.white);
                super.paint(g);
            }
        });

        dieCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setBackground(Color.white);
                super.paint(g);
            }
        });

        sidesCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setBackground(Color.white);
                super.paint(g);
            }
        });


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
        dieCombo.setSelectedIndex(5);
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
                JFrame game = new GamePlay("", numPlayers, numDice, numSides);
            }
        });
    }

}
