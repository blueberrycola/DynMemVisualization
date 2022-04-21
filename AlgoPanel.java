package guipackage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.*;
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
    ArrayList<String> labelpositions = new ArrayList<String>();
    int l = 0;
    JLabel procText[] = new JLabel[32];
    
    int pxLeng = 310;
    int pxWidth = 887;
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
                state[i][0] = "0"; //Free Process
            }

            

            
            
            
        } catch (Exception e) {
            System.out.println("Image cannot be found");
        }
    }
    /**
     * Function used to find index interval of block(s) of free memory
     * so it can be properly labeled.
     */
    ArrayList<String> freeblocks = new ArrayList<String>();
    public void findFree() {
        freeblocks = new ArrayList<String>();
        String temp = "";
        boolean startFound = false;
        boolean endFound = false;
        int start = 0;
        int end = 0;
        for(int i = 0; i < 38; i++) {
            if(state[i][0].equals("0") && !startFound) {
                //Start of free memory segment found
                start = i;
                startFound = true;
            } else if(!state[i][0].equals("0") && startFound && !endFound) {
                //Single space of memory
                end = start;
                startFound = false; //Reset to find another space of free mem
                temp = "" + start + " " + end;
                temp += " " + "100";
                freeblocks.add(temp);
                temp = "";
                
            }
            else if(state[i][0].equals("0") && startFound) {
                //Space of memory bigger than one tile
                end = i;
                endFound = true; //Free memory bigger than one tile found
            } else if((!state[i][0].equals("0") && startFound && endFound)) {
                //End segment discovered; create segment string
                temp = "" + start + " " + end;
                
                int size = 0;
                for(int j = start; j <= end; j++) {
                    size += 100;
                    
                }
                temp += " " + size;
                freeblocks.add(temp);
                //Reset start and end found
                startFound = false;
                endFound = false;
                temp = "";
            }
        }
        //Case for free memory that ends at last index
        if(start != 0 && startFound && endFound) {
            //End segment discovered; create segment string
            temp = "" + start + " " + end;
                
            int size = 0;
            for(int j = start; j <= end; j++) {
                size += 100;
                
            }
            temp += " " + size;
            freeblocks.add(temp);
            //Reset start and end found
            startFound = false;
            endFound = false;
            temp = "";
        }
        
    }
    int procNum = 1;
    public void blankSlate() {
        for(int i = 0; i < 38; i++) {
            if(i == 0) {
                displayField[i].setText("  FREE:                    (3800)");
            } else {displayField[i].setText("                                   "); }
            
        }
    }
    
    /**
     * Function used to add data relating to memory displayed on the stack.
     */
    public void renderLabels() {
        //Render Free memory
        findFree();
        for(int i = 0; i < freeblocks.size(); i++) {
            String arg = freeblocks.get(i);
            String[] cmd = arg.split(" ");
            int start = Integer.parseInt(cmd[0]);
            int end = Integer.parseInt(cmd[1]);
            int size = Integer.parseInt(cmd[2]);
            //Print start label of FREE
            String str = "FREE" + " Size (" + size + ")";
            displayField[start].setText(str);
            for(int j = start + 1; j <= end; j++) {
                displayField[j].setText("                             ");
            }
        }
        
        if(l != 0) {
            
            for(int i = 0; i < l; i++) {
                String cmd = labelpositions.get(i);
                String buff[] = cmd.split(" ");
                //Size is found by (buff[1]+1) - buff[0]) * 100
                String pnum = state[Integer.parseInt(buff[0])][0];
                System.out.println("PNUM: " + pnum);
                String arg = "     P" + pnum + " Size (" + (((Integer.parseInt(buff[1]) + 1) - Integer.parseInt(buff[0])) * 100) + ")";
                displayField[Integer.parseInt(buff[0])].setText(arg);
                
                int start = Integer.parseInt(buff[0]);
                int end = Integer.parseInt(buff[1]);
                
                for(int j = start + 1; j <= end; j++) {
                    displayField[j].setText("                             ");
                }

                
            }
            
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

    public int[] getProcInterval(int p) {
        int[] temp = {0, 0};
        boolean startFound = false;
        //Find process color
        for(int i = 0; i < 38; i++) {
            if(state[i][1] != null) {
                if(Integer.parseInt(state[i][0]) == p && !startFound) {
                    System.out.println("COLOR FOUND");
                    System.out.println(state[i][1]);
                    temp[0] = i;
                    startFound = true;
                } else if(Integer.parseInt(state[i][0]) == p) {
                    temp[1] = i;
                }
            }
            
        }
        String cmd = "" + temp[0] + " " + temp[1];
        labelpositions.remove(cmd);
        l--;
        return temp;
    }
    //Retrieves interval for start command in FirstFit scenario
    public int[] ffIndex(int size) {
        //Find the first available section of memory
        int start = 0;
        int end = 1;
        int cluster = getCluster(size)-1;
        int interval[] = new int[2];
        boolean startEnable = false;
        boolean endEnable = false;
        int temp = cluster;
        //BASECASE: If size < 100
        if(size <= 100) {
            for(int i = 0; i <= 37; i++){
                if(state[i][1] != null) {
                    if(state[i][1].equals("FREE")) {
                        interval[0] = i;
                        interval[1] = i;
                        break;
                    }
                }
            }
        } else {
            //Iterate until i and cluster size is = and all tiles are "FREE"
            for(int i = 0; i <= 37; i++) {
                //System.out.println(state[i][1]);
                if(state[i][1] != null) {
                    if(state[i][1].equals("FREE") && !startEnable) {
                        start = i;
                        startEnable = true;
                    } else if(state[i][1].equals("FREE") && temp != 0) {
                        end = i;
                        endEnable = true;
                        temp--;
                    } else if(!state[i][1].equals("FREE") && temp != 0) {
                        startEnable = false;
                        temp = cluster;
                    }
                    
                    if(temp == 0) {
                        interval[0] = start;
                        interval[1] = end;
                        
                    }
                }
            }
        }
        System.out.println("INTERVAL FOUND: " + start + " " + end);
        
        //IMPORTANT: used for renderLabel()
        String word = "" + interval[0] + " " + interval[1];
        labelpositions.add(word);
        l++; 
        
        return interval;
    }
    public void setMemSize(int s) {
        memSize = s;
    }
    public int[] bfIndex(int size) {
        int[] interval = new int[2];
        //Best-Fit will behave like First-Fit until the first block of memory is removed.
        if(freeblocks.isEmpty() || freeblocks.size() == 1) {
            return ffIndex(size);
        } else {
            //Refresh state of free memory
            findFree();

            //Scan thru each size of available free mem. Pick the one closest to actual param
            int[] comps = new int[freeblocks.size()];
            int comp = 0;
            for(int i = 0; i < freeblocks.size(); i++) {
                String str = freeblocks.get(i);
                
                String[] cmd = str.split(" ");
                int start = Integer.parseInt(cmd[0]);
                int end = Integer.parseInt(cmd[1]);
                comp = Integer.parseInt(cmd[2]);
                comp = comp - size;
                
                //Store comp into array at matching index with freeblocks.get findMin and choose
                if(comp >= 0) {
                    comps[i] = comp;
                } else {
                    comp = -42; //Labels as too big
                }
                    
                
                
                

            }
            System.out.println("TESTING COMPS");
            int min = comps[0];
            int minIndex = 0;
            for(int i = 1; i < freeblocks.size(); i++) {
                System.out.println(comps);
                if(comps[i] == 0) {
                    //Access freeblocks entry and set panel accordingly
                    minIndex = i;
                    break;
                } else {
                    //Find min of comps[i]
                    if(min > comps[i] && comps[i] > 0) {
                        min = comps[i];
                        minIndex = i;
                    } else {
                        
                    }

                }
                
            }
            //An interval has been decided
            String cmd = freeblocks.get(minIndex);
            String[] intervalcmd = cmd.split(" ");
            interval[0] = Integer.parseInt(intervalcmd[0]);
            int stepback = comps[minIndex] / 100;
            interval[1] = Integer.parseInt(intervalcmd[1]) - stepback;
        
            //IMPORTANT: used for renderLabel()
            String word = "" + interval[0] + " " + interval[1];
            labelpositions.add(word);
            l++; //END OF IMPORTANT renderLabel() vars
            
        }
        return interval;
    }
    int colorRotation = 1;
    public void recvInstr(String arg) {
        System.out.println(arg);
        String temp[] = arg.split(" ");
        if(colorRotation > 12) {
            colorRotation = 2;
        }
        //Parse first string, remove 'P' and parseInt the rest
        int proc = Integer.parseInt(temp[0].substring(1));
        //Examine second string
        if(temp[1].equals("start")) {
            int size = Integer.parseInt(temp[2]);
            //index determined by algo, colorid determined by rotation (i % colorIDsize + 1)
            int[] interval;
            if(this.algorithm.equals("First-Fit")) {
                interval = ffIndex(size);
                System.out.println("INTERVAL: " + interval[0] + " " + interval[1]);

                for(int i = interval[0]; i <= interval[1]; i++) {
                    //Change state
                    setPanel(i, proc, colorRotation);
                }
                colorRotation++;
                
                
            } else if(this.algorithm.equals("Best-Fit")) {
                interval = bfIndex(size);
                System.out.println(interval);
                System.out.println("INTERVAL: " + interval[0] + " " + interval[1]);

                for(int i = interval[0]; i <= interval[1]; i++) {
                    //Change state
                    setPanel(i, proc, colorRotation);
                }
                colorRotation++;
                
            }
            

        } else if (temp[1].equals("end")) {
            //Free array elements with matching proc num
            int[] interval;
            //Since regardless of algorithm a process will be removed no
            //if statements are required
            System.out.println("PROCESS: " + proc);
            interval = getProcInterval(proc);
            System.out.println(interval[0] + " " + interval[1]);
            //Case for when you are removing only one tile
            if(interval[0] > interval[1]) {
                freePanel(interval[0]);
            } else {
                
                for(int i = interval[0]; i <= interval[1]; i++) {
                    freePanel(i);
                }
            }

            
            
            
            
        }
        //Find free regions after placing new process
        findFree();
        renderLabels();

        //If start then parse 3rd string
    }

    public void debugInfo() {
        if(this.algorithm.equals("Best-Fit")) {
            
            
            
            for(int i = 0; i < freeblocks.size(); i++) {
                System.out.println(freeblocks.get(i));
            }
            System.out.println("ALLOC:");
            for(int i = 0; i < labelpositions.size(); i++) {
                System.out.println(labelpositions.get(i));
            }
            
        }
        
        
        
    }
    //Setters for modifying panel
    public void setPanel(int index, int proc, int colorid) {
        if(index >= 38) {
            System.out.println("ERROR INVALID PARAM" + "(" + index + ":" + "changePanel()");
            System.exit(0);
        } else {
            //Change STATE of panel
            displayField[index].setIcon(colors[colorid]);
            state[index][1] = colorName[colorid];
            state[index][0] = "" + proc;
        }
    }

    public void freePanel(int index) {
        if(index >= 38) {
            System.out.println("ERROR INVALID PARAM" + "(" + index + ":" + "changePanel()");
            System.exit(0);
        } else {
            //Change STATE of panel
            displayField[index].setIcon(colors[0]);
            state[index][1] = colorName[0];
            state[index][0] = "0";
            
        }
    }
    //Getter for panel color, TEST ME
    public String getPanel(int index) {
        return state[index][1];
    }
    public String getProc(int index) {
        return state[index][0];
    }
      
}