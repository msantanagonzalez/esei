import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

//Class to create and customize the game 
public class Game {
	private int playersAmount; //Amount of players on the game
	
	private Deck mainDeck = new Deck();
	private Stack<Round> rounds = new Stack<Round>();
	//private Round[] rounds = new Round[3];
	
	//Empty constructor 
	public Game(){
		playersAmount=0;
	}
	
	//Sets
	
		//Set the players on the game
		public void setPlayers(int p){
			playersAmount=p;
		}
		
	//Gets
		
		//Get amount of players in the game
		public int getPlayersAmount(){
			return playersAmount;
		}
		
		//Get the deck object
		public Deck getMainDeck(){
			return mainDeck;
		}
		
		//Get a round object
		public Round getRound(int p){
			return rounds.elementAt(p);
			//return rounds[p];
		}
		
		//Get amount of rounds;
		public int getRoundAmount(){
			return rounds.size();
		}
		
	//Others
		
		// Get player's info
		public void sitPlayers(int amountPlayers){
			setPlayers(amountPlayers);
			
			String names; //Name of the players
			int typeOfPlayer; //Type of player
			Scanner input=new Scanner(System.in); //Declare and start the scanner
			
			//Add players to the first round and create empty hands
			Round r1 = new Round();
			
			for(int i=0;i<getPlayersAmount();i++){
				int val=0;
				while(val==0){
					
					System.out.println("---|Player "+(i+1)+"|---");
					System.out.println("Type(1 for human, 0 for pc):");
					typeOfPlayer = input.nextInt();
					
					//Pc
					if(typeOfPlayer == 0){
						System.out.println("---|PC "+(i+1)+"|---");
						System.out.println("|Creating pc "+(i+1)+"|");
						Player p = new Computer();
						p.setName("PC"+(i+1));
						
						r1.setPlayer(p);
						val=1;
					}
					//Human
					if(typeOfPlayer == 1){
						System.out.println("---|Player "+(i+1)+"|---");
						System.out.println("Name:");
						names = input.next();
						
						Player p = new Human();
						p.setName(names);
						
						r1.setPlayer(p);
						val=1;
					}
					input.nextLine(); //Clear scanner buffer
				}//End of while
				
			}//End of for
			rounds.push(r1);
			//rounds[0]=r1;
			//roundsAmount++;
		}
		
		//Start a round
		public void startRound(){
			Round actualRound = rounds.peek();//rounds[roundsAmount-1];
			actualRound.createHands(getMainDeck()); //Create the random hands for each player
			
			//Sort the cards 
			/*
			for(int i=0;i<getPlayersAmount();i++){
				actualRound.getPlayer(i).getHand().sortCards();
			}
			*/
			
			//int selection=0; //card selection
			//Scanner input=new Scanner(System.in); //Declare and start the scanner
			
			System.out.println("----------|Round #:"+getRoundAmount()+"|----------");
			
			int times = actualRound.getPlayer(0).getHand().getAmount(); //Get amount of cards on a deck
			
			for(int z=0;z<times;z++){
				
				for(int i=0;i<getPlayersAmount();i++){ //Amount of players
					System.out.println("---|Player #"+(i+1)+":"+actualRound.getPlayer(i).getName()+"|---");
					
					//Show player's table
					System.out.println("Table:");
					for(int j=0;j<getPlayersAmount();j++){
						//actualRound.getPlayer(j).getTable().sortCards();
						System.out.println("|"+actualRound.getPlayer(j).getName()+"|");
						//actualRound.getPlayer(j).getTable().showTable();
						actualRound.getPlayer(j).getTable().showTableToString();
					}//End of for show player's table
					
					//Show player's hand
					//actualRound.getPlayer(i).getHand().sortCards();
					actualRound.getPlayer(i).getHand().showHand();
					//actualRound.getPlayer(i).getHand().showHandToString();
					
					actualRound.getPlayer(i).chooseCard();
					
				}//End of for amount of players

			//Rotate decks	
			actualRound.changeHand();
			}//End of for
			
			//Get points
			System.out.println("--------------- End of round ---------------");
			
			String makiWinner="";
			int makiMax=0;
			int makiAmount;
			int makiWinnerAmount=0;
			int makiPoints=0;
			
			//Get maki's cards max amount
			for(int j=0;j<getPlayersAmount();j++){
				//Get makiMax  
				makiAmount = actualRound.getPlayer(j).getTable().getMakiPoints();
				if(makiAmount > makiMax){
					makiMax=makiAmount;
				}
			}//End of for
			
			//Get the Maki winner or winners amount
			for(int j=0;j<getPlayersAmount();j++){
				if(actualRound.getPlayer(j).getTable().getMakiPoints() == makiMax){
					makiWinner= makiWinner + "|" + actualRound.getPlayer(j).getName() + "|";
					makiWinnerAmount++;
				}
			}//End of for
			
			makiPoints=6/makiWinnerAmount; //Points to each winner
			
			//System.out.println("------------------");
			//System.out.println("Maki winner:"+makiWinner+makiPoints+" Points");
			//System.out.println("------------------");
			
			//Set points
			for(int j=0;j<getPlayersAmount();j++){
				
				System.out.println("|"+actualRound.getPlayer(j).getName()+"|"); //Show player's name
				actualRound.getPlayer(j).getTable().showTable(); //Show player's table
				
				if(actualRound.getPlayer(j).getTable().getMakiPoints() == makiMax){ //If player it's a maki winner
					
					//System.out.println("Points(Without Maki): "+actualRound.getPlayer(j).getTable().getPoints());
					System.out.println("Points(With Maki): "+(actualRound.getPlayer(j).getTable().getPoints()+makiPoints));
					
					//Set points in the round
					actualRound.setPoints(j, actualRound.getPlayer(j).getTable().getPoints()+makiPoints);
					//Set the global points to player
					actualRound.getPlayer(j).setPoints(actualRound.getPlayer(j).getTable().getPoints()+makiPoints);	
				}else{ //If player isn't a maki winner
					
					System.out.println("Points(Without Maki): "+actualRound.getPlayer(j).getTable().getPoints());
					
					//Set points in the round
					actualRound.setPoints(j, actualRound.getPlayer(j).getTable().getPoints());
					//Set the global points to player
					actualRound.getPlayer(j).setPoints(actualRound.getPlayer(j).getTable().getPoints());
				}
				
			} //End of for
			System.out.println("------------------");
			//Show previous rounds
			for(int i=0;i<getRoundAmount();i++){
				System.out.println("|Round #"+(i+1)+"|");
				rounds.elementAt(i).getInfo();
				//rounds[i].getInfo();	
			}
		}
		
		//Create a new round 
		public void newRound(){
			Round tempo = rounds.peek(); //rounds[roundsAmount-1]; //Get the last round info
			Round newRound = new Round(); //Create the new round
			
			for(int i=0;i<getPlayersAmount();i++){
				
				newRound.setPlayer(tempo.getPlayer(i)); //Copy the same players into the new round 
				
				//Create empty table
				Table emptyTable = new Table();
				newRound.getPlayer(i).setTable(emptyTable);
				
				//Create empty hand
				Hand emptyHand = new Hand();
				newRound.getPlayer(i).setHand(emptyHand);
			}
			rounds.push(newRound);
			//rounds[roundsAmount]=newRound;
			//roundsAmount++;
		}
		
	//Get results
		public void getResults(){
			System.out.println("---------------|ShushiGo! RESULTS|---------------");
			int p=0; //Max points
			
			//Show previous rounds
			for(int i=0;i<getRoundAmount();i++){
				System.out.println("|Round #"+(i+1)+"|");
				rounds.elementAt(i).getInfo();	
			}
			
			//Get max points
			for(int j=0;j<getPlayersAmount();j++){
				if(p<rounds.peek().getPlayer(j).getPoints()){
					p=rounds.peek().getPlayer(j).getPoints();
				}
			}
			
			System.out.println("|---------------|WINNER:|---------------|");
			//Get the winner or the winners
			for(int j=0;j<getPlayersAmount();j++){
				//rounds[getRoundAmount()-1].getPlayer(j).clearPoints();
				//rounds[getRoundAmount()-1].getPlayer(j).setPoints(p);
				if(rounds.peek().getPlayer(j).getPoints() == p){
					System.out.println("|"+rounds.peek().getPlayer(j).getName()+"|");
					System.out.println("|"+rounds.peek().getPlayer(j).getPoints()+" Points|");	
				}
			}
		}
		
		
	//Game info(Test only)
		public void getInfo(){
			System.out.println("----------|SushiGo INFO|----------");
			System.out.println("|Players: "+getPlayersAmount()+"|");
			System.out.println("|MainDeck: "+getMainDeck().getCant()+"|");
		}
} //End of class
