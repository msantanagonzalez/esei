import java.util.InputMismatchException;
import java.util.Scanner; //To use Scanner(Read data)

/*
 * Marco Antonio Santana González
 * msgonzalez@esei.uvigo.es
 * ------------------- ShushiGo -------------------
 * |Classes|
 * | Card -> Contains the card type and aspect(how the player will see the card) |
 * | Deck -> (Main/Central deck) Contains "Card" objects(Up to 108 objects) |
 * | Table -> (Player's choice) Contains "Card" objects(Up to 10 objects) |
 * | Hand -> Contains "Card" objects(Rotates, up to 10 objects) |
 * | Player -> Contains the player's name, global points, one "Hand" object and one "Deck" object |
 * | Round -> Contains "Player" objects(Up to 5),and the points of each player(Up to 5) |
 * | Game -> Contains one "Deck" object, and one array of "Round" objects(Up to 3) |
*/


public class main{

	public static void main(String[] args) {
		
		int players=0; //Amount of players per game
		int val=0; //To validate data
		Scanner input=new Scanner(System.in); //Declare and start the scanner
		
			//Ask amount of players
			while(val==0){
				try{
					System.out.println("---------- |SushiGo| ----------");
					System.out.println("How many players?");
					players = input.nextInt(); //Save amount of players	
					if(players > 1 && players < 6){
						val=1;
						//Create "house" player
						Player house = new Computer();
						house.setName("house");
						
						Game sushiGo = new Game(); //Create the game
						
						sushiGo.sitPlayers(players);
						
						//Start the game and sets round one
						sushiGo.getInfo();
						//Round 1
						sushiGo.startRound();
						//Round 2
						sushiGo.newRound();
						sushiGo.startRound();
						//Round 3
						sushiGo.newRound();
						sushiGo.startRound();
						//End of game
						sushiGo.getResults();
						
					}else{
						System.out.println("|ERROR|-Invalid amount of players");
					}
				}catch(InputMismatchException e){
					System.out.println("|ERROR|- You must enter a number");	
					input.nextLine(); //Clear scanner buffer 
				}	
			} //End of while
		
										
	} // End of Main
} // End of Class
