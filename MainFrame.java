package guipackage;
import javax.swing.*;
public class MainFrame extends JFrame{
    JFrame frame;
    JLabel title, displayField1, displayField2, displayField3;
    JLabel memAmount;
    
    AlgoPanel firstFit, bestFit, worstFit;
    JPanel titlePanel, mainPanel, buttonPanel, tablePanel, instrPanel;
    public MainFrame() {
        frame = new JFrame("Dynamic Memory Visualization: Made by Chase Johnston");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Houses the 3 memory map diagrams.
        mainPanel = new JPanel();
        //Panel holds Autorun and Step button
        buttonPanel = new JPanel();
        //Panel holds tables corresponding the each algo
        tablePanel = new JPanel();
        //Panel holds text data containing instructions
        instrPanel = new JPanel();

        firstFit = new AlgoPanel();
        bestFit = new AlgoPanel();
        worstFit = new AlgoPanel();

        mainPanel.add(firstFit);
        mainPanel.add(bestFit);
        mainPanel.add(worstFit);
        frame.add(mainPanel);
        frame.setSize(1000, 950);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        
        MainFrame f = new MainFrame();
    }
}
