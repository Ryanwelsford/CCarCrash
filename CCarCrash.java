import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
 
 
public class CCarCrash extends JFrame implements ActionListener, ChangeListener
{
	// panels
	private JPanel jPCentre, jPBottomRight, jPBottomLeft;
	private JPanel jPRight, jPRightTop, jPRightBottom, jPRightMiddle,jPRightMiddleTimer, jPRightMovement;
	
	// Right top
	// text fields in top right and variables used within right top
    private JTextField jTFOption;
    private int nOption = 1;
    private JTextField jTFSquare;
    private int nSquare = 17;
    private JTextField jTFDirection;
    private String sDirection = "E";
    
    
    
    // labels in right top
    private JLabel jLOption, jLSquare, jLDirection;
    
    // right middle
    // labels in right middle
    private JLabel jLTimer;
    private JLabel jLTimerColons;
    
    // text fields in right middle
    private JTextField jTFTimerHours, jTFTimerMinutes, jTFTimerSeconds;
    
    // timer for hours, mins, secs.
    private javax.swing.Timer secsTimer;
    int secs = 0;
    
    // buttons options and exit (right hand side buttons)
    private JButton jBOption1, jBOption2, jBOption3;
    private JButton jBExit;
    
    
    // buttons for directional keys
    // buttons for top row labelled top left top middle etc
    private JButton jBTM;
    // buttons for middle row labelled middle left etc
    private JButton jBML, jBBlank, jBMR;
    // buttons for bottom row labelled bottom left etc
    private JButton jBBM;
    // bottom bar 
    // buttons for bottom area
    private JButton jBAct, jBRun, jBReset;
    
    // slider with slider label
    private JSlider jSSlider;
    private JLabel jLSlider;
    // timer for slider
    private javax.swing.Timer sliderTimer;
    
    
 
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
    	jBBlank.setBackground(Color.lightGray);
    	jPRightMovement.add(jBBlank);
    	
    }
    private void timerColons()
    {
    	jLTimerColons = new JLabel(":");
    	jLTimerColons.setHorizontalAlignment(JLabel.CENTER);
    	jPRightMiddleTimer.add(jLTimerColons);
    }
    private void secsTimer()
    {
        jTFTimerHours.setText(Integer.toString((secs/3600)%60));
        // set to 3600 secs in an hour + modulus 60 to prevent higher than 60 appearing in field
        jTFTimerMinutes.setText(Integer.toString((secs/60)%60));
        // 60 seconds in a minute modulus 60 prevents over 60 from being in field
        jTFTimerSeconds.setText(Integer.toString(secs%60));
        secs++;
    }
    private void sliderTimer()
    {
    	int speedValue = jSSlider.getValue();
        sliderTimer = new Timer(1000, this);
        sliderTimer.start();
    	sliderTimer.setDelay(speedValue);
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
        jPRightMiddle.setPreferredSize(new Dimension(100, 20));
        // jPRightMiddle.setBackground(Color.green);
        jPRight.add(jPRightMiddle);
        
        jPRightMiddleTimer = new JPanel();
        jPRightMiddleTimer.setPreferredSize(new Dimension(130, 20));
        // jPRightMiddleTimer.setBackground(Color.pink);
        jPRightMiddleTimer.setLayout(new GridLayout(1, 5));
        jPRight.add(jPRightMiddleTimer);
        
        
        // inside right bottom area
        jPRightBottom = new JPanel();
        jPRightBottom.setPreferredSize(new Dimension(180, 75));
        // jPRightBottom.setBackground(Color.gray);
        jPRightBottom.setLayout(new GridLayout(2, 2));
        jPRight.add(jPRightBottom);
        
        // bottom
        jPBottomLeft = new JPanel();
        jPBottomLeft.setPreferredSize(new Dimension (500, 40));
        // jPBottomLeft.setBackground(Color.cyan);
        window.add(jPBottomLeft);
        
        jPBottomRight = new JPanel();
        jPBottomRight.setPreferredSize(new Dimension(280, 40));
        // jPBottomRight.setBackground(Color.BLUE);
        window.add(jPBottomRight);
        
        
        //git test fun times
        // panel right top
        // Panel right top labels and text fields
        // Options label and text field
        jLOption = new JLabel("Option: ");
        jPRightTop.add(jLOption); 
        jTFOption = new JTextField(""+nOption);
        jTFOption.setHorizontalAlignment(JTextField.CENTER);
        jPRightTop.add(jTFOption);
        
        // square area label and text field
        jLSquare = new JLabel("Square: ");
        jPRightTop.add(jLSquare);
        jTFSquare = new JTextField(""+nSquare);
        jTFSquare.setHorizontalAlignment(JTextField.CENTER);
        jPRightTop.add(jTFSquare);
        
        // Direction area label and text field
        jLDirection = new JLabel("Direction: ");
        jPRightTop.add(jLDirection);
        jTFDirection = new JTextField(sDirection);
        jTFDirection.setHorizontalAlignment(JTextField.CENTER);
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
        
        jTFTimerHours = new JTextField("00");
        jTFTimerHours.setBackground(Color.BLACK);
        jTFTimerHours.setForeground(Color.WHITE);
        jPRightMiddleTimer.add(jTFTimerHours);
        
        timerColons();
        
        jTFTimerMinutes = new JTextField("00");
        jTFTimerMinutes.setBackground(Color.BLACK);
        jTFTimerMinutes.setForeground(Color.WHITE);
        jPRightMiddleTimer.add(jTFTimerMinutes);
        
        timerColons();
        
        jTFTimerSeconds = new JTextField("00");
        jTFTimerSeconds.setBackground(Color.BLACK);
        jTFTimerSeconds.setForeground(Color.WHITE);
        jPRightMiddleTimer.add(jTFTimerSeconds);
        
        secsTimer = new Timer(1000, this);
        secsTimer.start();
        
        
        
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
        // there must be a simplier way of adding an image to a button?
        try {
            Image img = ImageIO.read(getClass().getResource("resources/step.png"));
            jBAct.setIcon(new ImageIcon(img));
          } 
        catch (Exception ex) {
            System.out.println(ex);
          }


        jPBottomLeft.add(jBAct);
        // this way is much simplier than try/ catch thing
        ImageIcon runImg = new ImageIcon(getClass().getResource("resources/run.png"));
        
        jBRun = new JButton("Run", runImg);
        jPBottomLeft.add(jBRun);
        
        jBReset = new JButton("Reset");
        jPBottomLeft.add(jBReset);
        
        // slider section
        jLSlider = new JLabel("Speed:");
        jPBottomRight.add(jLSlider);
        
        jSSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        jSSlider.setMajorTickSpacing(25);
        jSSlider.setPaintTicks(true);
        jSSlider.addChangeListener(this);
       // jSSlider.setInverted(true); not sure if this works or not?

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
    	/* jTFOption.setText(""+(nOption+1));
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
    	exit(); */
    	secsTimer();
    	
    	
    }
    public void stateChanged(ChangeEvent event)
    {
    	sliderTimer();
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