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
 * Description:This is for the admin to log in. It is a simple 
 * GUI
 */
//Method lists
//public AdminLogin(PlayerAccount player) throws IOException
//public static void main(String[] args) throws IOException 
//public void actionPerformed(ActionEvent e) 
public class AdminLogin extends JFrame implements ActionListener{

	/**
	 * 
	 */
	//Private Data
	//Labels for the GUI
	private JLabel adminUsernamelbl,adminPasswordlbl, 
	adminWrongPasslbl, adminWrongUsernamelbl, background;
	//JButtons for the GUI
	private JButton adminLoginBtn,adminCancelBtn;
	//text field to enter username for admin
	private JTextField adminUsernametxt;
	//password field to enter password for admin
	private JPasswordField adminPasswordtxt;
	//number formatter 
	private NumberFormat nf;
	//Holder Variables For the Parameters
	//player account
	private PlayerAccount playerHolder;
	//String
	private String userNameHolder;
	//Double
	double accountBalHolder;
	public AdminLogin(PlayerAccount player) throws IOException {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
		//make the title be Slot Machine Game
		super ("Admin Login");
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
		//labels and setting fonts 
		adminUsernamelbl = new JLabel ("Please Enter Your User Name\n");
		adminPasswordlbl = new JLabel ("Please Enter Your Password\n");
		adminWrongPasslbl = new JLabel ("Wrong Password!");
		adminWrongPasslbl.setForeground(Color.RED);
		adminWrongUsernamelbl = new JLabel ("Wrong User Name!");
		adminWrongUsernamelbl.setForeground(Color.RED);
		background = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("adminPage.png"))));
		//buttons
		adminLoginBtn = new JButton ("Login");
		adminLoginBtn.setFont(new Font("PT Sans Caption", Font.PLAIN, 20));
		adminCancelBtn = new JButton ("Cancel");
		adminCancelBtn.setFont(new Font("PT Sans Caption", Font.PLAIN, 20));
		//text fields 
		adminUsernametxt = new JTextField();
		//password fields 
		adminPasswordtxt = new JPasswordField();
		//setting bounds for the admin components
		//labels
		adminUsernamelbl.setBounds(410, 128, 184, 41);
		adminPasswordlbl.setBounds(425,263,175,24);
		adminWrongUsernamelbl.setBounds(443,160,130,32);
		adminWrongPasslbl.setBounds(457, 287, 115, 24);
		background.setBounds(0, 0, 1000, 700);
		//buttons
		adminLoginBtn.setBounds(382,483,100,57);
		adminCancelBtn.setBounds(528, 483, 100, 57);
		//text fields
		adminUsernametxt.setBounds(382, 191, 246, 32);
		adminPasswordtxt.setBounds(382, 311, 246, 32);
		//adding all admin components to the frame
		//labels
		getContentPane().add(adminUsernamelbl);
		getContentPane().add(adminPasswordlbl);
		getContentPane().add(adminWrongUsernamelbl);
		getContentPane().add(adminWrongPasslbl);
		//buttons
		getContentPane().add(adminCancelBtn);
		getContentPane().add(adminLoginBtn);
		//text fields 
		getContentPane().add(adminUsernametxt);		
		getContentPane().add(adminPasswordtxt);
		//background
		getContentPane().add(background);
		adminLoginBtn.addActionListener(this);
		adminCancelBtn.addActionListener(this);
		//set visible false for the wrong username and wrong password labels 
		adminWrongPasslbl.setVisible(false);
		adminWrongUsernamelbl.setVisible(false);
		//
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
		//dummy player for the object
		PlayerAccount player = new PlayerAccount ("John","35th Street","9056661239","password",1000,"1.png");
		AdminLogin mainMenu = new AdminLogin (player);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==adminCancelBtn){
			//if cancel button is pressed, dispose and open main menu
			dispose();
			try {
				MainMenu resetMenu = new MainMenu(playerHolder);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == adminLoginBtn){
			//checks if data entered is correct 
			if (adminUsernametxt.getText().equalsIgnoreCase("campos")&& "dev".equalsIgnoreCase
					(new String (adminPasswordtxt.getPassword()))) {
				//if correct dispose current window and open admin lists 
				try {
					AdminInformationPage adminPage = new AdminInformationPage(playerHolder);
					dispose();
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					//show this
					JOptionPane.showMessageDialog(null, "ERROR: File Not Found");
				}
			}
			else {
				//else clear all entries and display error label
				adminWrongUsernamelbl.setVisible(true);
				adminWrongPasslbl.setVisible(true);
				adminUsernametxt.setText("");
				adminPasswordtxt.setText("");
			}
		}
	}
}
