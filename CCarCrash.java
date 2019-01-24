import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
 
 
public class CCarCrash extends JFrame implements ActionListener
{
	// panels
	private JPanel jPCentre, jPBottomRight, jPBottomLeft;
	private JPanel jPRight, jPRightTop, jPRightBottom, jPRightMiddle, jPRightMovement;
	
	// text fields in top right
    private JTextField jTFOption;
    private int nOption = 1;
    private JTextField jTFSquare;
    private int nSquare = 17;
    private JTextField jTFDirection;
    private String sDirection = "E";
    
    
    
    // labels in right top
    private JLabel jLOption, jLSquare, jLDirection;

    // labels in right middle
    private JLabel jLTimer;
    private JLabel jLTimerColons;
    
    // text fields in right middle
    private JTextField jTFTimer1, jTFTimer2, jTFTimer3;
    
    // buttons options and exit (right hand side buttons)
    private JButton jBOption1, jBOption2, jBOption3;
    private JButton jBExit;
    // buttons for bottom area
    private JButton jBAct, jBRun, jBReset;
    
    // buttons for directional keys
    // buttons for top row labelled top left top middle etc
    private JButton jBTM;
    // buttons for middle row labelled middle left etc
    private JButton jBML, jBBlank, jBMR;
    // buttons for bottom row labelled bottom left etc
    private JButton jBBM;
    
    // slider with slider label
    private JSlider jSSlider;
    private JLabel jLSlider;
    
 
    public static void main (String[] args)
    {
        CCarCrash frame = new CCarCrash();
        frame.setSize(810, 650);
        frame.createGUI();
        frame.setVisible(true);
    }
    private void blankButtons()
    {
    	jBBlank = new JButton();
    	jBBlank.setEnabled(false);
    	jPRightMovement.add(jBBlank);
    	
    }
    private void timerColons()
    {
    	jLTimerColons = new JLabel(":");
    	jPRightMiddle.add(jLTimerColons);
    }
 
    private void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() ); // sets items to centre into the window
        
        // panels section
        
        //Centre
        jPCentre = new JPanel();
        jPCentre.setPreferredSize(new Dimension(600, 560));
        jPCentre.setBackground(Color.orange);
        window.add(jPCentre);
        
        // right
        jPRight = new JPanel();
        jPRight.setPreferredSize(new Dimension(180, 560));
        // jPRight.setBackground(Color.magenta);
        window.add(jPRight);
        
        // inside right
        
        jPRightTop = new JPanel();
        jPRightTop.setPreferredSize(new Dimension(180, 100));
        // jPRightTop.setBackground(Color.gray);
        jPRightTop.setLayout(new GridLayout(3, 2));
        jPRight.add(jPRightTop);
        
        jPRightMovement = new JPanel();
        jPRightMovement.setPreferredSize(new Dimension(180, 100));
        jPRightMovement.setBackground(Color.WHITE);
        jPRightMovement.setLayout(new GridLayout(3, 3));
        jPRight.add(jPRightMovement);
        
        
        // inside right middle area
        
        jPRightMiddle = new JPanel();
        jPRightMiddle.setPreferredSize(new Dimension(90, 125));
        // jPRightMiddle.setBackground(Color.green);
        jPRight.add(jPRightMiddle);
        
        
        // inside right bottom area
        jPRightBottom = new JPanel();
        jPRightBottom.setPreferredSize(new Dimension(180, 75));
        jPRightBottom.setBackground(Color.gray);
        jPRightBottom.setLayout(new GridLayout(2, 2));
        jPRight.add(jPRightBottom);
        
        // bottom
        jPBottomLeft = new JPanel();
        jPBottomLeft.setPreferredSize(new Dimension (500, 40));
        jPBottomLeft.setBackground(Color.cyan);
        window.add(jPBottomLeft);
        
        jPBottomRight = new JPanel();
        jPBottomRight.setPreferredSize(new Dimension(280, 40));
        jPBottomRight.setBackground(Color.BLUE);
        window.add(jPBottomRight);
        
        
        //git test fun times
        // panel right top
        // Panel right top labels and text fields
        // Options label and text field
        jLOption = new JLabel("Option: ");
        jPRightTop.add(jLOption); 
        jTFOption = new JTextField(""+nOption);
        jPRightTop.add(jTFOption);
        
        // square area label and text field
        jLSquare = new JLabel("Square: ");
        jPRightTop.add(jLSquare);
        jTFSquare = new JTextField(""+nSquare);
        jPRightTop.add(jTFSquare);
        
        // Direction area label and text field
        jLDirection = new JLabel("Direction: ");
        jPRightTop.add(jLDirection);
        jTFDirection = new JTextField(sDirection);
        jPRightTop.add(jTFDirection);
        
        //panel right movement underneath right top
        
        // area for directional buttons 
        // buttons for top row
        blankButtons();

        jBTM = new JButton("^");
        jPRightMovement.add(jBTM);
        
        blankButtons();

        //buttons for middle row
        jBML = new JButton("<");
        jPRightMovement.add(jBML);
        
        blankButtons();
        
        jBMR = new JButton(">");
        jPRightMovement.add(jBMR);
        
        //buttons for bottom row
        blankButtons();
        
        jBBM = new JButton("v");
        jPRightMovement.add(jBBM);
        
        blankButtons();
        
        //area for right middle 
        // 3 text fields and a label required?
        jLTimer = new JLabel("DIGITAL TIMER");
        jPRightMiddle.add(jLTimer);
        
        jTFTimer1 = new JTextField("00");
        jTFTimer1.setBackground(Color.BLACK);
        jTFTimer1.setForeground(Color.WHITE);
        jPRightMiddle.add(jTFTimer1);
        
        timerColons();
        
        jTFTimer2 = new JTextField("00");
        jTFTimer2.setBackground(Color.BLACK);
        jTFTimer2.setForeground(Color.WHITE);
        jPRightMiddle.add(jTFTimer2);
        
        timerColons();
        
        jTFTimer3 = new JTextField("00");
        jTFTimer3.setBackground(Color.BLACK);
        jTFTimer3.setForeground(Color.WHITE);
        jPRightMiddle.add(jTFTimer3);
        
        
        // buttons exit option 1-3 placed within rightbottom
        jBOption1 = new JButton("Option 1");
        jPRightBottom.add(jBOption1);
        jBOption1.addActionListener(this);
        
        jBOption2 = new JButton("Option 2");
        jPRightBottom.add(jBOption2);
        jBOption2.addActionListener(this);
        
        jBOption3 = new JButton("Option 3");
        jPRightBottom.add(jBOption3);
        jBOption3.addActionListener(this);
        
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
        jSSlider.setValue(25);
        // jSSlider.setBackground(Color.BLUE); you can set the colour of the background too!
        jPBottomRight.add(jSSlider);
        
        
    }
   // public void actionPerformed(ActionEvent event)
   // {
   // 	System.exit(0);
   // }
    public void actionPerformed(ActionEvent event)
    { 
    	// nOption = nOption+1;
    	jTFOption.setText(""+(nOption+1));
    	nOption = nOption+1;
    	nSquare = nSquare+1;
    	jTFSquare.setText(""+nSquare);
    	jTFDirection.setText("S");
    	act();
    	run();
    	reset();
    	option1();
    	option2();
    	option3();
    	exit();
    	
    }
    // method act section
    public void act()
    {
    	System.out.println("method act is working");
    }
    
    //method run section
    public void run()
    {
    	System.out.println("method run is working");
    }
    
    //method reset
    public void reset()
    {
    	System.out.println("method reset is working");
    }
    
    //method option1
    public void option1()
    {
    	System.out.println("method option1 is working");
    }
    
    //method option2
    public void option2()
    {
    	System.out.println("method option2 is working");
    }
    
    //method option3
    public void option3()
    {
    	System.out.println("method option1 is working");
    }
    
    //method exit
    public void exit()
    {
    	System.out.println("exit method is working");
    }
    
}