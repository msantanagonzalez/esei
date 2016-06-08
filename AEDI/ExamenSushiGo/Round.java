import java.util.Stack;


public class Round {
	//private int playersAmount; //Amount of players on the game
	//private Player[] players = new Player[5]; //Player's array
	private Stack<Player> players = new Stack<Player>();
	
	private int [] points = {0,0,0,0,0}; //Points for each player on each round
	
	//Constructor
	public Round(){
	}
	
	//Sets
		public void setPlayer(Player p){
			//Create empty table
			Table emptyTable = new Table();
			p.setTable(emptyTable);
			//Create empty hand
			Hand emptyHand = new Hand();
			p.setHand(emptyHand);
			
			players.push(p);
			//players[playersAmount]=p;
			//playersAmount++;
		} //End of setPlayer
		
		
		public void setPoints(int pos,int poi){
			points[pos]=poi;
		}//End of setPoints
	
	//Gets
		public int getPlayerAmount(){
			return players.size();
			//return playersAmount;
		}
		
		public Player getPlayer(int p){
			return players.elementAt(p);
			//return players[p];
		}
		
		public int getPoints(int p){
			return points[p];
		}
	
	//Others
		
		//Create hands for each player
		public void createHands(Deck mainDeck){
			int amountCards=0;//To know how many cards to each player
			switch(getPlayerAmount()){
				case 2:
					amountCards=6;
				break;
				case 3:
					amountCards=5;
				break;
				case 4:
					amountCards=4;
				break;
				case 5:
					amountCards=3;
				break;
			}
			
			//Create random table for each players
			for(int j=0;j<getPlayerAmount();j++){
				for(int i=1;i<=amountCards;i++){
					getPlayer(j).getHand().setCard(mainDeck.giveCard());
				}
			}
		}//End of createHand
	

		//Rotate hands 
		public void changeHand(){
			Hand first = players.elementAt(0).getHand();
			for(int i=0; i<getPlayerAmount()-1;i++)
			{
				//players[i].setHand(players[i+1].getHand());
				players.elementAt(i).setHand(players.elementAt(i+1).getHand());
			}
			//players[getPlayerAmount()-1].setHand(first);
			players.peek().setHand(first);
		}//End of changeHand
	
		//Show round info
		public void getInfo(){
			for(int i=0;i<getPlayerAmount();i++){
				System.out.println("Player: "+players.elementAt(i).getName());
				System.out.println("Points: "+getPoints(i));
			}
		}//End of roundInfo
		
}//End of class
