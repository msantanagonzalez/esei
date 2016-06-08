import java.util.Scanner;

public class Juego {
	private static boolean gameOn=true;
	private static Jugador[] jugadores = new Jugador[4];
	private static int numJugadores;
	
	private static void error(String error) throws Exception {
		throw new Exception(error);
	}
	
	public static void setJugadores(Scanner input){
		boolean validate = false;
		while(!validate){
			try {
				System.out.println("Introduce cantidad de jugadores: ");
				numJugadores = input.nextInt();
				if(numJugadores < 2 || numJugadores > 4){
					error("Numero de jugadores no valido");
				}
				input.nextLine();
				for(int i=0;i<numJugadores;i++){
					System.out.println("\n--| Jugador "+(i+1)+" |--");
					System.out.print("Nombre: ");
					String nombre = input.next();
					jugadores[i] = new Jugador(nombre);
					Mano m = new Mano();
					jugadores[i].setMano(m);
				}
				input.nextLine();
				validate = true;
			} catch (Exception e) {
				if(e.getMessage() != null)
					System.out.println("|Error|-"+e.getMessage()+"\n");
				else
					System.out.println("|Error|- Entrada no valida"+"\n");
				input.nextLine();
			}	
		}
	}

	public static void seleccionFamilias(Scanner input){
		for(int i=0;i<numJugadores;i++){
			boolean validate = false;
			System.out.println("\n--| Jugador "+(i+1)+": "+jugadores[i].getNombre()+ " |--");
			System.out.println("Selecciona una familia:");
			/* Listar familias disponibles */
			for(int j=0;j<FamiliasJuego.familias.length;j++){
				if(!FamiliasJuego.seleccionada(j+1))
					System.out.println(j+")"+FamiliasJuego.listarFamilia(j));
			}
			
			int familiaSelec=0;
			while(!validate){
				/* validar seleccion de usuario */
				try {
					System.out.print("familia:");
					familiaSelec = input.nextInt();
						if(familiaSelec < FamiliasJuego.familias.length && !FamiliasJuego.seleccionada(familiaSelec+1))
							validate = true;
						else{
							System.out.println("|Error|- Opcion no valida"+"\n");
						}
						input.nextLine();	
				} catch (Exception e) {
					if(e.getMessage() != null)
						System.out.println("|Error|-"+e.getMessage()+"\n");
					else
						System.out.println("|Error|- Entrada no valida"+"\n");
					input.nextLine();
				}
			}
			/* Guardar seleccion en array de FamiliasJuego */
			FamiliasJuego.setSeleccionada(familiaSelec+1);
			/* Asignar la familia al jugador */
			jugadores[i].setFamilia(FamiliasJuego.getFamilia(familiaSelec));
			/* Robar mano principal */
			jugadores[i].robarCartas();
		}
	}
	
	public static void verFamiliaJugador(Jugador p){
		System.out.println("\nJugador: "+p.getNombre());
		System.out.println("Familia:");
		System.out.println(p.familiaToString());
	}
	
	public static Carta seleccionCarta(Jugador p, Scanner input){
		//Validate pick 
		boolean validate = false;
		int option=0;
		while(!validate){
			System.out.print("Carta: ");
			input.nextLine();
			try {
				option = input.nextInt();
				if(option <0 || option > 4)
					error("Opcion no valida"); //Throws exception
				else{
					validate = true;
				}
			} catch (Exception e) {
				if(e.getMessage() != null)
					System.out.println("|Error|-"+e.getMessage()+"\n");
				else
					System.out.println("|Error|- Entrada no valida"+"\n"); 
			}
		}
		return p.seleccionCarta(option);
	}
	
	
	public static Jugador seleccionJugador(Carta c,Scanner input){
		boolean validate = false;
		int option=0;
		Jugador player = null;
		while(!validate){
			try {
				System.out.println("Carta seleccionada:");
				System.out.println(c.toString());
				System.out.println("Selecciona un jugador para aplicar la carta: ");
				for(int i=0;i<numJugadores;i++){
					Jugador j = jugadores[i];
					System.out.println(i+")"+j.getNombre());
				}
				option = input.nextInt();
				if(option < 0 || option > numJugadores-1)
					error("Opcion no valida"); //Throws exception
				player = jugadores[option];
				validate = true;
			} catch (Exception e) {
				if(e.getMessage() != null)
					System.out.println("|Error|-"+e.getMessage()+"\n");
				else
					System.out.println("|Error|- Entrada no valida"+"\n");
				input.nextLine();
			}
		}
		return player;
	}
	
	public static Personaje seleccionPersonaje(Jugador p,Scanner input){
		boolean validate = false;
		int option=0;
		Jugador player = p;
		while(!validate){
			try {
				verFamiliaJugador(player);
				System.out.println("Selecciona un personaje para aplicar la carta: ");
				option = input.nextInt();
				if(option < 0 || option > 4)
					error("Opcion no valida"); //Throws exception
				validate = true;
			} catch (Exception e) {
				if(e.getMessage() != null)
					System.out.println("|Error|-"+e.getMessage()+"\n");
				else
					System.out.println("|Error|- Entrada no valida"+"\n");
				input.nextLine();
			}
		}
		return player.getPersonaje(option);
	}
	
	public static boolean validarCarta(Carta c,Personaje p,int jugada){
		boolean validate = true;
		//Check if player is dead
		if(!p.estaVivo() && !c.esRevive()){
			System.out.println("|Error|- No puedes jugar sobre un personaje muerto");
			validate = false;
		}
		//Check if dead card 
		if(c.esMuerte() && p.getAutoestima() >= 0){
			System.out.println("|Error|- Para matar a un personaje su autoestima tiene que ser menor a 0");
			validate = false;
		}
		//Check if dead card and second turn
		if(jugada==2 && c.esMuerte()){
			System.out.println("|Error|- No puedes jugar cartas de muerte en el segundo turno");
			validate = false;
		}
		//Examen 2 (Check if revive card and player it's not dead)
		if(p.estaVivo() && c.esRevive()){
			System.out.println("|Error|- No puedes jugar cartas revive sobre un jugador vivo");
			validate = false;
		}
		return validate;
	}
	
	public static void turno(Jugador playing, Scanner input,int jugada){
		boolean validate = false;
		while(!validate){
			System.out.println("\n-| Jugando: "+playing.getNombre()+" |-");
			System.out.println(playing.toString());
			//System.out.println("CARDS OVER:" + playing.modCardUsed());
			System.out.println("Selecciona una opcion: ");
			System.out.println("1) Ver jugadores");
			System.out.println("2) Seleccionar carta");
			System.out.println("3) Pasar");
			System.out.println("\n-| mazoCartas: "+Mazo.getNumCartas()+" |-");
			try {
				/* Check if option it´s valid */
				int option = input.nextInt();
				if(option < 1 || option > 3)
					error("Opcion no valida"); //Throws exception 
				
				switch (option) {
				case 1:
					/* See player´s cards */
					for(int i=0;i<numJugadores;i++){
						Jugador j = jugadores[i];
						if(j!=playing)
							verFamiliaJugador(j);
					}
					break;
				case 2:
					/* Pick a card */
					Carta card = seleccionCarta(playing, input);
					/* Pick a player */
					Jugador player = seleccionJugador(card, input);
					/* Pick a character */
					Personaje character = seleccionPersonaje(player, input);
					if(validarCarta(card,character,jugada)){
						playing.usarCarta(card);
						character.setCarta(card);
						for(int i=0; i<numJugadores; i++){
							if(jugadores[i].familiaMuertos()){
								gameOn = false;
							}		
						}
						validate = true;
					}
					break;
				case 3:
					validate = true;
					break;
				}
			} catch (Exception e) {
				if(e.getMessage() != null)
					System.out.println("|Error|-"+e.getMessage()+"\n");
				else
					System.out.println("|Error|- Entrada no valida"+"\n");
				input.nextLine();
			}
		}
		input.nextLine();
	}
	
/*	
	public static void robarCartas(Jugador p){
		if(Mazo.getNumCartas()<(5-p.getMano().getCantidadCartas())){
			while(Mazo.getNumCartas()!=0){
				p.getMano().setCarta(Mazo.darCarta());
			}
		}else{
			while(p.getMano().getCantidadCartas() < 5)
				p.getMano().setCarta(Mazo.darCarta());
		}
	}
*/	
	public static void ganador(){
		System.out.println("-----| FIN DEL JUEGO |-----");
		int ganador=0;
		int punt=0;
		for(int i=0;i<numJugadores;i++){
			System.out.println("Jugador "+jugadores[i].getNombre()+": P.Pathos = "+jugadores[i].getAutoestima()+"\n");
			punt=jugadores[0].getAutoestima();
			if(punt>jugadores[i].getAutoestima()){
				punt = jugadores[i].getAutoestima();
				ganador=i;
			}
		}
		System.out.println("El Jugador ganador es: "+jugadores[ganador].getNombre()+" con Puntos de Pathos = "+punt+"\n");
		System.out.println("Cartas modificadoras jugadas sobre la familia:" + jugadores[ganador].modCardUsed());
	}
	
	/* Game start here */ 
	public static void main(String[] args) {
			Mazo mazo = new Mazo();
			Scanner input = new Scanner(System.in);
			setJugadores(input);
			seleccionFamilias(input);
			while(gameOn){
				for(int i=0;i<numJugadores;i++){
					if(gameOn){
						System.out.println("\n--- |Primer turno| ---");
						turno(jugadores[i],input,1);	
					}if(gameOn){
						System.out.println("\n--- |Segundo turno| ---");
						turno(jugadores[i],input,2);
						jugadores[i].robarCartas();
					}
				}
			}
			input.close();
			ganador();
	}
}
