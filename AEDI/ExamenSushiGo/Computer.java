import java.util.Random;


public class Computer extends Player {

	//Generates a random number
	public int getRandom(int m){
		int min=0;
		int max=(m-1);
		Random random= new Random();
		
		int r = random.nextInt((max - min)+1)+min;
		return r;
	} 
	
	//Player's choose a card 
	public void chooseCard(){
		
		Card cardSelected = getHand().chooseCard(getRandom(getHand().getAmount()));

		//Wasabi + Nigiri system 
		if(cardSelected.getType() == "Nigiri de Calamar" || cardSelected.getType() == "Nigiri de Salmon" || cardSelected.getType() == "Nigiri de Tortilla"){
			if(getTable().haveWasabi()){
				if(getTable().chooseWasabi(cardSelected,0)){ // 1 for human, 0 for pc
					System.out.println("A Nigiri card has been mixed with a Wasabi card");
				}else{
					getTable().setCard(cardSelected);	
				}
			}else{
				getTable().setCard(cardSelected);	
			}
		}else{
			getTable().setCard(cardSelected);
		}//End of WasabiNigiri system
	}
	
	
}//End of class
