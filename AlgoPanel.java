package guipackage;

import javax.swing.*;
import java.awt.*;
public class AlgoPanel extends JPanel {
    JLabel displayField[] = new JLabel[38];
    ImageIcon colors[] = new ImageIcon[17]; //16 Processes + Possiblility of free
    String state[][] = new String[38][2]; //index, 0: procnum, 1:color
    String algorithm; //Variable used to determine how recvInstr will react
    String colorName[] = {"FREE", "GREEN", "RED",
                         "BLUE", "YELLOW", "DRED",
                        "DBLUE", "ORANGE", "PURPLE",
                        "SALMON", "SKYBLUE", "BROWN",
                        "LIME"}; //This array matches with the color ImageIcon indices
    int pxLeng = 210;
    int pxWidth = 587;
    String[] buff;
    int memSize = 0;
    //Constructor of panel
    public AlgoPanel(String arg) {
        //Setup Panel Layout. Images stack top to bottom
        algorithm = arg;
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
                state[i][1] = "FREE"; //Grey color string
            }

            //VERY IMPORTANT THIS IS HOW TO PARTITION THE PANEL BY PROCESS
            displayField[5].setIcon(colors[1]);
            displayField[10].setIcon(colors[1]);
            
            displayField[15].setIcon(colors[7]);

            
            
            
        } catch (Exception e) {
            System.out.println("Image cannot be found");
        }
    }
    public int getCluster(int size) {
        int clusterMod = size % 100;
        int clusterSize = size / 100;
        if(clusterMod == 0) {
            return clusterSize;
        } else {
            clusterSize++;
        }
        return clusterSize;
    }

    public int ffIndex(int size) {
        //Find the first available section of memory
        int start = 0;
        int end = 1;
        int cluster = getCluster(size);
        //Iterate until i and cluster size is = and all tiles are "FREE"
        for(int i = 0; i < 37; i++) {
            System.out.println(state[i][1]);
            if(state[i][1].equals("FREE")) {
                start = i;
                

            }
        }
        return 0;
    }
    public void setMemSize(int s) {
        memSize = s;
    }


    public void recvInstr(String arg) {
        System.out.println(arg);
        String temp[] = arg.split(" ");
        //Parse first string, remove 'P' and parseInt the rest
        int proc = Integer.parseInt(temp[0].substring(1));
        //Examine second string
        if(temp[1].equals("start")) {
            int size = Integer.parseInt(temp[2]);
            //index determined by algo, colorid determined by rotation (i % colorIDsize + 1)
            if(this.algorithm.equals("First-Fit")) {
                int index = ffIndex(size);
            }
            

        } else if (temp[1].equals("end")) {
            //Free array elements with matching proc num
        }

        //If start then parse 3rd string
    }

    
    //Setters for modifying panel, TEST ME
    public void setPanel(int procnum, int index, int colorid, int size) {
        if(index >= 38) {
            System.out.println("ERROR INVALID PARAM" + "(" + index + ":" + "changePanel()");
            System.exit(0);
        } else {
            
            displayField[index].setIcon(colors[colorid]);
            state[index][1] = colorName[colorid];
        }
    }
    //Getter for panel color, TEST ME
    public String getPanel(int index) {
        return state[index][1];
    }  
}