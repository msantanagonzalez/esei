import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Table {
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	//Constructor
	public Table(){
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
	
		public int getMakiPoints(){
			String type;
			int cantMaki=0;
			for(int i=0;i<getAmount();i++){
				type=getCard(i).getType();
				switch(type){
					case "MakiI":
						cantMaki=cantMaki+1;
					break;
					case "MakiII":
						cantMaki=cantMaki+2;
					break;
					case "MakiIII":
						cantMaki=cantMaki+3;
					break;
				}//End of switch
			}//End of for
			return cantMaki;
		}
		
		public int getPoints(){
			int points=0;
			String type;
			int cantTempura=0;
			int cantSashimi=0;
			int cantGyoza=0;
			
			for(int i=0;i<getAmount();i++){
				type=getCard(i).getType();
				//Check type of cards
				switch(type){
					case "Nigiri de Calamar":
						points=points+3;
					break;
					case "WasabiCalamar":
						points=points+9;
					break;
					case "Nigiri de Salmon":
						points=points+2;
					break;
					case "WasabiSalmon":
						points=points+6;
					break;
					case "Nigiri de Tortilla":
						points=points+1;
					break;
					case "WasabiTortilla":
						points=points+3;
					break;
					case "Tempura":
						if(cantTempura==2){
							points=points+5;
							cantTempura=0;
						}else{
							cantTempura=cantTempura+1;	
						}
					break;
					case "Sashimi":
						if(cantSashimi==3){
							points=points+10;
							cantSashimi=0;
						}else{
							cantSashimi=cantSashimi+1;	
						}
					break;
					case "Gyoza":
						cantGyoza=cantGyoza+1;
					break;
				}//End of type
			}//End of for
			
			switch (cantGyoza) {
			case 0:
			break;
			case 1:
				points=points+1;
			break;
			case 2:
				points=points+3;
			break;
			case 3:
				points=points+6;
			break;
			case 4:
				points=points+10;
			break;
			case 5:
				points=points+15;
			break;
			default:
				points=points+15;
			break;
			}//End of cantGyoza
			
			return points;
		}//End of get
		
	//Others	
		
		//Sort cards
		public void sortCards(){
			Card[] aux = new Card[getAmount()];
			int i=0;
			
			for (Card c : cards){
				aux[i] = c;
				cards.remove(c);
			}

			for (int w = 1; w < aux.length; w++) {
				Card c = aux[w];
				int j = (w - 1);

				while ((j >= 0)	&& (c.getType().compareTo(aux[j].getType()) > 0)) {
					aux[j + 1] = aux[j--];
				}
				aux[j + 1] = c;
			}
			
			for (int j = 0; j < aux.length; j++) {
				//cards.set(0,aux[j]);
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

			
		//Check if the player have a Wasabi without a Nigiri
			public boolean haveWasabi(){
				for(int i=0;i<getAmount();i++){
					if(cards.get(i).getType() == "Wasabi"){
						return true;	
					}
				}//End of for
				return false;
			}
			
		//Show the available Wasabi cards
			public boolean chooseWasabi(Card c, int type){
				int option;
				int val=0;
				Scanner input=new Scanner(System.in); //Declare and start the scanner
				
				while(val==0){
					if(type==1){
						System.out.println("Do you wanna put the Nigiri on the Wasabi?[yes=1/no=0]:");	
						option = input.nextInt();	
					}else{
						option = 1;
					}
					
					if(option == 1){
						val=1;
						String name="Wasabi";
						
						Card wasabiNigiri = new Card();
						
						switch (c.getType()){
						case "Nigiri de Calamar":
							name=name + "Calamar";
							wasabiNigiri.setType(name);
							wasabiNigiri.setAspect("-|WasabiCalamar|9|-");
						break;
						case "Nigiri de Salmon":
							name=name + "Salmon";
							wasabiNigiri.setType(name);
							wasabiNigiri.setAspect("-|WasabiSalmon|6|-");
						break;
						case "Nigiri de Tortilla":
							name=name + "Tortilla";
							wasabiNigiri.setType(name);
							wasabiNigiri.setAspect("-|WasabiTortilla|3|-");
						break;
						}//End of switch						
						
						int times=0;
						for(int i=0;i<getAmount();i++){
							if(cards.get(i).getType() == "Wasabi" && times <1){
								cards.remove(i);
								cards.add(wasabiNigiri);
								times++;
							}
						}//End of for
						
						return true;
					}else{
						if(option == 0){
							val=1;
						}
						input.nextLine(); //Clear scanner buffer
					}
				}//End of while
				return false;
			}//End of chooseWasabi
			
		//Show table
			public void showTable(){
				if(getAmount()==0){
					System.out.println("No card selected");
				}else{
					for(int i=0;i<getAmount();i++){
						System.out.println((i+1)+":"+getCard(i).getAspect());
					}
				}
			} //End of showTable
			
		//Show table same line
		public void showTableToString(){
			String table = "";
			if(getAmount()==0){
				System.out.println("No card selected");
			}else{
				for(int i=0;i<getAmount();i++){
					table += ((i+1)+":"+getCard(i).getAspect());
				}
				System.out.println(table);
			}
		}//End of showHandToString	
	}//End of class

