import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 */

/**
 * Author: Samandeep Singh Virdi
 * Date: Jan, 2017
 * Description: This GUI is for the user to register and make an account. 
 * They will have to input their username,password, address, phone number, and at least 
 * $10 to their account to create it.
 */
//METHOD LISTS
//public Register() throws NumberFormatException, IOException
//public static void main(String[] args) throws NumberFormatException, IOException
//public boolean checkFields (Register object)
//public void stateChanged(ChangeEvent e)
//public void actionPerformed(ActionEvent e)
public class Register extends JFrame implements ActionListener, ChangeListener{

	//Private Data 
	//Player list object
	private PlayerList list; 
	//All Labels for the GUI
	private JLabel usernamelbl,passwordlbl,addresslbl,
	phoneNumlbl, accountBalLbl, accountBalValue, selectIconlbl, background;
	//All text fields 
	private JTextField usernametxt, passwordtxt,addresstxt,phoneNumtxt;
	//Slider for the account balance
	private JSlider accountBalSlider;
	//Number Formatter 
	private NumberFormat nf;
	//file name variable 
	private String fileName = "Players.txt";
	//Panels for the icons
	private JLabel iconPanel1,iconPanel2,iconPanel3,iconPanel4,iconPanel5,iconPanel6;
	//picture objects for the pictures 
	private ImageIcon icon1,icon2,icon3,icon4,icon5,icon6;
	//Buttons to Create account or cancel 
	private JButton registerBtn, cancelBtn;
	//integer variable for slider value 
	private int sliderValue = 10;
	//Radio Buttons For Emblem Selection 
	private JRadioButton iconBtn1,iconBtn2,iconBtn3,iconBtn4,iconBtn5,iconBtn6;
	//button group to group the radio buttons together
	private ButtonGroup iconSelection;
	//String variable to get the icon selection
	private String iconName;
	//method object 
	private Methods methods; 
	public Register() throws NumberFormatException, IOException {
		// TODO Auto-generated constructor stub
		//make the title be Slot Machine Game
		super ("Register");
		//set the layout to border layout
		getContentPane().setLayout(null);
		//initializing the method object 
		methods = new Methods();
		//initializing the number format and getting the currency instance
		nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(2);
		//initializing the player ID object
		list = new PlayerList();
		list.readFromFile(fileName);
		//labels
		selectIconlbl = new JLabel ("Please Select Your Profile Picture:");
		selectIconlbl.setFont(new Font("PT Sans Caption", Font.PLAIN, 15));
		usernamelbl = new JLabel ("Please Enter Your Username:");
		passwordlbl = new JLabel ("Please Enter Your Password:");
		addresslbl = new JLabel ("Please Enter Your Address:");
		phoneNumlbl = new JLabel ("Please Enter Your Phone Number:");
		accountBalLbl = new JLabel ("Please Choose How Much Money You Want To Deposit (Minimum: $10.00:");
		accountBalValue = new JLabel (nf.format(sliderValue));
		accountBalValue.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		background = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("registerPage.png"))));
		//initializing the pictures 
		try {
			icon1 = new ImageIcon(ImageIO.read
					(getClass().getResource("1.png")));
			icon2 = new ImageIcon(ImageIO.read
					(getClass().getResource("2.png")));
			icon3 = new ImageIcon(ImageIO.read
					(getClass().getResource("3.png")));
			icon4 = new ImageIcon(ImageIO.read
					(getClass().getResource("4.png")));
			icon5 = new ImageIcon(ImageIO.read
					(getClass().getResource("5.png")));
			icon6 = new ImageIcon(ImageIO.read
					(getClass().getResource("6.png")));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//initializing the radio buttons
		iconBtn1 = new JRadioButton ("Icon 1");
		iconBtn1.setActionCommand("1.png");
		iconBtn2= new JRadioButton ("Icon 2");
		iconBtn2.setActionCommand("2.png");
		iconBtn3= new JRadioButton ("Icon 3");
		iconBtn3.setActionCommand("3.png");
		iconBtn4= new JRadioButton ("Icon 4");
		iconBtn4.setActionCommand("4.png");
		iconBtn5= new JRadioButton ("Icon 5");
		iconBtn5.setActionCommand("5.png");
		iconBtn6= new JRadioButton ("Icon 6");
		iconBtn6.setActionCommand("6.png");
		//initializing the button groups and adding buttons
		iconSelection = new ButtonGroup();
		iconSelection.add(iconBtn1);
		iconSelection.add(iconBtn2);
		iconSelection.add(iconBtn3);
		iconSelection.add(iconBtn4);
		iconSelection.add(iconBtn5);
		iconSelection.add(iconBtn6);
		//setting default selection for the icon selection
		iconBtn1.setSelected(true);
		//initializing the labels 
		iconPanel1 = new JLabel(icon1);
		iconPanel2 = new JLabel (icon2);
		iconPanel3 = new JLabel (icon3);
		iconPanel4 = new JLabel (icon4);
		iconPanel5 = new JLabel (icon5);
		iconPanel6= new JLabel (icon6);
		//initializing all text fields 
		usernametxt = new JTextField("",20);
		passwordtxt = new JTextField("",20);
		addresstxt = new JTextField("",20);
		phoneNumtxt = new JTextField("",20);
		//initializing the slider and minimum value
		accountBalSlider = new JSlider();
		accountBalSlider.setMinimum(10);
		accountBalSlider.setMinorTickSpacing(1);
		accountBalSlider.setSnapToTicks(true);
		accountBalSlider.setValue(10);
		accountBalSlider.setMaximum(1000);
		//initializing the buttons 
		registerBtn = new JButton ("Register");
		cancelBtn = new JButton ("Cancel");
		//setting bounds for all items
		//radio buttons
		iconBtn1.setBounds(648,244,93,31);
		iconBtn2.setBounds(744,244,93,31);
		iconBtn3.setBounds(845,244,100,31);
		iconBtn4.setBounds(648,337,93,31);
		iconBtn5.setBounds(744,337,93,31);
		iconBtn6.setBounds(845,337,93,31);
		//Icon Panels
		iconPanel1.setBounds(659, 184, 62, 48);
		iconPanel2.setBounds(744, 184, 62, 48);
		iconPanel3.setBounds(845, 184, 62, 48);
		iconPanel4.setBounds(658, 287, 62, 48);
		iconPanel5.setBounds(744, 287, 62, 48);
		iconPanel6.setBounds(845, 287, 62, 48);
		usernamelbl.setBounds(90, 153, 195, 31);
		passwordlbl.setBounds(90, 210, 176, 37);
		addresslbl.setBounds(100, 273, 176, 37);
		phoneNumlbl.setBounds(58, 337, 221, 31);
		accountBalLbl.setBounds(275, 428, 466, 48);
		selectIconlbl.setBounds(667,145, 255, 48);
		background.setBounds(0, 0, 1000, 700);
		//text fields
		usernametxt.setBounds(267, 150, 255, 37);
		passwordtxt.setBounds(267, 210, 255, 37);
		addresstxt.setBounds(269, 275, 255, 37);
		phoneNumtxt.setBounds(269, 335, 255, 37);
		//slider and slider value label
		accountBalSlider.setBounds(275,494,466,37);
		accountBalValue.setBounds(449, 544, 142, 37);
		//Buttons
		registerBtn.setBounds(440, 606, 132, 48);
		cancelBtn.setBounds(900, 6, 80, 48);
		//Adding All Labels to the frame
		//radio buttons
		getContentPane().add(iconBtn1);
		getContentPane().add(iconBtn2);
		getContentPane().add(iconBtn3);
		getContentPane().add(iconBtn4);
		getContentPane().add(iconBtn5);
		getContentPane().add(iconBtn6);
		//panels
		getContentPane().add(iconPanel1);
		getContentPane().add(iconPanel2);
		getContentPane().add(iconPanel3);
		getContentPane().add(iconPanel4);
		getContentPane().add(iconPanel5);
		getContentPane().add(iconPanel6);
		//labels
		getContentPane().add(selectIconlbl);
		getContentPane().add(usernamelbl);
		getContentPane().add(passwordlbl);
		getContentPane().add(addresslbl);
		getContentPane().add(phoneNumlbl);
		getContentPane().add(accountBalLbl);
		//text fields
		getContentPane().add(usernametxt);
		getContentPane().add(passwordtxt);
		getContentPane().add(addresstxt);
		getContentPane().add(phoneNumtxt);
		//slider and slider value label
		getContentPane().add(accountBalSlider);
		getContentPane().add(accountBalValue);
		//buttons
		getContentPane().add(registerBtn);
		getContentPane().add(cancelBtn);
		//background
		getContentPane().add(background);
		//adding action listener to buttons 
		registerBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		iconBtn1.addActionListener(this);
		iconBtn2.addActionListener(this);
		iconBtn3.addActionListener(this);
		iconBtn4.addActionListener(this);
		iconBtn5.addActionListener(this);
		iconBtn6.addActionListener(this);
		//adding change listener to the slider 
		accountBalSlider.addChangeListener(this);
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
		//test the menu
		Register register = new Register();

	}
	//checks the text fields
	public boolean checkFields (Register object){
		//usernametxt, passwordtxt,password2txt,addresstxt,phoneNumtxt
		//if the required fields are left blank error is displayed and returns false
		if (object.usernametxt.getText().equalsIgnoreCase("")||object.passwordtxt.getText().equalsIgnoreCase("")
				||object.addresstxt.getText().equalsIgnoreCase("")
				||object.phoneNumtxt.getText().equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(null, "ERROR: Please Fill All Of The Fields!");
			return false;
		}
		//if any of the fields contain commas display error and returns false
		else if (object.usernametxt.getText().contains(",")||object.passwordtxt.getText().contains(",")
				||object.addresstxt.getText().contains(",") || object.phoneNumtxt.getText().contains(",")){
			JOptionPane.showMessageDialog(null, "ERROR: Cannot Put A Comma In The Fields!!");
			return false;
		}
		//if the phone number text field contains any letters show error 
		// the [0-9]+ was taken from: 
		//http://stackoverflow.com/questions/10575624/java-string-see-if-a-string-contains-only-numbers-and-not-letters
		//it basically checks if the characters in the field are numbers are not and returns a boolean its true or false.
		//this if statement checks if when it is false and then shows a error and returns false
		else if (!object.phoneNumtxt.getText().matches("[0-9]+")){
			JOptionPane.showMessageDialog(null, "ERROR: Phone Number Can Only Be Numbers!");
			return false;
		}
		else if (object.phoneNumtxt.getText().length()<10){
			JOptionPane.showMessageDialog(null, "ERROR: Please Make Sure The Format of the Phone Number is Correct!");
			return false;
		}
		//if all the requirements are valid the method returns true.
		return true;
	}
	//method to check if the slider moved, if moved update label
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if (!accountBalSlider.getValueIsAdjusting()){
			sliderValue = accountBalSlider.getValue();
			accountBalValue.setText(nf.format(sliderValue));
		}

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//get the icon from what the user selected
		if (e.getSource() == iconBtn1){
			iconName = iconBtn1.getActionCommand();
		}
		else if (e.getSource() == iconBtn2){
			iconName = iconBtn2.getActionCommand();
		}
		else if (e.getSource() == iconBtn3){
			iconName = iconBtn3.getActionCommand();
		}
		else if (e.getSource() == iconBtn4){
			iconName = iconBtn4.getActionCommand();
		}
		else if (e.getSource() == iconBtn5){
			iconName = iconBtn5.getActionCommand();
		}
		else if (e.getSource() == iconBtn6){
			iconName = iconBtn6.getActionCommand();
		}
		//set default icon as icon 1 
		else {
			iconName = "1.png";
		}
		if (e.getSource()==cancelBtn){
			//if cancel is pressed, ask user yes or no, if yes then dispose and go back to login
			if (methods.yesOrNoDialogBox("Are You Sure You Want To Cancel?\nAll Data Will Be Lost!","Cancel")){
				dispose();
				try {
					LogIn loginMenu = new LogIn();
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					//show this
					JOptionPane.showMessageDialog(null, "ERROR: File Not Found");
				}
			}
		}
		else if (e.getSource()==registerBtn){
			if (checkFields(this)){
				//if user presses register check fields 
				//then check if player exists 
				String name = usernametxt.getText();
				list.insertion();
				if ((list.binarySearch(name)) >= 0){
					//if player exists display error and clear user name field 
					JOptionPane.showMessageDialog(null, "Sorry This User Name Is Already Taken");
					usernametxt.setText("");
				}
				else {
					//else get all the data from the text fields 
					String password = passwordtxt.getText();
					String address = addresstxt.getText();
					String phoneNum = phoneNumtxt.getText();
					double accBal = accountBalSlider.getValue();
					//create the player account and insert it into the player list 
					PlayerAccount player = new PlayerAccount(name,address,phoneNum,password,accBal,iconName);
					list.insert(player);
					try {
						//save to file then go back to main menu 
						list.saveToFile(fileName);
						dispose();
						LogIn logIn = new LogIn();
					} catch (NumberFormatException | IOException e1) {
						//show this
						JOptionPane.showMessageDialog(null, "ERROR: File Not Found");
					}
				}

			}
		}
	}
}
