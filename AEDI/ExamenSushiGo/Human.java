import java.util.Scanner;


public class Human extends Player{
	
	//Player's choose a card 
		public void chooseCard(){
			int selection=0; //card selection
			Scanner input=new Scanner(System.in); //Declare and start the scanner
			
			while(selection<1 || selection>getHand().getAmount()){
				System.out.println("Pick one card(1 to "+getHand().getAmount()+"):");
				selection = input.nextInt();	
			}
			
			Card cardSelected = getHand().chooseCard(selection-1);
			//getHand().sortCards();
			
			//Wasabi + Nigiri system 
			if(cardSelected.getType() == "Nigiri de Calamar" || cardSelected.getType() == "Nigiri de Salmon" || cardSelected.getType() == "Nigiri de Tortilla"){
				if(getTable().haveWasabi()){
					if(getTable().chooseWasabi(cardSelected,1)){ // 1 for human, 0 for pc
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
			//getTable().sortCards();
		}
}//End of class
