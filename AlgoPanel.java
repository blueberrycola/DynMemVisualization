package guipackage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.Border;
import java.awt.*;

/**
 *
 * @author chase-pc
 */
public class AlgoPanel extends JPanel {
    private boolean[] free = new boolean[16];
    private ImageIcon fill, memMap;
    private JLabel fillLabel;
    //Set state and images
    public AlgoPanel() {
        fill = new ImageIcon("fill.png");
        memMap = new ImageIcon("memMap.png");
        setBackground(Color.GRAY);
        fillLabel = new JLabel("This is a test");
        add(fillLabel);
    }
    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        //Add memory map image to panel
        fillLabel.setIcon(memMap);
    }


   
    
}
