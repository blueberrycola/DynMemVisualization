package guipackage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Algorithm {
    //Vars responsible for instruction interpretation
    String algos[] = {"First-Fit", "Best-Fit", "Worst-Fit"};
    String buffer[] = new String[128];
    int instrSize = 0; //Behaves like length() function (index + 1)
    int memorySize = 0;
    String path = "C:\\Users\\chase-pc\\Documents\\GitHub\\javaDynMemGUI\\src\\main\\java\\guipackage\\";
    //Supports up to 16 processes for a single image of memory
    int partitionbuffer[] = new int[16];
    /***
     *  Void function responsible for loading instructions from txt file
     *  into string buffer to be tokenized later. FIXME: pwd + instructions
     *  instead of hard coding path
     */
    public void readInstr() {
        BufferedReader reader;
        path += "instructions";
        try {
            int i = 1;
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            buffer[0] = line;
            while(line != null) {
                line = reader.readLine();
                buffer[i] = line;
                i++;
            }
            instrSize = i + 1;
            System.out.println("Finished!");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        algo.readInstr();
    }


    
}
