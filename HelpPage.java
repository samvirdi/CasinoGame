import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import javax.imageio.*;
import javax.swing.*;


public class HelpPage extends JFrame implements ActionListener
{
	//Button to go back
	JButton btnBack;
	//String
	private String userName;
	//Player account 
	private PlayerAccount playerHolder, playerEdited;
	//double
	private double accountBalHolder;
	//Labels
	private JLabel userNamelbl, amountInAcclbl, iconPanel;
	//Number Formatter
	private NumberFormat nf;
	public HelpPage(PlayerAccount player) throws IOException 
	{	
		//make the title be Help Page
		super ("Help Page");

		//make number format format for currency 
		nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);

		//holder global variable so that other methods can access the account balance
		playerHolder = player;
		playerEdited = player;
		accountBalHolder = player.getPlayerBalance();
		userName = player.getPlayerName();

		//setting the username, account balance labels as the player data
		userNamelbl = new JLabel(userName);
		amountInAcclbl = new JLabel(nf.format(accountBalHolder));

		userNamelbl = new JLabel(userName);
		amountInAcclbl = new JLabel (nf.format(accountBalHolder));

		try
		{
			iconPanel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource(player.getIconNumber()))));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//setting bounds for the name, balance and icon 
		userNamelbl.setBounds(58, 0, 100, 39);
		amountInAcclbl.setBounds(58, 25, 100, 39);
		iconPanel.setBounds(6,6,62,48);

		// add name, balance, and icon to frame
		add(userNamelbl);
		add(amountInAcclbl);
		add(iconPanel);
		
		// Creating JButton btnBack, setting bounds and adding it to the frame
		btnBack = new JButton("Back");
		btnBack.setBounds(900, 6, 80, 48);
		add(btnBack);
		btnBack.addActionListener(this);
		
		// Creating JLabel background, setting bounds and adding it to the frame
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("helpPage.png"))));
		background.setBounds(0, 0, 1000, 700);
		add(background);
		//setting size and showing frame 
		setBounds(150, 20, 1000, 700);
		setResizable(false);
		setVisible (true); // Show the frame
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PlayerAccount player = new PlayerAccount ("John","35th Street","9056661239","password",1000,"1.png");
		HelpPage mainMenu = new HelpPage (player);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnBack){
			dispose();
			try {
				MainMenu menu = new MainMenu(playerEdited);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
