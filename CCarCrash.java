import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
 
public class CCarCrash extends JFrame implements ActionListener, ChangeListener
{
	// panels
	private JPanel jPCentre, jPBottomRight, jPBottomLeft;
	private JPanel jPRight, jPRightTop, jPRightBottom, jPRightMiddle,jPRightMiddleTimer, jPRightMovement, jPRightCompass, jPRightSpacer;
	
	// Right top
	// text fields in top right and variables used within right top
    private JTextField jTFOption;
    private int nOption = 1;
    private JTextField jTFSquare;
    private int nSquare = 17;
    private JTextField jTFDirection;
    private String sDirection = "E";
    
    // center panel area
    
    private JButton jBGridArea;
    private ImageIcon carImageEast = new ImageIcon(getClass().getResource("resources/car-e.png"));
    private ImageIcon carImageSouth = new ImageIcon(getClass().getResource("resources/car-s.png"));
    private ImageIcon carImageWest = new ImageIcon(getClass().getResource("resources/car-w.png"));
    private ImageIcon carImageNorth = new ImageIcon(getClass().getResource("resources/car-n.png"));
    private ImageIcon wallHorizontal = new ImageIcon(getClass().getResource("resources/wall-horiz.png"));
    private ImageIcon wallVertical = new ImageIcon(getClass().getResource("resources/wall-vert.png"));
    private ImageIcon wallTopLeft = new ImageIcon(getClass().getResource("resources/wall-NW.png"));
    private ImageIcon wallTopRight = new ImageIcon(getClass().getResource("resources/wall-NE.png"));
    private ImageIcon wallBottomLeft = new ImageIcon(getClass().getResource("resources/wall-SW.png"));
    private ImageIcon wallBottomRight = new ImageIcon(getClass().getResource("resources/wall-SE.png"));

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
    
    // compass area images and label
    private JLabel jLCompass;
    private ImageIcon compassE = new ImageIcon(getClass().getResource("resources/east.jpg"));
    private ImageIcon compassS = new ImageIcon(getClass().getResource("resources/south.jpg"));
    private ImageIcon compassW = new ImageIcon(getClass().getResource("resources/west.jpg"));
    private ImageIcon compassN = new ImageIcon(getClass().getResource("resources/north.jpg"));
    
    // buttons for directional keys
    // buttons labelled as bottom top middle
    private JButton jBTM;
    // buttons labelled as bottom middle left and right
    private JButton jBML, jBBlank, jBMR;
    // buttons for blank button is generic button used multiple times see blankbuttons()
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
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
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
        jTFTimerHours.setText(Integer.toString((secs/3600)%99));
        // set to 3600 secs in an hour + modulus 99 to prevent higher than 99 appearing in field
        jTFTimerMinutes.setText(Integer.toString((secs/60)%60));
        // 60 seconds in a minute modulus 60 prevents over 60 from being in field
        jTFTimerSeconds.setText(Integer.toString(secs%60));
        secs++;
    }
    private void sliderTimer()
    {
    	int speedValue = jSSlider.getValue();
    	System.out.println(speedValue);
    	secsTimer.setDelay(speedValue);
    	// may need to change to its own timer at some point? slider timer does exist but breaks other timer atm
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
        jPCentre.setLayout(new GridLayout(13,16));
        window.add(jPCentre);
        
        for(int area=0; area<208; area++)
        {
        jBGridArea = new JButton(""+area);
        if (area<16 || area>192 )
        {
        	// jBGridArea.setForeground(Color.RED);
        	jBGridArea.setIcon(wallHorizontal);
        }
        if (area%16==0 || area%16==15)
        {
        	jBGridArea.setIcon(wallVertical);

        }
        if (area==0)
        {
        	jBGridArea.setIcon(wallTopLeft);
        }
        if (area==15)
        {
        	jBGridArea.setIcon(wallTopRight);
        }
        if (area==192)
        {
        	jBGridArea.setIcon(wallBottomLeft);
        }
        if (area==207)
        {
        	jBGridArea.setIcon(wallBottomRight);
        }
        
        if (area==17)
        {
        	//         	jBGridArea.setForeground(Color.BLUE);
        	jBGridArea.setIcon(carImageEast);
        }
        // jBGridArea.setEnabled(false);
        jBGridArea.setMargin(new Insets(0,0,0,0));
        jBGridArea.setBackground(Color.BLACK);
        jPCentre.add(jBGridArea);
        jBGridArea.addActionListener(this);
        }
        
        
        
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
        // spacer for moving option buttons and compass down 
        jPRightSpacer = new JPanel();
        jPRightSpacer.setPreferredSize(new Dimension(100, 80));
        jPRight.add(jPRightSpacer);
        
        // inside right bottom area
        jPRightBottom = new JPanel();
        jPRightBottom.setPreferredSize(new Dimension(180, 75));
        // jPRightBottom.setBackground(Color.orange);
        jPRightBottom.setLayout(new GridLayout(2, 2));
        jPRight.add(jPRightBottom);
        
        // panel for compass
        jPRightCompass = new JPanel();
        jPRightCompass.setPreferredSize(new Dimension(180, 150));
        
        jPRight.add(jPRightCompass);
        
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
        jBTM.addActionListener(this);
        jPRightMovement.add(jBTM);
        
        blankButtons();

        //buttons for middle row
        jBML = new JButton("<");
        jBML.addActionListener(this);
        jPRightMovement.add(jBML);
        
        blankButtons();
        
        jBMR = new JButton(">");
        jBMR.addActionListener(this);
        jPRightMovement.add(jBMR);
        
        //buttons for bottom row
        blankButtons();
        
        jBBM = new JButton("v");
        jBBM.addActionListener(this);
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
        
        //compass area 
        jLCompass = new JLabel(compassE);
        jPRightCompass.add(jLCompass);
        
        // buttons act run reset
        ImageIcon actImage = new ImageIcon(getClass().getResource("resources/step.png"));
        jBAct = new JButton("Act", actImage);
        jBAct.addActionListener(this);
        jPBottomLeft.add(jBAct);
        
        // this way is much simplier than try/ catch thing
        ImageIcon runImage = new ImageIcon(getClass().getResource("resources/run.png"));
        jBRun = new JButton("Run", runImage);
        jBRun.addActionListener(this);
        jPBottomLeft.add(jBRun);
        
        ImageIcon resetImage = new ImageIcon(getClass().getResource("resources/reset.png"));
        jBReset = new JButton("Reset", resetImage);
        jBReset.addActionListener(this);
        jPBottomLeft.add(jBReset);
        
        // slider section
        jLSlider = new JLabel("Speed:");
        jPBottomRight.add(jLSlider);
        
        jSSlider = new JSlider(JSlider.HORIZONTAL, 0, 2000, 1500);
        jSSlider.setMajorTickSpacing(500);
        jSSlider.setPaintTicks(true);
        jSSlider.addChangeListener(this);
        jSSlider.setInverted(true);
        //sliderTimer = new Timer (1000, this);
        //sliderTimer.start();
        
        

        // jSSlider.setBackground(Color.BLUE); you can set the colour of the background too!
        jPBottomRight.add(jSSlider);
        
    
        
        
    }

    public void actionPerformed(ActionEvent event)
    { 
    	Object source = event.getSource();
    	if (source == jBAct)
    	{
    		act();
    	}
    	
    	else if (source == jBRun)
    	{
    		
    		run();
    	}
    	
    	else if (source == jBReset)
    	{
    		reset();
    	}
    	
    	else if (source == jBOption1)
    	{
    		option1();
    	}
    	
    	else if (source == jBOption2)
    	{
    		option2();
    	}
    	
    	else if (source == jBOption3)
    	{
    		option3();
    	}
    	
    	else if (source == jBExit)
    	{
    		exit();
    	}
    	
    	else if (source == jBTM)
    	{
    		upButton();
    	}
    	
    	else if (source == jBML)
    	{
    		leftButton();
    	}
    	
    	else if (source == jBMR)
    	{
    		rightButton();
    	}
    	
    	else if (source == jBBM)
    	{
    		downButton();
    	}
    	// nOption = nOption+1;
    	/* jTFOption.setText(""+(nOption+1));
    	nOption = nOption+1;
    	nSquare = nSquare+1;
    	jTFSquare.setText(""+nSquare);
    	jTFDirection.setText("S"); */
    	
    	
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
    	
    	secsTimer = new Timer(1000, this);
        secsTimer.start();
        jBRun.setEnabled(false);
    }
    
    //method reset
    public void reset()
    {
    	System.out.println("method reset is working");
    	// stops null exception from appearing if attempting to reset before time has started
    	
    	
    	secs = 00;
    	jBRun.setEnabled(true);
    	secsTimer.stop();
    	nOption = 1;
    	sDirection = "E";
    	nSquare = 17;
    	jSSlider.setValue(1500);
    	buttonPressed();
    }
    
    //method option1
    public void option1()
    {
    	System.out.println("method option1 is working");
    	nOption = 1;
    	buttonPressed();
    }
    
    //method option2
    public void option2()
    {
    	System.out.println("method option2 is working");
    	nOption = 2;
    	buttonPressed();
    }
    
    //method option3
    public void option3()
    {
    	System.out.println("method option3 is working");
    	nOption = 3;
    	buttonPressed();
    }
    
    //method exit
    public void exit()
    {
    	System.out.println("exit method is working");
    	System.exit(0);
    }
    
    // method up key pressed
    public void upButton()
    {
    	System.out.println("up key pressed");
    	sDirection = "N";
    	buttonPressed();
    	secs = secs-1;
    }
    
    // method left key pressed
    public void leftButton()
    {
    	System.out.println("left key pressed");
    	sDirection = "W";
    	buttonPressed();
    }
    
    // method right key pressed
    public void rightButton()
    {
    	System.out.println("right key pressed");
    	sDirection = "E";
    	buttonPressed();
    }
    
    //method down key pressed
    public void downButton()
    {
    	System.out.println("down key pressed");
    	sDirection = "S";
    	buttonPressed();
    	
    }
    public void buttonPressed() {
    	jTFOption.setText(""+nOption);
    	jTFSquare.setText(""+nSquare);
    	jTFDirection.setText(sDirection);
    	compassDirection();
    	
    }
// method is used to set compass direction based on what sDirection is currently set to, this changes and is updated on button press
    public void compassDirection() {
    	if (sDirection.equals("N"))
    	{
    		jLCompass.setIcon(compassN);
    	}
    	if (sDirection.equals("E"))
    	{
    		jLCompass.setIcon(compassE);
    	}
    	if (sDirection.equals("S"))
    	{
    		jLCompass.setIcon(compassS);
    	}
    	if (sDirection.equals("W"))
    	{
    		jLCompass.setIcon(compassW);
    	}
    }
    
    
    
    
    
}