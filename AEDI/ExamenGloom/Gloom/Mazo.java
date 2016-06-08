import java.util.Random;

public class Mazo {
	public static final int CARTAS_MAZO = 81;
	private static Carta [] mazoCartas;
	private static int numCartas;
	
	public Mazo()
	{
		mazoCartas = new Carta[CARTAS_MAZO];
		numCartas = 81;
		//crear 17 cartas de muerte prematura
		int i = 0;
		for(; i<17; i++)
			mazoCartas[i] = new Carta(true,0,0,0);
		//crear 3 cartas de muerte prematura
		mazoCartas[i++] = new Carta(true,-15,0,0);
		mazoCartas[i++] = new Carta(true,0,0,-15);
		mazoCartas[i++] = new Carta(true,-1,-1,-1);
		//Examen 2 (Create new cards)
		mazoCartas[i++] = new Carta(false, 0, 0, 0);
		mazoCartas[i++] = new Carta(false, 0, 0, 0);
		mazoCartas[i++] = new Carta(false, 0, 0, 0);
		//crear las 58 cartas de modificadores
		//las que restan puntos
		for(int j = 1; j<=5; j++)
			mazoCartas[i++] = new Carta(false,-10,0,0);
		for(int j = 1; j<=5; j++)
			mazoCartas[i++] = new Carta(false,-15,0,0);
		for(int j = 1; j<=6; j++)
			mazoCartas[i++] = new Carta(false,-20,0,0);
		for(int j = 1; j<=2; j++)
			mazoCartas[i++] = new Carta(false,0,-10,0);
		for(int j = 1; j<=2; j++)
			mazoCartas[i++] = new Carta(false,0,-15,0);
		for(int j = 1; j<=3; j++)
			mazoCartas[i++] = new Carta(false,0,-20,0);
		mazoCartas[i++] = new Carta(false,0,-25,0);
		mazoCartas[i++] = new Carta(false,0,0,-10);
		mazoCartas[i++] = new Carta(false,0,0,-15);
		mazoCartas[i++] = new Carta(false,0,0,-25);
		mazoCartas[i++] = new Carta(false, -5,-5,0);
		mazoCartas[i++] = new Carta(false,-10,-5,0);
		mazoCartas[i++] = new Carta(false,-10,-10,0);
		mazoCartas[i++] = new Carta(false,-10,-10,0);
		mazoCartas[i++] = new Carta(false,-15,-10,0);
		mazoCartas[i++] = new Carta(false,-15,-15,0);
		mazoCartas[i++] = new Carta(false,-15,-15,0);
		mazoCartas[i++] = new Carta(false,-20,-10,0);
		mazoCartas[i++] = new Carta(false,0,-10,-10);
		mazoCartas[i++] = new Carta(false,0,-15,-20);
		mazoCartas[i++] = new Carta(false,0,-20,-20);
		mazoCartas[i++] = new Carta(false,-10,0,-15);
		mazoCartas[i++] = new Carta(false,-15,0,-10);
		mazoCartas[i++] = new Carta(false,-10,0,-20);
		for(int j = 1; j<=2; j++)
			mazoCartas[i++] = new Carta(false,-15,0,-15);
		mazoCartas[i++] = new Carta(false,-15,-15,-15);
		
		//crear las que suman puntos
		for(int j = 1; j<=2; j++)
			mazoCartas[i++] = new Carta(false,10,0,0);
		mazoCartas[i++] = new Carta(false,15,0,0);
		mazoCartas[i++] = new Carta(false,0,10,0);
		mazoCartas[i++] = new Carta(false,0,15,0);
		mazoCartas[i++] = new Carta(false,10,5,0);
		mazoCartas[i++] = new Carta(false,10,-1,0);
		for(int j = 1; j<=2; j++)
			mazoCartas[i++] = new Carta(false,-1,0,15);
		for(int j = 1; j<=2; j++)
			mazoCartas[i++] = new Carta(false,-1,0,20);
		mazoCartas[i++] = new Carta(false,-1,10,0);
		for(int j = 1; j<=2; j++)
			mazoCartas[i++] = new Carta(false,-1,15,0);
		
		barajar();
	}
	
	private void barajar()
	{
		Random randomNumber = new Random(); //Create a random object
        for( int primera = 0; primera < mazoCartas.length; primera++ ){
            int segunda = randomNumber.nextInt( CARTAS_MAZO ); //Create a random number
            Carta tempo = mazoCartas[ primera ]; //Create a temporal card with the actual card
            /* Change the actual card with the random one */
            mazoCartas[ primera ] = mazoCartas[ segunda ];
            mazoCartas[ segunda ] = tempo;
        }
	}
	
	public static int getNumCartas(){
		return numCartas;
	}
	
	public static Carta darCarta(){
		Carta tempo = mazoCartas[CARTAS_MAZO - numCartas]; //Gets the first card on deck
		numCartas--;
		return tempo;
	}
	
	/* Print the deck info */
    public String toString(){
    	String data = "-----| Mazo: |----- \n";
    	int start = CARTAS_MAZO - numCartas;
    	for(int i=start;i<mazoCartas.length;i++){
    		data += i + ")" + mazoCartas[i].toString() + "\n";
    	}
    	data += " \n";
    	return data;
    }
}
