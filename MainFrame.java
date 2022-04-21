package guipackage;
import java.awt.BorderLayout;
import java.io.*;
import javax.swing.*;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;
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
        firstFit.blankSlate();
        bestFit = new AlgoPanel("Best-Fit");
        bestFit.blankSlate();
        worstFit = new AlgoPanel("Worst-Fit");
        worstFit.blankSlate();
        //Load instruction buffer
        loadInstr();
        
        mainPanel.add(firstFit);
        mainPanel.add(bestFit);
        mainPanel.add(worstFit);
        
        JLabel algo = new JLabel();
        algo.setText("First-Fit                                                                     Best-Fit                                                                                                   Worst-Fit");
        JPanel algolabel = new JPanel();
        algolabel.add(algo);
        frame.add(algolabel, BorderLayout.PAGE_START);
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
        bestFit.recvInstr(buffer[step_i]);
        worstFit.recvInstr(buffer[step_i]);
        step_i++;
    }
    
    public void autorun() {
        for(int i = step_i; i < 124; i++) {
            if(buffer[i] != null) {
                try {
                    Thread.sleep(100);
                    
                } catch (Exception e) {
                    System.out.println(e);
                }
                step();

            }
            

        }

    }
    
    public static void main(String args[]) {
        
        MainFrame f = new MainFrame();
    }
}
