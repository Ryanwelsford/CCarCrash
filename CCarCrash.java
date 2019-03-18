import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;
 
 
public class CCarCrash extends JFrame implements ActionListener, ChangeListener
{
	// menu bar
	private JMenuBar JMBMenu;
	private JMenu JMScenario, JMEdit, JMControls, JMHelp;
	private JMenuItem JMIExit, JMITopic, JMIAbout;
	// panels
	private JPanel jPCentre, jPBottomRight, jPBottomLeft;
	private JPanel jPRight, jPRightTop, jPRightBottom, jPRightMiddle,jPRightMiddleTimer, jPRightMovement, jPRightCompass, jPRightSpacer;
	
	// Right top
	// text fields in top right and variables used within right top
    private JTextField jTFOption;
    private int nOption = 1;
    private JTextField jTFSquare;
    private JTextField jTFDirection;
    private String sDirection = "E";
    
    // center panel area
    
    private JButton jBGridArea[] = new JButton[208];
    private int nCarLocation = 17;
    private Icon iconCarEast;
    private Icon iconCarSouth;
    private Icon iconCarWest;
    private Icon iconCarNorth;
    private Icon iconWallHorizontal;
    private Icon iconWallVertical;
    private Icon iconWallTopLeft;
    private Icon iconWallTopRight;
    private Icon iconWallBottomLeft;
    private Icon iconWallBottomRight; 
    private Icon iconSpace;
    private Icon iconObstacle;
    
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
    private javax.swing.Timer runTimer;
    
    int secs = 0;
    boolean timerController = false;
    
    // buttons options and exit (right hand side buttons)
    private JButton jBOption1, jBOption2, jBOption3;
    private JButton jBExit;
    
    // compass area images and label
    private JButton jLCompass;
    private Icon compassE;
    private Icon compassS;
    private Icon compassW;
    private Icon compassN;
    
    
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
    private Icon iconAct, iconRun, iconReset;
    // slider with slider label
    private JSlider jSSlider;
    private JLabel jLSlider;
    
    Random random = new Random();
    
    public int move_Left = -1;
    public int move_Right = 1;
    public int move_Up = -16;
    public int move_Down =16;
    
    
 
    public static void main (String[] args)
    {
        CCarCrash frame = new CCarCrash();
        frame.setSize(810, 650);
        frame.createGUI();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("CarCrash - Car Race Application");
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
    	// changed the way of setting set text in fields to include a formatter makes timers always have two digits
    	NumberFormat times = new DecimalFormat("00");
    	secs++;
        jTFTimerHours.setText(times.format((secs/3600)%99));
        // set to 3600 secs in an hour + modulus 99 to prevent higher than 99 appearing in field
        jTFTimerMinutes.setText(times.format((secs/60)%60));
        // 60 seconds in a minute modulus 60 prevents over 60 from being in field
        jTFTimerSeconds.setText(times.format(secs%60));
        
    }
    private void sliderTimer()
    {
    	int speedValue = jSSlider.getValue();
    	System.out.println(speedValue);
    	runTimer.setDelay(speedValue);
    	// may need to change to its own timer at some point? slider timer does exist but breaks other timer atm
    }
 private void menuBarCreation() {
	 JMBMenu = new JMenuBar();
     setJMenuBar(JMBMenu);
     JMScenario = new JMenu("Scenario");
     JMIExit = new JMenuItem("Exit");
     JMIExit.addActionListener(this);
     JMScenario.add(JMIExit);
     JMEdit = new JMenu("Edit");
     JMControls = new JMenu("Controls");
     JMHelp = new JMenu("Help");
     JMIAbout = new JMenuItem("About");
     JMIAbout.addActionListener(this);
     JMHelp.add(JMIAbout);
     JMITopic = new JMenuItem("Topic");
     JMITopic.addActionListener(this);
     JMHelp.add(JMITopic);
     
     
     JMBMenu.add(JMScenario);
     JMBMenu.add(JMEdit);
     JMBMenu.add(JMControls);
     JMBMenu.add(JMHelp);
     
 }
    private void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() ); // sets items to centre into the window
        
        // menubar area
        menuBarCreation();
        
        // panels section
        
        //Centre
        jPCentre = new JPanel();
        jPCentre.setPreferredSize(new Dimension(600, 550));
        // jPCentre.setBackground(Color.orange);
        jPCentre.setLayout(new GridLayout(13,16));
        window.add(jPCentre);
        try {
        	iconSpace = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/space.png")));
        	iconCarSouth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/car-s.png")));
        	iconCarNorth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/car-n.png")));
        	iconCarWest = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/car-w.png")));
        	iconCarEast = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/car-e.png")));
        	iconWallHorizontal = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/wall-horiz.png")));
        	iconWallVertical = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/wall-vert.png")));
        	iconWallTopRight = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/wall-NE.png")));
        	iconWallTopLeft = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/wall-NW.png")));
        	iconWallBottomRight = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/wall-SE.png")));
        	iconWallBottomLeft = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/wall-SW.png")));
        	iconObstacle = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/person.png")));
        }
        catch(Exception c) {
        	System.err.println("car, wall, space images not found");
        }
        
        for(int area=0; area<208; area++)
        {
        jBGridArea[area] = new JButton(""+area);
        // sets all buttons to space by default
        jBGridArea[area].setIcon(iconSpace);
        // sets all outer horizontal walls
        if ((area<16 || area>192) || (area>51 && area<60) || (area>147 && area<157) )
        {
        	// jBGridArea.setForeground(Color.RED);
        	jBGridArea[area].setIcon(iconWallHorizontal);
        }
        // sets all outer vertical walls
        if ((area%16==0 || area%16==15) || (area>50 && area<148 && area%16==3) || (area>59 && area<171 && area%16==12))
        {
        	jBGridArea[area].setIcon(iconWallVertical);
        }
        // sets corner areas of outer red ring box
        if (area==0 || area==51)
        {
        	jBGridArea[area].setIcon(iconWallTopLeft);
        }
        if (area==15 || area==60)
        {
        	jBGridArea[area].setIcon(iconWallTopRight);
        }
        if (area==192 || area==147)
        {
        	jBGridArea[area].setIcon(iconWallBottomLeft);
        }
        if (area==207 || area==156)
        {
        	jBGridArea[area].setIcon(iconWallBottomRight);
        }
        if (area==17)
        {
        	jBGridArea[area].setIcon(iconCarEast);
        	//jBGridArea[area].setIcon(carImageEast);
        }
        // jBGridArea.setEnabled(false);
        jBGridArea[area].setMargin(new Insets(0,0,0,0));
        jBGridArea[area].setBackground(Color.BLACK);
        jBGridArea[area].setBorderPainted(false);
        jBGridArea[area].setBackground(null);
        jBGridArea[area].addActionListener(this);
        jPCentre.add(jBGridArea[area]);
        }
        
        // right
        jPRight = new JPanel();
        jPRight.setPreferredSize(new Dimension(180, 550));
         //jPRight.setBackground(Color.magenta);
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
        jPRightSpacer.setPreferredSize(new Dimension(100, 110));
        jPRight.add(jPRightSpacer);
        
        // inside right bottom area
        jPRightBottom = new JPanel();
        jPRightBottom.setPreferredSize(new Dimension(180, 75));
        // jPRightBottom.setBackground(Color.orange);
        jPRightBottom.setLayout(new GridLayout(2, 2));
        jPRight.add(jPRightBottom);
        
        // panel for compass
        jPRightCompass = new JPanel();
        // jPRightCompass.setBackground(Color.CYAN);
        jPRightCompass.setPreferredSize(new Dimension(180, 90));
        
        jPRight.add(jPRightCompass);
        
        // bottom
        jPBottomLeft = new JPanel();
        jPBottomLeft.setPreferredSize(new Dimension (500, 40));
        // jPBottomLeft.setBackground(Color.cyan);
        window.add(jPBottomLeft);
        
        jPBottomRight = new JPanel();
        jPBottomRight.setPreferredSize(new Dimension(280, 40));
        //jPBottomRight.setBackground(Color.BLUE);
        window.add(jPBottomRight);
        

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
        jTFSquare = new JTextField(""+nCarLocation);
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
        try {
        	compassE = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/east.jpg")));
        	compassW = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/west.jpg")));
        	compassN = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/north.jpg")));
        	compassS = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/south.jpg")));
        }
        catch (Exception d) {
        	System.err.println("compass images not found");
        }
        jLCompass = new JButton();
        //jLCompass.setEnabled(false);
        jLCompass.setBorder(null);
        //jLCompass.setBorderPainted(false);
        jLCompass.setMargin(new Insets(0,0,0,0));
        jLCompass.setLayout(new GridLayout(1,1));
       // jLCompass.setPreferredSize(new Dimension(150, 150));
        jLCompass.setIcon(compassE);
        
        jPRightCompass.add(jLCompass);
        // buttons act run reset
        
        try	
        {
        	//icons act run reset
        	iconAct = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/step.png")));
        	iconRun = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/run.png")));
        	iconReset = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("resources/reset.png")));


        	
        }
        catch (Exception e)
        {
            System.err.println("bottom bar Images not found "+e);
        } 
        
        jBAct = new JButton("Act");
        jBAct.setIcon(iconAct);
        jBAct.addActionListener(this);
        jPBottomLeft.add(jBAct);
        
        // images should be inserted with try/catch method rather than declaring an iconimage
        jBRun = new JButton("Run");
        jBRun.setIcon(iconRun);
        jBRun.addActionListener(this);
        jPBottomLeft.add(jBRun);
        
        jBReset = new JButton("Reset");
        jBReset.setIcon(iconReset);
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
    	else if (source == secsTimer) {
    		secsTimer();
    	}
    	else if (source == runTimer) {
    		act();
    	}
    	else if (source == JMIExit) {
    		exit();
    	}
    	else if (source == JMIAbout) {
    		JOptionPane.showMessageDialog(null, "Hello");
    	}
    	else if (source == JMITopic) {
    		JOptionPane.showMessageDialog(null, "Hello");
    	}
    	// need to add sources for menu items 
    	
    	
    }
    public void stateChanged(ChangeEvent event)
    {
    	sliderTimer();
    
    }
    // method act section
    public void act()
    {
    	System.out.println("method act is working");
    	driveCar();
    	
    	
    }
    
    //method run section
    public void run() 
    {
    	System.out.println("method run is working");
    	if (timerController==false) {
	    	secsTimer = new Timer(1000, this);
	        secsTimer.start();
	        jBRun.setEnabled(false);
	        timerController = true;
	        runTimer = new Timer(1000, this);
	        runTimer.start();
    	}
    	
    }
    
    //method reset
    public void reset()
    {
    	System.out.println("method reset is working");
    	jBRun.setEnabled(true);
    	jBGridArea[nCarLocation].setIcon(iconSpace);
    	nCarLocation = 17;
    	jBGridArea[17].setIcon(iconCarEast);
    	nOption = 1;
    	sDirection = "E";
    	jSSlider.setValue(1500);
    	jBGridArea[179].setIcon(iconSpace);
    	jBGridArea[114].setIcon(iconSpace);
    	jBGridArea[36].setIcon(iconSpace);
    	jBGridArea[171].setIcon(iconSpace);
    	
    	
    	if (timerController==true) 
    	{
    		secsTimer.stop();
    		runTimer.stop();
    		timerController = false;
    	} 
    	
    	secs = 00;
    	NumberFormat times = new DecimalFormat("00");
        jTFTimerHours.setText(times.format((secs/3600)%99));
        jTFTimerMinutes.setText(times.format((secs/60)%60));
        jTFTimerSeconds.setText(times.format(secs%60));
    	buttonPressed();
    }
    
    //method option1
    public void option1()
    {
    	System.out.println("method option1 is working");
    	nOption = 1;
    	sDirection = "E";
    	jBGridArea[nCarLocation].setIcon(iconSpace);
    	nCarLocation = 17;
    	jBGridArea[nCarLocation].setIcon(iconCarEast);
    	jBGridArea[171].setIcon(iconSpace);
    	jBGridArea[179].setIcon(iconSpace);
    	jBGridArea[114].setIcon(iconSpace);
    	jBGridArea[36].setIcon(iconSpace);
    	buttonPressed();
    }
    
    //method option2
    public void option2()
    {
    	System.out.println("method option2 is working");
    	nOption = 2;
    	sDirection = "W";
    	jBGridArea[nCarLocation].setIcon(iconSpace);
    	nCarLocation = 190;
    	jBGridArea[190].setIcon(iconCarWest);
    	jBGridArea[179].setIcon(iconObstacle);
    	jBGridArea[171].setIcon(iconObstacle);
    	jBGridArea[114].setIcon(iconObstacle);
    	jBGridArea[36].setIcon(iconObstacle);
    	buttonPressed();
    }
    
    //method option3
    public void option3()
    {
    	System.out.println("method option3 is working");
    	nOption = 3;
    	//jBGridArea[(random.nextInt(29)+17)].setIcon(iconObstacle);
    	buttonPressed();
    }
    
    //method exit
    public void exit()
    {
    	System.out.println("exit method is working");
    	System.exit(0);
    }
    
    // method up key pressed

    public void driveCar() {
    	
    	Icon right = jBGridArea[nCarLocation+1].getIcon();
    	Icon left = jBGridArea[nCarLocation-1].getIcon();
    	Icon down = jBGridArea[nCarLocation+16].getIcon();
    	Icon up = jBGridArea[nCarLocation-16].getIcon();
    	Icon downLeft = jBGridArea[nCarLocation+15].getIcon();

    	// if surrounded on three sides move into open side
    	// could use random rolls for movements when 3 options / 2 options / the rare 4 options
    	if (nOption==2 || nOption==1) { 
	    	if (right==iconSpace && left!=iconSpace && up!=iconSpace && down!=iconSpace) {
	    		move(move_Right);
	    		System.out.println("1");
	    	}
	    	else if (right!=iconSpace && left==iconSpace && up!=iconSpace && down!=iconSpace) {
	    		move(move_Left);
	    		System.out.println("2");
	    	}
	    	else if (right!=iconSpace && left!=iconSpace && up==iconSpace && down!=iconSpace) {
	    		move(move_Up);
	    		System.out.println("3");
	    	}
	    	else if (right!=iconSpace && left!=iconSpace && up!=iconSpace && down==iconSpace) {
	    		move(move_Down);
	    		System.out.println("4");
	    	}
	    	else if (right!=iconSpace && left!=iconSpace && up!=iconSpace && down!=iconSpace) {
	    		System.out.println("Trapped!");
	    		System.out.println("5");
	    	}
	    	else if (right!=iconSpace && left!=iconSpace) {
	    		int decide = random.nextInt(2);
	    		if (decide==0) {
	    			move(move_Up);
	    			System.out.println("6");
	    		}
	    		else {
	    			move(move_Down);
	    			System.out.println("7");
	    		}
	    	}
	    	else if (up!=iconSpace && down!=iconSpace) {
	    		int decide = random.nextInt(2);
	    		System.out.println(decide);
	    		if (decide==0) {
	    			move(move_Right);
	    			System.out.println("8");
	    		}
	    		else {
	    			move(move_Left);
	    			System.out.println("9");
	    		}
	    	}
	    	else if (right!=iconSpace && down!=iconSpace) {
	    		move(move_Left);
	    		System.out.println("10");
	    	}
	    	else if (right!=iconSpace && up!=iconSpace) {
	    		move(move_Down);
	    		System.out.println("11");
	    	}
	    	
	    	else if (up!=iconSpace && left!=iconSpace) {
	    		move(move_Right);
	    		System.out.println("12");
	    	}
	    	else if (up!=iconSpace && right!=iconSpace) {
	    		move(move_Down);
	    	}
	    	else if (left!=iconSpace && down!=iconSpace) {
	    		move(move_Up);
	    		System.out.println("13");
	    	}
	    	else if (left!=iconSpace) {
	    		move(move_Up);
	    		System.out.println("14");
	    	}
	    	else if (left==iconSpace && right==iconSpace && up!=iconSpace && nCarLocation>100) {
	    		move(move_Left);
	    		System.out.println("15");
	    	}
	    	/*else if (up!=iconSpace && downLeft!=iconSpace ) {
	    		int decide = random.nextInt(2);
	    		if (decide==0) {
	    			move(move_Left);
	    		}
	    		else if (decide==1) {
	    			move(move_Right);
	    		}
	    		
	    	}*/
	    	else if (up!=iconSpace) {
	    		move(move_Right);
	    		System.out.println("16");
	    	} 
	    	else if (down!=iconSpace) {
	    		move(move_Left);
	    		System.out.println("17");
	    	}
	    	else if (right!=iconSpace) {
	    		move(move_Down);
	    		System.out.println("18");
	    	}
	    	else if (right==iconSpace && left==iconSpace && down==iconSpace && up==iconSpace) {
	    		int decide = random.nextInt(4);
	    		if (decide==0) {
	    			move(move_Right);
	    		}
	    		else if (decide==1) {
	    			move(move_Left);
	    		}
	    		else if (decide==2) {
	    			move(move_Down);
	    		}
	    		else {
	    			move(move_Up);
	    		}
	    	}
	    	else {
	    		move(move_Right);
	    		System.out.println("19");
	    	}
    	}
    	buttonPressed();
    
    }
    public void move(int nDirection) {
    	Icon check = jBGridArea[nCarLocation+nDirection].getIcon();
    	if (check==iconSpace) {
    		jBGridArea[nCarLocation].setIcon(iconSpace);
    		if (nDirection==16) {
    			jBGridArea[nCarLocation+nDirection].setIcon(iconCarSouth);
    			sDirection = "S";
    		}
    		else if(nDirection==1) {
    			jBGridArea[nCarLocation+nDirection].setIcon(iconCarEast);
    			sDirection = "E";
    		}
    		else if(nDirection==-1) {
    			jBGridArea[nCarLocation+nDirection].setIcon(iconCarWest);
    			sDirection = "W";
    		}
    		else if (nDirection==-16) {
    			jBGridArea[nCarLocation+nDirection].setIcon(iconCarNorth);
    			sDirection = "N";
	    	}
    		nCarLocation = nCarLocation+nDirection;
    }
    }
    
    public void upButton()
    {
    	System.out.println("up key pressed");
    	sDirection = "N";
    	if ((nCarLocation>16 && nCarLocation<32) || (nCarLocation>162 && nCarLocation<173)) {
    		jBGridArea[nCarLocation].setIcon(iconCarNorth);
    	}
    	else { 
    	jBGridArea[nCarLocation].setIcon(iconSpace);
    	jBGridArea[nCarLocation-16].setIcon(iconCarNorth);
    	nCarLocation = nCarLocation-16;
    	}
    	buttonPressed();
    }
    
    // method left key pressed
    public void leftButton()
    {
    	System.out.println("left key pressed");
    	sDirection = "W";
    	
    	if((nCarLocation%16==1) || (nCarLocation%16==13 && nCarLocation>=60 && nCarLocation<=157)) {
    		jBGridArea[nCarLocation].setIcon(iconCarWest);
    	}
    	else {
    		jBGridArea[nCarLocation].setIcon(iconSpace);
	    	jBGridArea[nCarLocation-1].setIcon(iconCarWest);
	    	nCarLocation--;
    	}
    	buttonPressed();
    }
    
    // method right key pressed
    public void rightButton()
    {
    	System.out.println("right key pressed");
    	sDirection = "E";
    	
    	if((nCarLocation%16==14) || (nCarLocation%16==2 && nCarLocation>=50 && nCarLocation<=146)) {
    		jBGridArea[nCarLocation].setIcon(iconCarEast);
    	}
    	else {
			jBGridArea[nCarLocation].setIcon(iconSpace);
			jBGridArea[nCarLocation+1].setIcon(iconCarEast);
			nCarLocation++;
    	}
    	buttonPressed();
    }
    
    //method down key pressed
    public void downButton()
    {
    	System.out.println("down key pressed");
    	sDirection = "S";
    	if((nCarLocation<191 && nCarLocation>176) || (nCarLocation>34 && nCarLocation<45)) {
    		jBGridArea[nCarLocation].setIcon(iconCarSouth);
    	}
    	else {
	    	jBGridArea[nCarLocation].setIcon(iconSpace);
	    	jBGridArea[nCarLocation+16].setIcon(iconCarSouth);
	    	nCarLocation = nCarLocation+16;
    	}
    	buttonPressed();
    	
    }
    // method updates various text fields 
    public void buttonPressed() {
    	jTFOption.setText(""+nOption);
    	jTFSquare.setText(""+nCarLocation);
    	jTFDirection.setText(sDirection);
    	
    	compassDirection();
    	
    }
// method is used to set compass direction based on what sDirection is currently set to, this changes and is updated on button press
    public void compassDirection() {
    	if (sDirection.equals("N"))
    	{
    		jLCompass.setIcon(compassN);
    	}
    	else if (sDirection.equals("E"))
    	{
    		jLCompass.setIcon(compassE);
    	}
    	else if (sDirection.equals("S"))
    	{
    		jLCompass.setIcon(compassS);
    	}
    	else if (sDirection.equals("W"))
    	{
    		jLCompass.setIcon(compassW);
    	}
    }
    
    
    
    
    
}