import java.util.ArrayList;

//import java.util.Stack;

//Class to create the hand of a player
public class Hand {
	private ArrayList<Card> cards = new ArrayList<Card>();
	//private Card[] cards = new Card[10];
	
	//Constructor
	public Hand(){
	}
	
	//Sets
		public void setCard(Card c){
			cards.add(c);
		}
		
	//Gets
		public int getAmount(){
			return cards.size();
		}
		
		public Card getCard(int p){
			return cards.get(p);
		}
		
		//Returns a card 
		public Card chooseCard(int p){
			return cards.remove(p);
		}
	
	//Others	
		
		//Sort cards
				public void sortCards(){
					System.out.println("Sorting");
					Card[] aux = new Card[getAmount()];
					int i=0;
					for (Card c : cards){
						System.out.println("1Blucle");
						aux[i] = c;
						cards.remove(c);
						//mano.suprimir(c);
					}
					
					for (int w = 1; w < aux.length; w++) {
						System.out.println("2Blucle");
						Card c = aux[w];
						int j = (w - 1);

						while ((j >= 0)	&& (c.getType().compareTo(aux[j].getType()) > 0)) {
							aux[j + 1] = aux[j--];
						}
						aux[j + 1] = c;
					}
					
					for (int j = 0; j < aux.length; j++) {
						System.out.println("3Blucle");
						cards.add(0,aux[j]);
					}
					
					/*
					for(int i=0;i<getAmount();i++){
						for(int j=i+1;j<getAmount();j++){
							if(cards.get(j).getType().compareToIgnoreCase(cards.get(i).getType())<0){
								
							}	
						}
					}
					*/
				}//End of sort
				
		//Show hand
			public void showHand(){
				if(getAmount()==0){
					System.out.println("Empty hand");
				}else{
					System.out.println("Hand:");
					for(int i=0;i<getAmount();i++){
						System.out.println("("+(i+1)+")"+getCard(i).getAspect());
					}
				}
			} //End of showHand
			
		//Show hand same line
			public void showHandToString(){
				String hand = "Hand:";
				if(getAmount()==0){
					System.out.println("Empty hand");
				}else{
					for(int i=0;i<getAmount();i++){
						hand += ("("+(i+1)+")"+getCard(i).getAspect());
					}
					System.out.println(hand);
				}
			}//End of showHandToString
}//End of class
