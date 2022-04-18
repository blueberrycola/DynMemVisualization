package guipackage;

import javax.swing.*;
import java.awt.*;
public class AlgoPanel extends JPanel {
    JLabel displayField[] = new JLabel[38];
    ImageIcon colors[] = new ImageIcon[17]; //16 Processes + Possiblility of free
    int pxLeng = 210;
    int pxWidth = 587;
    public AlgoPanel() {
        //Setup Panel Layout. Images stack top to bottom
        this.setMaximumSize(new Dimension(pxLeng, pxWidth));
        GridLayout experimentLayout = new GridLayout(38,2);
        this.setLayout(experimentLayout);
        //Load Memory Map Image
        try {
            colors[0] = new ImageIcon(getClass().getResource("fill.png"));
            colors[1] = new ImageIcon(getClass().getResource("green.png"));
            for(int i = 0; i < 38; i++) {
                displayField[i] = new JLabel(colors[0]);
                this.add(displayField[i]);
            }
            //VERY IMPORTANT THIS IS HOW TO PARTITION THE PANEL BY PROCESS
            displayField[5].setIcon(colors[1]);
            displayField[10].setIcon(colors[1]);

            
            
            
        } catch (Exception e) {
            System.out.println("Image cannot be found");
        }
    }

    
    //Setters for modifying panel


    //Insert
    
/*
    public static void main(String args[]) {
        AlgoPanel p = new AlgoPanel();
        JFrame f = new JFrame();
        f.add(p);
        f.setVisible(true);
    }

 */   
}