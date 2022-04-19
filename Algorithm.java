package guipackage;

import java.lang.*;

public class Algorithm {
    //Vars responsible for instruction interpretation
    String algos[] = {"First-Fit", "Best-Fit", "Worst-Fit"};
    int instrSize = 3800; //Behaves like length() function (index + 1)
    String algorithm; //Name of algorithm
    

    int partitionbuffer[][] = new int[16][2];    //Data of each process. second dimension is used for if process now free or active (0, 1)
    int memorySize = 0;
    String[][] allocTable = new String[16][2];
    String[][] freeTable = new String[16][2];
        

    

    public void setAlgo(String a) {
        this.algorithm = a;
    }
    
    /***
     * A void function responsible for taking a single line of instruction
     * input and seperating each word by space into a temporary array.
     * 
     * Tokenized input then calls functions relating to keywords
     * ie: the first line with an integer number will set variable memorySize
     * @param arg -> a single element of buffer to be tokenized
     */
    public void execInstr(String arg) {
        System.out.println("Test");
        //0: process num, 1: command, 2: if start, declare size
        String[] temp = arg.split(" ");
        //Read proc number
        int proc = Integer.parseInt(String.valueOf(temp[0].charAt(1)));
        //Interpret either start/end command
        if(temp[1].equals("start")) {
            //Start procedure
            System.out.println("START");
            //Turn temp[2] into int
            int size = Integer.parseInt(temp[2]);
            System.out.println(size);
        } else if(temp[1].equals("end")) {
            System.out.println("END");
            //End Procedure
        } else {
            //Throw error
            System.out.println("Error: Invalid Arg");
        }
        
    }
    //Calls execInstr until at end of buffer
    public void autoRunListener() {
        System.out.println("Button Test");
    }
    //Treats buffer like a single linked list. Terminates at NULL
    public void stepListener() {
        System.out.println("Step Test");
    }
    


    public static void main(String args[]) {
        Algorithm algo = new Algorithm();
        
        algo.execInstr("P1 start 400");
    }


    
}
