package guipackage;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener{
    

    JFrame frame;
    JLabel title, displayField1, displayField2, displayField3;
    JLabel memAmount;
    JButton autoRun, step;
    AlgoPanel firstFit, bestFit, worstFit;
    JPanel titlePanel, mainPanel, buttonPanel, tablePanel, instrPanel;
    int step_i = 1; //Step iterator
    //Instruction Variables
    String buffer[] = new String[128]; //Dictates instruction for all 3 panels
    int instrSize = 0;
    String path = "C:\\Users\\chase-pc\\Documents\\GitHub\\javaDynMemGUI\\src\\main\\java\\guipackage\\instructions.txt";
    String filepath = "instructions.txt"; //default value
    public MainFrame() {
        frame = new JFrame("Dynamic Memory Visualization: Made by Chase Johnston");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Houses the 3 memory map diagrams.
        mainPanel = new JPanel();
        autoRun = new JButton("Auto Run");
        step = new JButton("Step");
        step.addActionListener(this);
        autoRun.addActionListener(this);
        //Panel holds Autorun and Step button
        buttonPanel = new JPanel();
        buttonPanel.add(autoRun);
        buttonPanel.add(step);
        //Panel holds tables corresponding the each algo
        tablePanel = new JPanel();
        //Panel holds text data containing instructions
        instrPanel = new JPanel();

        firstFit = new AlgoPanel("First-Fit");
        bestFit = new AlgoPanel("Best-Fit");
        worstFit = new AlgoPanel("Worst-Fit");
        //Load instruction buffer
        loadInstr();
        
        mainPanel.add(firstFit);
        mainPanel.add(bestFit);
        mainPanel.add(worstFit);
        
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);
        frame.setSize(1000, 950);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == autoRun) {
            autorun();
        } else if(e.getSource() == step) {
            step();
        }
    }
    
    /***
     *  Void function responsible for loading instructions from txt file
     *  into string buffer to be split later. FIXME: pwd + instructions
     *  instead of hard coding path
     */
    public void loadInstr() {
        BufferedReader reader;
        
        try {
            int i = 1;
            
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            buffer[0] = line;
            //Set memory size for each panel
            firstFit.setMemSize(Integer.parseInt(buffer[0]));
            bestFit.setMemSize(Integer.parseInt(buffer[0]));
            worstFit.setMemSize(Integer.parseInt(buffer[0]));
            
            while(line != null) {
                line = reader.readLine();
                buffer[i] = line;
                i++;
            }
            instrSize = i;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public void step() {
        firstFit.recvInstr(buffer[step_i]);
        step_i++;
    }
    //BEING USED AS DEBUGGING TOOL FOR NOW
    public void autorun() {
        firstFit.debugInfo();

    }
    
    public static void main(String args[]) {
        
        MainFrame f = new MainFrame();
    }
}
