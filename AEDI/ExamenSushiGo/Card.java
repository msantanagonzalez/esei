//Generic class to create the cards
public class Card {
	private String type;
	private String aspect;
	
	public Card(){
		type=null;
		aspect=null;		
	}
	
	//Constructor of a card
	public Card(String t){
		type=t;
		aspect=null;
	}
	
	//Sets
		public void setAspect(String a){
			aspect=a;
		}
		
		public void setType(String t){
			type=t;
		}
	//Gets
		
		//Get the type of the card
		public String getType(){
			return type;
		}
		
		//Get the aspect of the card
		public String getAspect(){
			return aspect;
		}
}
