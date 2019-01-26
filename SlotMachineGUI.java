import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * Author: Samandeep Singh Virdi
 * Date: Jan, 2017
 * Description: This is the Graphical User Interface for the 
 * slot machine game, in this you first bet an amount and then 
 * you pull the level and it spins, depending on the combo 
 * you win back money.
 */

//Method lists 
//public SlotMachineGUI(PlayerAccount player) 
//public static void main(String[] args)
//public void betAndSet (double amount)
//public void actionPerformed(ActionEvent e) 
//public void checkAndSetSlots ()
public class SlotMachineGUI extends JFrame implements ActionListener{


	//Encapsulated Data Declaration
	//Double variable for amount that will be bet
	private double amountBet, amountWon;
	//Different JPanels for different sections of the GUI
	//Labels
	private JLabel title, userNamelbl, amountInAcclbl, amountBetlbl, 
	jackpotlbl,amountWonlbl, inputMoneylbl, background,iconPanel;
	//Picture Objects 
	private Picture amountBetBackground,
	winningsBackground, amountWonBackground, slotImages1,slotImages2,slotImages3,generalBackground;
	//Slot Machine object for the game
	private SlotMachine slots;
	//text field for custom bets 
	private JTextField customtxt;
	//Buttons for betting
	private JButton bet1Btn,bet5Btn,bet10Btn,bet25Btn,bet50Btn,bet100Btn,customBetBtn, spinBtn, backBtn,enterBtn;
	//Number Formatter
	private NumberFormat nf;
	//player list object to change the file whenever money is lost or bet
	private PlayerList list;
	//String file names 
	private String fileName ="Players.txt";
	//holder variable for parameter
	//Player account 
	private PlayerAccount playerHolder, playerEdited;
	//String
	private String userName;
	//double
	private double accountBalHolder;
	//Constructor for SlotMachine GUI
	public SlotMachineGUI(PlayerAccount player) {
		// TODO Auto-generated constructor stub
		//make the title be Slot Machine Game
		super ("Slot Machine Game");
		//set the layout to border layout
		getContentPane().setLayout(null);
		//make number format format for currency 
		nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);
		//init the slots object
		slots = new SlotMachine();
		//holder global variable so that other methods can access the account balance
		playerHolder = player;
		playerEdited = player;
		accountBalHolder = player.getPlayerBalance();
		userName = player.getPlayerName();
		//get the background for the slot
		try {
			//set list to the file 
			list = new PlayerList();
			list.readFromFile(fileName);
			amountBetBackground = new Picture(new ImageIcon(ImageIO.read
					(getClass().getResource("Amount Bet Gold Rectangle.png"))), 0, 0);
			winningsBackground = new Picture(new ImageIcon(ImageIO.read
					(getClass().getResource("Potential Winnings Gold Rectangle.png"))), 0, 0);
			amountWonBackground = new Picture(new ImageIcon(ImageIO.read
					(getClass().getResource("Amount Won Gold Rectangle.png"))), 0, 0);
			slotImages1 = new Picture(new ImageIcon(ImageIO.read
					(getClass().getResource("Lucky 7.png"))), 0, 0);
			slotImages2 = new Picture(new ImageIcon(ImageIO.read
					(getClass().getResource("Lucky 7.png"))), 0, 0);
			slotImages3 = new Picture(new ImageIcon(ImageIO.read
					(getClass().getResource("Lucky 7.png"))), 0, 0);
			iconPanel = 
					new JLabel(new ImageIcon(ImageIO.read(getClass().getResource(player.getIconNumber()))));
			background = new JLabel
					(new ImageIcon(ImageIO.read(getClass().getResource("slotMachine.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//initializing the button, setting font, color and bounds
		bet1Btn = new JButton ("$1");
		bet1Btn.setFont(new Font("Charlemagne Std", Font.BOLD | Font.ITALIC, 25));
		bet1Btn.setForeground(Color.BLACK);
		bet1Btn.setBackground(Color.WHITE);
		bet1Btn.setBounds(66, 625, 69, 35);

		//initializing the button, setting font, color and bounds
		bet5Btn= new JButton ("$5");
		bet5Btn.setFont(new Font("Charlemagne Std", Font.BOLD | Font.ITALIC, 25));
		bet5Btn.setForeground(Color.BLACK);
		bet5Btn.setBackground(Color.WHITE);
		bet5Btn.setBounds(145, 625, 75, 34);

		//initializing the button, setting font, color and bounds
		bet10Btn= new JButton ("$10");
		bet10Btn.setFont(new Font("Charlemagne Std", Font.BOLD | Font.ITALIC, 25));
		bet10Btn.setForeground(Color.BLACK);
		bet10Btn.setBackground(Color.WHITE);
		bet10Btn.setBounds(230, 625, 91, 34);

		//initializing the button, setting font, color and bounds
		bet25Btn= new JButton ("$25");
		bet25Btn.setFont(new Font("Charlemagne Std", Font.BOLD | Font.ITALIC, 25));
		bet25Btn.setForeground(Color.BLACK);
		bet25Btn.setBackground(Color.WHITE);
		bet25Btn.setBounds(331, 625, 91, 34);

		//initializing the button, setting font, color and bounds
		bet50Btn= new JButton ("$50");
		bet50Btn.setFont(new Font("Charlemagne Std", Font.BOLD | Font.ITALIC, 25));
		bet50Btn.setForeground(Color.BLACK);
		bet50Btn.setBackground(Color.WHITE);
		bet50Btn.setBounds(432, 625, 91, 35);

		//initializing the button, setting font, color and bounds
		bet100Btn= new JButton ("$100");
		bet100Btn.setFont(new Font("Charlemagne Std", Font.BOLD | Font.ITALIC, 25));
		bet100Btn.setForeground(Color.BLACK);
		bet100Btn.setBackground(Color.WHITE);
		bet100Btn.setBounds(533, 625, 103, 35);

		//initializing the button, setting font, color and bounds
		customBetBtn= new JButton ("Custom Bet");
		customBetBtn.setFont(new Font("Charlemagne Std", Font.BOLD, 15));
		customBetBtn.setForeground(Color.BLACK);
		customBetBtn.setBackground(Color.WHITE);
		customBetBtn.setBounds(650, 625, 150, 35);

		//back button initialized
		backBtn = new JButton("Back");

		//Enter Button initialized and set bounds
		enterBtn = new JButton ("Enter");
		enterBtn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		enterBtn.setBounds(925, 629, 75, 35);
		enterBtn.setVisible(false);

		//initailize the custom text field 
		customtxt = new JTextField();
		customtxt.setBounds(804,629,124,34);
		customtxt.setVisible(false);

		//initializing the amount bet, jackpot and amount won labels
		amountBetlbl = new JLabel(nf.format(amountBet));
		amountBetlbl.setFont(new Font("IMPACT",Font.ITALIC, 45));
		jackpotlbl = new JLabel(nf.format(amountBet*100));
		jackpotlbl.setFont(new Font("IMPACT",Font.ITALIC, 45));
		amountWonlbl = new JLabel (nf.format(amountWon));
		amountWonlbl.setFont(new Font("IMPACT",Font.ITALIC, 45));


		//setting bounds for all other objects
		amountWonlbl.setBounds(450, 130, 400, 75);
		amountBetlbl.setBounds(250, 520, 253, 75);
		jackpotlbl.setBounds(600,520, 400, 75);
		slotImages1.setBounds(192, 274, 213, 234);
		slotImages2.setBounds(428, 267, 213, 234);
		slotImages3.setBounds(650, 267, 213, 234);
		amountWonBackground.setBounds(200, 25, 500, 200);
		amountBetBackground.setBounds(0, 410, 500, 500);
		winningsBackground.setBounds(350, 410, 500, 200);
		background.setBounds(0,0,1000,700);
		backBtn.setBounds(900, 6, 80, 48);

		//initializing, setting font and bounds of the spin button
		spinBtn = new JButton ("Spin");
		spinBtn.setFont(new Font("IMPACT",Font.ITALIC, 25));
		spinBtn.setBounds(900, 100, 90, 500);

		//setting the username, account balance labels as the player data
		userNamelbl = new JLabel(userName);
		amountInAcclbl = new JLabel(nf.format(accountBalHolder));
		//setting bounds for the name, balance and icon 
		userNamelbl.setBounds(58, 0, 100, 39);
		amountInAcclbl.setBounds(58, 25, 100, 39);
		iconPanel.setBounds(6,6,62,48);

		//adding all components to the frame
		getContentPane().add(bet1Btn);
		getContentPane().add(bet5Btn);
		getContentPane().add(bet10Btn);
		getContentPane().add(bet25Btn);
		getContentPane().add(bet50Btn);
		getContentPane().add(bet100Btn);
		getContentPane().add(customBetBtn);
		getContentPane().add(amountBetlbl);
		getContentPane().add(jackpotlbl);
		getContentPane().add(slotImages1);
		getContentPane().add(slotImages2);
		getContentPane().add(slotImages3);
		getContentPane().add(backBtn);
		getContentPane().add(amountWonlbl);
		getContentPane().add(amountWonBackground);
		getContentPane().add(userNamelbl);
		getContentPane().add(amountInAcclbl);
		getContentPane().add(amountBetBackground);
		getContentPane().add(winningsBackground);
		getContentPane().add(spinBtn);
		getContentPane().add(iconPanel);
		getContentPane().add(customtxt);
		getContentPane().add(enterBtn);
		getContentPane().add(background);
		//add actionListners to each button 
		bet1Btn.addActionListener(this);
		bet5Btn.addActionListener(this);
		bet10Btn.addActionListener(this);
		bet25Btn.addActionListener(this);
		bet50Btn.addActionListener(this);
		bet100Btn.addActionListener(this);
		customBetBtn.addActionListener(this);
		backBtn.addActionListener(this);
		spinBtn.addActionListener(this);
		spinBtn.setEnabled(false);
		enterBtn.addActionListener(this);
		//setting size and showing frame 
		setBounds(150, 20, 1000, 700);
		setResizable(false);
		setVisible (true); // Show the frame

	}
	
	/**
	 * @param args
	 */
	//Self Testing Method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create player to pass in 
		PlayerAccount player = new PlayerAccount ("John","35th Street","9056661239","password",1000,"1.png");
		//create new slot machine GUI object
		SlotMachineGUI game = new SlotMachineGUI(player);
	}
	
	//method to subtract the amount thats being betted 
	public void betAndSet (double amount){
		//set spin button as true
		spinBtn.setEnabled(true);
		//subtract amount from the account bal holder and
		//add the amount to amountBet 
		accountBalHolder -= amount;
		amountBet += amount;
		//update the account balance, set the labels for GUI
		amountInAcclbl.setText(nf.format(accountBalHolder));
		amountBetlbl.setText(nf.format(amountBet));
		jackpotlbl.setText(nf.format(amountBet*100));
		amountWonlbl.setText(nf.format(amountWon));
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==bet1Btn){
			//if user presses bet 1 dollar button, the program checks if the user 
			//has enough then calls the method to set the GUI up
			if (((accountBalHolder -1) < 0) == false){
				betAndSet (1);
			}
			else {
				//if user doesnt have enough money then display this
				JOptionPane.showMessageDialog(null, 
						"ERROR: Insufficient Funds\nPlease Go To Manage Funds To Update Funds");
			}

		}
		else if (e.getSource()==bet5Btn){
			//if user presses bet 5 dollar button, the program checks if the user 
			//has enough then calls the method to set the GUI up
			if (((accountBalHolder -5) < 0) == false){
				betAndSet(5);
			}
			else {
				//if user doesnt have enough money then display this
				JOptionPane.showMessageDialog(null, 
						"ERROR: Insufficient Funds\nPlease Go To Manage Funds To Update Funds");	
			}
		}
		else if (e.getSource()==bet10Btn){
			//if user presses bet 10 dollar button, the program checks if the user 
			//has enough then calls the method to set the GUI up
			if (((accountBalHolder -10) < 0) == false){
				betAndSet(10);
			}
			else {
				//if user doesnt have enough money then display this
				JOptionPane.showMessageDialog(null, 
						"ERROR: Insufficient Funds\nPlease Go To Manage Funds To Update Funds");	
			}
		}
		else if (e.getSource()==bet25Btn){
			//if user presses bet 25 dollar button, the program checks if the user 
			//has enough then calls the method to set the GUI up
			if (((accountBalHolder -25) < 0) == false){
				betAndSet(25);
			}
			else {
				//if user doesnt have enough money then display this
				JOptionPane.showMessageDialog(null, 
						"ERROR: Insufficient Funds\nPlease Go To Manage Funds To Update Funds");	
			}
		}
		else if (e.getSource()==bet50Btn){
			//if user presses bet 50 dollar button, the program checks if the user 
			//has enough then calls the method to set the GUI up
			if (((accountBalHolder -50) < 0) == false){
				betAndSet(50);
			}
			else {
				//if user doesnt have enough money then display this
				JOptionPane.showMessageDialog(null, 
						"ERROR: Insufficient Funds\nPlease Go To Manage Funds To Update Funds");	
			}
		}
		else if (e.getSource()==bet100Btn){
			//if user presses bet 100 dollar button, the program checks if the user 
			//has enough then calls the method to set the GUI up
			if (((accountBalHolder -100) < 0) == false){
				betAndSet(100);
			}
			else {
				//if user doesnt have enough money then display this
				JOptionPane.showMessageDialog(null, 
						"ERROR: Insufficient Funds\nPlease Go To Manage Funds To Update Funds");	
			}
		}
		else if (e.getSource()==customBetBtn){
			//if user presses custom bet button then the text field and enter button appear  
			customtxt.setVisible(true);
			enterBtn.setVisible(true);
		}
		else if (e.getSource()==enterBtn){
			//if user presses enter check the field
			if (!customtxt.getText().matches("[0-9]+.*")){
				//if invalid input then display this 
				JOptionPane.showMessageDialog(null, 
						"ERROR: Invalid Input");
			}
			//else get the custom amount and then check if the user has enough, 
			//if enough set the GUI up
			else {
				double customAmount = Double.parseDouble(customtxt.getText());
				if (((accountBalHolder -customAmount) < 0) == false){
					betAndSet(customAmount);
				}
				else {
					//if user doesnt have enough money then display this
					JOptionPane.showMessageDialog(null,
							"ERROR: Insufficient Funds\nPlease Go To Manage Funds To Update Funds");
				}
			}
			//set the text field as empty and set visible false of text field and enter button
			customtxt.setText("");
			customtxt.setVisible(false);
			enterBtn.setVisible(false);
		}
		//if user presses spin button
		else if (e.getSource()== spinBtn){
			//spin the slots using the slot object then set the pictures based on what you roller
			slots.spinSlots();
			checkAndSetSlots();
			//set up the labels 
			amountWon = slots.checkCombination(amountBet);
			accountBalHolder += amountWon;
			amountInAcclbl.setText(nf.format(accountBalHolder));
			amountWonlbl.setText(nf.format(amountWon));
			//withraw the amount bet and then deposit the amount won into the players account
			playerEdited.withdraw(amountBet);
			playerEdited.deposit(amountWon);
			//insertion sort the list then change the players information
			list.insertion();
			list.change(playerHolder, playerEdited);
			try {
				//save new information to file 
				list.saveToFile(fileName);
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				//show this error 
				JOptionPane.showMessageDialog(null,"Error File Not Found!");
			}
			//set the amount won and bet as zero
			amountWon = 0;
			amountBet = 0;
			//set the labels as needed and then set the spin button as false 
			amountBetlbl.setText(nf.format(amountBet));
			jackpotlbl.setText(nf.format(amountBet*100));
			spinBtn.setEnabled(false);
		}

		else if (e.getSource() == backBtn){
			//dispose current window
			dispose();
			try {
				//go back to the main menu as the same player 
				MainMenu menu = new MainMenu(playerEdited);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//show this error 
				JOptionPane.showMessageDialog(null,"Error File Not Found!");
			}
		}
	}
	public void checkAndSetSlots (){
		//make integer variables equal the slots value 
		int slot1 =	slots.getSlot1();
		int slot2 = slots.getSlot2();
		int slot3 = 	slots.getSlot3();
		try {
			//set the slot picture according to the value of the slot spun
			if (slot1 == 4){
				//set the image as needed for slot 1 
				slotImages1.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Lucky 7.png")))));
			}
			else if (slot1 ==3){
				//set the image as needed for slot 1 
				slotImages1.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Double Bar.png")))));
			}
			else if (slot1 ==2){
				//set the image as needed for slot 1 
				slotImages1.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Single Bar.png")))));
			}
			else if (slot1 ==1){
				//set the image as needed for slot 1 
				slotImages1.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Cherry.png")))));
			}
			else if (slot1 ==0){
				//set the image as needed for slot 1 
				slotImages1.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Blank.png")))));
			}
			if (slot2 ==4){
				//set the image as needed for slot 2 
				slotImages2.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Lucky 7.png")))));
			}
			else if (slot2 ==3){
				//set the image as needed for slot 2 
				slotImages2.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Double Bar.png")))));
			}
			else if (slot2 ==2){
				//set the image as needed for slot 2
				slotImages2.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Single Bar.png")))));
			}
			else if (slot2 ==1){
				//set the image as needed for slot 2
				slotImages2.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Cherry.png")))));
			}
			else if (slot2 ==0){
				//set the image as needed for slot 2
				slotImages2.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Blank.png")))));
			}
			if (slot3 ==4){
				//set the image as needed for slot 3
				slotImages3.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Lucky 7.png")))));
			}
			else if (slot3 ==3){
				//set the image as needed for slot 3
				slotImages3.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Double Bar.png")))));
			}
			else if (slot3 ==2){
				//set the image as needed for slot 3
				slotImages3.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Single Bar.png")))));
			}
			else if (slot3 ==1){
				//set the image as needed for slot 3
				slotImages3.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Cherry.png")))));
			}
			else if (slot3 ==0){
				//set the image as needed for slot 3
				slotImages3.setPic((new ImageIcon(ImageIO.read
						(getClass().getResource("Blank.png")))));
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			//show this error 
			JOptionPane.showMessageDialog(null,"Error File Not Found!");
		}
		//repaint the frame to update it
		repaint();
	}
}
