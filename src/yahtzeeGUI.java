import javax.swing.*;

public class yahtzeeGUI extends JFrame{

    private JPanel mainPanel;

    public yahtzeeGUI(String title){
        super(title);

        setSize(300,300);
        setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }
}
