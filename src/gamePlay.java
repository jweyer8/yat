import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BorderFactory.createEtchedBorder;


public class gamePlay extends JFrame {

    private JPanel rootPanel;

    //game play panel fields
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
    private JButton RollButton;
    private JLabel playerLabel;
    private JLabel scorecardLabel;
    private JLabel turnLabel;
    private JComboBox selectScoreCombo;
    private JPanel rollButtonPanel;
    private JPanel scoreFunctionPanel;
    private JLabel selectScoreLabel;
    private JButton takeScoreButton;
    private JLabel playerFinalScoreLabel;
    private JPanel gameStatsPanel;
    private JPanel player1StatsPanel;
    private JPanel player3StatsPanel;
    private JPanel player4StatsPanel;
    private JLabel player1StatsLabel;
    private JLabel player2StatsLabel;
    private JLabel player3StatsLabel;
    private JLabel player4StatsLabel;
    private JLabel pl1;
    private JLabel pl2;
    private JLabel pl3;
    private JLabel pl4;


    //die Icons
    Icon die0 = new ImageIcon("src/dieImages/die0.png");
    Icon die1 = new ImageIcon("src/dieImages/die1.png");
    Icon die2 = new ImageIcon("src/dieImages/die2.png");
    Icon die3 = new ImageIcon("src/dieImages/die3.png");
    Icon die4 = new ImageIcon("src/dieImages/die4.png");
    Icon die5 = new ImageIcon("src/dieImages/die5.png");
    Icon die6 = new ImageIcon("src/dieImages/die6.png");
    Icon die7 = new ImageIcon("src/dieImages/die7.png");
    Icon die8 = new ImageIcon("src/dieImages/die8.png");
    Icon die9 = new ImageIcon("src/dieImages/die9.png");
    Icon die10 = new ImageIcon("src/dieImages/die10.png");
    Icon die11 = new ImageIcon("src/dieImages/die11.png");
    Icon die12 = new ImageIcon("src/dieImages/die12.png");

    private int round = 0;
    //
    private int turn = 0;
    //
    private int playerCount = 0;
    //Number of dice in the hand
    private int numDice = 6;
    //Number of players in game
    private int numPlayers = 2;
    //Number of sides on the dice
    private int numSides = 6;
    //Number of turns
    private int maxTurns = 3;
    //hand
    private Hand hand = new Hand(numSides, numDice);




    public gamePlay(String title) {
        super(title);
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(rootPanel);


        //create borders
        Border border = dieButtonPanel.getBorder();
        Border margin = new EmptyBorder(20, 20, 20, 20);
        Border noSpace = new EmptyBorder(0,0,0,0);
        rootPanel.setBorder(new CompoundBorder(border,margin));
        Border bl = BorderFactory.createCompoundBorder(new EtchedBorder(Color.black,Color.white),noSpace);
        scorecardLabel.setBorder(bl);
        playerFinalScoreLabel.setBorder(bl);
        dieButtonPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(Color.black,Color.white),new EmptyBorder(20,20,20,20)));
        gameStatsPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(Color.black,Color.white),new EmptyBorder(20,20,20,20)));
        gameStatsPanel.setVisible(false);
        scorecardLabel.setVisible(false);
        selectScoreCombo.setVisible(false);
        selectScoreLabel.setVisible(false);
        selectScoreCombo.setEnabled(false);
        Border margin2 = new EmptyBorder(75, 5, 0, 0);
        Border cbl = BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0),new EtchedBorder(Color.black,Color.white));
        selectScoreCombo.setBorder(cbl);
        selectScoreLabel.setBorder(cbl);
        selectScoreLabel.setText("<html><br/> &nbsp &nbsp &nbsp &nbsp SELECT SCORE &nbsp &nbsp &nbsp &nbsp  <br/><br/></html>");
        selectScoreCombo.setPrototypeDisplayValue("                                    ");
        takeScoreButton.setVisible(false);
        playerFinalScoreLabel.setVisible(false);

        for(Component c : gameStatsPanel.getComponents()){
            ((JPanel)c).setBorder(bl);
        }


        int counter = 0;
        for(Component c : gameStatsPanel.getComponents()){
            if(c instanceof JPanel){
                if (counter >= numPlayers){
                    c.setVisible(false);
                }
                counter++;
            }
        }

        counter = 0;
        for (Component c : dieButtonPanel.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).setOpaque(false);
                ((JButton) c).setFocusPainted(false);
                ((JButton) c).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                ((JButton) c).setEnabled(false);
                if (counter >= numDice){
                    c.setVisible(false);
                }
                counter++;
            }
        }

        turnLabel.setText(turnLabel.getText() + " " + Integer.toString(turn));

////////////////////////////////////////
        //Holds the player objects
        ArrayList<Player> players = new ArrayList<>();

        //Initialize player objects
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(numSides, numDice));
        }

        //Set number of rounds which is equal to the number of rows not including the bonus row
        int numRounds = 3;//players.get(0).getChoices().size();

       // Scoring score = new Scoring(hand,players.get(playerCount));


///////////////////////////////////////

        for (Component c : dieButtonPanel.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton) c).isSelected() == false) {
                            ((JButton) c).setSelected(true);
                            Border border = createEtchedBorder(Color.white, Color.green);
                            ((JButton) c).setBorder(border);
                            for (int i = 0; i < dieButtonPanel.getComponentCount(); i++) {
                                if (((JButton) c).equals(dieButtonPanel.getComponent(i))) {
                                    hand.setDieKeep('y', i);
                                }
                            }
                        }
                        else{
                            ((JButton) c).setSelected(false);
                            ((JButton) c).setOpaque(false);
                            ((JButton) c).setFocusPainted(false);
                            ((JButton) c).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                            for (int i = 0; i < dieButtonPanel.getComponentCount(); i++) {
                                if (((JButton) c).equals(dieButtonPanel.getComponent(i))) {
                                    hand.setDieKeep('n', i);
                                }
                            }
                        }
                        allDieSelected();
                    }
                });
            }
        }

        RollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component c : dieButtonPanel.getComponents()) {
                    if (c instanceof JButton) {
                        ((JButton)c).setEnabled(true);
                    }
                }

                hand.rollDice();
                turn++;
                playerFinalScoreLabel.setVisible(true);
                players.get(playerCount).clearSb();
                playerFinalScoreLabel.setText(players.get(playerCount).printFinalCard().toString());
                selectScoreCombo.setVisible(true);
                selectScoreLabel.setVisible(true);
                gameStatsPanel.setVisible(true);
                Scoring score = new Scoring(hand,players.get(playerCount));
                scorecardLabel.setText(score.printScore().toString());
                scorecardLabel.setVisible(true);
                int count = 0;
                for (Component c : dieButtonPanel.getComponents()) {
                    if (c instanceof JButton && count < numDice) {
                        switch (hand.getVals().get(count)) {
                            case 1:
                                ((JButton) c).setIcon(die1);
                                break;
                            case 2:
                                ((JButton) c).setIcon(die2);
                                break;
                            case 3:
                                ((JButton) c).setIcon(die3);
                                break;
                            case 4:
                                ((JButton) c).setIcon(die4);
                                break;
                            case 5:
                                ((JButton) c).setIcon(die5);
                                break;
                            case 6:
                                ((JButton) c).setIcon(die6);
                                break;
                            case 7:
                                ((JButton) c).setIcon(die7);
                                break;
                            case 8:
                                ((JButton) c).setIcon(die8);
                                break;
                            case 9:
                                ((JButton) c).setIcon(die9);
                                break;
                            case 10:
                                ((JButton) c).setIcon(die10);
                                break;
                            case 11:
                                ((JButton) c).setIcon(die11);
                                break;
                            case 12:
                                ((JButton) c).setIcon(die12);
                                break;
                            default:
                                ((JButton) c).setIcon(die12);
                        }
                        count++;
                    }
                }

                if (turn == maxTurns){
                    RollButton.setEnabled(false);
                    setComboItems(players,playerCount);
                    selectScoreCombo.setEnabled(true);
                    takeScoreButton.setVisible(true);

                    for (Component c : dieButtonPanel.getComponents()) {
                        if(c instanceof JButton){
                            ((JButton) c).setEnabled(false);
                            ((JButton) c).setOpaque(false);
                            ((JButton) c).setFocusPainted(false);
                            ((JButton) c).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                        }
                    }
                }

                turnLabel.setText("TURN " + Integer.toString(turn));
            }
        });

        takeScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosen = selectScoreCombo.getSelectedItem().toString();
                Scoring score = new Scoring(hand,players.get(playerCount));
                score.clearScores();
                score.printScore();
                players.get(playerCount).setChosenRow(chosen,score.getScores());
                gameStatsPanel.setVisible(false);

                ArrayList<Integer> playerScores = new ArrayList<>();
                for(int i = 0; i<numPlayers; i++){
                    playerScores.add(0);
                }
                int winingScore = 0;

                switch(playerCount){
                    case 0:
                        playerScores.set(0,players.get(playerCount).getFinalScore());
                        player1StatsLabel.setText("TOTAL SCORE " + playerScores.get(0));
                        break;
                    case 1:
                        playerScores.set(1,players.get(playerCount).getFinalScore());
                        player2StatsLabel.setText("TOTAL SCORE " + playerScores.get(1));
                        break;
                    case 2:
                        playerScores.set(2,players.get(playerCount).getFinalScore());
                        player3StatsLabel.setText("TOTAL SCORE " + playerScores.get(2));
                        break;
                    case 3:
                        playerScores.set(3,players.get(playerCount).getFinalScore());
                        player4StatsLabel.setText("TOTAL SCORE " + playerScores.get(3));
                        break;
                }

                int winP = 0;
                for(int i = 0; i<numPlayers; i++){
                    if(playerScores.get(i) > winingScore){
                        winP = i;
                        winingScore = playerScores.get(i);
                    }
                }

                for(Component c : gameStatsPanel.getComponents()){
                    ((JPanel)c).setBorder(bl);
                }

                if(winingScore != 0){
                    ((JPanel) gameStatsPanel.getComponent(winP)).setBorder(new EtchedBorder(Color.ORANGE,Color.white));
                }


                RollButton.setEnabled(true);
                turn = 0;
                playerCount++;

                if(playerCount == numPlayers){
                    playerCount = 0;
                    round++;
                    if(round == numRounds){
                        rootPanel.setVisible(false);
                    }
                }

                playerLabel.setText("PLAYER " + Integer.toString(playerCount + 1));
                turnLabel.setText("TURN 0");
                selectScoreCombo.setSelectedIndex(0);
                selectScoreCombo.setEnabled(false);
                takeScoreButton.setVisible(false);
                selectScoreLabel.setVisible(false);
                selectScoreCombo.setVisible(false);
                scorecardLabel.setVisible(false);
                playerFinalScoreLabel.setVisible(false);
                RollButton.setEnabled(true);

                for (Component c : dieButtonPanel.getComponents()) {
                    if(c instanceof JButton){
                        ((JButton) c).setIcon(die0);
                        ((JButton) c).setSelected(false);
                        ((JButton) c).setOpaque(false);
                        ((JButton) c).setFocusPainted(false);
                        ((JButton) c).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                        for (int i = 0; i < numDice; i++){
                            hand.setDieKeep('n',i);
                        }
                    }
                }

            }
        });
        this.pack();
    }



        public void setComboItems(ArrayList<Player> players,int playerNum){
            ArrayList<String> used = players.get(playerNum).getUsed();
            ArrayList<String> choices = players.get(playerNum).getChoices();
            selectScoreCombo.setMaximumRowCount(choices.size() - used.size());
            selectScoreCombo.removeAllItems();
            boolean isUsed = false;
            for (String s : choices){
                for (String s2: used){
                   if (s.equals(s2)){
                       isUsed = true;
                   }
                }
                if (!(isUsed)){
                    selectScoreCombo.addItem(s);
                }
              isUsed = false;
            }
        }

    public void allDieSelected() {
        boolean allSelected = true;
        for (Component c : dieButtonPanel.getComponents()) {
            if(c instanceof JButton) {
                if (((JButton) c).isSelected() == false) {
                    allSelected = false;
                }
            }
        }
        if (allSelected == true) {
            RollButton.setEnabled(false);
            selectScoreCombo.setEnabled(true);
        }
    }
}



