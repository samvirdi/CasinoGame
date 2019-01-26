import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Author:Samandeep Singh Virdi
 * Date: Jan,2017
 * Description: This is the login page for the application, here you can either login
 * if you are an existing member or create a new account 
 *
 */
//Methods Lists
//public LogIn() throws NumberFormatException, IOException 
//public static void main(String[] args) throws NumberFormatException, IOException
//public void actionPerformed(ActionEvent e) 
public class LogIn extends JFrame implements ActionListener{
	//Private data
	//Login and register button
	private JButton loginBtn,registerBtn, exitBtn;
	//Labels for the GUI
	private JLabel usernamelbl,passwordlbl,wrongUserNamelbl,wrongPasswordlbl,background;
	//Text field for entering the username 
	private JTextField usernametxt;
	//password field for entering the password
	private JPasswordField passwordtxt;
	//Panels for different areas
	private JPanel userPanel,passwordPanel,buttonPanel;
	//Player object 
	private PlayerList list;
	//methods object
	private Methods methods = new Methods();
	//String file name 
	private String fileName = "Players.txt", musicFileName = "DANCING_IN_THE_DARK_ROMANTIC_PIANO_AND_SAX_LOVE_SO.wav";
	//Constructor
	public LogIn() throws NumberFormatException, IOException {
		// TODO Auto-generated constructor stub
		//make the title be Slot Machine Game
		super ("Login");
		// creating music method class object
		//set the layout to border layout
		getContentPane().setLayout(null);
		//Initializing the picture for background
		background = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("login.png"))));
		//initializing the player ID object
		list = new PlayerList();
		list.readFromFile(fileName);
		//initializing the buttons
		loginBtn = new JButton ("Login");
		registerBtn = new JButton ("Register");
		exitBtn = new JButton ("Exit");
		//initializing the labels
		passwordlbl = new JLabel ("Please Enter Your Password\n");
		//initializing the text and password fields
		usernametxt = new JTextField("",20);
		passwordtxt = new JPasswordField("",20);
		//initializing the labels for incorrect password or username
		wrongUserNamelbl = new JLabel ("Wrong Username");
		wrongUserNamelbl.setForeground(Color.RED);
		wrongPasswordlbl = new JLabel("Wrong Password");
		wrongPasswordlbl.setForeground(Color.RED);
		//setting visible false for the wrong username or password labels
		wrongUserNamelbl.setVisible(false);
		wrongPasswordlbl.setVisible(false);
		//Initializing panels for different areas 
		usernamelbl = new JLabel ("Please Enter Your Username");
		
		//Setting bounds 
		usernamelbl.setBounds (430, 210, 286, 75);
		usernametxt.setBounds (390, 260, 250, 22);
		wrongUserNamelbl.setBounds(465, 190, 286, 75);
		passwordlbl.setBounds(430, 340, 286, 75);
		passwordtxt.setBounds (390, 390, 250, 22);
		wrongPasswordlbl.setBounds (465, 320, 286, 75);
		loginBtn.setBounds (400, 508, 100, 50);
		registerBtn.setBounds (530, 508, 100, 50);
		exitBtn.setBounds(932, 6, 62, 48);
		background.setBounds(0, 0, 1000, 700);
		
		//Adding the labels and fields to the panel
		add(usernamelbl);
		add(wrongUserNamelbl);
		add(usernametxt);
		add(passwordlbl);
		add(wrongPasswordlbl);
		add(passwordtxt);
		add(loginBtn);
		add(registerBtn);
		add(exitBtn);
		add(background);
		
		//Add action listeners to the buttons
		loginBtn.addActionListener(this);
		registerBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		
		// Calling on method music located Methods class to play music
		methods.music(musicFileName);
		
		//setting size and showing frame 
		setBounds(150, 20, 1000, 700);
		setResizable(false);
		setVisible (true); // Show the frame
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		LogIn loginMenu = new LogIn();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if the login button is pressed
		if(e.getSource()==loginBtn){
			//get the values from text fields 
			String usernameEntered = usernametxt.getText();
			String passwordEntered = new String (passwordtxt.getPassword());
			//sort the list and then binary search for the user name that was entered 
			list.insertion();
			int userNameLoc = list.binarySearch(usernameEntered);
			//if the location is greater than -1
			if (userNameLoc > -1)
			{
				//create a new PlayerAccoount object and make it equal that player 
				PlayerAccount playerLoggedIn = list.getPlayer(userNameLoc);
				//if the username and password match
				if ((usernameEntered.equalsIgnoreCase(playerLoggedIn.getPlayerName())) && 
						(passwordEntered.equalsIgnoreCase(playerLoggedIn.getPassword()))==true){
					//dispose of the current window 
					dispose();
					//
					try {
						//create the main menu GUI and pass in the player logged in 
						MainMenu menu = new MainMenu (playerLoggedIn);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						//if file is not found then show this 
						JOptionPane.showMessageDialog(null, "ERROR: RECORDS FILE NOT FOUND!");
					}
				}
				//if the data entered is false 
				else{
					//clear all text fields and display the wrong entry labels 
					usernametxt.setText("");
					passwordtxt.setText("");
					wrongUserNamelbl.setVisible(true);
					wrongPasswordlbl.setVisible(true);	
				}
			}
			//if the data entered is false 
			else{
				//clear all text fields and display the wrong entry labels 
				usernametxt.setText("");
				passwordtxt.setText("");
				wrongUserNamelbl.setVisible(true);
				wrongPasswordlbl.setVisible(true);

			}
		}
		//if the register button is pressed 
		else if (e.getSource() == registerBtn){
			//the current window is disposed and a new register window is opened
			dispose();
			try {
				Register register = new Register();
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				
			}
		}
		//if user presses exit 
		else if (e.getSource()==exitBtn){
			//asks user if they want to exit 
			if (methods.yesOrNoDialogBox ("Are You Sure You Want To Quit?", "Quit Message")){
				//if they select yes then system exits 
				System.exit(0);
			}	
			//music ends
			methods.music("");
		}
	}
}
