import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * 
 */

/**
 * Author: Samandeep Singh Virdi
 * Date: Jan,2017
 * Description: This is the page where the administrator can view 
 * all of the accounts for his/her game. Here you can search for a 
 * specific account and sort alphabetically or the highest account 
 * balance
 */
public class New_Editor extends JFrame implements ActionListener{

        //private data 
        //table for the table of accounts 
        private JTable bigTable,outputTable;
        //string variable for the list's column names 
        private String [] columnNames= {"Player ID",
                        "Player Name",
                        "Address", "Phone Number","Password","Account Balance"};
        //string variable for table data
        private String [][] tableInfo,outputTableInfo;
        //PlayerIDList variable created to get the data 
        private PlayerList list;
        //scroll pane to make the table scrollable
        private JScrollPane scrollPane,sp2;
        //Labels for the page 
        private JLabel titlelbl,sortinglbl,searchlbl,outputlbl,inputSearchlbl, background;
        //text field to take in the search 
        private JTextField searchtxt;
        //radio buttons for what to search for and how to sort 
        private JRadioButton searchPlayer,searchID,sortName,sortBal;
        //button group object to group the buttons 
        private ButtonGroup searchGroup,sortGroup;
        //buttons for the application 
        private JButton searchBtn, sortBtn, backBtn, resetBtn;
        //holder variables for the parameters
        //player account
        private PlayerAccount playerHolder;
        //String
        private String usernameHolder;
        //Double
        private double accountBalHolder;
        public New_Editor(PlayerAccount player) throws NumberFormatException, IOException {
                // TODO Auto-generated constructor stub
                //setting the title 
                super("All Accounts");
                //setting layout manager as null
                getContentPane().setLayout(null);
                //initialize the holders 
                playerHolder= player;
                usernameHolder = player.getPlayerName();
                accountBalHolder= player.getPlayerBalance();
                //initializing the player ID object
                list = new PlayerList();
                list.readFromFile("Players.txt");
                tableInfo = list.printToList();
                //initializing the tables
                bigTable = new JTable (tableInfo,columnNames);
                bigTable.setRowSelectionAllowed(true);             
                //CODE TAKEN FROM: http://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
                //Making a DefaultTableModel which takes in the tableInfo and the columnNames 
                DefaultTableModel tableModel = new DefaultTableModel(tableInfo, columnNames) {
                        //overiding the isCellEditable method from DefaultTableModel
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                //all cells false therefore they are not editable
                                return false;
                        }
                };
                
                //prevent user from draggin the info
                bigTable.getTableHeader().setReorderingAllowed(false);
        
                //make the table single selection
                bigTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
                //set the model for the JTable as the tableModel
                bigTable.setModel(tableModel);
                
                //Initializing the variables 
                //labels 
                titlelbl = new JLabel ("Administrator Menu");
                titlelbl.setFont(new Font ("PT Sans Caption", Font.BOLD, 40));
                titlelbl.setHorizontalAlignment(SwingConstants.CENTER);
                searchlbl = new JLabel ("Search Options");
                searchlbl.setFont(new Font("PT Sans Caption", Font.BOLD | Font.ITALIC, 15));
                sortinglbl = new JLabel ("Sort Options");
                sortinglbl.setFont(new Font("PT Sans Caption", Font.BOLD | Font.ITALIC, 15));
                outputlbl = new JLabel ("Search Output");
                outputlbl.setFont(new Font("PT Sans Caption", Font.BOLD | Font.ITALIC, 15));
                inputSearchlbl = new JLabel ("Input Search");
                inputSearchlbl.setFont(new Font("PT Sans Caption", Font.BOLD | Font.ITALIC, 15));
                background = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("admin.png"))));
                //radio buttons 
                searchPlayer = new JRadioButton ("Search Player Name");
                searchPlayer.setActionCommand("Player");
                searchID = new JRadioButton ("Search Player ID");
                searchID.setActionCommand("ID");
                sortName = new JRadioButton ("Sort Alphabetically");
                sortName.setActionCommand("sName");
                sortBal = new JRadioButton ("Sort By Highest Balance");
                sortBal.setActionCommand("sBal");
                //default selection
                searchPlayer.setSelected(true);
                sortName.setSelected(true);
                //group buttons 
                searchGroup = new ButtonGroup();
                sortGroup = new ButtonGroup();
                //buttons 
                searchBtn = new JButton ("Search");
                sortBtn = new JButton ("Sort");
                backBtn = new JButton ("Back");
                resetBtn = new JButton ("Reset");
                //text fields 
                searchtxt = new JTextField();
                //add radio buttons to the button groups 
                searchGroup.add(searchPlayer);
                searchGroup.add(searchID);
                sortGroup.add(sortName);
                sortGroup.add(sortBal);
                //set the scroll pane as the jTable
                scrollPane = new JScrollPane(bigTable);
                //setting bounds for all components 
                //labels 
                titlelbl.setBounds(262, 0, 511, 52);
                searchlbl.setBounds(404, 431, 154,37);
                sortinglbl.setBounds(404, 386, 163, 23);
                outputlbl.setBounds(403, 545, 176, 37);
                inputSearchlbl.setBounds(404, 480, 125, 37);
                background.setBounds(0, 0, 1000, 700);
                //scroll pane
                scrollPane.setBounds(0, 42, 1000, 332);
                //radio buttons
                searchPlayer.setBounds(273, 456, 154, 36);
                searchID.setBounds(471, 459, 154, 29);
                sortName.setBounds(273, 409, 163, 23);
                sortBal.setBounds(471, 406, 179,29);
                //buttons 
                searchBtn.setBounds(664, 501, 100, 36);
                sortBtn.setBounds(664, 404, 100, 36);
                backBtn.setBounds(925, 0, 75, 36);
                resetBtn.setBounds(664,501,100,37);
                //text field
                searchtxt.setBounds(371, 504, 170, 29);
                //adding components to frame 
                getContentPane().add(inputSearchlbl);
                getContentPane().add(searchtxt);
                getContentPane().add(outputlbl);
                getContentPane().add(sortinglbl);
                getContentPane().add(searchlbl);
                getContentPane().add(searchBtn);
                getContentPane().add(sortBtn);
                getContentPane().add(backBtn);
                getContentPane().add(searchPlayer);
                getContentPane().add(searchID);
                getContentPane().add(sortName);
                getContentPane().add(sortBal);
                getContentPane().add(titlelbl);
                getContentPane().add(scrollPane);
                getContentPane().add(resetBtn);
                getContentPane().add(background);
                //add action listner to all of the buttons 
                searchBtn.addActionListener(this);
                sortBtn.addActionListener(this);
                searchPlayer.addActionListener(this);
                searchID.addActionListener(this);
                sortName.addActionListener(this);
                sortBal.addActionListener(this);
                backBtn.addActionListener(this);
                resetBtn.addActionListener(this);
                resetBtn.setVisible(false);
                //setting the size, window resizable and visible
                setBounds(150, 20, 1000, 700);
                setResizable(false);
                setVisible(true);
        }
        public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (e.getSource()==searchBtn){
                        String str,data;
                        String [] temp = null;
                        int pos;
                        PlayerAccount player;
                        String selection = searchGroup.getSelection().getActionCommand();
                        if (selection.equalsIgnoreCase("Player")){
                                str = searchtxt.getText();
                                list.insertion();
                                pos = list.binarySearch(str);
                                player = list.getPlayer(pos);
                                outputTableInfo = new String [6][6];
                                data = list.toString(player);
                                temp = data.split(",");
                        }
                        else {
                                str= searchtxt.getText();
                                pos = list.linearSearchModel(str);
                                player = list.getPlayer(pos);
                                outputTableInfo = new String [6][6];
                                data = list.toString(player);
                                temp = data.split(",");
                        }
                        outputTableInfo [0][0] = temp[0];
                        outputTableInfo[0][1] = temp[1];
                        outputTableInfo[0][2] = temp[2];
                        outputTableInfo[0][3] = temp[3];
                        outputTableInfo[0][4] = temp[4];
                        outputTableInfo[0][5] = temp[5];
                        outputTable = new JTable (outputTableInfo,columnNames);
                        outputTable.setRowSelectionAllowed(true);
                        DefaultTableModel smallTableModel = new DefaultTableModel(outputTableInfo,columnNames){
                                //overiding the isCellEditable method from DefaultTableModel
                                @Override
                                public boolean isCellEditable(int row, int column) {
                                        //all cells false therefore they are not editable
                                        return false;
                                }
                        };
                        outputTable.setModel(smallTableModel);
                        outputTable.getTableHeader().setReorderingAllowed(false);
                        outputTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        //table
                        sp2 = new JScrollPane (outputTable);
                        sp2.setBounds(250, 578, 519, 81);
                        getContentPane().remove(background);
                        getContentPane().add(sp2);
                        getContentPane().add(background);
                        searchBtn.setVisible(false);
                        resetBtn.setVisible(true);
                        repaint();
                }
                else if (e.getSource()==sortBtn){
                        String selection = sortGroup.getSelection().getActionCommand();
                        if (selection.equalsIgnoreCase("sName")){
                                list.insertion();
                        }
                        else {
                                list.rippleSort();
                        }
                        try {
                                list.saveToFile("Players.txt");
                                New_Editor updatedList = new New_Editor(playerHolder);
                        } catch (NumberFormatException | IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                        }
                        dispose();
                        
                }
                else if (e.getSource()==backBtn){
                		dispose();
                		try {
							MainMenu menu = new MainMenu(playerHolder);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                }
                else if (e.getSource()==resetBtn){
                	dispose();
                	try {
						New_Editor resetAdminMenu = new New_Editor (playerHolder);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
        }
        
        /**
         * @param args
         * @throws IOException 
         * @throws NumberFormatException 
         */
        public static void main(String[] args) throws NumberFormatException, IOException {
                // TODO Auto-generated method stub
        	PlayerAccount player = new PlayerAccount ("John","35th Street","9056661239","password",1000,"1.png");
                New_Editor test = new New_Editor(player);
        }





}
