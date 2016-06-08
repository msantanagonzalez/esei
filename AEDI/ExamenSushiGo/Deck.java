import java.util.Random; //To use Random class
import java.util.Stack;

//Generic class to create the decks
public class Deck{
	private Stack<Card> cards = new Stack<Card>();
	
	//Constructor
	public Deck(){
		//Creation of Cards
		
			//Nigiri
			for (int i=0;i<5;i++){
				Card NCalamar = new Card("Nigiri de Calamar");
				NCalamar.setAspect("-|NCalamar|3|-");
				setCard(NCalamar); //Add cart into the mainDeck
			}
			
			for (int i=0;i<10;i++){
				Card NSalmon = new Card("Nigiri de Salmon");
				NSalmon.setAspect("-|NSalmon|2|-");
				setCard(NSalmon); //Add cart into the mainDeck
			}
			for (int i=0;i<5;i++){
				Card NTortilla = new Card("Nigiri de Tortilla");
				NTortilla.setAspect("-|NTortilla|1|-");
				setCard(NTortilla); //Add cart into the mainDeck
			}
			
			//Tempura
			for (int i=0;i<14;i++){
				Card Tempura = new Card("Tempura"); 
				Tempura.setAspect("-|Tempura|5|-");
				setCard(Tempura); //Add cart into the mainDeck
			}
			
			//Sashimi
			for (int i=0;i<14;i++){
				Card Sashimi = new Card("Sashimi"); 
				Sashimi.setAspect("-|Sashimi|10|-");
				setCard(Sashimi); //Add cart into the mainDeck			
			}
			
			//Gyoza
			for (int i=0;i<14;i++){
				Card Gyoza = new Card("Gyoza"); 
				Gyoza.setAspect("-|Gyoza|1+|-"); 
				setCard(Gyoza); //Add cart into the mainDeck
			}
			
			//Wasabi
			for (int i=0;i<6;i++){
				Card Wasabi = new Card("Wasabi"); 
				Wasabi.setAspect("-|Wasabi|x3|-"); 
				setCard(Wasabi); //Add cart into the mainDeck
			}
			
			//Maki(1 rollo)
			for (int i=0;i<6;i++){
				Card MakiI = new Card("MakiI"); 
				MakiI.setAspect("-|Maki I|6|-"); 
				setCard(MakiI); //Add cart into the mainDeck
			}
			
			//Maki(2 rollos)
			for (int i=0;i<12;i++){
				Card MakiII = new Card("MakiII"); 
				MakiII.setAspect("-|Maki II|6|-"); 
				setCard(MakiII); //Add cart into the mainDeck
			}
			
			//Maki(3 rollo)
			for (int i=0;i<8;i++){
				Card MakiIII = new Card("MakiIII"); 
				MakiIII.setAspect("-|Maki III|6|-"); 
				setCard(MakiIII); //Add cart into the mainDeck
			}
	} //End of constructor 
	
	//Sets 
			
			//Set a card in the deck
			private void setCard(Card c){
				cards.push(c);
			}
			
	//Gets		
			//Get amount of objects
			public int getCant(){
				return cards.size();
			}
			
			//Get the card
			public Card getCard(int p){
				return cards.elementAt(p);
			}
			
	//Others
			
			//Generates a random number
			public int getRandom(){
				int min=0;
				int max=(getCant()-1);
				Random random= new Random();
				
				int r = random.nextInt((max - min)+1)+min;
				return r;
			} 
			
			//Returns a random card 
			public Card giveCard(){
				int p = getRandom();
				
				return cards.remove(p);
			}
			
			//Returns a card 
			public Card chooseCard(int p){
				return cards.remove(p);
			}
			
						
			
			//Deck info
			public void getInfo(){
				System.out.println("----------|Deck INFO|----------");
				System.out.println("|Amount of cards: "+getCant()+"|");
			}	
			
			//Show deck to the player
			public void showDeck(){
				System.out.println("---|Deck|---");
				for(int i=0;i<getCant();i++){
					System.out.println("("+(i+1)+")"+getCard(i).getAspect());
				}
			}
}
