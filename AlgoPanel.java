package guipackage;

import javax.swing.*;
import java.awt.*;
public class AlgoPanel extends JPanel {
    JLabel displayField[] = new JLabel[38];
    ImageIcon colors[] = new ImageIcon[17]; //16 Processes + Possiblility of free
    String state[] = new String[38];
    
    String colorName[] = {"FREE", "GREEN", "RED",
                         "BLUE", "YELLOW", "DRED",
                        "DBLUE", "ORANGE", "PURPLE",
                        "SALMON", "SKYBLUE", "BROWN",
                        "LIME"}; //This array matches with the color ImageIcon indices
    int pxLeng = 210;
    int pxWidth = 587;
    String[] buff;
    //Constructor of panel
    public AlgoPanel(String[] buffer) {
        //Setup Panel Layout. Images stack top to bottom
        buff = buffer; //Load instructions into panel
        this.setMaximumSize(new Dimension(pxLeng, pxWidth));
        GridLayout experimentLayout = new GridLayout(38,2);
        this.setLayout(experimentLayout);
        //Load Memory Map Image
        try {
            //Initialize Color Icons for Panel
            colors[0] = new ImageIcon(getClass().getResource("fill.png"));
            colors[1] = new ImageIcon(getClass().getResource("green.png"));
            colors[2] = new ImageIcon(getClass().getResource("red.png"));
            colors[3] = new ImageIcon(getClass().getResource("blue.png"));
            colors[4] = new ImageIcon(getClass().getResource("yellow.png"));
            colors[5] = new ImageIcon(getClass().getResource("dred.png"));
            colors[6] = new ImageIcon(getClass().getResource("dblue.png"));
            colors[7] = new ImageIcon(getClass().getResource("orange.png"));
            colors[8] = new ImageIcon(getClass().getResource("purp.png"));
            colors[9] = new ImageIcon(getClass().getResource("salmon.png"));
            colors[10] = new ImageIcon(getClass().getResource("skyblue.png"));
            colors[11] = new ImageIcon(getClass().getResource("brown.png"));
            colors[12] = new ImageIcon(getClass().getResource("lime.png"));
            //On initialization of panel all tiles are set to grey
            for(int i = 0; i < 38; i++) {
                displayField[i] = new JLabel(colors[0]);
                this.add(displayField[i]);
                state[i] = "FREE"; //Grey color string
            }

            //VERY IMPORTANT THIS IS HOW TO PARTITION THE PANEL BY PROCESS
            displayField[5].setIcon(colors[1]);
            displayField[10].setIcon(colors[1]);
            
            displayField[15].setIcon(colors[7]);

            
            
            
        } catch (Exception e) {
            System.out.println("Image cannot be found");
        }
    }

    
    //Setters for modifying panel, TEST ME
    public void setPanel(int index, int colorid) {
        if(index >= 38) {
            System.out.println("ERROR INVALID PARAM" + "(" + index + ":" + "changePanel()");
            System.exit(0);
        } else {
            
            displayField[index].setIcon(colors[colorid]);
            state[index] = colorName[colorid];
        }
    }
    //Getter for panel color, TEST ME
    public String getPanel(int index) {
        return state[index];
    }  
}