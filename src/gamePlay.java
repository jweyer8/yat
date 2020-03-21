import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createEtchedBorder;

/**
 * Game play JFrame displays all game content
 */
public class gamePlay extends JFrame {

    //game play panel fields
    private JPanel rootPanel;
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
    private JPanel player2StatsPanel;
    private JButton finishButton;

    //die Icons (Images of die sides)
    Icon dieRoll = new ImageIcon("src/dieImages/dieRoll2.png");
    Icon dieTake = new ImageIcon("src/dieImages/dieTake.png");
    Icon dieEnd = new ImageIcon("src/dieImages/dieEnd.png");
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


    //contains the value of rounds played
    private int round = 0;
    //contains the turn in which a player is on
    private int turn = 0;
    //contains the number of players in the game
    private int playerCount = 0;
    //Number of dice in the hand
    private int numDice;
    //Number of players in game
    private int numPlayers;
    //Number of sides on the dice
    private int numSides;
    //Number of turns per player
    private int maxTurns = 3;
    //holds an array of die objects
    private Hand hand;
    //player that is winning
    int winP = 0;
    //score of winning player
    int winingScore = 0;



    /**
     * EVC for the gamePlay class
     *
     * @param title header of frame
     */
    public gamePlay(String title, int numPlayers,int numDice, int numSides) {
        super(title);
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(rootPanel);
        this.setVisible(true);

        //set size based on setup args
        switch(numSides){
            case 1:
                this.setPreferredSize(new Dimension(1000,410));
                break;
            case 2:
                this.setPreferredSize(new Dimension(1000,420));
                break;
            case 3:
                this.setPreferredSize(new Dimension(1000,430));
                break;
            case 4:
                this.setPreferredSize(new Dimension(1000,450));
                break;
            case 5:
                this.setPreferredSize(new Dimension(1000,470));
                break;
            case 6:
                this.setPreferredSize(new Dimension(1000,490));
                break;
            case 7:
                this.setPreferredSize(new Dimension(1000,510));
                break;
            case 8:
                this.setPreferredSize(new Dimension(1000,530));
                break;
            case 9:
                this.setPreferredSize(new Dimension(1000,550));
                break;
            case 10:
                this.setPreferredSize(new Dimension(1000,570));
                break;
            case 11:
                this.setPreferredSize(new Dimension(1000,590));
                break;
            case 12:
                this.setPreferredSize(new Dimension(1000,610));
                break;
        }


        //set game values
        this.numPlayers = numPlayers;
        this.numDice = numDice;
        this.numSides = numSides;
        hand = new Hand(numSides, numDice);

        //create borders
        Border border = dieButtonPanel.getBorder();
        Border margin = new EmptyBorder(20, 20, 20, 20);
        Border noSpace = new EmptyBorder(0,0,0,0);
        Border bl = BorderFactory.createCompoundBorder(new EtchedBorder(Color.black,Color.white),noSpace);
        Border margin2 = new EmptyBorder(75, 5, 0, 0);
        Border cbl = BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0),new EtchedBorder(Color.black,Color.white));

        //set borders
        gameStatsPanel.setPreferredSize(new Dimension(500,500));
        RollButton.setBorder(new EmptyBorder(5,5,5,5));
        takeScoreButton.setBorder(new EmptyBorder(5,5,5,5));
        playerFinalScoreLabel.setBorder(new EmptyBorder(0,5,5,5));
        scorecardLabel.setBorder(new EmptyBorder(0,5,5,5));
        player1StatsLabel.setBorder(new EmptyBorder(10,10,10,10));
        player2StatsLabel.setBorder(new EmptyBorder(10,10,10,10));
        player3StatsLabel.setBorder(new EmptyBorder(10,10,10,10));
        player4StatsLabel.setBorder(new EmptyBorder(10,10,10,10));
        pl1.setBorder(new EmptyBorder(10,10,10,10));
        pl2.setBorder(new EmptyBorder(10,10,10,10));
        pl3.setBorder(new EmptyBorder(10,10,10,10));
        pl4.setBorder(new EmptyBorder(10,10,10,10));
        rootPanel.setBorder(new CompoundBorder(border,margin));
        scorecardLabel.setBorder(bl);
        playerFinalScoreLabel.setBorder(bl);
        dieButtonPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(Color.black,Color.white),new EmptyBorder(20,20,20,20)));
        gameStatsPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(Color.black,Color.white),new EmptyBorder(20,20,20,20)));
        selectScoreCombo.setBorder(cbl);
        selectScoreLabel.setBorder(cbl);
        scoreFunctionPanel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(Color.black,Color.white), new EmptyBorder(10,10,10,10)));
        //border for the die buttons
        for(Component c : gameStatsPanel.getComponents()){
            ((JPanel)c).setBorder(bl);
        }


        //set text for components
        Font s1 = new Font("serif",Font.PLAIN,20);
        Font s2 = new Font("serif",Font.PLAIN,11);
        Font s3 = new Font("serif",Font.PLAIN,15);
        scorecardLabel.setFont(s2);
        playerFinalScoreLabel.setFont(s2);
        selectScoreLabel.setFont(s2);
        player1StatsLabel.setFont(s2);
        player2StatsLabel.setFont(s2);
        player3StatsLabel.setFont(s2);
        player4StatsLabel.setFont(s2);
        pl1.setFont(s3);
        pl2.setFont(s3);
        pl3.setFont(s3);
        pl4.setFont(s3);
        selectScoreCombo.setFont(s2);
        playerLabel.setFont(s1);
        turnLabel.setFont(s1);
        takeScoreButton.setIcon(dieTake);
        RollButton.setIcon(dieRoll);
        finishButton.setIcon(dieEnd);
        selectScoreLabel.setText("<html><br/><b>  &nbsp &nbsp &nbsp SELECT SCORE &nbsp &nbsp &nbsp &nbsp   </b><br/><br/></html>");
        selectScoreCombo.setPrototypeDisplayValue("                                  ");
        turnLabel.setText(turnLabel.getText() + " " + Integer.toString(turn));

        //set visibility of components
        finishButton.setVisible(false);
        scoreFunctionPanel.setVisible(false);
        scorecardLabel.setVisible(false);
        selectScoreCombo.setVisible(false);
        selectScoreLabel.setVisible(false);
        selectScoreCombo.setEnabled(false);
        takeScoreButton.setVisible(false);
        playerFinalScoreLabel.setVisible(false);
        //set player stat components visible based on number of players
        int counter = 0;
        for(Component c : gameStatsPanel.getComponents()){
            if(c instanceof JPanel){
                if (counter >= numPlayers){
                    c.setVisible(false);
                }
                counter++;
            }
        }
        //set die buttons visible based on number of die in the game
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


        //intitialize player array
        //Holds the player objects
        ArrayList<Player> players = new ArrayList<>();

        //Initialize player objects
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(numSides, numDice));
        }

        //number of rounds in a game
        int numRounds = 1;//players.get(0).getChoices().size();

        //array of player scores
        ArrayList<Integer> playerScores = new ArrayList<>();
        for(int i = 0; i<numPlayers; i++){
            playerScores.add(0);
        }

        //Set same action listener for all die buttons
        for (Component c : dieButtonPanel.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //set visibilty and enabled componenets
                        RollButton.setEnabled(true);
                        takeScoreButton.setVisible(false);

                        //Preform actions based on whether the die has already been  selected
                        if (((JButton) c).isSelected() == false) {
                            ((JButton) c).setSelected(true);
                            Border border = createEtchedBorder(Color.white, Color.green);
                            ((JButton) c).setBorder(border);

                            //change the die objects to selected if die button is selected
                            for (int i = 0; i < dieButtonPanel.getComponentCount(); i++) {
                                if (((JButton) c).equals(dieButtonPanel.getComponent(i))) {
                                    hand.setDieKeep('y', i);
                                }
                            }
                        }

                        //if die button already selected then change back to non selected
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
                        allDieSelected(players);
                    }
                });
            }
        }

        //set what to do when player pushes roll button
        RollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //enable die buttons so that user can interact
                for (Component c : dieButtonPanel.getComponents()) {
                    if (c instanceof JButton) {
                        ((JButton)c).setEnabled(true);
                    }
                }

                //do nececary backend for the certain turn
                hand.rollDice();
                Scoring score = new Scoring(hand,players.get(playerCount));
                turn++;
                players.get(playerCount).clearSb();

                //set visibility and text of components
                scoreFunctionPanel.setVisible(true);
                playerFinalScoreLabel.setVisible(true);
                playerFinalScoreLabel.setText(players.get(playerCount).printFinalCard().toString());
                selectScoreCombo.setVisible(true);
                selectScoreLabel.setVisible(true);
                gameStatsPanel.setVisible(true);
                scorecardLabel.setText(score.printScore().toString());
                scorecardLabel.setVisible(true);


                //set the images that die button displays based on values of hand
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

                //when no more turns make user select a score for that round and reset for next player
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

        //set actions to be preformed when a user selects a score for the round
        takeScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosen = selectScoreCombo.getSelectedItem().toString();
                Scoring score = new Scoring(hand,players.get(playerCount));
                score.clearScores();
                score.printScore();
                players.get(playerCount).setChosenRow(chosen,score.getScores());
                scoreFunctionPanel.setVisible(false);

                //get all players total scores and display, winning player will be shown

                //set player scores
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

                //change border of winning player
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

                //reset for next player
                RollButton.setEnabled(true);
                turn = 0;
                playerCount++;

                if(playerCount == numPlayers){
                    playerCount = 0;
                    round++;
                    if(round == numRounds){
                        finishButton.setVisible(true);
                        RollButton.setVisible(false);
                    }
                }

                //reset components for next player
                playerLabel.setText("PLAYER " + Integer.toString(playerCount + 1));
                turnLabel.setText("TURN 0");
                selectScoreCombo.setSelectedIndex(0);
                selectScoreCombo.setEnabled(false);
                takeScoreButton.setVisible(false);
                selectScoreLabel.setVisible(false);
                selectScoreCombo.setVisible(false);
                scorecardLabel.setVisible(false);
                RollButton.setEnabled(true);

                //reset buttons for next player
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

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame gameOver = new gameOver("",playerScores,players,winP,winingScore);
                gameOver.setVisible(true);
                gameOver.setPreferredSize(new Dimension(50,50));
            }
        });

        this.pack();
    }


    /**
     * sets the combo box based off what options the player has left
     *
     * @param players holds array of player objects
     * @param playerNum holds the position of the certain player in the players array
     */
    public void setComboItems(ArrayList<Player> players,int playerNum){
        ArrayList<String> used = players.get(playerNum).getUsed(); //the rows that the player has used
        ArrayList<String> choices = players.get(playerNum).getChoices(); //all the rows avalible to player
        selectScoreCombo.setMaximumRowCount(choices.size() - used.size()); //make so no scroll bar
        selectScoreCombo.removeAllItems(); //reset combobox
        boolean isUsed = false;

        //create combo box option for all rows that the player hasnt used
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

    /**
     * allows player to take score if all die are select even tho they have more turn avalible to them
     *
     * @param players array of player objects
     */
    public void allDieSelected(ArrayList<Player> players) {
        boolean allSelected = true;
        for (Component c : dieButtonPanel.getComponents()) {
            if(c instanceof JButton) {
                if (((JButton) c).isSelected() == false && ((JButton)c).isVisible() == true){
                    allSelected = false;
                }
            }
        }
        if (allSelected == true) {
            RollButton.setEnabled(false);
            setComboItems(players,playerCount);
            selectScoreCombo.setEnabled(true);
            takeScoreButton.setVisible(true);
        }
    }

}



