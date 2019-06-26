package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI for assignment 1, DualThreads
 */
public class GUI implements ActionListener{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;		// The Main window
	private JButton btnDisplay;	// Start thread moving display
	private JButton btnDStop;	// Stop moving display thread
	private JButton btnTriangle;// Start moving graphics thread
	private JButton btnTStop;	// Stop moving graphics thread
	private JButton btnOpen;	// Open audio file 
	private JButton btnPlay;	// Start playing audio
	private JButton btnStop;	// Stop playing
	private JButton btnGo;		// Start game catch me
	private JPanel pnlMove;		// The panel to move display in
//	private JPanel pnlRotate;	// The panel to move graphics in
	private JPanel pnlGame;		// The panel to play in
	private JLabel lblPlaying;	// Playing text
	private JLabel lblAudio;	// Audio file
	private JTextArea txtHits;	// Dispaly hits
	private JComboBox cmbSkill;	// Skill combo box, needs to be filled in
	
	
	private DrawTrianglePanel pnlRotate; // Custom JPanel for drawing triangle
	
	private JLabel displayText = new JLabel("Display Thread"); // text displayed in displayThread
	
	/*
	 * Initializations of both DisplayThread and TriangleRotate classes 
	 */
	private DisplayThread dThread = new DisplayThread(this);
	private TriangleRotate triRotate = new TriangleRotate(this);
	
	/*
	 *  Boolean variables used to restart thread for respective class.
	 */
	private boolean displayReplay = false;
	private boolean triangleReplay = false;
	
	
	
	
	/**
	 * Constructor
	 */
	public GUI(){
	
		
	}
	
	/**
	 * Starts the application
	 */
	public void Start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 819, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
		
		
		
		btnDisplay.addActionListener(this);
		btnDStop.addActionListener(this);
		btnTriangle.addActionListener(this);
		btnTStop.addActionListener(this);
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI()
	{
		// The music player outer panel
		JPanel pnlSound = new JPanel();
		Border b1 = BorderFactory.createTitledBorder("Music Player");
		pnlSound.setBorder(b1);
		pnlSound.setBounds(12, 12, 450, 100);
		pnlSound.setLayout(null);
		
		// Add labels and buttons to this panel
		lblPlaying = new JLabel("Now Playing: ");	// Needs to be alteraed
		lblPlaying.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblPlaying.setBounds(128, 16, 300, 20);
		pnlSound.add(lblPlaying);
		JLabel lbl1 = new JLabel("Loaded Audio File: ");
		lbl1.setBounds(10, 44, 130, 13);
		pnlSound.add(lbl1);
		lblAudio = new JLabel("...");				// Needs to be altered
		lblAudio.setBounds(115, 44, 300, 13);
		pnlSound.add(lblAudio);
		btnOpen = new JButton("Open");
		btnOpen.setBounds(6, 71, 75, 23);
		pnlSound.add(btnOpen);
		btnPlay = new JButton("Play");
		btnPlay.setBounds(88, 71, 75, 23);
		pnlSound.add(btnPlay);
		btnStop = new JButton("Stop");
		btnStop.setBounds(169, 71, 75, 23);
		pnlSound.add(btnStop);		
		frame.add(pnlSound);
		
		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 118, 222, 269);
		pnlDisplay.setLayout(null);
				
		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		pnlDisplay.add(btnDisplay);				
		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		pnlDisplay.add(btnDStop);				
		pnlMove = new JPanel();
		pnlMove.setBounds(10,  19,  200,  200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
		pnlDisplay.add(pnlMove);
		displayText.setLocation(0, 0);
		displayText.setVisible(false);
		pnlMove.add(displayText);
		frame.add(pnlDisplay);
				
		// The moving graphics outer panel
		JPanel pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
		pnlTriangle.setBorder(b3);
		pnlTriangle.setBounds(240, 118, 222, 269);
		pnlTriangle.setLayout(null);
		
		// Add buttons and drawing panel to this panel
		btnTriangle = new JButton("Start Rotate");
		btnTriangle.setBounds(10, 226, 121, 23);
		pnlTriangle.add(btnTriangle);		
		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		pnlTriangle.add(btnTStop);		
		pnlRotate = new DrawTrianglePanel();
		pnlRotate.setBounds(10,  19,  200,  200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.setBorder(b31);
		pnlTriangle.add(pnlRotate);
		// Add this to main window
		frame.add(pnlTriangle);	
		
		// The game outer panel
		JPanel pnlCatchme = new JPanel();
		Border b4 = BorderFactory.createTitledBorder("Catch Me");
		pnlCatchme.setBorder(b4);
		pnlCatchme.setBounds(468, 12, 323, 375);
		pnlCatchme.setLayout(null);
		
		// Add controls to this panel
		JLabel lblSkill = new JLabel("Skill:");
		lblSkill.setBounds(26, 20, 50, 13);
		pnlCatchme.add(lblSkill);
		JLabel lblInfo = new JLabel("Hit Image with Mouse");
		lblInfo.setBounds(107, 13, 150, 13);
		pnlCatchme.add(lblInfo);
		JLabel lblHits = new JLabel("Hits:");
		lblHits.setBounds(240, 20, 50, 13);
		pnlCatchme.add(lblHits);
		cmbSkill = new JComboBox();			// Need to be filled in with data
		cmbSkill.setBounds(19, 41, 61, 23);
		pnlCatchme.add(cmbSkill);
		btnGo = new JButton("GO");
		btnGo.setBounds(129, 41, 75, 23);
		pnlCatchme.add(btnGo);
		txtHits = new JTextArea();			// Needs to be updated
		txtHits.setBounds(233, 41, 71, 23);
		Border b40 = BorderFactory.createLineBorder(Color.black);
		txtHits.setBorder(b40);
		pnlCatchme.add(txtHits);
		pnlGame = new JPanel();
		pnlGame.setBounds(19, 71, 285, 283);
		Border b41 = BorderFactory.createLineBorder(Color.black);
		pnlGame.setBorder(b41);
		pnlCatchme.add(pnlGame);
		frame.add(pnlCatchme);
	}
	
	
	/**
	 * Displays the JLabel and sets its location to the specified X and Y coordinates.
	 * @param posX position along the X-axis
	 * @param posY position along the Y-axis
	 */
	public void displayText(int posX, int posY){
		displayText.setVisible(true);
		displayText.setLocation(posX, posY);
		
		
	}
	
	/**
	 * Calls method in the custom JPanel in order to draw a triangle on specified coordinates.
	 * @param x0 position of point 1 along the X-axis
	 * @param y0 position of point 1 along the Y-axis
	 * @param x1 position of point 2 along the X-axis
	 * @param y1 position of point 2 along the Y-axis
	 * @param x2 position of point 3 along the X-axis
	 * @param y2 position of point 3 along the Y-axis
	 */
	public void drawTriangle(int x0,int y0, int x1, int y1, int x2, int y2 ){
		pnlRotate.drawTriangle(x0, y0, x1, y1, x2, y2);		
	}
	
	
	
	/**
	 * Listener-method, starting the thread of a class when respective button is pressed. 
	 * Stops thread and calls its .join method when stop-button is pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnDisplay){
			if(displayReplay){
				dThread = new DisplayThread(this);
			}
			dThread.start();
			btnDisplay.setEnabled(false);
			
		}
		if(e.getSource() == btnDStop){
			try {
				dThread.shutdown();
				dThread.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			displayReplay = true;
			btnDisplay.setEnabled(true);
			displayText.setVisible(false);
		}
		
		if(e.getSource() == btnTriangle){
			if(triangleReplay){
				triRotate = new TriangleRotate(this);
			}
			triRotate.start();
			btnTriangle.setEnabled(false);
		}
		if(e.getSource() == btnTStop){
			triRotate.shutdown();
			try {
				triRotate.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			triangleReplay = true;
			btnTriangle.setEnabled(true);

		}
		
	}

}
