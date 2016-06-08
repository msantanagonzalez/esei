// Class to create players and save useful info
abstract class Player {
	
	private String name; //Player's name
	private int points; //Player's point on the game
	
	private Table table = new Table(); //Player's table
	private Hand hand = new Hand(); //Player's hand
	
	//Empty constructor 
	public Player(){
		name=null;
		points=0;
	}
	
	////////////TEST ONLY(TO TRY SCORE DRAW)
	public void clearPoints(){
		points = 0;
	}
	/////////////////////////////////
	
	//Sets
	public void setName(String n){
		name = n;
	}
	
	public void setPoints(int p){
		points = points + p;
	}
	
	
	public void setHand(Hand h){
		hand = h;
	}
	
	public void setTable(Table t){
		table = t;
	}
	
	//Gets
	public String getName(){
		return name;
	}
	
	public int getPoints(){
		return points;
	}
	
	public Hand getHand(){
		return hand;
	}
	
	public Table getTable(){
		return table;
	}
	
//Player's choose a card 
	abstract void chooseCard();
	
} // End of class

