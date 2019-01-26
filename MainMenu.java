import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Author: Samandeep Singh Virdi
 * Date: Jan, 2017
 * Description: This GUI is the main menu of the application, here you can choose
 * one of five buttons, play slot machine, play black jack, settings, manage accounts
 * and log out 
 */
//Methods List 
//public MainMenu(PlayerAccount player) throws IOException 
//public static void main(String[] args) throws IOException 
//public void actionPerformed(ActionEvent e)
public class MainMenu extends JFrame implements ActionListener{

	/**
	 * 
	 */
	//Private Data
	//Labels for the GUI
	private JLabel usernamelbl,accountBallbl,iconPanel, background;
	//JButtons for the GUI
	private JButton slotMachineBtn,blackJackBtn,settingsBtn,manageAccBtn,logOutBtn;
	//number formatter 
	private NumberFormat nf;
	//file names 
	private String musicFile = "DANCING_IN_THE_DARK_ROMANTIC_PIANO_AND_SAX_LOVE_SO.wav";
	//Holder Variables For the Parameters
	//player account
	private PlayerAccount playerHolder;
	//String
	private String userNameHolder;
	//Double
	double accountBalHolder;
	//trial variables 
	JPopupMenu popup;
	//Methods object
	private Methods music = new Methods();
	//Constructor
	public MainMenu(PlayerAccount player) throws IOException {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
		//make the title be Slot Machine Game
		super ("Main Menu");
		//set the layout to border layout
		getContentPane().setLayout(null);
		//Initializing all variables
		//making the holder variables equal the parameters
		playerHolder = player;
		userNameHolder = player.getPlayerName();
		accountBalHolder = player.getPlayerBalance();
		//initializing the number format and getting the currency instance
		nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);
		//Labels
		usernamelbl = new JLabel (userNameHolder);
		accountBallbl= new JLabel ((nf.format(accountBalHolder)));
		background = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("mainPage.png"))));
		iconPanel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource(player.getIconNumber()))));
		//Buttons
		slotMachineBtn = new JButton ("Play Lucky 7z");
		slotMachineBtn.setFont(new Font("PT Sans Caption", Font.PLAIN, 20));
		blackJackBtn = new JButton ("Play Black Jack");
		blackJackBtn.setFont(new Font("PT Sans Caption", Font.PLAIN, 20));
		settingsBtn = new JButton ("Settings");
		settingsBtn.setFont(new Font("PT Sans Caption", Font.PLAIN, 20));
		manageAccBtn = new JButton ("Manage Funds");
		manageAccBtn.setFont(new Font("PT Sans Caption", Font.PLAIN, 20));
		logOutBtn = new JButton ("Log Out");
		logOutBtn.setFont(new Font("PT Sans Caption", Font.PLAIN, 20));
		//setting bounds for all items
		//labels
		usernamelbl.setBounds(71, 0, 100, 39);
		accountBallbl.setBounds(71, 25, 100, 39);
		background.setBounds(0, 0, 1000, 700);
		iconPanel.setBounds(6,6,62,48);
		//buttons
		slotMachineBtn.setBounds(400, 178, 193, 64);
		blackJackBtn.setBounds(400, 275, 193, 64);
		settingsBtn.setBounds(395, 372, 193, 64);
		manageAccBtn.setBounds(392, 470, 193, 64);
		logOutBtn.setBounds(390, 570, 193, 55);
		//Adding All Labels to the frame
		//labels
		getContentPane().add(usernamelbl);
		getContentPane().add(accountBallbl);
		//Buttons
		getContentPane().add(slotMachineBtn);
		getContentPane().add(blackJackBtn);
		getContentPane().add(settingsBtn);
		getContentPane().add(manageAccBtn);
		getContentPane().add(logOutBtn);
		getContentPane().add(iconPanel);
		getContentPane().add(background);
		//Adding action listeners to all buttons
		slotMachineBtn.addActionListener(this);
		blackJackBtn.addActionListener(this);
		settingsBtn.addActionListener(this);
		manageAccBtn.addActionListener(this);
		logOutBtn.addActionListener(this);
		//add mouse listener for popup menu 
		//GOT FROM http://stackoverflow.com/questions/1692677/how-to-create-a-jbutton-with-a-menu
		settingsBtn.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		//inits the new popup menu object and sets up the first option 
		//as all accounts 
		popup = new JPopupMenu();
		popup.add(new JMenuItem(new AbstractAction("All Accounts (ADMIN)"){
			public void actionPerformed(ActionEvent e) {
				try {
					//if all accounts is selected dispose current window and open 
					//admin login
					dispose();
					AdminLogin adminLogin = new AdminLogin (playerHolder);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//show this
					JOptionPane.showMessageDialog(null, "ERROR: File Not Found");
				}

			}		
		}));
		//the second option is set as help menu
		popup.add(new JMenuItem(new AbstractAction("Help Menu"){
			public void actionPerformed(ActionEvent e) {
				try {
					//if help menu is selected, dispose current window and open help menu
					dispose();
					HelpPage adminLogin = new HelpPage (playerHolder);
				} catch (IOException e1) {
					//show this
					JOptionPane.showMessageDialog(null, "ERROR: File Not Found");
				}
			}		
		}));
		//Calling method music in methods class to play song
		music.music(musicFile);
		//setting size and showing frame 
		setBounds(150, 20, 1000, 700);
		setResizable(false);
		setVisible (true); // Show the frame
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//dummy player to make object work
		PlayerAccount player = new PlayerAccount ("John","35th Street","9056661239","password",1000,"1.png");
		MainMenu mainMenu = new MainMenu (player);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == slotMachineBtn){
			//if slot machine is pressed dispose and open the slot machine game and stop music
			dispose();
			SlotMachineGUI slotMachine = new SlotMachineGUI (playerHolder);
			music.music("");
		}
		else if (e.getSource()==blackJackBtn){
			try {
				//if black jack is pressed dispose and open the black jack game and stop music
				BlackJackGUI blackJack = new BlackJackGUI(playerHolder);
				dispose();
				music.music("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//show this
				JOptionPane.showMessageDialog(null, "ERROR: File Not Found");
			}
		}
		else if (e.getSource()==manageAccBtn){
			//if manage funds is pressed dispose and open the manage funds page and stop music
			dispose();
			try {
				ManageFunds funds = new ManageFunds (playerHolder);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//show this
				JOptionPane.showMessageDialog(null, "ERROR: File Not Found");
			}
		}
		else if (e.getSource()==logOutBtn){
			//if log out is pressed dispose and open the login menu and stop music
			dispose();
			try {
				LogIn backToLogIn = new LogIn ();
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				//show this
				JOptionPane.showMessageDialog(null, "ERROR: File Not Found");
			}
		}
	}
}
