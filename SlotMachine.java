/**
 *Author: Samandeep Singh Virdi
 *Data: Jan, 2017
 *Description: This is the SlotMachine object in which contains the 
 *methods to spin the slot, check the combination and get the money 
 */

//METHOD LISTS
//SlotMachine()
//public static void main(String[] args)
//public void spinSlots ()
//public String getSlots()
//public int getSlot1()
//public int getSlot2()
//public int getSlot3()
//public double checkCombination (double amountBet)
public class SlotMachine {
	//private data 
	private int slot1, slot2, slot3;
	private int amountOfMoney;
	//default constructor setting the slots as 4(7s)
	public SlotMachine() {
		// TODO Auto-generated constructor stub
		this.slot1 = 4;
		this.slot2 = 4;
		this.slot3 = 4;
	}
	//set the value of the slots by using matho random
	public void spinSlots (){
		this.slot1 = (int)(Math.random() * 5);
		this.slot2 = (int)(Math.random() * 5);
		this.slot3 = (int)(Math.random() * 5);
	}
	//get all the slots value
	public String getSlots(){
		String slots = slot1 + "," + slot2 + "," + slot3;
		return slots;
	}
	//get the value of slot 1
	public int getSlot1(){
		return slot1;
	}
	//get the value of slot 2
	public int getSlot2(){
		return slot2;
	}
	//get the value of slot 3
	public int getSlot3(){
		return slot3;
	}
	public double checkCombination (double amountBet){
		double amountWon = 0;
		//if there is a zero in any of the slots, the amount won back is 0
		if (this.slot1 == 0 ||this.slot2 == 0 || this.slot3 == 0){
			return amountWon;
		}
		//if there is straight 7s then there is a jack pot and the 
		//amount won back is the amount bet times 100
		else if (this.slot1 == 4 && this.slot2 ==4  && this.slot3 == 4){
			amountWon = amountBet * 100;
			return amountWon;
		}
		//if there is straight double bars, then the amount won back is
		//the amount bet times 50
		else if (this.slot1 == 3 && this.slot2 ==3  && this.slot3 == 3){
			amountWon = amountBet * 50;
			return amountWon;
		}
		//if there is straight single bars, then the amount won back 
		//is the amount bet times 25
		else if (this.slot1 == 2 && this.slot2 == 2  && this.slot3 == 2){
			amountWon = amountBet * 25;
			return amountWon;
		}
		//if there are straight cherries, then the amount won back is 
		//the amount bet times 2 
		else if (this.slot1 == 1 && this.slot2 == 1  && this.slot3 == 1){
			amountWon = amountBet * 2;
			return amountWon;
		}
		//if there are 3 of any bar in the slots, then the amount won back is 
		//amount bet times 5 then divided by 2
		else if ((this.slot1 == 2 && this.slot2 == 2  && this.slot3 == 3) || 
				(this.slot1 == 2 && this.slot2 == 3  && this.slot3 == 3) ||
				(this.slot1 == 3 && this.slot2 == 3  && this.slot3 == 2)|| 
				(this.slot1 == 3 && this.slot2 == 2  && this.slot3 == 2)||
				(this.slot1 == 3 && this.slot2 == 2  && this.slot3 == 3)|| 
				(this.slot1 == 2 && this.slot2 == 3  && this.slot3 == 2)){
			amountWon = amountBet * 5;
			return amountWon;
		}
		//if 2 double bars
		else if ((this.slot1 == 3 && this.slot2 ==3)|| 
				(this.slot2 ==3 && this.slot3 == 3) || 
				(this.slot1 == 3 && this.slot3 ==3)){
			amountWon = amountBet + 10;
			return amountWon;
		}
		//if 2 single bars
		else if ((this.slot1 == 2 && this.slot2 ==2)|| 
				(this.slot2 ==2 && this.slot3 == 2) || 
				(this.slot1 == 2 && this.slot3 ==2)){
			amountWon = amountBet + 5;
			return amountWon;
		}
		//if there are 2 cherries, then the amount won is 
		//2 dollars
		else if ((this.slot1 == 1 && this.slot2 ==1)|| 
				(this.slot2 ==1 && this.slot3 == 1) || 
				(this.slot1 == 1 && this.slot3 ==1)){
			amountWon = amountBet+ 2;
			return amountWon;
		}
		//if there is 1 double bar in the slots, then the amount won is 10 dollars
		else if (this.slot1 ==3 || this.slot2 == 3 || this.slot3 ==3){
			amountWon = 10;
			return amountWon;
		}
		//if there is 1 single bar in the slots, then the amount won is 5 dollars
		else if (this.slot1 ==2 || this.slot2 == 2 || this.slot3 ==2){
			amountWon = 5;
			return amountWon;
		}
		//if there is 1 cherry in the slots, then the amount won back is 2 dollars
		else if (this.slot1 ==1 || this.slot2 == 1 || this.slot3 ==1){
			amountWon = 2;
			return amountWon;
		}
		//if there is an unknown combo, then return -1
		return -1;
	}
	/**
	 * @param args
	 */
	//SELF TESTING MAIN
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Make a slotMachine object and make the amount be $100
		double amountBet = 100;
		SlotMachine testSlots = new SlotMachine();
		//test the getter method and print the values for the slots
		String slots = testSlots.getSlots();
		System.out.println(slots);
		//spins the slots, then get the values then print them 
		testSlots.spinSlots();
		slots = testSlots.getSlots();
		System.out.println(slots);
		//get individual value for each slot 
		System.out.println("The Value of Slot 1 is " + testSlots.getSlot1());
		System.out.println("The Value of Slot 2 is " + testSlots.getSlot2());
		System.out.println("The Value of Slot 2 is " + testSlots.getSlot3());
		//check the combination to see how much money is won
		System.out.println("the Amount Won is: $" + testSlots.checkCombination(amountBet));
	}
}
