import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createEtchedBorder;

/**
 * Game play JFrame displays all game content
 *
 * @author Jared Weyer
 * @version 1.0
 */
public class GamePlay extends JFrame {
    //game play panel fields

    /**
     * main JPanel for gamePlay frame
     */
    private JPanel rootPanel;
    /**
     * holds the die buttons
     */
    private JPanel dieButtonPanel;
    /**
     * die one button
     */
    private JButton dieButton1;
    /**
     * die two button
     */
    private JButton dieButton2;
    /**
     * die three button
     */
    private JButton dieButton3;
    /**
     * die four button
     */
    private JButton dieButton4;
    /**
     * die five button
     */
    private JButton dieButton5;
    /**
     * die six button
     */
    private JButton dieButton6;
    /**
     * die seven button
     */
    private JButton dieButton7;
    /**
     * die eight button
     */
    private JButton dieButton8;
    /**
     * die nine button
     */
    private JButton dieButton9;
    /**
     * die ten button
     */
    private JButton dieButton10;
    /**
     * die eleven button
     */
    private JButton dieButton11;
    /**
     * die twelve button
     */
    private JButton dieButton12;
    /**
     * button to roll the selected dice
     */
    private JButton RollButton;
    /**
     * label showing which players turn it is
     */
    private JLabel playerLabel;
    /**
     * label that shows the scores for the current hand
     */
    private JLabel scorecardLabel;
    /**
     * label that shows how many rolls the current player has used
     */
    private JLabel turnLabel;
    /**
     * combo box for selecting which score the player would like to take for the round
     */
    private JComboBox selectScoreCombo;
    /**
     * panel that contains the Final scorecard, scores for the round, and the selection combo box
     */
    private JPanel scoreFunctionPanel;
    /**
     * combo box label
     */
    private JLabel selectScoreLabel;
    /**
     * button for taking the score which has been selected and ending turn for given player
     */
    private JButton takeScoreButton;
    /**
     * label that shows the final scorecard for the given player
     */
    private JLabel playerFinalScoreLabel;
    /**
     * panel that includes the stats for all players in the game
     */
    private JPanel gameStatsPanel;
    /**
     * contains player1StatsLabel
     */
    private JPanel player1StatsPanel;
    /**
     * contains player2StatsLabel
     */
    private JPanel player2StatsPanel;
    /**
     * private JPanel player3StatsPanel;
     */
    private JPanel player3StatsPanel;
    /**
     * contains player4StatsLabel
     */
    private JPanel player4StatsPanel;
    /**
     * shows player ones total score
     */
    private JLabel player1StatsLabel;
    /**
     * shows player twos total score
     */
    private JLabel player2StatsLabel;
    /**
     * shows player threes total score
     */
    private JLabel player3StatsLabel;
    /**
     * shows player fours final score
     */
    private JLabel player4StatsLabel;
    /**
     * label of total score for player 1
     */
    private JLabel pl1;
    /**
     * label of total score for player 2
     */
    private JLabel pl2;
    /**
     * label of total score for player 3
     */
    private JLabel pl3;
    /**
     * label of total score for player 4
     */
    private JLabel pl4;
    /**
     * button for ending game
     */
    private JButton finishButton;
    private JButton finalScoreButton;

    //die Icons (Images of die sides)
    private Icon dieRoll = new ImageIcon("src/dieImages/dieRoll.png");
    private Icon dieSee = new ImageIcon("src/dieImages/seeDie.png");
    private Icon dieTake = new ImageIcon("src/dieImages/dieTake.png");
    private Icon dieEnd = new ImageIcon("src/dieImages/dieEnd.png");
    private Icon die0 = new ImageIcon("src/dieImages/die0.png");
    private Icon die1 = new ImageIcon("src/dieImages/die1.png");
    private Icon die2 = new ImageIcon("src/dieImages/die2.png");
    private Icon die3 = new ImageIcon("src/dieImages/die3.png");
    private Icon die4 = new ImageIcon("src/dieImages/die4.png");
    private Icon die5 = new ImageIcon("src/dieImages/die5.png");
    private Icon die6 = new ImageIcon("src/dieImages/die6.png");
    private Icon die7 = new ImageIcon("src/dieImages/die7.png");
    private Icon die8 = new ImageIcon("src/dieImages/die8.png");
    private Icon die9 = new ImageIcon("src/dieImages/die9.png");
    private Icon die10 = new ImageIcon("src/dieImages/die10.png");
    private Icon die11 = new ImageIcon("src/dieImages/die11.png");
    private Icon die12 = new ImageIcon("src/dieImages/die12.png");


    /**
     * contains the value of rounds played
     */
    private int round = 0;
    /**
     * contains the turn which a player is on
     */
    private int turn = 0;
    /**
     * contains which players turn it is
     */
    private int playerCount = 0;
    /**
     * number of dice in hand
     */
    private int numDice;
    /**
     * number of players in game
     */
    private int numPlayers;
    /**
     * number of sides on a die
     */
    private int numSides;
    /**
     * the number of rolls a player gets per turn
     */
    private int maxTurns = 3;
    /**
     * hold an array of die objects
     */
    private Hand hand;
    /**
     * player that is winning the game currently
     */
    int winP = 0;
    /**
     * score of player that is winning the game
     */
    int winingScore = 0;


    /**
     * EVC for the gamePlay class
     *
     * @param title sets title of JFrame
     * @param numPlayers number of players in game
     * @param numDice number of dice in game
     * @param numSides number of sides on a dice
     */
    public GamePlay(String title, int numPlayers, int numDice, int numSides) {
        super(title);
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(rootPanel);
        this.setVisible(true);

        //set size based on setup args
        switch(numSides){
            case 1:
                this.setPreferredSize(new Dimension(1000,420));
                break;
            case 2:
                this.setPreferredSize(new Dimension(1000,430));
                break;
            case 3:
                this.setPreferredSize(new Dimension(1000,440));
                break;
            case 4:
                this.setPreferredSize(new Dimension(1000,460));
                break;
            case 5:
                this.setPreferredSize(new Dimension(1000,480));
                break;
            case 6:
                this.setPreferredSize(new Dimension(1000,500));
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
        finalScoreButton.setBorder(new EmptyBorder(5,5,5,5));
        finalScoreButton.setIcon(dieSee);
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
        finalScoreButton.setVisible(false);
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
        int numRounds = players.get(0).getChoices().size();

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
                playerFinalScoreLabel.setVisible(false);
                finalScoreButton.setVisible(true);
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
                finalScoreButton.setVisible(false);
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
                JFrame gameOver = new GameOver("",playerScores,players,winP,winingScore);
                gameOver.setVisible(true);
                gameOver.setPreferredSize(new Dimension(50,50));
            }
        });

        finalScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(finalScoreButton.isSelected() == true){
                    playerFinalScoreLabel.setVisible(false);
                    finalScoreButton.setSelected(false);
                }
                else{
                    finalScoreButton.setSelected(true);
                    playerFinalScoreLabel.setVisible(true);
                }
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



