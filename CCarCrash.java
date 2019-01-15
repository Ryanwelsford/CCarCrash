import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
 
public class CCarCrash extends JFrame implements ActionListener
{
	// panels
	private JPanel jPCentre, jPBottomRight, jPBottomLeft;
	private JPanel jPRight, jPRightTop, jPRightBottom, jPRightMiddle;
	// text fields
    private JTextField jTFOption;
    
    // labels
    private JLabel jLOption;
    
    // buttons options and exit (right hand side buttons)
    private JButton jBOption1, jBOption2, jBOption3;
    private JButton jBExit;
    // buttons for bottom area
    private JButton jBAct, jBRun, jBReset;
    
    // slider with slider label
    private JSlider jSSlider;
    private JLabel jLSlider;
    
 
    public static void main (String[] args)
    {
        CCarCrash frame = new CCarCrash();
        frame.setSize(810, 650);
        frame.createGUI();
        frame.show();
    }
 
    private void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() );
        
        // panels section
        
        //Centre
        jPCentre = new JPanel();
        jPCentre.setPreferredSize(new Dimension(600, 500));
        jPCentre.setBackground(Color.orange);
        window.add(jPCentre);
        
        // right
        jPRight = new JPanel();
        jPRight.setPreferredSize(new Dimension(180, 500));
        jPRight.setBackground(Color.magenta);
        window.add(jPRight);
        
        // inside right
        
        jPRightTop = new JPanel();
        jPRightTop.setPreferredSize(new Dimension(180, 100));
        jPRightTop.setBackground(Color.gray);
        jPRight.add(jPRightTop);
        
        jPRightMiddle = new JPanel();
        jPRightMiddle.setPreferredSize(new Dimension(180, 300));
        jPRightMiddle.setBackground(Color.green);
        jPRight.add(jPRightMiddle);
        
        jPRightBottom = new JPanel();
        jPRightBottom.setPreferredSize(new Dimension(180, 100));
        jPRightBottom.setBackground(Color.gray);
        jPRight.add(jPRightBottom);
        
        // bottom
        jPBottomLeft = new JPanel();
        jPBottomLeft.setPreferredSize(new Dimension (500,150));
        jPBottomLeft.setBackground(Color.cyan);
        window.add(jPBottomLeft);
        
        jPBottomRight = new JPanel();
        jPBottomRight.setPreferredSize(new Dimension(280, 150));
        jPBottomRight.setBackground(Color.BLUE);
        window.add(jPBottomRight);
        
        
        //git test fun times
        
        // labels section
        jLOption = new JLabel("Option");
        jPRightMiddle.add(jLOption); 
        
        // text fields section
        jTFOption = new JTextField("1");
        jPRightMiddle.add(jTFOption);
        
        // buttons exit option 1-3 placed within rightbottom
        jBOption1 = new JButton("Option 1");
        jPRightBottom.add(jBOption1);
        
        jBOption2 = new JButton("Option 2");
        jPRightBottom.add(jBOption2);
        
        jBOption3 = new JButton("Option 3");
        jPRightBottom.add(jBOption3);
        
        jBExit = new JButton("Exit");
        jPRightBottom.add(jBExit);
        jBExit.addActionListener(this);
        
        // buttons act run reset
        
        jBAct = new JButton("Act");
        jPBottomLeft.add(jBAct);
        
        jBRun = new JButton("Run");
        jPBottomLeft.add(jBRun);
        
        jBReset = new JButton("Reset");
        jPBottomLeft.add(jBReset);
        
        // slider section
        jLSlider = new JLabel("Speed:");
        jPBottomRight.add(jLSlider);
        
        jSSlider = new JSlider();
        jSSlider.setMajorTickSpacing(25);
        jSSlider.setPaintTicks(true);
        // jSSlider.setBackground(Color.BLUE); you can set the colour of the background too!
        jPBottomRight.add(jSSlider);
        
        
    }
    public void actionPerformed(ActionEvent event)
    {
    	System.exit(0);
    }
}